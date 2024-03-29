package com.books.fastBooks.service;

import com.books.fastBooks.dto.request.RegisterRequest;
import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.books.fastBooks.dto.response.ApiResponse;
import com.books.fastBooks.dto.response.ReadingListResponse;
import com.books.fastBooks.dto.response.RegisterResponse;
import com.books.fastBooks.dto.response.SearchBookResponse;
import com.books.fastBooks.exception.BookNotFound;
import com.books.fastBooks.exception.UserNotFoundException;
import com.books.fastBooks.model.User;
import com.books.fastBooks.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testThatUserCanRegister(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@gmail.com");
        registerRequest.setUsername("test");
        registerRequest.setPassword("mzor@1");

        RegisterResponse registerResponse = userService.register(registerRequest);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getUserId()).isNotNull();
    }
    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void userSearchForBookTest() throws BookNotFound {
        SearchForBookRequest bookRequest = new SearchForBookRequest();
        bookRequest.setUserId(201L);
        bookRequest.setTitle("Romeo and Juliet");

      ApiResponse<SearchBookResponse> searchForBookResponse = userService.search(bookRequest);

      assertThat(searchForBookResponse.getMessage()).isNotNull();
      assertThat(searchForBookResponse.getMessage().getBooks().getTitle().equalsIgnoreCase(bookRequest.getTitle()));

    }
    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void getReadingListTest() throws Exception {
       ApiResponse< List<ReadingListResponse>> response = userService.getReadingList(201L);
        assertThat(response).isNotNull();
        assertThat(response.getMessage().size()).isEqualTo(1);

    }

    @Test
    public void testThatABookThatAlreadyExistInReadingListCantBeAddedAgain() throws BookNotFound, UserNotFoundException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setPassword("opemip@1");
        registerRequest.setUsername("ope");
         Long id =  userService.register(registerRequest).getUserId();

         SearchForBookRequest bookRequest = new SearchForBookRequest();
          bookRequest.setUserId(id);
          bookRequest.setTitle("ali");
          userService.search(bookRequest);

          userService.search(bookRequest);

          List<ReadingListResponse> responseList = userService.getReadingList(id).getMessage();
        Assertions.assertThat(responseList).size().isEqualTo(1);
    }

}