package com.example.springsecurityjwt.dto.email.registration;

public class MailRegistrationRequest {
    private String to;
    private String from;
    private String firstName;
    private String lastName;
    private String subject;

    public String getTo() {
        return to;
    }

    public MailRegistrationRequest(String to, String from, String firstName, String lastName, String subject) {
        this.to = to;
        this.from = from;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
