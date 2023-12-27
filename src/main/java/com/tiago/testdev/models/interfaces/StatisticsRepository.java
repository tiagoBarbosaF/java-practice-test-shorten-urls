package com.tiago.testdev.models.interfaces;

import com.tiago.testdev.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
