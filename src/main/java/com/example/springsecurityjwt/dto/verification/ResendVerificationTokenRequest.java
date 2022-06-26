package com.example.springsecurityjwt.dto.verification;

public class ResendVerificationTokenRequest {
    private String email;


    public ResendVerificationTokenRequest() {
    }

    public ResendVerificationTokenRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
