package com.example.springsecurityjwt.services.register;

import com.example.springsecurityjwt.enums.UserStatus;
import com.example.springsecurityjwt.dto.email.registration.MailRegistrationRequest;
import com.example.springsecurityjwt.mail.service.MailService;
import com.example.springsecurityjwt.models.Role;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.verification.VerificationToken;
import com.example.springsecurityjwt.dto.register.RegisterRequest;
import com.example.springsecurityjwt.dto.register.RegisterResponse;
import com.example.springsecurityjwt.repositories.RoleRepository;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegisterUserService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    public MailService mailService;

    public RegisterResponse registerUser(RegisterRequest registerRequest) throws Exception{
        RegisterResponse response = new RegisterResponse();

        Role userRole = roleRepository.findByRole("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);

        if(userRepository.findExistingUserByEmail(registerRequest.getEmail())) {
            response.setMessage("User with email(" + registerRequest.getEmail() + ") exists!");
            response.setStatus(Boolean.FALSE);
        }
        else {
            try {
                //Creating user and setting verification token
                User user = new User(registerRequest.getFirstName(), registerRequest.getLastName(),
                        registerRequest.getEmail(),encoder.encode(registerRequest.getPassword()),
                        UserStatus.NOT_VERIFIED, roles);

                userRepository.save(user);

                String token = UUID.randomUUID().toString();
                VerificationToken userToken = new VerificationToken(token, user);
                tokenRepository.save(userToken);
                //Creating user and setting verification token

                //Sending notification email to the registered user
                MailRegistrationRequest emailRegistrationRequest = new MailRegistrationRequest(registerRequest.getEmail(),
                        "todor@test.bg", registerRequest.getFirstName(), registerRequest.getLastName(),
                        "Registration Test Email");

                Map<String, Object> model = new HashMap<>();

                model.put("firstName", registerRequest.getFirstName());
                model.put("lastName", registerRequest.getLastName());
                model.put("email", registerRequest.getEmail());
                model.put("verificationToken", token);

                mailService.sendRegistrationEmail(emailRegistrationRequest, model);
                //Sending notification email to the registered user

                response.setMessage("User with email (" + registerRequest.getEmail() + ") created. Check the corresponding email to complete the registration process!");
                response.setStatus(Boolean.TRUE);

            } catch (Exception e) {
                response.setMessage("Registration failure : "+e.getMessage());
                response.setStatus(Boolean.FALSE);
            }
        }

        return response;
    }


}
