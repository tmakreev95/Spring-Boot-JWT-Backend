package com.example.springsecurityjwt.dto.post;

public class PostRequest {
    private String title;
    private String description;
    private String[] categories;
    private String featuredImageContents;
    private String featuredImageMimeType;
    private String featuredImageName;

    public PostRequest() {
    }

    public PostRequest(String title, String description, String[] categories, String featuredImageContents, String featuredImageMimeType, String featuredImageName) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.featuredImageContents = featuredImageContents;
        this.featuredImageMimeType = featuredImageMimeType;
        this.featuredImageName = featuredImageName;
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

    public String getFeaturedImageContents() {
        return featuredImageContents;
    }

    public void setFeaturedImageContents(String featuredImageContents) {
        this.featuredImageContents = featuredImageContents;
    }

    public String getFeaturedImageMimeType() {
        return featuredImageMimeType;
    }

    public void setFeaturedImageMimeType(String featuredImageMimeType) {
        this.featuredImageMimeType = featuredImageMimeType;
    }

    public String getFeaturedImageName() {
        return featuredImageName;
    }

    public void setFeaturedImageName(String featuredImageName) {
        this.featuredImageName = featuredImageName;
    }
}


