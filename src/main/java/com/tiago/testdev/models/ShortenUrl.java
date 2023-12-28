package com.tiago.testdev.models;

import com.tiago.testdev.models.dtos.ShortenUrlDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "shorten_url")
@Entity(name = "ShortenUrl")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String alias;
    private String urlOriginal;
    private String shortenedUrl;
    @OneToOne
    private Statistics statistics;

    public ShortenUrl(ShortenUrlDto shortenUrl) {
        this.alias = shortenUrl.alias();
        this.urlOriginal = shortenUrl.url();
        this.shortenedUrl = "http://shortener/u/" + this.alias;
        this.statistics = shortenUrl.statistics();
    }
}
