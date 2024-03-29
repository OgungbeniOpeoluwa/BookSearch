package com.books.fastBooks.service;


import com.books.fastBooks.dto.request.RegisterRequest;
import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.books.fastBooks.dto.response.ApiResponse;
import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.dto.response.RegisterResponse;
import com.books.fastBooks.dto.response.SearchBookResponse;
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
}
