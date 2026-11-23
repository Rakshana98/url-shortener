package com.sysdesign.urlshortener.model;

import java.time.Instant;

public record ShortUrl(String shortCode, String originalUrl, Instant createdAt) {
}
