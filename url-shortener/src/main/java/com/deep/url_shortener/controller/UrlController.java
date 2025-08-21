package com.deep.url_shortener.controller;

import com.deep.url_shortener.service.UrlMappingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
@Tag(name = "URL Shortener API", description = "APIs for shortening URLs, redirection, and stats")
public class UrlController {
    private final UrlMappingService service;

    @Operation(summary = "Shorten a URL", description = "Provide an original URL with optional custom code and expiry time (minutes).")
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(
            @Parameter(description = "Original URL to shorten", required = true)
            @RequestParam String url,

            @Parameter(description = "Custom short code (optional)")
            @RequestParam(required = false) String custom,

            @Parameter(description = "Expiry time in minutes (optional)")
            @RequestParam(required = false) Integer expiry
    ) {
        String shortCode = service.shortenUrl(url, custom, expiry);
        return ResponseEntity.ok("http://localhost:8080/api/url/" + shortCode);
    }

    @Operation(summary = "Redirect to original URL", description = "Takes a short code and redirects to the original URL.")
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(
            @Parameter(description = "Short code generated earlier", required = true)
            @PathVariable String shortUrl
    ) {
        String originalUrl = service.getOriginalUrl(shortUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalUrl);
        return ResponseEntity.status(302).headers(headers).build();
    }

    @Operation(summary = "Get click statistics", description = "Returns the number of times the short URL was accessed.")
    @GetMapping("/{shortUrl}/stats")
    public ResponseEntity<Integer> getClickStats(
            @Parameter(description = "Short code generated earlier", required = true)
            @PathVariable String shortUrl
    ) {
        return ResponseEntity.ok(service.getClickCount(shortUrl));
    }
}
