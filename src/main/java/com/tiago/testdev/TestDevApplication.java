package com.tiago.testdev;

import com.tiago.testdev.client.ShortenUrlClientApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestDevApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestDevApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }
}
