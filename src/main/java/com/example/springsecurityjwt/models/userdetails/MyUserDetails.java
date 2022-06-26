package com.example.springsecurityjwt.models.userdetails;


import com.example.springsecurityjwt.enums.UserStatus;
import com.example.springsecurityjwt.models.Role;
import com.example.springsecurityjwt.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

public class MyUserDetails implements UserDetails, Serializable {
    private static final long serialVersionUID = 1234556L;

    private UserStatus userStatus;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<GrantedAuthority> authorities;

    public MyUserDetails(User user){
        //Getting joined roles to authorities class field
        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        for(Role role: user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        //Getting joined roles to authorities class field

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = roles;
        /* In Case of single role at user
        this.authorities = Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        */
        this.userStatus = user.getUserStatus();
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

