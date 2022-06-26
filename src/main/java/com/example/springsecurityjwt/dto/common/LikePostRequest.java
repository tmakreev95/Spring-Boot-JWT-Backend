package com.example.springsecurityjwt.dto.common;

public class LikePostRequest {
    Long id;

    public LikePostRequest() {
    }

    public LikePostRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
