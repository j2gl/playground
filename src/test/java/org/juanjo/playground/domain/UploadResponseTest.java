package org.juanjo.playground.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a test just to have a template for an external builder class.
 */
class UploadResponseTest {

    @Test
    void testUploadResponseBuilder() {
        UploadResponse uploadResponse = new UploadResponseBuilder()
                .documentId("123")
                .transactionId("456")
                .fileName("File")
                .build();

        assertEquals("123", uploadResponse.getDocumentId());
        assertEquals("456", uploadResponse.getTransactionId());
        assertEquals("File", uploadResponse.getFileName());
    }
}