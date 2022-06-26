package com.example.springsecurityjwt.enums;

public enum ContactStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    public final String value;

    ContactStatus(String value) {
        this.value = value;
    }
}
