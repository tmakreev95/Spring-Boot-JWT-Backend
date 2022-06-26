package com.example.springsecurityjwt.dto.post;

public class PostResponse {
    private String message;
    private Boolean status;

    public PostResponse() {
    }

    public PostResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
