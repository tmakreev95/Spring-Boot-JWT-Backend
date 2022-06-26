package com.example.springsecurityjwt.enums;

public enum CategoryStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    public final String value;

    CategoryStatus(String value) {
        this.value = value;
    }
}
