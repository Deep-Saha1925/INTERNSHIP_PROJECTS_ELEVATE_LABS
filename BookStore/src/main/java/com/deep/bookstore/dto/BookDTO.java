package com.deep.bookstore.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private double price;
    private Long authorId;
}
