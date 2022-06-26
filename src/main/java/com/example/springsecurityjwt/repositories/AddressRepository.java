package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT address FROM Address address WHERE address.user.email = :email and address.status like('ACTIVE')")
    Address findByUser(@Param("email") String email);
}
