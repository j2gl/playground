package org.juanjo.playground.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

public class UniqueIdGenerator {

    private UniqueIdGenerator() {
    }

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomHexId(int length) {
        var token = new byte[length];
        secureRandom.nextBytes(token);
        var hexNumber = new BigInteger(1, token).toString(16).toUpperCase();
        var padding = length * 2 - hexNumber.length();
        if (padding > 0) {
            hexNumber = "0".repeat(padding) + hexNumber;
        }
        return "0x" + hexNumber;
    }

    public static String generateRandomBase64Id(int length) {
        var token = new byte[length];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }

    public static String getRandomString(int length) {
        final String characters = "1234567890ABCDEFGHIJLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();

        while (length > 0) {
            result.append(characters.charAt(secureRandom.nextInt(characters.length())));
            length--;
        }
        return result.toString();
    }
}
