package com.RL.repository;

import com.RL.domain.Book;
import com.RL.dto.BookDTO;
import com.RL.dto.BookInfoDTO;
import com.RL.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

   Page<Book> findBookByLoanableIsFalse(Pageable pageable);

   @Query("select new com.RL.dto.BookDTO(book) from Book book")
   Page<BookDTO> findAllBookPage(Pageable pageable);

   @Query("select new com.RL.dto.BookInfoDTO(book) from Book book")
   Page<BookInfoDTO> findBookAll(Pageable pageable);


}