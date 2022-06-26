package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.models.address.Address;
import com.example.springsecurityjwt.dto.authentication.AuthenticationRequest;
import com.example.springsecurityjwt.dto.authentication.AuthenticationResponse;
import com.example.springsecurityjwt.models.contact.Contact;
import com.example.springsecurityjwt.dto.userdetails.UserDetailsResponse;
import com.example.springsecurityjwt.services.MyUserDetailsService;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    //UserDetails
    @RequestMapping(value = "/user/profile", method = RequestMethod.POST)
    public UserDetailsResponse userDetailReport(HttpServletRequest httpServletRequest) {
        return userDetailsService.userDetailsReport(httpServletRequest);
    }

    //Address Details
    @RequestMapping(value = "/user/profile/address", method = RequestMethod.POST)
    public ResponseEntity<Address> userAddressReport(HttpServletRequest httpServletRequest) {
        return userDetailsService.userAddressReport(httpServletRequest);
    }

    //Address Details
    @RequestMapping(value = "/user/profile/contact", method = RequestMethod.POST)
    public ResponseEntity<Contact> userContactReport(HttpServletRequest httpServletRequest) {
        return userDetailsService.userContactReport(httpServletRequest);
    }

}
