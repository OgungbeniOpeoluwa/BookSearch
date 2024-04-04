package com.books.fastBooks.service;


import com.books.fastBooks.dto.request.LoginRequest;
import com.books.fastBooks.dto.request.RegisterRequest;
import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.books.fastBooks.dto.response.*;
import com.books.fastBooks.exception.BookNotFound;
import com.books.fastBooks.exception.UserNotFoundException;
import com.books.fastBooks.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);

    ApiResponse <SearchBookResponse> search(SearchForBookRequest bookRequest) throws BookNotFound;

    ApiResponse<List<ReadingListResponse>> getReadingList(long l) throws BookNotFound, UserNotFoundException;
    Optional<User>  findUserBy(long id);

    LoginResponse login(LoginRequest loginRequest) throws UserNotFoundException;
}
