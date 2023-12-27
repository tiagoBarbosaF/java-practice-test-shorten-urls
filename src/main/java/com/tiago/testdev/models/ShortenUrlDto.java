package com.tiago.testdev.models;

public record ShortenUrlDto(
        Long id,
        String alias,
        String url,
        Statistics statistics
) {
}
