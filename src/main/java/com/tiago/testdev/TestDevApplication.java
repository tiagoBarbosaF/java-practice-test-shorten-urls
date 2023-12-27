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
//        long startTime = System.currentTimeMillis();
//
//        // Seu código aqui (por exemplo, gerar a URL encurtada)
//
//        // Obter o tempo final
//        long endTime = System.currentTimeMillis();
//
//        // Calcular a diferença
//        long elapsedTime = endTime - startTime;
//
//        // Imprimir o tempo decorrido em milissegundos
//        System.out.println("Tempo decorrido: " + elapsedTime + " ms");

        Instant startInstant = Instant.now();

        // Seu código aqui (por exemplo, gerar a URL encurtada)
        for (int i = 0; i < 100000000; i++) {
            i++;
        }

        // Obter o tempo final
        Instant endInstant = Instant.now();

        // Calcular a diferença
        Duration elapsedTime = Duration.between(startInstant, endInstant);

        // Imprimir o tempo decorrido em milissegundos
        System.out.println("Tempo decorrido: " + elapsedTime.toMillis() + " ms");
//        Instant end = Instant.now();
//        Duration between = Duration.between(start, end);
//        System.out.println(between.toMillis());
    }
}
