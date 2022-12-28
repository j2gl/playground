package org.juanjo.playground.client;

import lombok.extern.slf4j.Slf4j;
import org.juanjo.playground.domain.greeting.GreetingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class GreetingWebClient {

    private final WebClient webClient;

    public GreetingWebClient(@Value("${playground.greeting.baseUrl:http://localhost:8080/api}") String baseUrl,
                             WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    public GreetingResponse callGreeting(String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToMono(GreetingResponse.class)
                .doOnError(throwable -> log.error("Error invoking callGreeting for name={}.", name, throwable))
                .block();
    }
}
