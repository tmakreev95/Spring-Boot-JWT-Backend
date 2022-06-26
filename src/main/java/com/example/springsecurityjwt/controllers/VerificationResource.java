package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.dto.verification.ResendVerificationTokenRequest;
import com.example.springsecurityjwt.dto.verification.VerificationResponse;
import com.example.springsecurityjwt.services.verification.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VerificationResource {

    @Autowired
    VerificationService verificationService;

    @RequestMapping(value="/verify-token/{token}", method = RequestMethod.GET)
    public VerificationResponse verifyUser(@PathVariable String token) throws Exception {
        return verificationService.verifyUser(token);
    }

    @RequestMapping(value="/resend-verification-token", method = RequestMethod.POST)
    public VerificationResponse resendToken(@RequestBody ResendVerificationTokenRequest resendRequest) throws Exception {
        return verificationService.resendToken(resendRequest);
    }

}
