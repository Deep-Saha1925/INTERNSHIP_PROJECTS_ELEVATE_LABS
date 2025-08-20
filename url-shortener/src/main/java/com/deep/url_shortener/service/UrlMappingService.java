package com.deep.url_shortener.service;

import com.deep.url_shortener.entity.UrlMapping;
import com.deep.url_shortener.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UrlMappingService {
    private final UrlMappingRepository repository;

    public UrlMappingService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    public String shortenUrl(String originalUrl, String customCode, Integer expiryMinutes) {
        String shortCode;
        if (customCode != null && !customCode.isEmpty()) {
            // user-provided
            if (repository.findByShortUrl(customCode).isPresent()) {
                throw new RuntimeException("Custom code already exists. Try another one.");
            }
            shortCode = customCode;
        } else {
            // auto-generate
            shortCode = generateShortCode(originalUrl);
        }

        LocalDateTime expiryDate = expiryMinutes != null
                ? LocalDateTime.now().plusMinutes(expiryMinutes)
                : null;

        UrlMapping mapping = new UrlMapping(null, shortCode, originalUrl, LocalDateTime.now(), expiryDate);
        repository.save(mapping);
        return shortCode;
    }

    public String getOriginalUrl(String shortUrl) {
        UrlMapping mapping = repository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        if (mapping.getExpiryDate() != null && LocalDateTime.now().isAfter(mapping.getExpiryDate())) {
            throw new RuntimeException("This short link has expired.");
        }

        return mapping.getOriginalUrl();
    }

    private String generateShortCode(String url) {
        String raw = url.hashCode() + "_" + System.nanoTime();
        return Base64.getUrlEncoder()
                .encodeToString(raw.getBytes())
                .substring(0, 8); // shorten to 8 chars
    }
}
