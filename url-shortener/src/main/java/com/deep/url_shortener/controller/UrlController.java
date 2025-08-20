package com.deep.url_shortener.controller;

import com.deep.url_shortener.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {
    private final UrlMappingService service;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(
            @RequestParam String url,
            @RequestParam(required = false) String custom,
            @RequestParam(required = false) Integer expiry
    ) {
        String shortCode = service.shortenUrl(url, custom, expiry);
        return ResponseEntity.ok("http://localhost:8080/api/url/" + shortCode);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String originalUrl = service.getOriginalUrl(shortUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalUrl);
        return ResponseEntity.status(302).headers(headers).build();
    }

    @GetMapping("/{shortUrl}/stats")
    public ResponseEntity<Integer> getClickStats(@PathVariable String shortUrl) {
        return ResponseEntity.ok(service.getClickCount(shortUrl));
    }
}
