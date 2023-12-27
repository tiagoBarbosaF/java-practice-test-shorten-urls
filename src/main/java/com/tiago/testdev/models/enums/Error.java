package com.tiago.testdev.models.enums;

public enum Error {
    E001("CUSTOM ALIAS ALREADY EXISTS");

    private final String value;

    Error(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
