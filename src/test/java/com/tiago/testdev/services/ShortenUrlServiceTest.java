package com.tiago.testdev.services;

import com.tiago.testdev.controller.ShortenUrlController;
import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.Statistics;
import com.tiago.testdev.models.dtos.ShortenUrlDto;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.models.interfaces.StatisticsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

@ExtendWith(MockitoExtension.class)
public class ShortenUrlServiceTest {

    @Mock
    private ShortenUrlRepository shortenUrlRepository;
    @Mock
    private StatisticsRepository statisticsRepository;
    @InjectMocks
    private ShortenUrlService shortenUrlService;
    @InjectMocks
    private ShortenUrlController shortenUrlController;

    @Test
    @DisplayName("Test - Create hash alias")
    void testCreateHashAlias() {
        String url = "http://example.com";
        String alias = shortenUrlService.createHashAlias(url);
        Assertions.assertNotNull(alias);
    }

    @Test
    @DisplayName("Test - Get Shorten url")
    void testGetShortenUrl() {
        String url = "http://example.com";
        Duration duration = Duration.ofMillis(100);
        String alias = "customAlias";

        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(null, alias, url, null);
        Mockito.lenient().when(shortenUrlRepository.save(Mockito.any(ShortenUrl.class))).thenReturn(new ShortenUrl(shortenUrlDto));
        Mockito.lenient().when(statisticsRepository.save(Mockito.any(Statistics.class))).thenReturn(new Statistics(null, "100ms"));

//        ShortenUrl result = shortenUrlService.getShortenUrl(url, alias);

//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(alias, result.getAlias());
    }
}