package com.tiago.testdev.services;

import com.tiago.testdev.models.dtos.RetrieveUrlDetails;
import com.tiago.testdev.models.dtos.RetrieveUrlsMostAccessed;
import com.tiago.testdev.models.dtos.ShortenUrlErrorDetails;
import com.tiago.testdev.models.enums.Error;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RetrieveUrlServiceTest {
    @Autowired
    private RetrieveUrlService retrieveUrlService;

    @Test
    @DisplayName("Test - get retrieve error bad request")
    public void testGetRetrieveErrorBadRequest() {
        List<RetrieveUrlDetails> emptyList = new ArrayList<>();
        ResponseEntity<ShortenUrlErrorDetails> responseEntity = retrieveUrlService.getRetrieveErrorDetails(emptyList);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Test - get retrieve error 002 details")
    public void testGetRetrieveErrorDetails() {
        List<RetrieveUrlDetails> emptyList = new ArrayList<>();
        ResponseEntity<ShortenUrlErrorDetails> responseEntity = retrieveUrlService.getRetrieveErrorDetails(emptyList);
        assertNotNull(responseEntity);
        assertEquals(Error.E002.getValue(), Objects.requireNonNull(responseEntity.getBody()).description());
    }

    @Test
    @DisplayName("Test - return true if list is not empty")
    public void testGetUrlsMostAccessed() {
        List<RetrieveUrlsMostAccessed> urlsMostAccessed = retrieveUrlService.getUrlsMostAccessed();
        assertEquals(true,!urlsMostAccessed.isEmpty());
    }
}