package com.example.springsecurityjwt.dto.password_reset;

public class PasswordResetTokenValidationRequest {
    private String token;

    public PasswordResetTokenValidationRequest() {
    }

    public PasswordResetTokenValidationRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
