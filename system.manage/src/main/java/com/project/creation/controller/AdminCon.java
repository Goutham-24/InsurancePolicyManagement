package com.project.creation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.creation.DTO.PolicyDetails;
import com.project.creation.Model.Policy;
import com.project.creation.Model.User;
import com.project.creation.Service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/AdminAccess")
public class AdminCon {
    @Autowired
    AdminService adminService;

    @GetMapping("/CustomerToAgent/{CustomerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> CustomerConversion(@PathVariable Long CustomerId) {
        return adminService.CustomerToAgent(CustomerId);
    }

    @GetMapping("/AgentToAdmin/{AgentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> AgentConversion(@PathVariable Long AgentId) {
        return adminService.AgentToAdmin(AgentId);
    }

    @PostMapping("/AddPolicy")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> newPolicy(@RequestBody PolicyDetails policyDetails) {
        return adminService.addPolicy(policyDetails);
    }
    
}
