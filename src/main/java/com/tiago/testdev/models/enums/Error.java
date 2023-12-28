package com.tiago.testdev.models.enums;

import lombok.Getter;

@Getter
public enum Error {
    E001("CUSTOM ALIAS ALREADY EXISTS"),
    E002("SHORTENED URL NOT FOUND");

    private final String value;

    Error(String value) {
        this.value = value;
    }

}
