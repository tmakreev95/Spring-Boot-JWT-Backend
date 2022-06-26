package com.example.springsecurityjwt.dto.profile;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class ProfileUpdateRequest {
    @Length(min = 4, max = 255)
    @NotNull(message = "First name is required field!")
    private String firstName;

    @Length(min = 4, max = 255)
    @NotNull(message = "Last name is required field!")
    private String lastName;

    public ProfileUpdateRequest() {
    }

    public ProfileUpdateRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
