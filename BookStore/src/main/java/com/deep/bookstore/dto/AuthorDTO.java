package com.deep.bookstore.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO {

    private Long id;
    private String name;
    private List<BookDTO> books; // Nested DTO

}