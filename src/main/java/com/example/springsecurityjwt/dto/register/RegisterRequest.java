package com.example.springsecurityjwt.dto.register;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RegisterRequest {

    @Length(min = 8, max = 255)
    @NotNull(message = "First Name is required field!")
    private String firstName;

    @Length(min = 8, max = 255)
    @NotNull(message = "Last Name is required field!")
    private String lastName;

    @Length(min = 8, max = 100)
    @NotNull(message = "Email is required field!")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Password is required field!")
    @Length(min = 6, max=30, message = "Password should be at least 6 characters!")
    private String password;


    public RegisterRequest() {
    }

    public RegisterRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
