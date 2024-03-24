package com.books.fastBooks.repository;

import com.books.fastBooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
