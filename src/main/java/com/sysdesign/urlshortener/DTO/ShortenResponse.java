package com.sysdesign.urlshortener.DTO;

import java.time.Instant;

public record ShortenResponse(String shortCode, String shortUrl, String origUrl, Instant createdAt) {

}
