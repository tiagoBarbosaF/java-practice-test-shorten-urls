package com.tiago.testdev.controller;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.enums.Error;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.services.ShortenUrlService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/shorten/create")
public class ShortenUrlController {
    private final ShortenUrlService shortenUrlService;
    private final ShortenUrlRepository shortenUrlRepository;
    private final MeterRegistry meterRegistry;

    @Autowired
    public ShortenUrlController(ShortenUrlService shortenUrlService, ShortenUrlRepository shortenUrlRepository, MeterRegistry meterRegistry) {
        this.shortenUrlService = shortenUrlService;
        this.shortenUrlRepository = shortenUrlRepository;
        this.meterRegistry = meterRegistry;
    }

    @Timed(value = "endpoint_duration")
    @PostMapping
    @Transactional
    public ResponseEntity shorten(@RequestParam @Valid @NotEmpty String url, @RequestParam(required = false) String customAlias) {
        log.info("Starting endpoint /shorten/create for the URL: {}. Custom alias: {}", url, customAlias);
        Timer.Sample start = Timer.start(meterRegistry);
        String alias;

        if (customAlias != null) alias = customAlias;
        else alias = shortenUrlService.createHashAlias(url);

        if (shortenUrlRepository.existsByAlias(alias)) {
            ResponseEntity<ShortenUrlErrorDetails> errorDetails = ResponseEntity.badRequest().body(new ShortenUrlErrorDetails(alias,
                    Error.E001.name().replace("E", ""),
                    Error.E001.getValue()));
            log.error("Url error: {}", errorDetails);
            return errorDetails;
        }

        long end = start.stop(meterRegistry.timer("endpoint_duration"));
        long endToMilli = end / 1_000_000;
        ShortenUrl shortenUrl = shortenUrlService.getShortenUrl(url, endToMilli, alias);

        log.info("Endpoint /shorten/create finished. Total time: {} ms.", endToMilli);
        return ResponseEntity.ok().body(shortenUrl);
    }
}
