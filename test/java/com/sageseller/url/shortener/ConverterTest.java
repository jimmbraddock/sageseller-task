package com.sageseller.url.shortener;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test
    public void firstCharConversationTest() {
        assertEquals("a", Converter.intToBase62String(0));
    }

    @Test
    public void lastCharConversationTest() {
        assertEquals("9", Converter.intToBase62String(61));
    }

    @Test
    public void customConversationTest() {
        assertEquals("ce", Converter.intToBase62String(128));
    }
}