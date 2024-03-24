package com.books.fastBooks.dto.response;

import com.books.fastBooks.model.Persons;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReadingListResponse {
    private String title;
    private Persons author;
    private List<String> subjects;
    private List<String> bookshelves;

}
