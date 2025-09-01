package com.project.creation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.creation.DTO.userProfile;
import com.project.creation.Service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/CustomerAccess")
public class CustomerCon {
    
    @Autowired 
    CustomerService customerService;

    @Autowired
    Controller controller;

    @PostMapping("/Profile")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<String> profileCustomer(@RequestBody userProfile profile) {
        return customerService.customerProfile(profile,controller.getusername());
    }
    
}
