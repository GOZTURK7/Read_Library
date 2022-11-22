package com.RL.dto.response;

import com.RL.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String name;
    private String isbn;
    private int pageCount;

    private String publisherName;

    private String authorName;

    private int publishDate;

    private String categoryName;

    private String shelfCode;

    private boolean featured;


    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.isbn = book.getIsbn();
    }
}