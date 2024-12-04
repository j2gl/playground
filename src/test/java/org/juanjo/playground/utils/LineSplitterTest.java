package org.juanjo.playground.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LineSplitterTest {
    @ParameterizedTest
    @CsvSource({
            "2, A, A",
            "5, ABCD, ABCD",
            "16, 1234567890ABCDEF, 1234567890ABCDEF"
    })
    void splitToLines_whenValueIsLessOrEqualThanMax_shouldReturnOneLines(int maxLineSize,
                                                                         String value,
                                                                         String expected) {
        var lines = LineSplitter.splitToLines(maxLineSize, value);

        assertThat(lines).hasSize(1);
        assertThat(lines.getFirst()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "2, ABC, AB, C",
            "4, ABCD1234, ABCD, 1234",
    })
    void splitToLines_whenValueIsMoreThanMax_shouldReturnTwoLines(int maxLineSize,
                                                                  String value,
                                                                  String expectedLine1,
                                                                  String expectedLine2) {

        var lines = LineSplitter.splitToLines(maxLineSize, value);

        assertThat(lines).hasSize(2);
        assertThat(lines.getFirst()).isEqualTo(expectedLine1);
        assertThat(lines.get(1)).isEqualTo(expectedLine2);
    }

    @Test
    void splitToLines_whenValueIsEmpty_shouldReturnEmptyArray() {
        var lines = LineSplitter.splitToLines(4, "");

        assertThat(lines).isEmpty();
    }

    @Test
    void splitToLines_whenValueIsNull_shouldReturnEmptyArray() {
        var lines = LineSplitter.splitToLines(4, null);

        assertThat(lines).isEmpty();
    }
}