package com.books.fastBooks.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    @ElementCollection
    private List<String> subjects;
    @ElementCollection
    private List<String> bookshelves;
    @ElementCollection
    private List<String> languages;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;
}
