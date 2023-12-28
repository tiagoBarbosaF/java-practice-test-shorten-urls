package com.tiago.testdev.utils;

import com.tiago.testdev.models.ShortenUrl;
import com.tiago.testdev.models.Statistics;
import com.tiago.testdev.models.dtos.ShortenUrlDto;
import com.tiago.testdev.models.interfaces.ShortenUrlRepository;
import com.tiago.testdev.models.interfaces.StatisticsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ShortenUrlRepository shortenUrlRepository;
    private final StatisticsRepository statisticsRepository;

    public DataInitializer(ShortenUrlRepository shortenUrlRepository, StatisticsRepository statisticsRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Statistics> statistics = new ArrayList<>();

        for (Long i = 0L; i < 11; i++) {
            statistics.add(new Statistics(i, "12ms"));
        }



        statisticsRepository.saveAll(statistics);

        if (shortenUrlRepository.count() == 0) {
            ShortenUrlDto shortenUrlDto1 = new ShortenUrlDto(null,
                    Arrays.stream(UUID.nameUUIDFromBytes("https://www.google.com".getBytes())
                                    .toString()
                                    .split("-"))
                            .reduce((s, s2) -> s2)
                            .orElse(null), "https://www.google.com",statisticsRepository.findAll().get(0));
            ShortenUrlDto shortenUrlDto2 = new ShortenUrlDto(null,
                    Arrays.stream(UUID.nameUUIDFromBytes("https://www.bing.com".getBytes())
                                    .toString()
                                    .split("-"))
                            .reduce((s, s2) -> s2)
                            .orElse(null), "https://www.bing.com", statisticsRepository.findAll().get(1));
            ShortenUrlDto shortenUrlDto3 = new ShortenUrlDto(null, "meugoogle", "https://www.google.com", statisticsRepository.findAll().get(2));
            ShortenUrlDto shortenUrlDto4 = new ShortenUrlDto(null,
                    Arrays.stream(UUID.nameUUIDFromBytes("https://github.com".getBytes())
                                    .toString()
                                    .split("-"))
                            .reduce((s, s2) -> s2)
                            .orElse(null), "https://github.com", statisticsRepository.findAll().get(3));
            ShortenUrlDto shortenUrlDto5 = new ShortenUrlDto(null, "myGitHub", "https://github.com", statisticsRepository.findAll().get(4));
            ShortenUrlDto shortenUrlDto6 = new ShortenUrlDto(null,
                    Arrays.stream(UUID.nameUUIDFromBytes("https://www.instagram.com".getBytes())
                                    .toString()
                                    .split("-"))
                            .reduce((s, s2) -> s2)
                            .orElse(null), "https://www.instagram.com", statisticsRepository.findAll().get(5));
            ShortenUrlDto shortenUrlDto7 = new ShortenUrlDto(null, "myInstagram", "https://www.instagram.com", statisticsRepository.findAll().get(6));
            ShortenUrlDto shortenUrlDto8 = new ShortenUrlDto(null,
                    Arrays.stream(UUID.nameUUIDFromBytes("https://www.linkedin.com".getBytes())
                                    .toString()
                                    .split("-"))
                            .reduce((s, s2) -> s2)
                            .orElse(null), "https://www.linkedin.com", statisticsRepository.findAll().get(7));
            ShortenUrlDto shortenUrlDto9 = new ShortenUrlDto(null,
                    Arrays.stream(UUID.nameUUIDFromBytes("https://twitter.com".getBytes())
                                    .toString()
                                    .split("-"))
                            .reduce((s, s2) -> s2)
                            .orElse(null), "https://twitter.com", statisticsRepository.findAll().get(8));
            ShortenUrlDto shortenUrlDto10 = new ShortenUrlDto(null, "X", "https://twitter.com", statisticsRepository.findAll().get(9));

            List<ShortenUrl> list = Arrays.asList(
                    new ShortenUrl(shortenUrlDto1),
                    new ShortenUrl(shortenUrlDto2),
                    new ShortenUrl(shortenUrlDto3),
                    new ShortenUrl(shortenUrlDto4),
                    new ShortenUrl(shortenUrlDto5),
                    new ShortenUrl(shortenUrlDto6),
                    new ShortenUrl(shortenUrlDto7),
                    new ShortenUrl(shortenUrlDto8),
                    new ShortenUrl(shortenUrlDto9),
                    new ShortenUrl(shortenUrlDto10)
            );
            shortenUrlRepository.saveAll(list);
        }
    }
}
