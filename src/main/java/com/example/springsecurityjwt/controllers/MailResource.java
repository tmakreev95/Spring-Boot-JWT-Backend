package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.dto.email.MailRequest;
import com.example.springsecurityjwt.dto.email.MailResponse;
import com.example.springsecurityjwt.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MailResource {
    @Autowired
    private MailService mailService;

    @RequestMapping(value="/send-email",method = RequestMethod.POST)
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "Test,Test ");
        return mailService.sendEmail(request, model);
    }
}
