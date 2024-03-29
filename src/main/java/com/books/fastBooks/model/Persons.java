package com.books.fastBooks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@ToString
@Entity
public class Persons {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonIgnore
    private Long id;
    private Integer birth_year;
    private Integer death_year;
    private String  name;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private ReadingBooks readingBooks;

}
