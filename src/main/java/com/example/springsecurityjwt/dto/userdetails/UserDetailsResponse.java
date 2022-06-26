package com.example.springsecurityjwt.dto.userdetails;

import com.example.springsecurityjwt.enums.UserStatus;

public class UserDetailsResponse {
    private String email;
    private String firstName;
    private String lastName;
    private UserStatus status;

    public UserDetailsResponse() {
    }

    public UserDetailsResponse(String email, String firstName, String lastName, UserStatus status) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
