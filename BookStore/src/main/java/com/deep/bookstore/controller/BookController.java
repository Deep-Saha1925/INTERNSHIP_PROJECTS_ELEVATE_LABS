package com.deep.bookstore.controller;

import com.deep.bookstore.dto.BookDTO;
import com.deep.bookstore.entity.Book;
import com.deep.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAll(Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<Book> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Long authorId,
            Pageable pageable) {
        return bookService.search(title, genre, authorId, pageable);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookDTO createBook(@Valid @RequestBody BookDTO book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO Book) {
        Book.setId(id);
        return bookService.save(Book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
