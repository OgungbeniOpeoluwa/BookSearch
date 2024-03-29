package com.books.fastBooks.service;

import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.model.Persons;
import com.books.fastBooks.model.ReadingBooks;

import java.util.List;

public interface PersonService {
    void save(List<Persons> authors, ReadingBooks readingBooks);

    List<Persons> getAuthor(ReadingBooks readingBooks);
}
