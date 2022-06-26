package com.example.springsecurityjwt.enums;

public enum AddressStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    public final String value;

    AddressStatus(String value) {
        this.value = value;
    }
}
