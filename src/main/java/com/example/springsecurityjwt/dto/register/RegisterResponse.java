package com.example.springsecurityjwt.dto.register;

public class RegisterResponse {
    private String message;
    private boolean status;

    public RegisterResponse() {
    }

    public RegisterResponse(String message, boolean status) {
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
