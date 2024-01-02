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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class RetrieveUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test - retrieve url status code ok")
    void testRetrieveUrlOk() throws Exception {
        String url = "https://github.com";
        MockHttpServletResponse response = mockMvc.perform(get("/retrieve").param("url", url)).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test - retrieve url status code bad request")
    void testRetrieveUrlBadRequest() throws Exception {
        String url = "https://any.com";
        MockHttpServletResponse response = mockMvc.perform(get("/retrieve").param("url", url)).andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test - retrieve most used urls status code ok")
    void testRetrieveMostUsedUrlsOk() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/retrieve/mostUsedUrls")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}