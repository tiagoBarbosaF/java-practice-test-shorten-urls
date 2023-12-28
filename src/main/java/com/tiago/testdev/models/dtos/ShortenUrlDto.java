package com.tiago.testdev.models.dtos;

import com.tiago.testdev.models.Statistics;

public record ShortenUrlDto(
        Long id,
        String alias,
        String url,
        Statistics statistics
) {
}
