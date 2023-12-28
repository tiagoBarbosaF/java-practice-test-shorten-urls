package com.tiago.testdev.controller;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.dtos.ShortenUrlDto;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.Statistics;
import com.tiago.testdev.models.enums.Error;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.models.interfaces.StatisticsRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @Timed(description = "testTimer")
    public ResponseEntity shorten(@RequestParam String url, @RequestParam(required = false) String customAlias) {
        Instant start = Instant.now();
        String[] uuid = UUID.nameUUIDFromBytes(url.getBytes()).toString().split("-");
        String alias;

        if (customAlias != null) {
            alias = customAlias;
        } else {
            alias = Arrays.stream(uuid).reduce((s, s2) -> s2).orElse(null);
        }

        if (shortenUrlRepository.existsByAlias(alias)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ShortenUrlErrorDetails(alias,
                            Error.E001.name().replace("E", ""),
                            Error.E001.getValue()));
        }

        Instant end = Instant.now();
        Duration elapsedTime = Duration.between(start, end);
        Statistics statistics = new Statistics(null, elapsedTime.toMillis() + "ms");
        statisticsRepository.save(statistics);
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(null, alias, url, statistics);
        ShortenUrl shortenUrl = new ShortenUrl(shortenUrlDto);
        shortenUrlRepository.save(shortenUrl);

        return ResponseEntity.ok().body(shortenUrl);
    }
}
