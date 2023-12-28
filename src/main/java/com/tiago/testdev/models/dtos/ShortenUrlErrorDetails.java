package com.tiago.testdev.models.dtos;

public record ShortenUrlErrorDetails(
        String alias,
        String errorCode,
        String description
) {
}
