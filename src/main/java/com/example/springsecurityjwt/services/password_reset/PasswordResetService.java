package com.example.springsecurityjwt.services.password_reset;

import com.example.springsecurityjwt.dto.email.password_reset.MailPasswordResetTokenRequest;
import com.example.springsecurityjwt.dto.password_reset.PasswordResetSaveRequest;
import com.example.springsecurityjwt.dto.password_reset.PasswordResetTokenValidationRequest;
import com.example.springsecurityjwt.enums.TokenStatus;
import com.example.springsecurityjwt.mail.service.MailService;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.password_reset.PasswordResetToken;
import com.example.springsecurityjwt.repositories.PasswordResetTokenRepository;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.dto.password_reset.PasswordResetRequest;
import com.example.springsecurityjwt.dto.password_reset.PasswordResetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public MailService mailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public PasswordResetResponse passwordResetRequest(PasswordResetRequest passwordResetRequest) {
        PasswordResetResponse passwordResetResponse = new PasswordResetResponse();

        String email = passwordResetRequest.getEmail();
        String token = UUID.randomUUID().toString();
        try {
            if (!userRepository.findExistingUserByEmail(email)) {
                passwordResetResponse.setMessage("The specified email does not exists! Check your email before proceeding!");
                passwordResetResponse.setStatus(Boolean.FALSE);
            }
            else {
                User user = userRepository.findUserByEmail(email);

                if(passwordResetTokenRepository.findByUser(user) == null ||
                        passwordResetTokenRepository.findByUser(user).getStatus() == TokenStatus.INACTIVE) {
                    PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
                    passwordResetTokenRepository.save(passwordResetToken);

                    MailPasswordResetTokenRequest mailPasswordResetTokenRequest = new MailPasswordResetTokenRequest(email,
                            "todor@test.bg","Password Reset Email", user.getFirstName(), user.getLastName());

                    Map<String, Object> model = new HashMap<>();

                    model.put("firstName", user.getFirstName());
                    model.put("lastName", user.getLastName());
                    model.put("email", email);
                    model.put("passwordResetToken", token);

                    mailService.sendPasswordResetEmail(mailPasswordResetTokenRequest, model);


                    passwordResetResponse.setMessage("Password Reset Token issued successfully!An email has been sent to " +email+ "It will expire in 24 hours!");
                    passwordResetResponse.setStatus(Boolean.TRUE);
                }


                else {
                    passwordResetResponse.setMessage("Password Reset Token already issued! Check your email and complete the password reset process!");
                    passwordResetResponse.setStatus(Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            passwordResetResponse.setMessage("Internal error occurred! Please contact administrator!");
            passwordResetResponse.setStatus(Boolean.FALSE);
        }

        return passwordResetResponse;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public PasswordResetResponse passwordResetValidate(PasswordResetTokenValidationRequest passwordResetTokenValidationRequest) {
        PasswordResetResponse passwordResetResponse = new PasswordResetResponse();

        try{
            if(passwordResetTokenRepository.findByToken(passwordResetTokenValidationRequest.getToken()) != null
                    && passwordResetTokenRepository.findByToken(passwordResetTokenValidationRequest.getToken())
                    .getStatus().equals(TokenStatus.ACTIVE)) {

                if(!isTokenExpired(passwordResetTokenRepository.findByToken(passwordResetTokenValidationRequest.getToken()))){
                   passwordResetResponse.setMessage("Password reset token found and non-expired!");
                   passwordResetResponse.setStatus(Boolean.TRUE);
                }
                else {
                    passwordResetResponse.setMessage("Password reset token expired!");
                    passwordResetResponse.setStatus(Boolean.FALSE);
                }
            }
            else {
                passwordResetResponse.setMessage("Password reset token not found!");
                passwordResetResponse.setStatus(Boolean.FALSE);
            }
        }
        catch (Exception e) {
            passwordResetResponse.setMessage("Error while trying to reset password!");
            passwordResetResponse.setStatus(Boolean.FALSE);
        }

        return passwordResetResponse;
    }

    public PasswordResetResponse passwordResetSave(PasswordResetSaveRequest passwordResetSaveRequest) {
        PasswordResetResponse passwordResetResponse = new PasswordResetResponse();

        try{
            if(passwordResetTokenRepository.findByToken(passwordResetSaveRequest.getToken()) != null
            && passwordResetTokenRepository.findByToken(passwordResetSaveRequest.getToken())
                    .getStatus().equals(TokenStatus.ACTIVE)) {
                final PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(passwordResetSaveRequest.getToken());
                User user = passwordResetToken.getUser();

                if(!isTokenExpired(passwordResetToken)) {
                    user.setPassword(bCryptPasswordEncoder.encode(passwordResetSaveRequest.getPassword()));
                    userRepository.save(user);

                    //passwordResetToken.setStatus(TokenStatus.INACTIVE);
                    passwordResetTokenRepository.delete(passwordResetToken);

                    passwordResetResponse.setMessage("Password successfully reset!");
                    passwordResetResponse.setStatus(Boolean.TRUE);
                }
                else {
                    passwordResetResponse.setMessage("Password reset token expired!");
                    passwordResetResponse.setStatus(Boolean.FALSE);
                }
            }
            else {
                passwordResetResponse.setMessage("Password reset token not found!");
                passwordResetResponse.setStatus(Boolean.FALSE);
            }
        }
        catch (Exception e) {
            passwordResetResponse.setMessage("Error while trying to reset password!");
            passwordResetResponse.setStatus(Boolean.FALSE);
        }

        return passwordResetResponse;
    }

}
