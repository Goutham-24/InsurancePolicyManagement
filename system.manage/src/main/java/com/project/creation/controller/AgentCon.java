package com.project.creation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.creation.DTO.ClaimDto;
import com.project.creation.Model.Claim;
import com.project.creation.Service.AgentService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/AgentAccess")
public class AgentCon {
    @Autowired
    AgentService agentService;

    @GetMapping("/getClaims")
    public List<?> getCustomerClaims() {
        return agentService.customerClaims();
    }

    @PutMapping("/ClaimApproval/{claimId}")
    public ResponseEntity<String> updateAgentApproval(@PathVariable Long claimId) {
        return agentService.agentApprove(claimId);
    }

    @PutMapping("/ClaimDenial/{claimId}")
    public ResponseEntity<String> updateAgentRejection(@PathVariable Long claimId) {
        return agentService.agentReject(claimId);
    }
    
}
