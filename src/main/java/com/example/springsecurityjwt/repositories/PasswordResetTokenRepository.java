package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.password_reset.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);
}
