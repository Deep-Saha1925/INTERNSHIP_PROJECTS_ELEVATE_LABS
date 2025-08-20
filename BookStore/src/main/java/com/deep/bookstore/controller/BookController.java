package com.deep.bookstore.controller;

import com.deep.bookstore.dto.BookDTO;
import com.deep.bookstore.entity.Book;
import com.deep.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Operations related to Books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Returns a paginated list of books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "Books not found")
    })
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
    @Operation(summary = "Get book by ID", description = "Retrieve details of a specific book")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Creates and returns the added book")
    public BookDTO createBook(@Valid @RequestBody BookDTO book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book", description = "Update details of an existing book")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO Book) {
        Book.setId(id);
        return bookService.save(Book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book", description = "Remove a book by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully")
    })
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
