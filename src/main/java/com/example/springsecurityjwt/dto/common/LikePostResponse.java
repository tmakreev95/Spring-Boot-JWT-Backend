package com.example.springsecurityjwt.dto.common;

public class LikePostResponse {
    String message;
    boolean status;

    public LikePostResponse() {
    }

    public LikePostResponse(String message, boolean status) {
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
