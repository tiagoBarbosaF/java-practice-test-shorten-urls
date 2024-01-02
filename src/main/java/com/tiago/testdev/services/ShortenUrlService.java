package com.tiago.testdev.services;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.Statistics;
import com.tiago.testdev.models.dtos.ShortenUrlDto;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.models.interfaces.StatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ShortenUrlService {
    private final ShortenUrlRepository shortenUrlRepository;
    private final StatisticsRepository statisticsRepository;

    @Autowired
    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository, StatisticsRepository statisticsRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
        this.statisticsRepository = statisticsRepository;
    }

    public String createHashAlias(String url) {
        String pattern = "^(https?|ftp)://[^\\s/$.?#].\\S*$";
        if (url.isEmpty() || !Pattern.matches(pattern, url)) {
            return null;
        }

        String[] uuid = UUID.nameUUIDFromBytes(url.getBytes()).toString().split("-");
        log.info("Alias created: {} for URL: {}. ", uuid, url);
        return Arrays.stream(uuid).reduce((s, s2) -> s2).orElse(null);
    }

    public ShortenUrl getShortenUrl(String url, long elapsedTime, String alias) {
        Statistics statistics = new Statistics(null, elapsedTime + "ms");
        statisticsRepository.save(statistics);
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(null, alias, url, statistics);
        ShortenUrl shortenUrl = new ShortenUrl(shortenUrlDto);
        shortenUrlRepository.save(shortenUrl);
        return shortenUrl;
    }
}
