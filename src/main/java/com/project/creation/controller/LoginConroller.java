package com.project.creation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.creation.DTO.UserCredential;
import com.project.creation.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Authentication")
public class LoginConroller {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> userSignup(@RequestBody UserCredential credentials) {
        return userService.signupUser(credentials);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserCredential credentials) {
        System.out.println(">>> Inside /Authentication/login");
        return userService.loginUser(credentials);
        

    }
    
}
