package com.deep.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title is required")
    private String title;

    private String genre;
    @Positive(message = "Price must be positive")
    private double price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    @NotNull(message = "Author s required")
    private Author author;

}
