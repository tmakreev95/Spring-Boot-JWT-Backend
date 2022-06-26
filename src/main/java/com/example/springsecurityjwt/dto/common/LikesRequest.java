package com.example.springsecurityjwt.dto.common;

public class LikesRequest {
    Long id;

    public LikesRequest() {
    }

    public LikesRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
