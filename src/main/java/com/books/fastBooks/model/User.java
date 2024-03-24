package com.books.fastBooks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String email;
    private String password;
    private String username;
}
