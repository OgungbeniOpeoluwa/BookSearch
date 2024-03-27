package com.books.fastBooks.model;

import com.books.fastBooks.dto.response.ReadingListResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Persons authors;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> subjects;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> bookshelves;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;

}
