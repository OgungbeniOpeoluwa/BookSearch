package com.books.fastBooks.model;

import com.books.fastBooks.dto.response.ReadingListResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@ToString
public class ReadingBooks {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> subjects;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> bookshelves;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;
    private String imageLink;
    private String bookLink;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;

}
