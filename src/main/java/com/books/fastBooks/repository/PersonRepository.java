package com.books.fastBooks.repository;

import com.books.fastBooks.model.Persons;
import com.books.fastBooks.model.ReadingBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Persons,Long> {
    List<Persons> findByReadingBooks(ReadingBooks readingBooks);
}
