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

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class FastBooksBookService implements BookService{
    private ReadingBooksRepository readingBooksRepository;
    private ModelMapper mapper;
    private PersonService personService;
    private RestTemplate restTemplate;
    private final String imageKey = "image/jpeg";
    private final String eBookKey = "text/html";


    @Override
    public SearchBookResponse searchForBooks(String title, User user) throws BookNotFound {
        String url = "https://gutendex.com/books?search=" + title;
        ResponseEntity<FoundBooksRequest> response = restTemplate.getForEntity(url, FoundBooksRequest.class);
        if(response.getBody().getResults().isEmpty())throw new BookNotFound("Book Not Found");


        SearchBookResponse bookResponse = new SearchBookResponse();
        bookResponse.setBooks(response.getBody().getResults().getFirst());

        addBookToReadingList(user,bookResponse);

        return bookResponse;

    }

    private void addBookToReadingList(User user,SearchBookResponse searchBookResponse ) {
        if(!checkIfBookExitInReadingList(user,searchBookResponse.getBooks().getTitle())) {
            ReadingBooks readingBooks = mapper.map(searchBookResponse.getBooks(), ReadingBooks.class);
            personService.save(searchBookResponse.getBooks().getAuthors(), readingBooks);
            readingBooks.setImageLink(searchBookResponse.getBooks().getFormats().get(imageKey));
            readingBooks.setBookLink(searchBookResponse.getBooks().getFormats().get(eBookKey));
            readingBooks.setUser(user);
            readingBooksRepository.save(readingBooks);
        }
    }


    @Override
    public List<ReadingListResponse> readingList(User user) {
        List<ReadingListResponse> bookResponse = readingBooksRepository.findByUser(user).stream()
                .map(x->new ReadingListResponse(x,personService.getAuthor(x))).toList();
        System.out.println(bookResponse);
        return bookResponse;
    }

    private boolean checkIfBookExitInReadingList(User user,String title){
        List<ReadingListResponse>  userBooks = readingList(user);
        Optional<ReadingListResponse> readingBooks = userBooks.stream()
                .filter(x->x.getTitle().equals(title))
                .findAny();
        if(readingBooks.isPresent())return true;
        return false;
    }

}
