package com.tiago.testdev.models;

public record ShortenUrlErrorDetails(
        String alias,
        String errorCode,
        String description
) {
}
