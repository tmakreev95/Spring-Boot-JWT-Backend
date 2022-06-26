package com.example.springsecurityjwt.dto.email.password_reset;

public class MailPasswordResetTokenRequest {
    private String to;
    private String from;
    private String subject;
    private String firstName;
    private String lastName;

    public MailPasswordResetTokenRequest() {
    }

    public MailPasswordResetTokenRequest(String to, String from, String subject, String firstName, String lastName) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getTo() {
        return to;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
