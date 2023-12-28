package com.tiago.testdev.models.dtos;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.Statistics;

public record RetrieveUrlDetails(
        String alias,
        String urlOriginal,
        String shortenedUrl,
        Statistics statistics
) {
    public RetrieveUrlDetails(ShortenUrl shortenUrl) {
        this(shortenUrl.getAlias(), shortenUrl.getUrlOriginal(), shortenUrl.getShortenedUrl(), shortenUrl.getStatistics());
    }
}
