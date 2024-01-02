package com.tiago.testdev.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class ShortenUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test - Shorten Url status created")
    void testShortenUrlEndpoint() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/shorten/create").param("url", "https://mysite.com")).andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void testShortenUrlError() throws Exception {
        String url = "https://www.anysite.com";
        mockMvc.perform(post("/shorten/create").param("url", url)).andReturn().getResponse();
        MockHttpServletResponse response = mockMvc.perform(post("/shorten/create").param("url", url)).andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}