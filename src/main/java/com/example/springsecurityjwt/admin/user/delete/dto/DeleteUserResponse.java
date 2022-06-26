package com.example.springsecurityjwt.admin.user.delete.dto;

public class DeleteUserResponse {
    private String message;
    private Boolean status;

    public DeleteUserResponse() {
    }

    public DeleteUserResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
