package com.example.springsecurityjwt.dto.post;

public class PostUpdateResponse {
    private String message;
    private Boolean status;

    public PostUpdateResponse() {
    }

    public PostUpdateResponse(String message, Boolean status) {
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
