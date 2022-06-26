package com.example.springsecurityjwt.dto.password_reset;

public class PasswordResetSaveRequest {
    private String token;
    private String password;

    public PasswordResetSaveRequest() {
    }

    public PasswordResetSaveRequest(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
