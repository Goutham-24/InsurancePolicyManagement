package com.project.creation.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.creation.DTO.UserCredential;
import com.project.creation.JWT.JwtUtil;
import com.project.creation.Model.User;
import com.project.creation.Repository.UserRepsitory;


@Service
public class UserService{
    @Autowired
    UserRepsitory userrepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signupUser(UserCredential credentials) {

        if(credentials.getUserEmailId() == null || credentials.getUserPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password must be provided");
        }

        else if(userrepo.findByUserEmailId(credentials.getUserEmailId()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
        }

        User userdetails= User.builder()
                                .userEmailId(credentials.getUserEmailId())
                                .userPassword(passwordEncoder.encode(credentials.getUserPassword()))
                                .role("ROLE_CUSTOMER")
                                .build();

        userrepo.save(userdetails);
        return ResponseEntity.ok("User signed up successfully");
    }

    public ResponseEntity<String> loginUser(UserCredential credentials){
        System.out.println(">>> loginUser called with: " + credentials.getUserEmailId());

        if(credentials.getUserEmailId() == null || credentials.getUserPassword() == null){
            System.out.println(">>> Missing email or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password must be provided");
        }
        else{
            Optional<User> userdets= userrepo.findByUserEmailId(credentials.getUserEmailId());
            System.out.println(">>> START");
            if(userdets.isPresent() && passwordEncoder.matches(credentials.getUserPassword(), userdets.get().getUserPassword())){
                System.out.println(">>> BEFORE TOKEN GENERATION");
                String token = JwtUtil.generateToken(userdets.get().getUserEmailId(),userdets.get().getRole()); 
                System.out.println(">>> User authenticated successfully, token generated");
                return ResponseEntity.ok(token);
            }
        }

        System.out.println(">>> Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    
}
