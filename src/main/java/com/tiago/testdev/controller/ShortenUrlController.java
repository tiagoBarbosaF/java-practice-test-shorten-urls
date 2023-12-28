package com.tiago.testdev.controller;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.enums.Error;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.services.ShortenUrlService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/shorten/create")
public class ShortenUrlController {

    private final ShortenUrlService shortenUrlService;
    private final ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public ShortenUrlController(ShortenUrlService shortenUrlService, ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlService = shortenUrlService;
        this.shortenUrlRepository = shortenUrlRepository;
    }

    @PostMapping
    @Transactional
    @Timed(description = "testTimer")
    public ResponseEntity shorten(@RequestParam String url, @RequestParam(required = false) String customAlias) {
        Instant start = Instant.now();
        String alias;

        if (customAlias != null) alias = customAlias;
        else alias = shortenUrlService.createHashAlias(url);

        if (shortenUrlRepository.existsByAlias(alias)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ShortenUrlErrorDetails(alias,
                            Error.E001.name().replace("E", ""),
                            Error.E001.getValue()));
        }

        Instant end = Instant.now();
        Duration elapsedTime = Duration.between(start, end);

        ShortenUrl shortenUrl = shortenUrlService.getShortenUrl(url, elapsedTime, alias);
        return ResponseEntity.ok().body(shortenUrl);
    }
}
