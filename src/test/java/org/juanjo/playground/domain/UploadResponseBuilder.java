package org.juanjo.playground.domain;

/**
 * Builder for {@link UploadResponse}
 */
public class UploadResponseBuilder {

    private String documentId = "documentId";
    private String transactionId = "transactionId";
    private String fileName = "fileName";

    public UploadResponse build() {
        return new UploadResponse(
                this.documentId, this.transactionId, this.fileName
        );
    }

    public UploadResponseBuilder documentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public UploadResponseBuilder transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public UploadResponseBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

}