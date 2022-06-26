package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.dto.password_reset.PasswordResetSaveRequest;
import com.example.springsecurityjwt.dto.password_reset.PasswordResetTokenValidationRequest;
import com.example.springsecurityjwt.services.password_reset.PasswordResetService;
import com.example.springsecurityjwt.dto.password_reset.PasswordResetRequest;
import com.example.springsecurityjwt.dto.password_reset.PasswordResetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PasswordResetResource {
    @Autowired
    PasswordResetService passwordResetService;

    @RequestMapping(value="/password-reset", method = RequestMethod.POST)
    public PasswordResetResponse requestPasswordResetToken(@RequestBody PasswordResetRequest passwordResetRequest) {
        return passwordResetService.passwordResetRequest(passwordResetRequest);
    }

    @RequestMapping(value = "/password-reset/verify-token", method = RequestMethod.POST)
    public PasswordResetResponse passwordResetSavePassword(@RequestBody PasswordResetTokenValidationRequest passwordResetTokenValidationRequest) {
        return passwordResetService.passwordResetValidate(passwordResetTokenValidationRequest);
    }

    @RequestMapping(value = "/password-reset/verify-token/save-password", method = RequestMethod.POST)
    public PasswordResetResponse passwordResetSavePassword(@RequestBody PasswordResetSaveRequest passwordResetSaveRequest) {
        return passwordResetService.passwordResetSave(passwordResetSaveRequest);
    }



}
