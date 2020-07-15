package com.sageseller.url.shortener;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.net.URISyntaxException;


public class UrlShortenerTest {

    @Test
    public void generateShortUrlByKeywordTest() throws URISyntaxException {
        UrlShortener urlShortener = new UrlShortener(URI.create("https://short.com"), 5);
        URI originalUrl = URI.create("https://twitter.com/michigan-news");
        URI shortUrl = urlShortener.createShortUrl(originalUrl, "michi");
        assertEquals("https://short.com/michi", shortUrl.toString());
        assertEquals(originalUrl, urlShortener.getOriginalUrl(shortUrl));
    }

    @Test
    public void tooLongKeywordExceptionTest() {
        UrlShortener urlShortener = new UrlShortener(URI.create("https://short.com"), 5);
        URI originalUrl = URI.create("https://twitter.com/michigan-news");
        assertThrows(IllegalArgumentException.class,
                () -> urlShortener.createShortUrl(originalUrl, "a".repeat(21))
        );
    }

    @Test
    public void keywordIsNullExceptionTest() {
        UrlShortener urlShortener = new UrlShortener(URI.create("https://short.com"), 5);
        URI originalUrl = URI.create("https://twitter.com/michigan-news");
        assertThrows(IllegalArgumentException.class,
                () -> urlShortener.createShortUrl(originalUrl, null)
        );
    }

    @Test
    public void firstUrlTest() throws URISyntaxException {
        UrlShortener urlShortener = new UrlShortener(URI.create("https://short.com"), 5);
        URI originalUrl = URI.create("https://twitter.com/michigan-news");
        URI shortUrl = urlShortener.createShortUrl(originalUrl);
        assertEquals("https://short.com/baaaa", shortUrl.toString());
        assertEquals(originalUrl, urlShortener.getOriginalUrl(shortUrl));
    }

    @Test
    public void overwhelmingCounterTest() throws URISyntaxException {
        UrlShortener urlShortener = new UrlShortener(URI.create("https://short.com"), 5);
        URI originalUrl = URI.create("https://twitter.com/michigan-news");
            URI shortUrl = urlShortener.createShortUrl(originalUrl);
        assertEquals("https://short.com/baaaa", shortUrl.toString());
        assertEquals(originalUrl, urlShortener.getOriginalUrl(shortUrl));
    }

}