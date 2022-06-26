package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.verification.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
