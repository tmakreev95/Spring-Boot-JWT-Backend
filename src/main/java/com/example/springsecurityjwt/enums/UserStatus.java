package com.example.springsecurityjwt.enums;

public enum UserStatus {
    VERIFIED("VERIFIED"),
    NOT_VERIFIED("NOT_VERIFIED");

    public final String value;

    UserStatus(String value) {
        this.value = value;
    }
}
