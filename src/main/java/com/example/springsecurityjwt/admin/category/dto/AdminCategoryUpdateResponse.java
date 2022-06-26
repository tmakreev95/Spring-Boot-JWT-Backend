package com.example.springsecurityjwt.admin.category.dto;

public class AdminCategoryUpdateResponse {
    private String message;
    private Boolean status;

    public AdminCategoryUpdateResponse() {
    }

    public AdminCategoryUpdateResponse(String message, Boolean status) {
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
