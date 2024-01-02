package com.tiago.testdev.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ShortenUrlClientApi {
    private static final String SHORTEN_BASE_URL = "http://localhost:8080/shorten/create";
    private static final String RETRIEVE_BASE_URL = "http://localhost:8080/retrieve";

    public static Mono<String> shortenUrl(String originalUrl, String customAlias) {
        WebClient webClient = WebClient.create(SHORTEN_BASE_URL);

        String apiUrl = "?url=" + originalUrl;
        if (customAlias != null && !customAlias.isEmpty()) {
            apiUrl += "&customAlias=" + customAlias;
        }

        return webClient.post()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class);
    }

    public static Mono<String> retrieveUrl(String url){
        WebClient webClient = WebClient.create(RETRIEVE_BASE_URL);

        String apiUrl = "?url=" + url;

        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class);
    }
}
