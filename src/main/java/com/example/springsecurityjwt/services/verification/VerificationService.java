package com.example.springsecurityjwt.services.verification;

import com.example.springsecurityjwt.enums.UserStatus;
import com.example.springsecurityjwt.enums.TokenStatus;
import com.example.springsecurityjwt.dto.email.verification.MailResendVerificationTokenRequest;
import com.example.springsecurityjwt.mail.service.MailService;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.verification.VerificationToken;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.repositories.VerificationTokenRepository;
import com.example.springsecurityjwt.dto.verification.ResendVerificationTokenRequest;
import com.example.springsecurityjwt.dto.verification.VerificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VerificationService {
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    public VerificationResponse verifyUser(String token) throws Exception {
        VerificationResponse response = new VerificationResponse();

        //Check if token exists
        if(verificationTokenRepository.findByToken(token) != null &&
                verificationTokenRepository.findByToken(token).getStatus().equals(TokenStatus.ACTIVE)) {
            VerificationToken verToken = verificationTokenRepository.findByToken(token);
            User user = verToken.getUser();

            if (user.getUserStatus().equals(UserStatus.VERIFIED)) {
                response.setMessage("User with email: " + user.getEmail() + " has already been verified!");
                response.setStatus(Boolean.FALSE);
            }
            else {
                try {
                    //Setting up dates for comparison
                    Date expirationDate = verToken.getExpiryDate();
                    Date date = new Date();
                    Timestamp todayDate=new Timestamp(date.getTime());
                    //Setting up dates for comparison

                    if(todayDate.before(expirationDate)) {
                        user.setUserStatus(UserStatus.VERIFIED);
                        userRepository.save(user);

                        verToken.setStatus(TokenStatus.INACTIVE);
                        verificationTokenRepository.save(verToken);

                        response.setMessage("User with email: " + user.getEmail() + " verified successfully!");
                        response.setStatus(Boolean.TRUE);
                    }
                    else {
                        response.setMessage("Verification token has expired! Consider sending request for issuing another verification token!");
                        response.setStatus(Boolean.FALSE);
                    }

                }
                catch (Exception e) {
                    response.setMessage("Verification failure : "+e.getMessage());
                    response.setStatus(Boolean.FALSE);
                }
            }
        }
        else {
            response.setMessage("Specified token does not exist!");
            response.setStatus(Boolean.FALSE);
        }
        //Check if token exists

        return response;
    }

    public VerificationResponse resendToken(ResendVerificationTokenRequest resendVerificationTokenRequest){
        VerificationResponse response = new VerificationResponse();

        //Setting up dates for comparison
        Date date = new Date();
        Timestamp todayDate=new Timestamp(date.getTime());
        //Setting up dates for comparison

        if(userRepository.findUserByEmail(resendVerificationTokenRequest.getEmail()).getUserStatus().equals(UserStatus.VERIFIED)){
            response.setMessage("Cannot resend verification token - user already verified. " +
                    "Consider sending a reset password request!");
            response.setStatus(Boolean.FALSE);
        }
        else if (verificationTokenRepository.findByUser(userRepository.findUserByEmail(resendVerificationTokenRequest.getEmail()))
                .getStatus().equals(TokenStatus.ACTIVE) &&
                todayDate.before(verificationTokenRepository.findByUser(userRepository.findUserByEmail(resendVerificationTokenRequest.getEmail()))
                .getExpiryDate())) {
            response.setMessage("Cannot resend verification token - token is active. " +
                    "Check your email and complete the verification request!");
            response.setStatus(Boolean.FALSE);
        }
        else {
            try {
                VerificationToken oldToken = verificationTokenRepository.findByUser
                        (userRepository.findUserByEmail(resendVerificationTokenRequest.getEmail()));

                //Deleting the old token and issuing new
                verificationTokenRepository.delete(oldToken);

                User user = userRepository.findUserByEmail(resendVerificationTokenRequest.getEmail());
                String token = UUID.randomUUID().toString();
                VerificationToken userToken = new VerificationToken(token, user);
                verificationTokenRepository.save(userToken);

                MailResendVerificationTokenRequest emailRequest = new MailResendVerificationTokenRequest(user.getEmail(),
                        "todor@test.bg", "Resent Verification Token Test Email", user.getFirstName(), user.getLastName());

                Map<String, Object> model = new HashMap<>();

                model.put("firstName", user.getFirstName());
                model.put("lastName", user.getLastName());
                model.put("email", user.getEmail());
                model.put("verificationToken", token);

                mailService.resendVerificationEmail(emailRequest, model);

                response.setMessage("Verification token resent successfully! Proceed to your email for completing the registration process!");
                response.setStatus(Boolean.TRUE);

            }
            catch (Exception e) {
                response.setMessage("Error while resending verification token! Please contact an administrator!");
                response.setStatus(Boolean.FALSE);
            }
        }

        return response;
    }
}
