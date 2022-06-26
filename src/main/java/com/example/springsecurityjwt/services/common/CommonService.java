package com.example.springsecurityjwt.services.common;

import com.example.springsecurityjwt.dto.user_check.UserCheckResponse;
import com.example.springsecurityjwt.enums.AddressStatus;
import com.example.springsecurityjwt.enums.ContactStatus;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.address.Address;
import com.example.springsecurityjwt.dto.address.RegisterAddressRequest;
import com.example.springsecurityjwt.dto.address.RegisterAddressResponse;
import com.example.springsecurityjwt.models.contact.Contact;
import com.example.springsecurityjwt.dto.contact.RegisterContactRequest;
import com.example.springsecurityjwt.dto.contact.RegisterContactResponse;
import com.example.springsecurityjwt.repositories.AddressRepository;
import com.example.springsecurityjwt.repositories.ContactRepository;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.dto.profile.ProfileUpdateRequest;
import com.example.springsecurityjwt.dto.profile.ProfileUpdateResponse;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CommonService {
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    public ProfileUpdateResponse updateProfile(ProfileUpdateRequest profileUpdateRequest, HttpServletRequest httpServletRequest) {
        ProfileUpdateResponse profileUpdateResponse = new ProfileUpdateResponse();

        String token = httpServletRequest.getHeader("Authorization").substring(7);

        final String userName = jwtTokenUtil.extractUsername(token);

        User user = userRepository.findUserByEmail(userName);

        try {
            if (profileUpdateRequest.getFirstName().equals(user.getFirstName()) &&
                profileUpdateRequest.getLastName().equals(user.getLastName())) {

                profileUpdateResponse.setMessage("Nothing to update!");
                profileUpdateResponse.setStatus(Boolean.FALSE);
            }
            else {
                if(profileUpdateRequest.getFirstName() != null && !profileUpdateRequest.getFirstName().isEmpty()) {
                    user.setFirstName(profileUpdateRequest.getFirstName());
                }

                if(profileUpdateRequest.getLastName() != null && !profileUpdateRequest.getLastName().isEmpty()) {
                    user.setLastName(profileUpdateRequest.getLastName());
                }

                userRepository.save(user);

                profileUpdateResponse.setMessage("Profile updated successfully!");
                profileUpdateResponse.setStatus(Boolean.TRUE);
            }
        }
        catch (Exception e) {
            profileUpdateResponse.setMessage("Error while updating user's profile.Please contact administrator!");
            profileUpdateResponse.setStatus(Boolean.TRUE);
        }

        return profileUpdateResponse;
    }

    public RegisterAddressResponse registerAddress(RegisterAddressRequest registerAddressRequest, HttpServletRequest httpServletRequest) {
        RegisterAddressResponse registerAddressResponse = new RegisterAddressResponse();

        String token = httpServletRequest.getHeader("Authorization").substring(7);

        final String userName = jwtTokenUtil.extractUsername(token);

        User user = userRepository.findUserByEmail(userName);

        try {
            if(user.getAddress() == null) {
                Address address = new Address(registerAddressRequest.getCountry(), registerAddressRequest.getDistrict(),
                        registerAddressRequest.getMunicipality(), registerAddressRequest.getCity(),
                        registerAddressRequest.getAddress(), AddressStatus.ACTIVE);

                address.setUser(user);
                user.setAddress(address);

                addressRepository.save(address);
                userRepository.save(user);

                registerAddressResponse.setMessage("Address added successfully!");
                registerAddressResponse.setStatus(Boolean.TRUE);
            }
            else {
                Address address = user.getAddress();

                address.setCountry(registerAddressRequest.getCountry());
                address.setDistrict(registerAddressRequest.getDistrict());
                address.setMunicipality(registerAddressRequest.getMunicipality());
                address.setCity(registerAddressRequest.getCity());
                address.setAddress(registerAddressRequest.getAddress());

                addressRepository.save(address);

                registerAddressResponse.setMessage("Address updated successfully!");
                registerAddressResponse.setStatus(Boolean.TRUE);
            }
        }
        catch (Exception e) {
            registerAddressResponse.setMessage("Error while registering user's address.Please contact administrator!");
            registerAddressResponse.setStatus(Boolean.TRUE);
        }

        return registerAddressResponse;

    }

    public RegisterContactResponse registerContact(RegisterContactRequest registerContactRequest, HttpServletRequest httpServletRequest) {
        RegisterContactResponse registerContactResponse = new RegisterContactResponse();

        String token = httpServletRequest.getHeader("Authorization").substring(7);

        final String userName = jwtTokenUtil.extractUsername(token);

        User user = userRepository.findUserByEmail(userName);

        try {
            if(user.getContact() == null) {
                Contact contact = new Contact(registerContactRequest.getWebsite(), registerContactRequest.getMobile(),
                        registerContactRequest.getFax(), registerContactRequest.getAltEmail(), ContactStatus.ACTIVE);

                contact.setUser(user);
                user.setContact(contact);

                contactRepository.save(contact);
                userRepository.save(user);

                registerContactResponse.setMessage("Contact added successfully!");
                registerContactResponse.setStatus(Boolean.TRUE);
            }
            else {
                Contact contact = user.getContact();

                contact.setWebsite(registerContactRequest.getWebsite());
                contact.setMobile(registerContactRequest.getMobile());
                contact.setFax(registerContactRequest.getFax());
                contact.setAltEmail(registerContactRequest.getAltEmail());

                contactRepository.save(contact);

                registerContactResponse.setMessage("Contact updated successfully!");
                registerContactResponse.setStatus(Boolean.TRUE);
            }
        }
        catch (Exception e) {
            registerContactResponse.setMessage("Error while registering user's contact.Please contact administrator!");
            registerContactResponse.setStatus(Boolean.FALSE);
        }
        return registerContactResponse;
    }

    public UserCheckResponse checkUserRole(HttpServletRequest httpServletRequest) {
        UserCheckResponse userCheckResponse = new UserCheckResponse();

        try {
            String token = httpServletRequest.getHeader("Authorization").substring(7);
            final String userName = jwtTokenUtil.extractUsername(token);

            User user = userRepository.findUserByEmail(userName);

            userCheckResponse.setRole(user.getRoles().get(0).getRole());
            userCheckResponse.setEmail(user.getEmail());

        } catch (Exception e) {

        }

        return userCheckResponse;
    }
}
