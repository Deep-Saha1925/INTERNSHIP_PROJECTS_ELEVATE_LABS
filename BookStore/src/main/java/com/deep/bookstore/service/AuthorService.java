package com.deep.bookstore.service;

import com.deep.bookstore.dto.AuthorDTO;
import com.deep.bookstore.entity.Author;
import com.deep.bookstore.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository repo;
    private final ModelMapper mapper;

    public AuthorService(AuthorRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<AuthorDTO> getAll(Pageable pageable) {
        return repo.findAll(pageable)
                .stream()
                .map(author -> mapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    public Author getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public AuthorDTO save(AuthorDTO authorDTO) {
        Author author = mapper.map(authorDTO, Author.class);
        Author saved = repo.save(author);
        return mapper.map(saved, AuthorDTO.class);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}