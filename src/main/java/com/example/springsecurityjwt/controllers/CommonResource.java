package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.dto.address.RegisterAddressRequest;
import com.example.springsecurityjwt.dto.address.RegisterAddressResponse;
import com.example.springsecurityjwt.dto.contact.RegisterContactRequest;
import com.example.springsecurityjwt.dto.contact.RegisterContactResponse;
import com.example.springsecurityjwt.dto.user_check.UserCheckResponse;
import com.example.springsecurityjwt.services.common.CommonService;
import com.example.springsecurityjwt.dto.profile.ProfileUpdateRequest;
import com.example.springsecurityjwt.dto.profile.ProfileUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommonResource {
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/user/profile/update", method = RequestMethod.PUT)
    public ProfileUpdateResponse profileUpdate(@RequestBody ProfileUpdateRequest profileUpdateRequest, HttpServletRequest httpServletRequest) {
        return commonService.updateProfile(profileUpdateRequest, httpServletRequest);
    }

    @RequestMapping(value = "/user/profile/register/address", method = RequestMethod.POST)
    public RegisterAddressResponse registerAddress(@RequestBody RegisterAddressRequest registerAddressRequest, HttpServletRequest httpServletRequest) {
        return commonService.registerAddress(registerAddressRequest, httpServletRequest);
    }

    @RequestMapping(value = "/user/profile/register/contact", method = RequestMethod.POST)
    public RegisterContactResponse registerContact(@RequestBody RegisterContactRequest registerContactRequest, HttpServletRequest httpServletRequest) {
        return commonService.registerContact(registerContactRequest, httpServletRequest);
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.POST)
    public UserCheckResponse checkUserRole(HttpServletRequest httpServletRequest) {
        return commonService.checkUserRole(httpServletRequest);
    }




}
