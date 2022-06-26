package com.example.springsecurityjwt.models.post.like;

import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.post.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="post_like")
public class Like implements Serializable {
    private static final long serialVersionUID = 123456788L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Post.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Like() {
    }

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Transient
    @JsonGetter(value = "userInfo")
    private String userInfo() {
        return this.user.getFirstName() + " " + this.user.getLastName();
    }

    @Transient
    @JsonGetter(value = "userInfoEmail")
    private String userInfoEmail() {
        return this.user.getEmail();
    }
}
