package com.tiago.testdev;

import com.tiago.testdev.models.enums.Error;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class TestDevApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestDevApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }
}
