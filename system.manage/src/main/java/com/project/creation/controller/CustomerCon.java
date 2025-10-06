package com.project.creation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.creation.DTO.ClaimDto;
import com.project.creation.DTO.UserPolicyDto;
import com.project.creation.DTO.UserProfile;
import com.project.creation.Model.Policy;
import com.project.creation.Model.UserPolicy;
import com.project.creation.Service.CustomerService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "insurance-policy-management.vercel.app")
@RestController
@RequestMapping("/CustomerAccess")
public class CustomerCon {
    
    @Autowired 
    CustomerService customerService;

    @Autowired
    Controller controller;

    @GetMapping("/getUserName")
    public String getusername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/getProfile")
    public UserProfile getProfile() {
        return customerService.gatherProfile(getusername());
    }
    

    @PutMapping("/setProfile")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<String> profileCustomer(@Valid @RequestBody UserProfile profile) {
        return customerService.customerProfile(profile,getusername());
    }

    @PostMapping("/BuyPolicy/{policyId}")
    public ResponseEntity<String> PolicyPurchase(@PathVariable Long policyId) {
        System.out.println(">>> PolicyID"+ policyId);
        return customerService.PurchasePolicy(getusername(), policyId);
        
    }

    @PostMapping("/claim/{userpolicyId}/{Amount}")
    public ResponseEntity<String> policyClaim(@PathVariable Long userpolicyId, @PathVariable Double Amount) {
        return customerService.ClaimPolicy(getusername(),Amount, userpolicyId);
    }

    @GetMapping("/getAllClaims")
    public List<?> getClaims() {
        return customerService.acquireClaims(getusername());
    }
    
    
    @GetMapping("/AllPolicies")
    public List<Policy> getAllPolicies() {
        return customerService.AllPolicies();
    }

    @GetMapping("/MyPolicies")
    public List<?> getMyPolicies() {
        return customerService.myPolicies(getusername());
    }
    
    
    
    
}
