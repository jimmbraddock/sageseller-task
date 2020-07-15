package com.sageseller.url.shortener;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UrlShortener {
    private static final int KEYWORD_MAX_SIZE = 20;
    private final URI baseHost;
    private final ConcurrentHashMap<URI, URI> urlsCache;
    private final AtomicInteger counter;
    private final int urlSize;


    public UrlShortener(URI baseHost, int shortUrlSize) {
        this.counter = new AtomicInteger((int) Math.pow(Converter.BASE, shortUrlSize - 1d));
        this.baseHost = Objects.requireNonNull(baseHost);
        this.urlsCache = new ConcurrentHashMap<>();
        this.urlSize = shortUrlSize;
    }

    /**
     * Create short url by keyword
     *
     * @param originalUrl original url
     * @param keyword     keyword
     * @return short url
     * @throws URISyntaxException
     */
    public URI createShortUrl(URI originalUrl, String keyword) throws URISyntaxException {
        if (originalUrl == null || keyword == null) {
            throw new IllegalArgumentException("Url and keyword can't be null");
        }
        if (keyword.length() > KEYWORD_MAX_SIZE) {
            throw new IllegalArgumentException("Length of keyword should be less than " + KEYWORD_MAX_SIZE);
        }

        URI shortUrl = new URI(baseHost.getScheme(), baseHost.getHost(), "/" + keyword, null);
        urlsCache.put(shortUrl, originalUrl);
        return shortUrl;
    }

    /**
     * Create short url with keyword based on counter
     *
     * @param originalUrl original url
     * @return short url
     * @throws URISyntaxException
     */
    public URI createShortUrl(URI originalUrl) throws URISyntaxException {
        int maxCounter = (int) Math.pow(Converter.BASE, urlSize);
        if (this.counter.intValue() == maxCounter) {
            this.counter.compareAndSet(maxCounter, (int) Math.pow(Converter.BASE, urlSize - 1d));
        }
        String shortPath = Converter.intToBase62String(this.counter.intValue());
        URI shortUrl = new URI(baseHost.getScheme(), baseHost.getHost(), "/" + shortPath, null);
        urlsCache.put(shortUrl, originalUrl);
        this.counter.incrementAndGet();
        return shortUrl;
    }

    public URI getOriginalUrl(URI shortUrl) {
        return urlsCache.get(shortUrl);
    }

}
