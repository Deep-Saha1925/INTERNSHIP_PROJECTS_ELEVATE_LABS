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

    public String shortenUrl(String originalUrl) {
        String shortCode = generateShortCode(originalUrl);
        UrlMapping mapping = new UrlMapping(null, shortCode, originalUrl, LocalDateTime.now());
        repository.save(mapping);
        return shortCode;
    }

    public String getOriginalUrl(String shortUrl) {
        return repository.findByShortUrl(shortUrl)
                .map(UrlMapping::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

    private String generateShortCode(String url) {
        String raw = url.hashCode() + "_" + System.nanoTime();
        return Base64.getUrlEncoder()
                .encodeToString(raw.getBytes())
                .substring(0, 8); // shorten to 8 chars
    }
}
