package com.tiago.testdev.services;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.Statistics;
import com.tiago.testdev.models.dtos.ShortenUrlDto;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.models.interfaces.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

@Service
public class ShortenUrlService {
    private final ShortenUrlRepository shortenUrlRepository;
    private final StatisticsRepository statisticsRepository;

    @Autowired
    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository, StatisticsRepository statisticsRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
        this.statisticsRepository = statisticsRepository;
    }

    public String createHashAlias(String url) {
        String[] uuid = UUID.nameUUIDFromBytes(url.getBytes()).toString().split("-");
        return Arrays.stream(uuid).reduce((s, s2) -> s2).orElse(null);
    }

    public ShortenUrl getShortenUrl(String url, Duration elapsedTime, String alias) {
        Statistics statistics = new Statistics(null, elapsedTime.toMillis() + "ms");
        statisticsRepository.save(statistics);
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(null, alias, url, statistics);
        ShortenUrl shortenUrl = new ShortenUrl(shortenUrlDto);
        shortenUrlRepository.save(shortenUrl);
        return shortenUrl;
    }
}
