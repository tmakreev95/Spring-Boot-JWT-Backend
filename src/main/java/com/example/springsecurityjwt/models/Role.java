package com.example.springsecurityjwt.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auth_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1233456L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auth_role_id")
    private Long id;

    @Column(name = "role_name")
    private String role;

    @Column(name = "role_desc")
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}