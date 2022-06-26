package com.example.springsecurityjwt.enums;

public enum TokenStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    FOR_PURGING("FOR_PURGING"),
    EXPIRED("EXPIRED");

    public final String value;

    TokenStatus(String value) {
        this.value = value;
    }
}