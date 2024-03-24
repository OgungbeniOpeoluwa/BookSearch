package com.books.fastBooks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@ToString
@Entity
public class Persons {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer birth_year;
    private Integer death_year;
     private String  name;
}
