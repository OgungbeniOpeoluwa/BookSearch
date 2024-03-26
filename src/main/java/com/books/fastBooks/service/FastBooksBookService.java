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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public List<ReadingListResponse> readingList(Long id) {
        List<ReadingListResponse> bookResponse = readingBooksRepository.findAll().stream().filter(x->x.getUser().getId() == id)
                .map(x->new ReadingListResponse()).toList();
        return bookResponse;
    }
}
