package org.juanjo.playground.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class UploadResponse {

    private String documentId;
    private String transactionId;
    private String fileName;

}
