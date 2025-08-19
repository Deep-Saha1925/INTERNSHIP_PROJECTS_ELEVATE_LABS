package com.deep.bookstore.repository;

import com.deep.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    // title contains (case-insensitive)
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // filter by genre
    Page<Book> findByGenreIgnoreCase(String genre, Pageable pageable);

    // filter by author
    Page<Book> findByAuthorId(Long authorId, Pageable pageable);

    // combine title + genre
    Page<Book> findByTitleContainingIgnoreCaseAndGenreIgnoreCase(String title, String genre, Pageable pageable);

}
