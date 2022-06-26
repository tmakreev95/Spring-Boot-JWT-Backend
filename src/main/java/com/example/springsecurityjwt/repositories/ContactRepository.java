package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT contact FROM Contact contact WHERE contact.user.email = :email and contact.status like('ACTIVE')")
    Contact findByUser(@Param("email") String email);
}
