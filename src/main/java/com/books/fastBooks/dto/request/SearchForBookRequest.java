package com.books.fastBooks.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class SearchForBookRequest {
    private Long userId;
    private String title;

}
