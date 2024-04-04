package com.books.fastBooks.service;

import com.books.fastBooks.dto.request.LoginRequest;
import com.books.fastBooks.dto.request.RegisterRequest;
import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.books.fastBooks.dto.response.*;
import com.books.fastBooks.exception.BookNotFound;
import com.books.fastBooks.exception.LoginException;
import com.books.fastBooks.exception.UserNotFoundException;
import com.books.fastBooks.model.User;
import com.books.fastBooks.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FastBookUserService implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookService bookService;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = modelMapper.map(registerRequest,User.class);
        userRepository.save(user);
        RegisterResponse response = new RegisterResponse();
        System.out.println(user.getId());
        response.setUserId(user.getId());
        return response;
    }

    @Override
    public ApiResponse <SearchBookResponse> search(SearchForBookRequest bookRequest) throws BookNotFound {
        Optional<User> foundUser = userRepository.findById(bookRequest.getUserId());
        SearchBookResponse searchBookResponse = bookService.searchForBooks(bookRequest.getTitle(),foundUser.orElseThrow());
       return new ApiResponse<>(searchBookResponse,true);
    }

    @Override
    public ApiResponse<List<ReadingListResponse> >getReadingList(long userId) throws BookNotFound, UserNotFoundException {
        User user = findUserBy(userId)
                .orElseThrow(()->new UserNotFoundException("User with "+ userId +"doesn't exist"));
        List<ReadingListResponse> readingListResponses = bookService.readingList(user);
        System.out.println(readingListResponses.get(0));
        if(readingListResponses.isEmpty())throw new BookNotFound("No available book  in your reading list");
        return new ApiResponse<>(readingListResponses,true);
    }



    @Override
    public Optional<User> findUserBy(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws UserNotFoundException {
        LoginResponse loginResponse = new LoginResponse();
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
       if( user.orElseThrow(()->new UserNotFoundException("User doesn't exist"))
               .getPassword().equals(loginRequest.getPassword())){
           loginResponse.setMessage("You have Successfully login");
           return loginResponse;
       }
        throw new LoginException("Invalid credential");
    }


}
