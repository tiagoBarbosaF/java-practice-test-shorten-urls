package com.tiago.testdev.controller;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.services.ShortenUrlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ShortenUrlControllerTest {
    @MockBean
    private MockMvc mockMvc;
    @Autowired
    private ShortenUrlService shortenUrlService;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Test
    public void testShortenUrlEndpoint() throws Exception {
        // Configuração do mock para o serviço
        when(shortenUrlService.createHashAlias(any())).thenReturn("alias");
        when(shortenUrlService.getShortenUrl(any(), anyLong(), any())).thenReturn(new ShortenUrl());

        // Teste para o endpoint /shorten/create
        mockMvc.perform(post("/shorten/create")
                        .param("url", "https://www.example.com")
                        .param("customAlias", "alias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alias").value("alias"));
    }
}