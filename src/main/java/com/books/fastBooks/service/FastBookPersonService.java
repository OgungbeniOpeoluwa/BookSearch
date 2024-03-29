package com.books.fastBooks.service;

import com.books.fastBooks.model.Persons;
import com.books.fastBooks.model.ReadingBooks;
import com.books.fastBooks.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FastBookPersonService implements PersonService{

    private PersonRepository personRepository;
    @Override
    public void save(List<Persons> authors, ReadingBooks readingBooks) {
      List<Persons> persons = authors.stream().map(x->{
           x.setReadingBooks(readingBooks);
           return x;
       }).toList();
       personRepository.saveAll(persons);
    }

    @Override
    public List<Persons> getAuthor(ReadingBooks readingBooks) {
        List<Persons> authors = personRepository.findByReadingBooks(readingBooks);
        return authors;
    }
}
