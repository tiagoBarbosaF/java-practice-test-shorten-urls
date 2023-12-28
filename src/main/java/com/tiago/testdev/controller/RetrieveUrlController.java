package com.tiago.testdev.controller;

import com.tiago.testdev.models.dtos.RetrieveUrlDetails;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.enums.Error;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
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

    public RetrieveUrlController(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    @GetMapping
    public ResponseEntity retrieveUrl(@RequestParam String url) {
        List<RetrieveUrlDetails> list = shortenUrlRepository.findAllByUrlOriginal(url).stream().map(RetrieveUrlDetails::new).toList();

        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(new ShortenUrlErrorDetails(null,
                    Error.E002.name().replace("E", ""),
                    Error.E002.getValue()));
        }
        return ResponseEntity.ok(list);
    }
}
