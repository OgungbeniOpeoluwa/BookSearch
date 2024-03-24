package com.books.fastBooks.service;


import com.books.fastBooks.dto.request.RegisterRequest;
import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.dto.response.RegisterResponse;
import com.books.fastBooks.dto.response.SearchBookResponse;
import com.books.fastBooks.exception.BookNotFound;

import java.util.List;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);

    SearchBookResponse search(SearchForBookRequest bookRequest) throws BookNotFound;

    List<ReadingListResponse> getReadingList(long l) throws BookNotFound;
}
