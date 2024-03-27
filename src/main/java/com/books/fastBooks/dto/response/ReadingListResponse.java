package com.books.fastBooks.dto.response;

import com.books.fastBooks.model.Persons;
import com.books.fastBooks.model.ReadingBooks;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadingListResponse {
    private String title;
    private Persons authors;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String>  Languages;
    public ReadingListResponse(ReadingBooks response){
        this.title = response.getTitle();
        this.authors = response.getAuthors();
        this.subjects = response.getSubjects();
        this.Languages = response.getLanguages();
    }

}
