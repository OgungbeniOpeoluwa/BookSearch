package com.books.fastBooks.service;

import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.dto.response.SearchBookResponse;
import com.books.fastBooks.exception.BookNotFound;
import com.books.fastBooks.model.User;

import java.util.List;

public interface BookService {


    SearchBookResponse searchForBooks(String title, User user) throws BookNotFound;

    List<ReadingListResponse> readingList(User id);

}
