package org.juanjo.playground.utils;

import org.junit.jupiter.api.Test;

class UniqueIdGeneratorTest {

    @Test
    void generateRandomHexId() {
        for (int i = 0; i < 10; i++) {
            var uniqueId = UniqueIdGenerator.generateRandomHexId(20);
            System.out.println("Hex uniqueId = " + uniqueId);
        }
    }

    @Test
    void generateRandomBase64Id() {
        for (int i = 0; i < 10; i++) {
            var uniqueId = UniqueIdGenerator.generateRandomBase64Id(20);
            System.out.println("Base64 uniqueId = " + uniqueId);
        }
    }

    @Test
    void generateRandomString() {
        for (int i = 0; i < 10; i++) {
            var uniqueId = UniqueIdGenerator.getRandomString(20);
            System.out.println("Random String = " + uniqueId);
        }
    }
}
