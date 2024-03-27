package com.books.fastBooks.repository;

import com.books.fastBooks.model.ReadingBooks;
import com.books.fastBooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReadingBooksRepository  extends JpaRepository<ReadingBooks,Long> {

    List <ReadingBooks> findByUser(User user);
}
