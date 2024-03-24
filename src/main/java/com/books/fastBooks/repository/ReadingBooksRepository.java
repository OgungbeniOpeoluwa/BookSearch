package com.books.fastBooks.repository;

import com.books.fastBooks.model.ReadingBooks;
import com.books.fastBooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingBooksRepository  extends JpaRepository<ReadingBooks,Long> {
    User findByUserId(Long id);
}
