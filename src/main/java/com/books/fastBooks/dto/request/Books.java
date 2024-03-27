package com.books.fastBooks.dto.request;

import com.books.fastBooks.model.Persons;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString
public class Books {
     private String title;
     private List<String> subject;
     private List<Persons> authors;
     private List<String> bookshelves;
     private List<String> languages;

}
