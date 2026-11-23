package com.sysdesign.urlshortener.Service;

import com.sysdesign.urlshortener.model.ShortUrl;

public interface UrlShortenerService {
    ShortUrl shorten(String url);
    String resolve(String code);
}
