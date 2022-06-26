package com.example.springsecurityjwt.enums;

public enum PostStatus {
    PENDING_APPROVE("PENDING_APPROVE"),
    PUBLISHED("PUBLISHED"),
    INACTIVE("INACTIVE");

    public final String value;

    PostStatus(String value) {
        this.value = value;
    }
}
