package com.example.springsecurityjwt.admin.user.delete.service;

import com.example.springsecurityjwt.admin.user.delete.dto.DeleteUserRequest;
import com.example.springsecurityjwt.admin.user.delete.dto.DeleteUserResponse;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.verification.VerificationToken;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) {
        DeleteUserResponse response = new DeleteUserResponse();

        User user = userRepository.findUserByEmail(deleteUserRequest.getEmail());

        if (user == null) {
            response.setMessage("User with the provided email does not exist!");
            response.setStatus(Boolean.FALSE);
        }
        else {
            try {
                VerificationToken oldToken = verificationTokenRepository.findByUser(user);

                if(oldToken != null) {
                    verificationTokenRepository.delete(oldToken);
                }

                userRepository.delete(user);

                response.setMessage("User with email: "+deleteUserRequest.getEmail()+" deleted successfully!");
                response.setStatus(Boolean.TRUE);
            }
            catch (Exception e) {
                response.setMessage("Error deleting user with email: " +deleteUserRequest.getEmail());
                response.setStatus(Boolean.FALSE);
            }
        }

        return response;
    }

}
