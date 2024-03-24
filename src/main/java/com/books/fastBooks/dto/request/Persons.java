package com.books.fastBooks.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Persons {
    private Integer birth_year;
    private Integer death_year;
    private String  name;
}
