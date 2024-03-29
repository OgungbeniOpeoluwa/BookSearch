package com.books.fastBooks.exception;

import com.books.fastBooks.model.User;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String messge) {
        super(messge);
    }
}
