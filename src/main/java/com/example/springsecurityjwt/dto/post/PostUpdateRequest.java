package com.example.springsecurityjwt.dto.post;

public class PostUpdateRequest {
    private Long id;
    private String title;
    private String description;
    private String[] categories;

    public PostUpdateRequest() {
    }

    public PostUpdateRequest(Long id, String title, String description, String[] categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
