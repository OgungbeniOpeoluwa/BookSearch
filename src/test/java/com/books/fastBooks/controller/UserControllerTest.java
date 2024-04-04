package com.books.fastBooks.controller;

import com.books.fastBooks.dto.request.LoginRequest;
import com.books.fastBooks.dto.request.ReadingListRequest;
import com.books.fastBooks.dto.request.RegisterRequest;

import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void testRegisterConsumer() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("opemip1");
        registerRequest.setUsername("test");
        registerRequest.setEmail("ope@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(registerRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void searchForBookTest() throws Exception {
        SearchForBookRequest searchForBookRequest = new SearchForBookRequest();
        searchForBookRequest.setTitle("pride and prejudice");
        searchForBookRequest.setUserId(201L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(searchForBookRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
    @Test
    public void getReadingListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/readingList/201")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void testLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@gmail.com");
        loginRequest.setPassword("mxor123");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(loginRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}
