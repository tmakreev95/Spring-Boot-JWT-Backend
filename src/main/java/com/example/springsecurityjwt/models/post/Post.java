package com.example.springsecurityjwt.models.post;

import com.example.springsecurityjwt.enums.PostStatus;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.post.category.Category;
import com.example.springsecurityjwt.models.post.like.Like;
import com.example.springsecurityjwt.models.post.featured_image.FeaturedImage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "Post")
@Table(name="post")
public class Post implements Serializable {
    private static final long serialVersionUID = 1123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @Column(name = "description", length = 4000, nullable = false)
    private String description;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "auth_user_id")
    private User author;

    @JsonManagedReference
    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private FeaturedImage featuredImage;

    @Column(name = "status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_category", joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Set<Like> likes;

    public Post() {
    }

    public Post(String title, String description, User author, PostStatus status, Set<Category> categories) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.status = status;
        this.categories = categories;
    }

    public FeaturedImage getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(FeaturedImage featuredImage) {
        this.featuredImage = featuredImage;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Like> getLikes() { return likes; }

    public void setLikes(Set<Like> likes) { this.likes = likes; }

    @Transient
    @JsonGetter(value = "authorInfo")
    private String authorInfo() {
        return this.author.getFirstName() + " " + this.author.getLastName();
    }

    @Transient
    @JsonGetter(value = "authorInfoEmail")
    private String authorInfoEmail() {
        return this.author.getEmail();
    }

}
