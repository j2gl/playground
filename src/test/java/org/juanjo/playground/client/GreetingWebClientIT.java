package org.juanjo.playground.client;

import org.juanjo.playground.WireMockBase;
import org.juanjo.playground.domain.greeting.GreetingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "playground.greeting.baseUrl=http://localhost:8082/api"
})
class GreetingWebClientIT extends WireMockBase {

    @Autowired
    private GreetingWebClient greetingWebClient;

    @Test
    void whenCallGreeting_thenReturnGreeting() {
        // given
        String name = "MrSnow";
        wireMockServer.stubFor(get(urlPathEqualTo("/api/greeting"))
                        .withQueryParam("name", equalTo(name))
                        .willReturn(okJson(jsonResponse(name))));

        // when
        GreetingResponse greetingResponse = greetingWebClient.callGreeting(name);

        // then
        assertThat(greetingResponse.getMessage()).isEqualTo("Hello %s!".formatted(name));
    }

    private String jsonResponse(String name) {
        return """
                {"message":"Hello %s!"}
                """.formatted(name);
    }
}