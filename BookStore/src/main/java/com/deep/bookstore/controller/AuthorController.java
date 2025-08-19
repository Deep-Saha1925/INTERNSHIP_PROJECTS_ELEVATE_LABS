package com.deep.bookstore.controller;

import com.deep.bookstore.dto.AuthorDTO;
import com.deep.bookstore.entity.Author;
import com.deep.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuthorDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable Long id, @RequestBody AuthorDTO author) {
        author.setId(id);
        return service.save(author);
    }

    @PostMapping
    public AuthorDTO createAuthor(@Valid @RequestBody AuthorDTO author) {
        return service.save(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}