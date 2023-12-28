package com.tiago.testdev.controller;

import com.tiago.testdev.models.dtos.RetrieveUrlDetails;
import com.tiago.testdev.models.dtos.RetrieveUrlsMostAccessed;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.services.RetrieveUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/retrieve")
public class RetrieveUrlController {

    private final ShortenUrlRepository shortenUrlRepository;
    private final RetrieveUrlService retrieveUrlService;

    public RetrieveUrlController(ShortenUrlRepository shortenUrlRepository, RetrieveUrlService retrieveUrlService) {
        this.shortenUrlRepository = shortenUrlRepository;
        this.retrieveUrlService = retrieveUrlService;
    }

    @GetMapping
    public ResponseEntity retrieveUrl(@RequestParam String url) {
        List<RetrieveUrlDetails> list = shortenUrlRepository.findAllByUrlOriginal(url).stream().map(RetrieveUrlDetails::new).toList();

        ResponseEntity<ShortenUrlErrorDetails> checkUrl = retrieveUrlService.getRetrieveErrorDetails(list);
        if (checkUrl != null) return checkUrl;

        return ResponseEntity.ok(list);
    }

    @GetMapping("/mostUsedUrls")
    public ResponseEntity getMostUrlUsed() {
        List<RetrieveUrlsMostAccessed> listUrls = retrieveUrlService.getUrlsMostAccessed();
        return ResponseEntity.ok().body(listUrls);
    }
}
