package com.deep.bookstore.controller;

import com.deep.bookstore.dto.AuthorDTO;
import com.deep.bookstore.entity.Author;
import com.deep.bookstore.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "Operations related to authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieve a list of authors")
    public List<AuthorDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID", description = "Retrieve details of a specific author")
    public Author getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable Long id, @RequestBody AuthorDTO author) {
        author.setId(id);
        return service.save(author);
    }

    @PostMapping
    @Operation(summary = "Add new author", description = "Create and return a new author")
    public AuthorDTO createAuthor(@Valid @RequestBody AuthorDTO author) {
        return service.save(author);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete author", description = "Deletes the author")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}