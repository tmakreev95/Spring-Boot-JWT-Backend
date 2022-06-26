package com.example.springsecurityjwt.models.post.featured_image;

import com.example.springsecurityjwt.models.post.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;


@Entity
public class FeaturedImage implements Serializable {
    private static final long serialVersionUID = 12345677L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "path", length = 510)
    private String path;

    @Column(name = "name")
    private String name;

    @Column(name = "mimeType")
    private String mimeType;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public FeaturedImage() {
    }

    public FeaturedImage(String name, String mimeType, String path) {
        this.name = name;
        this.mimeType = mimeType;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
