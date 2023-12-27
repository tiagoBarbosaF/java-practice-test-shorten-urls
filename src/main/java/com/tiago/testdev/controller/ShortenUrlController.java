package com.tiago.testdev.controller;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.ShortenUrlDto;
import com.tiago.testdev.models.Statistics;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.models.interfaces.StatisticsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/shorten/create")
public class ShortenUrlController {

    private final ShortenUrlRepository shortenUrlRepository;
    private final StatisticsRepository statisticsRepository;

    public ShortenUrlController(ShortenUrlRepository shortenUrlRepository, StatisticsRepository statisticsRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
        this.statisticsRepository = statisticsRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ShortenUrl> shorten(@RequestParam String url) {
        Instant start = Instant.now();
        String[] uuid = UUID.nameUUIDFromBytes(url.getBytes()).toString().split("-");
        String hash = Arrays.stream(uuid).reduce((s, s2) -> s2).orElse(null);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Instant end = Instant.now();
        Duration elapsedTime = Duration.between(start, end);
        Statistics statistics = new Statistics(null, elapsedTime.toMillis()+"ms");
        statisticsRepository.save(statistics);
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(null, hash, url, statistics);
        ShortenUrl shortenUrl = new ShortenUrl(shortenUrlDto);
        shortenUrlRepository.save(shortenUrl);

        return ResponseEntity.ok().body(shortenUrl);
    }
}
