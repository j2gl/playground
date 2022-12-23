package org.juanjo.playground.controller;

import lombok.extern.slf4j.Slf4j;
import org.juanjo.playground.domain.UploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class DocumentController {

    private final Path rootLocation;

    public DocumentController(@Value("${playground.documents.location}") String location) {
        this.rootLocation = Paths.get(location);
    }

    @PostMapping("/document")
    public ResponseEntity<UploadResponse> upload(@RequestParam("param1") String param1,
                                 @RequestParam(value = "param2", required = false) String param2,
                                 @RequestParam("file") MultipartFile file) {
        log.info("Received a request for upload a file with param1={}, param2={}", param1, param2);
        log.info("File name={}, size={}, contentType={}", file.getOriginalFilename(), file.getSize(), file.getContentType());
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Cannot store an empty file.");
            }
            String docId = "doc_" + System.currentTimeMillis();
            var fileName = Optional.ofNullable(file.getOriginalFilename())
                    .orElseGet(() -> docId + ".pdf");

            Path destinationFile = rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new IOException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            UploadResponse uploadResponse = new UploadResponse(docId, "trx_12345", fileName);
            return ResponseEntity.ok(uploadResponse);
        } catch (IOException ioException) {
            log.error("Error uploading document.", ioException);
            return ResponseEntity.badRequest()
                    .body(UploadResponse.builder().documentId(ioException.getMessage()).build());
        }
    }

}
