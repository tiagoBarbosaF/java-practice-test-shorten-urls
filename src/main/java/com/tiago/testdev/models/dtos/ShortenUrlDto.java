package com.tiago.testdev.models.dtos;

import com.tiago.testdev.models.Statistics;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ShortenUrlDto(
        Long id,
        String alias,
        @NotEmpty @NotBlank @NotNull String url,
        Statistics statistics
) {
}
