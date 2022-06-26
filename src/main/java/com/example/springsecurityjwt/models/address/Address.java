package com.example.springsecurityjwt.models.address;

import com.example.springsecurityjwt.enums.AddressStatus;
import com.example.springsecurityjwt.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="address")
public class Address implements Serializable {
    private static final long serialVersionUID = 12345678910111212L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Select
    @Column(name = "country", length = 255)
    private String country;

    //Select
    @Column(name = "district", length = 255)
    private String district;

    //Select
    @Column(name = "municipality", length = 255)
    private String municipality;

    //Select
    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "address", length = 255)
    private String address;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auth_user_id", nullable = false)
    private User user;

    @Column(name = "status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressStatus status;

    public Address() {
    }

    public Address(String country, String district, String municipality, String city, String address, AddressStatus status) {
        this.country = country;
        this.district = district;
        this.municipality = municipality;
        this.city = city;
        this.address = address;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AddressStatus getStatus() {
        return status;
    }

    public void setStatus(AddressStatus status) {
        this.status = status;
    }
}
