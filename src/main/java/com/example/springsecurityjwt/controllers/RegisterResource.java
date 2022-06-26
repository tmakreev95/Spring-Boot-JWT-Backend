package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.dto.register.RegisterRequest;
import com.example.springsecurityjwt.dto.register.RegisterResponse;
import com.example.springsecurityjwt.services.register.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterResource {
    @Autowired
    public RegisterUserService userService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        return userService.registerUser(registerRequest);
    }

}
