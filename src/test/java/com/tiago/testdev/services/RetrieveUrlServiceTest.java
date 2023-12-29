package com.tiago.testdev.services;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.dtos.RetrieveUrlDetails;
import com.tiago.testdev.models.dtos.RetrieveUrlsMostAccessed;
import com.tiago.testdev.models.dtos.ShortenUrlDto;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RetrieveUrlServiceTest {
    @Mock
    private ShortenUrlRepository shortenUrlRepository;
    @InjectMocks
    private RetrieveUrlService retrieveUrlService;

    @Test
    @DisplayName("Test - Get error details")
    void testGetRetrieveErrorDetails() {
        List<RetrieveUrlDetails> emptyList = Collections.emptyList();
        ResponseEntity<ShortenUrlErrorDetails> result = retrieveUrlService.getRetrieveErrorDetails(emptyList);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    @DisplayName("Test - Get Urls most accessed")
    void testGetUrlsMostAccessed() {
        List<ShortenUrl> urls = Arrays.asList(
                new ShortenUrl(new ShortenUrlDto(1L, "alias1", "http://exemple.com", null)),
                new ShortenUrl(new ShortenUrlDto(2L, "alias1", "http://exemple.com", null))
        );

        Mockito.when(shortenUrlRepository.findAll()).thenReturn(urls);

        List<RetrieveUrlsMostAccessed> result = retrieveUrlService.getUrlsMostAccessed();

        Assertions.assertNotNull(result);
    }
}