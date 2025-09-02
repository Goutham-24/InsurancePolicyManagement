package com.project.creation.Service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.creation.DTO.PolicyDetails;
import com.project.creation.Enum.PolicyStatus;
import com.project.creation.Model.Policy;
import com.project.creation.Model.User;
import com.project.creation.Repository.PolicyRepository;
import com.project.creation.Repository.UserRepsitory;

@Service
public class AdminService {
    
    @Autowired
    UserRepsitory userrepo;

    @Autowired
    PolicyRepository policyrepo;

    public ResponseEntity<String> CustomerToAgent(Long CustomerId) {
        User user = userrepo.findById(CustomerId).orElseThrow(() -> new RuntimeException("User not found"));
        if("ROLE_CUSTOMER".equals(user.getRole())) {
            user.setRole("ROLE_AGENT");
            userrepo.save(user);
            return ResponseEntity.ok("User role updated to AGENT");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a CUSTOMER");
        }
    }

    public ResponseEntity<String> AgentToAdmin(Long AgentId){
        User agent = userrepo.findById(AgentId).orElseThrow(() -> new RuntimeException("User not found"));
        if("ROLE_AGENT".equals(agent.getRole())){
            agent.setRole("ROLE_ADMIN");
            userrepo.save(agent);
        } else {
            throw new RuntimeException("User is not an AGENT");
        }

        return ResponseEntity.ok("Agent updated to ADMIN");
    }

    public ResponseEntity<String> addPolicy(PolicyDetails policyDetails){
        Policy policy = Policy.builder()
                            .policyName(policyDetails.getPolicyName())
                            .premiumAmount(policyDetails.getPremiumAmount())
                            .validFrom(LocalDate.now())
                            .validTo(LocalDate.parse(policyDetails.getValidTo()))
                            .userPolicyValidity(policyDetails.getUserPolicyValidity())
                            .status(PolicyStatus.ACTIVE)
                            .build();
        policyrepo.save(policy);
        return ResponseEntity.ok("Policy added successfully");
    }
}
