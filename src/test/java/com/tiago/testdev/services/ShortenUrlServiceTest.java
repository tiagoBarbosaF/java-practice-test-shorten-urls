package com.tiago.testdev.services;

import com.tiago.testdev.models.ShortenUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ShortenUrlServiceTest {
    @Autowired
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("Test - return null if url is empty or wrong format")
    public void testCreateHashAliasEmptyOrWrongFormat() {
        String alias = shortenUrlService.createHashAlias("httpss://any.com");
        assertEquals(null, alias);
    }

    @Test
    @DisplayName("Test - return null if url is not empty or wrong format")
    public void testCreateHashAliasIsNotEmptyOrWrongFormat() {
        String alias = shortenUrlService.createHashAlias("https://any.com");
        assertNotNull(alias);
    }

    @Test
    @DisplayName("Test - get shorten url is not null")
    public void testGetShortenUrlNotNull() {
        ShortenUrl shortenUrl = shortenUrlService.getShortenUrl("https://www.example.com", 100, "alias");
        assertNotNull(shortenUrl);
    }
}