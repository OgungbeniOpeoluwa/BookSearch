package com.books.fastBooks.dto.response;

import com.books.fastBooks.model.Persons;
import com.books.fastBooks.model.ReadingBooks;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadingListResponse {
    private String title;
    private List<Persons> authors;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String>  Languages;
    private String imageLink;
    private String bookLink;
    public ReadingListResponse(ReadingBooks response,List<Persons> authors){
        this.title = response.getTitle();
        this.subjects = response.getSubjects();
        this.Languages = response.getLanguages();
        this.bookshelves = response.getBookshelves();
        this.authors = authors;
        this.imageLink = response.getImageLink();
        this.bookLink = response.getBookLink();
    }

}
