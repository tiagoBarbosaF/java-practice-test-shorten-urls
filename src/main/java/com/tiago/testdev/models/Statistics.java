package com.tiago.testdev.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "statistics")
@Entity(name = "Statistics")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String timeTaken;
}
