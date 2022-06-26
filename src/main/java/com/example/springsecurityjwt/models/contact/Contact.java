package com.example.springsecurityjwt.models.contact;

import com.example.springsecurityjwt.enums.ContactStatus;
import com.example.springsecurityjwt.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name="contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1234567891010L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "website", length = 255)
    private String website;

    @Column(name = "mobile", length = 20)
    private String mobile;

    @Column(name = "fax", length = 10)
    private String fax;

    @Email
    @Column(name = "alt_email", length = 50)
    private String altEmail;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auth_user_id", nullable = false)
    private User user;

    @Column(name = "status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactStatus status;

    public Contact() {
    }

    public Contact(String website, String mobile, String fax, @Email String altEmail, ContactStatus status) {
        this.website = website;
        this.mobile = mobile;
        this.fax = fax;
        this.altEmail = altEmail;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContactStatus getStatus() {
        return status;
    }

    public void setStatus(ContactStatus status) {
        this.status = status;
    }
}
