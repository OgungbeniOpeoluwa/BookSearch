package com.books.fastBooks.service;

import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.dto.response.SearchBookResponse;
import com.books.fastBooks.exception.BookNotFound;
import com.books.fastBooks.dto.request.FoundBooksRequest;
import com.books.fastBooks.model.ReadingBooks;
import com.books.fastBooks.model.User;
import com.books.fastBooks.repository.ReadingBooksRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FastBooksBookService implements BookService{
    private ReadingBooksRepository readingBooksRepository;
    private ModelMapper mapper;


    @Override
    public SearchBookResponse searchForBooks(String title, User user) throws BookNotFound {
        String url = "https://gutendex.com/books?search=" + title;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FoundBooksRequest> response = restTemplate.getForEntity(url, FoundBooksRequest.class);
        if(response.getBody().getResults().isEmpty())throw new BookNotFound("Book Not Found");


        ReadingBooks readingBooks = mapper.map(response.getBody().getResults().getFirst(),ReadingBooks.class);
        readingBooks.setAuthors(response.getBody().getResults().getFirst().getAuthors().getFirst());
        readingBooks.setUser(user);
        readingBooksRepository.save(readingBooks);

        SearchBookResponse bookResponse = new SearchBookResponse();
        bookResponse.setBooks(response.getBody().getResults().getFirst());
        System.out.println(bookResponse);

        return bookResponse;

    }

    @Override
    public List<ReadingListResponse> readingList(User user) {
        System.out.println(user.getId());
        List<ReadingListResponse> bookResponse = readingBooksRepository.findByUser(user).stream()
                .map(ReadingListResponse::new).toList();
        System.out.println(bookResponse);
        return bookResponse;
    }
}
