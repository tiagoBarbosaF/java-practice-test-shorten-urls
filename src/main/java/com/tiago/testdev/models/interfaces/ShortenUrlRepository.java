package com.tiago.testdev.models.interfaces;

import com.tiago.testdev.models.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
    boolean existsByAlias(String alias);

    List<ShortenUrl> findAllByUrlOriginal(String url);
}
