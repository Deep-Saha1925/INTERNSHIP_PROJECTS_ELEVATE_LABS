package com.deep.bookstore.service;

import com.deep.bookstore.dto.BookDTO;
import com.deep.bookstore.entity.Author;
import com.deep.bookstore.entity.Book;
import com.deep.bookstore.repository.AuthorRepository;
import com.deep.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDTO> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(book -> {
                    BookDTO dto = modelMapper.map(book, BookDTO.class);
                    dto.setAuthorId(book.getAuthor().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public BookDTO save(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);

        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);

        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookDTO.class);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> search(String title, String genre, Long authorId, Pageable pageable) {
        if (title != null && genre != null) {
            return bookRepository.findByTitleContainingIgnoreCaseAndGenreIgnoreCase(title, genre, pageable);
        } else if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else if (genre != null) {
            return bookRepository.findByGenreIgnoreCase(genre, pageable);
        } else if (authorId != null) {
            return bookRepository.findByAuthorId(authorId, pageable);
        } else {
            return bookRepository.findAll(pageable);
        }
    }

}
