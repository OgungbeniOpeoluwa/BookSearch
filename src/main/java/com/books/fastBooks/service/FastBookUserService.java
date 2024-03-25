package com.books.fastBooks.service;

import com.books.fastBooks.dto.request.RegisterRequest;
import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.books.fastBooks.dto.response.ApiResponse;
import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.dto.response.RegisterResponse;
import com.books.fastBooks.dto.response.SearchBookResponse;
import com.books.fastBooks.exception.BookNotFound;
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
        response.setId(user.getId());
        return response;
    }

    @Override
    public ApiResponse <SearchBookResponse> search(SearchForBookRequest bookRequest) throws BookNotFound {
        Optional<User> foundUser = userRepository.findById(bookRequest.getUserId());
        SearchBookResponse searchBookResponse = bookService.searchForBooks(bookRequest.getTitle(),foundUser.orElseThrow());
       return new ApiResponse<>(searchBookResponse);
    }

    @Override
    public ApiResponse<List<ReadingListResponse> >getReadingList(long userId) throws BookNotFound {
        List<ReadingListResponse> readingListResponses = bookService.readingList(userId);
        System.out.println(readingListResponses);
        if(readingListResponses.isEmpty())throw new BookNotFound("No available book  in your reading list");
        return new ApiResponse<>(readingListResponses);
    }
}
