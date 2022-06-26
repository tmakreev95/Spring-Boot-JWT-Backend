package com.example.springsecurityjwt.dto.user_check;

public class UserCheckResponse {
    private String email;
    private String role;

    public UserCheckResponse() {
    }

    public UserCheckResponse(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
