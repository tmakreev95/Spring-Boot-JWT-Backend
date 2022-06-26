package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.post.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT CASE WHEN COUNT(l)> 0 THEN TRUE ELSE FALSE END FROM Like l WHERE LOWER(l.user.email) LIKE LOWER(:email) and l.post.id = :id")
    boolean findExistingLikeByUserEmail(@Param("email") String email, @Param("id") Long id);

    @Query("SELECT lik FROM Like lik WHERE lik.post.id = :id")
    ArrayList<Like> findLikesByPostId(@Param("id") Long id);

}
