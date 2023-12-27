package com.tiago.testdev.models.interfaces;

import com.tiago.testdev.models.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
    boolean existsByAlias(String alias);
}
