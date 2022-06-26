package com.example.springsecurityjwt.services;

import com.example.springsecurityjwt.bfd.service.LoginAttemptService;
import com.example.springsecurityjwt.enums.UserStatus;
import com.example.springsecurityjwt.models.address.Address;
import com.example.springsecurityjwt.models.contact.Contact;
import com.example.springsecurityjwt.models.userdetails.MyUserDetails;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.dto.userdetails.UserDetailsResponse;
import com.example.springsecurityjwt.repositories.AddressRepository;
import com.example.springsecurityjwt.repositories.ContactRepository;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        Optional<User> user = userRepository.findByEmail(email);

        //Option 1 for status
        if(user.get().getUserStatus().equals(UserStatus.NOT_VERIFIED)) {
            user = null;
        }
        //Option 1 for status

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return user.map(MyUserDetails::new).get();
    }

    public UserDetailsResponse userDetailsReport(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        final String userName = jwtTokenUtil.extractUsername(token);

        final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(userName);

        return new UserDetailsResponse(userDetails.getUsername(), userDetails.getFirstName(),
                userDetails.getLastName(), userDetails.getUserStatus());
    }

    public ResponseEntity<Address> userAddressReport(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        final String userName = jwtTokenUtil.extractUsername(token);

        return new ResponseEntity(addressRepository.findByUser(userName), HttpStatus.OK);
    }

    public ResponseEntity<Contact> userContactReport(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        final String userName = jwtTokenUtil.extractUsername(token);

        return new ResponseEntity(contactRepository.findByUser(userName), HttpStatus.OK);
    }

    private String getClientIP() {
        String xfHeader = httpServletRequest.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return httpServletRequest.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}



