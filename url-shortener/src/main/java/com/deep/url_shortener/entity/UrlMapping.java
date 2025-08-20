package com.deep.url_shortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private String originalUrl;

    private LocalDateTime expiryDate;

    private int clickCount = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    public UrlMapping(Long id, String shortUrl, String originalUrl, LocalDateTime expiryDate, LocalDateTime createdAt) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
    }

    public void incrementClickCount(){
        this.clickCount++;
    }
}