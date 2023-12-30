package com.tiago.testdev.controller;

import com.tiago.testdev.models.dtos.RetrieveUrlDetails;
import com.tiago.testdev.models.dtos.RetrieveUrlsMostAccessed;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.services.RetrieveUrlService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/retrieve")
public class RetrieveUrlController {

    private final ShortenUrlRepository shortenUrlRepository;
    private final RetrieveUrlService retrieveUrlService;
    private final MeterRegistry meterRegistry;

    public RetrieveUrlController(ShortenUrlRepository shortenUrlRepository, RetrieveUrlService retrieveUrlService, MeterRegistry meterRegistry) {
        this.shortenUrlRepository = shortenUrlRepository;
        this.retrieveUrlService = retrieveUrlService;
        this.meterRegistry = meterRegistry;
    }

    @Timed(value = "endpoint_duration")
    @GetMapping
    public ResponseEntity retrieveUrl(@RequestParam String url) {
        Timer.Sample start = Timer.start(meterRegistry);
        log.info("Starting endpoint /retrieve for the URL: {}.", url);
        List<RetrieveUrlDetails> list = shortenUrlRepository.findAllByUrlOriginal(url).stream().map(RetrieveUrlDetails::new).toList();

        ResponseEntity<ShortenUrlErrorDetails> checkUrl = retrieveUrlService.getRetrieveErrorDetails(list);
        if (checkUrl != null) {
            log.error("Url error: {}", checkUrl);
            return checkUrl;
        }

        long end = start.stop(meterRegistry.timer("endpoint_duration"));
        log.info("Endpoint /retrieve finished. Total time: {} ms.", end / 1_000_000);
        return ResponseEntity.ok().body(list);
    }

    @Timed(value = "endpoint_duration")
    @GetMapping("/mostUsedUrls")
    public ResponseEntity getMostUrlUsed() {
        Timer.Sample start = Timer.start(meterRegistry);
        log.info("Starting endpoint /retrieve/mostUsedUrls");
        List<RetrieveUrlsMostAccessed> listUrls = retrieveUrlService.getUrlsMostAccessed();

        long end = start.stop(meterRegistry.timer("endpoint_duration"));
        log.info("Endpoint /retrieve/mostUsedUrls finished. Total time: {} ms. List urls: {}", end / 1_000_000, listUrls);
        return ResponseEntity.ok().body(listUrls);
    }
}
