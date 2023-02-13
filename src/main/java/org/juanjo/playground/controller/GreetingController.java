package org.juanjo.playground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.juanjo.playground.client.GreetingWebClient;
import org.juanjo.playground.domain.greeting.GreetingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class GreetingController {

    private final GreetingWebClient greetingWebClient;

    @GetMapping("/greeting")
    public ResponseEntity<GreetingResponse> greet(@RequestParam String name) {
        String greeting = "Hello %s!".formatted(name);

        log.debug("Called /greeting; name={}", name);
        return ResponseEntity
                .ok(GreetingResponse.builder()
                        .message(greeting)
                        .build());
    }

    @GetMapping("/test-greeting")
    public ResponseEntity<GreetingResponse> callGreetWithWebClient() {
        var response = greetingWebClient.callGreeting("Webclient");
        log.debug("Called /test-greeting; response={}", response);
        return ResponseEntity.ok(response);
    }

}
