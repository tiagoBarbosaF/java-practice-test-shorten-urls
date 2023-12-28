package com.tiago.testdev.services;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.dtos.RetrieveUrlDetails;
import com.tiago.testdev.models.dtos.RetrieveUrlsMostAccessed;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.enums.Error;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RetrieveUrlService {
    private final ShortenUrlRepository shortenUrlRepository;

    public RetrieveUrlService(ShortenUrlRepository shortenUrlRepository){
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ResponseEntity<ShortenUrlErrorDetails> getRetrieveErrorDetails(List<RetrieveUrlDetails> list) {
        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(new ShortenUrlErrorDetails(null,
                    Error.E002.name().replace("E", ""),
                    Error.E002.getValue()));
        }
        return null;
    }

    public List<RetrieveUrlsMostAccessed> getUrlsMostAccessed() {
        List<ShortenUrl> allUrls = shortenUrlRepository.findAll();
        Map<String, Long> mapAllUrls = allUrls.stream().map(ShortenUrl::getUrlOriginal).collect(Collectors.groupingBy(urlOriginal -> urlOriginal, Collectors.counting()));
        return mapAllUrls.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .map(item -> new RetrieveUrlsMostAccessed(item.getKey(), item.getValue()))
                .toList();
    }
}
