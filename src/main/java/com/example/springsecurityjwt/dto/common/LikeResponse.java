package com.example.springsecurityjwt.dto.common;

public class LikeResponse {
    String message;
    boolean status;

    public LikeResponse() {
    }

    public LikeResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
