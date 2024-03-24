package com.books.fastBooks.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString
public class FoundBooksRequest {
    private int count;
    private String next;
    private String previous;
    private List<Books> results;
}
