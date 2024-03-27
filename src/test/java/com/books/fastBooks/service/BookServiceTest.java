package com.books.fastBooks.service;

import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.dto.response.SearchBookResponse;
import com.books.fastBooks.exception.BookNotFound;
import com.books.fastBooks.model.User;
import com.books.fastBooks.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserRepository userRepository;

        @Test
        public void searchForBookTest() throws BookNotFound {
            User user = new User();
            user.setEmail("ope@gmail.com");
            user.setPassword("opemip@1");
            user.setUsername("ope");
            SearchBookResponse searchBookResponse = bookService.searchForBooks("Romeo and Juliet",user);

            assertThat(searchBookResponse).isNotNull();
        }
        @Test

       public void throwExceptionIfBookDoesntExistException() throws BookNotFound {
            User user = new User();
            user.setEmail("ope@gmail.com");
            user.setPassword("opemip@1");
            user.setUsername("ope");

            assertThrows(BookNotFound.class,()->bookService.searchForBooks("Half of the yellow sun", user));
        }

        @Test
        @Sql(scripts = {"/scripts/insert.sql"})
    public void findBookInReadingListTest(){
            Optional<User> user = userRepository.findById(201L);
            List<ReadingListResponse> responseList = bookService.readingList(user.get());
            assertThat(responseList).isNotNull();
            assertThat(responseList.get(0).getTitle()).isEqualTo("My love life");
            assertThat(responseList).size().isEqualTo(1);

        }
    }
