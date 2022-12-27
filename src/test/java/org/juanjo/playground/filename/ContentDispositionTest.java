package org.juanjo.playground.filename;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ContentDispositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"A_File-test-1234.pdf", "document", "123.zip", "The Document.pdf", "filename.jpg"})
    void givenHeaderStyle1_whenExtractFileName_thenShouldReturnFileName(String fileName) {
        String contentDisposition = "Content-Disposition: attachment; filename=\"%s\"; filename*=UTF-8''Invoice-69FA099E-0051.pdf"
                .formatted(fileName);

        String extractedFileName = extractFileName(contentDisposition);

        assertThat(extractedFileName).isEqualTo(fileName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A_File-test-1234.pdf", "document", "123.zip", "The Document.pdf", "filename.jpg"})
    void givenHeaderStyle2_whenExtractFileName_thenShouldReturnFileName(String fileName) {
        String contentDisposition = "Content-Disposition: form-data; name=\"fieldName\"; filename=\"%s\""
            .formatted(fileName);

        String extractedFileName = extractFileName(contentDisposition);

        assertThat(extractedFileName).isEqualTo(fileName);
    }

    @Test
    void whenExtractFileName_thenThrowError() {
        String contentDisposition = "Content-Disposition: form-data; name=\"fieldName\"";

        assertThatThrownBy(() -> extractFileName(contentDisposition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Could not extract filename in header=");
    }

    private static String extractFileName(String contentDispositionHeader) {
        return Arrays.stream(contentDispositionHeader.split(";"))
                .filter(split -> split.trim().startsWith("filename=\""))
                .findFirst()
                .map(str -> str.substring(11, str.length() - 1))
                .orElseThrow(() -> new IllegalArgumentException("Could not extract filename in header=%s."
                        .formatted(contentDispositionHeader)));
    }

}
