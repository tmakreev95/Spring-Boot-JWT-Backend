package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);

    @Query("SELECT post FROM Post post WHERE post.id = :id")
    Post findPostById(@Param("id") Long id);

    @Query("SELECT post FROM Post post WHERE post.status LIKE('PUBLISHED')")
    ArrayList<Post> findAllPublishedPosts();

    @Query("SELECT post FROM Post post WHERE post.status LIKE('PENDING_APPROVE')")
    ArrayList<Post> findAllPendingApprovePosts();

    @Query("SELECT post FROM Post post WHERE post.author.email = :email AND post.status LIKE('PUBLISHED')")
    ArrayList<Post> findPublishedPostsByUser(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(post)> 0 THEN TRUE ELSE FALSE END FROM Post post WHERE LOWER(post.title) LIKE LOWER(:title)")
    boolean findExistingPostByTitle(@Param("title") String title);

    @Query("SELECT post FROM Post post JOIN post.likes postl WHERE postl.user.email = :email")
    ArrayList<Post> findLikedPosts(@Param("email") String email);

    @Query("SELECT post FROM Post post JOIN post.categories postcat WHERE postcat.id = :id")
    ArrayList<Post> findPostByCategoryId(@Param("id") Long id);

}
