package com.tiago.testdev.models.interfaces;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.dtos.ShortenUrlDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortenUrlRepositoryTest {
    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Test
    @DisplayName("Test - check if exists by alias")
    public void testExistsByAlias() {
        ShortenUrl shortenUrl = new ShortenUrl(new ShortenUrlDto(null, "alias1", "https://www.example.com", null));
        shortenUrlRepository.save(shortenUrl);

        boolean exists = shortenUrlRepository.existsByAlias("alias1");
        assertTrue(exists);
    }

    @Test
    @DisplayName("Test - check if exists one or more url")
    public void testFindAllByUrlOriginal() {
        ShortenUrl shortenUrl = new ShortenUrl(new ShortenUrlDto(null, "alias2", "https://www.example.com", null));
        shortenUrlRepository.save(shortenUrl);

        List<ShortenUrl> resultList = shortenUrlRepository.findAllByUrlOriginal("https://www.example.com");
        assertEquals(1, resultList.size());
    }
}