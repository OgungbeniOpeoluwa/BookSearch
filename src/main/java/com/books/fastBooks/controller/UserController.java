package com.books.fastBooks.controller;

import com.books.fastBooks.dto.request.LoginRequest;
import com.books.fastBooks.dto.request.RegisterRequest;
import com.books.fastBooks.dto.request.SearchForBookRequest;
import com.books.fastBooks.dto.response.ApiResponse;
import com.books.fastBooks.exception.UserNotFoundException;
import com.books.fastBooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping("/register")
    private ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        System.out.println(registerRequest.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
    }
    @PostMapping("/search")
    private ResponseEntity<?> search(@RequestBody SearchForBookRequest searchForBookRequest){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.search(searchForBookRequest));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(exception.getMessage(),false));
        }

    }
    @GetMapping("/readingList/{id}")
    private ResponseEntity<?> getReadingList(@PathVariable(name = "id") Long id){

        try{
            return  ResponseEntity.status(HttpStatus.OK).body(userService.getReadingList(id));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(exception.getMessage(),false));
        }
    }
    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(userService.login(loginRequest),true));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(e.getMessage(),false));
        }
    }
}
