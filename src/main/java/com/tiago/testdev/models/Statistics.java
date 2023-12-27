package com.tiago.testdev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "statistics")
@Entity(name = "Statistics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String timeTaken;
}
