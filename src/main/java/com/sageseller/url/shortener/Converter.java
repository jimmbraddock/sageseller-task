package com.sageseller.url.shortener;

public class Converter {
    private static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    public static final int BASE = CHARS.length;

    /**
     * Generate base {@value BASE} string
     * @param val int value
     * @return converted string
     */
    public static String intToBase62String(int val) {
        if (val == 0) {
            return String.valueOf(CHARS[0]);
        }
        StringBuilder sb = new StringBuilder();
        while (val > 0) {
            sb.append(CHARS[val % BASE]);
            val = val / BASE;
        }
        return sb.reverse().toString();
    }
}
