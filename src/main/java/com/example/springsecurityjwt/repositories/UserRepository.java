package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u)> 0 THEN TRUE ELSE FALSE END FROM User u WHERE LOWER(u.email) LIKE LOWER(:email)")
    boolean findExistingUserByEmail(@Param("email") String email);

    User findUserByEmail(String email);
}