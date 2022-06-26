package com.example.springsecurityjwt.admin.category.dto;


public class AdminCategoryAddRequest {
    private String name;
    private String description;

    public AdminCategoryAddRequest() {
    }

    public AdminCategoryAddRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


