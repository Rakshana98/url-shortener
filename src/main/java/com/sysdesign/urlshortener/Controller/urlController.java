package com.sysdesign.urlshortener.Controller;

import com.sysdesign.urlshortener.DTO.ShortenRequest;
import com.sysdesign.urlshortener.DTO.ShortenResponse;
import com.sysdesign.urlshortener.Service.UrlShortenerService;
import com.sysdesign.urlshortener.model.ShortUrl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("/api/v1")
public class urlController {

    private final UrlShortenerService urlShortenerService;

    public urlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/urls")
    public ResponseEntity<ShortenResponse> createShortUrl(@RequestBody ShortenRequest request) {
        ShortUrl url = urlShortenerService.shorten(request.url());

        String fullShortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{shortCode}")
                .buildAndExpand(url.shortCode())
                .toUriString();

        ShortenResponse response = new ShortenResponse(url.shortCode(), fullShortUrl , url.originalUrl(), url.createdAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = urlShortenerService.resolve(code);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        return new ResponseEntity(headers,HttpStatus.FOUND);
    }
}
