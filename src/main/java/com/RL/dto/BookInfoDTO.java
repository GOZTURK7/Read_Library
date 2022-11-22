package com.RL.dto;

import com.RL.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoDTO {

    private Long id;
    private String name;
    private String isbn;
    private int pageCount;
    private String authorName;
    private String publisherName;
    private int publishDate;
    private String categoryName;
    private File image;
    private String shelfCode;
    private boolean featured;

    public BookInfoDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.isbn = book.getIsbn();
        this.pageCount = book.getPageCount();
        this.publishDate = book.getPublishDate();
        this.shelfCode = book.getShelfCode();
        this.featured = book.isFeatured();
        this.authorName = book.getAuthorId().getName();
        this.publisherName = book.getPublisherId().getName();
        this.categoryName = book.getCategoryId().getName();
    }

}