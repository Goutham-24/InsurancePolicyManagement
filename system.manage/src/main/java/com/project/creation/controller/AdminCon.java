package com.project.creation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.creation.DTO.ClaimDto;
import com.project.creation.DTO.PolicyDetails;
import com.project.creation.Model.Policy;
import com.project.creation.Model.User;
import com.project.creation.Service.AdminService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@CrossOrigin(origins = "insurance-policy-management.vercel.app")
@RestController
@RequestMapping("/AdminAccess")
public class AdminCon {
    @Autowired
    AdminService adminService;

    @PutMapping("/CustomerToAgent/{CustomerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> CustomerConversion(@PathVariable Long CustomerId) {
        return adminService.CustomerToAgent(CustomerId);
    }

    @PutMapping("/AgentToAdmin/{AgentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> AgentConversion(@PathVariable Long AgentId) {
        return adminService.AgentToAdmin(AgentId);
    }

    @PostMapping("/AddPolicy")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> newPolicy(@Valid @RequestBody PolicyDetails policyDetails) {
        System.out.println(">>> admincall");
        return adminService.addPolicy(policyDetails);
    }
    
    @GetMapping("/getAllClaims")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<?> getAllClaims() {
        return adminService.totalClaims();
    }
    
    @PutMapping("/claim-Approval/{Id}")
    public ResponseEntity<String> ClaimApproval(@PathVariable Long Id) {
        return adminService.ApproveClaim(Id);
    }

    @PutMapping("/claim-Reject/{Id}")
    public ResponseEntity<String> ClaimReject(@PathVariable Long Id) {
        return adminService.RejectClaim(Id);
    }
}
