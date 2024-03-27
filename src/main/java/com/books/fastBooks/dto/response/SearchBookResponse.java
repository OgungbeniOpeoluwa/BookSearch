package com.books.fastBooks.dto.response;

import com.books.fastBooks.dto.request.Books;
import com.books.fastBooks.model.ReadingBooks;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchBookResponse {
    private Books books;
}
