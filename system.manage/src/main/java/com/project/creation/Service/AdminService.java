package com.project.creation.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.creation.DTO.ClaimDto;
import com.project.creation.DTO.PolicyDetails;
import com.project.creation.Enum.ApprovalStatus;
import com.project.creation.Enum.PolicyStatus;
import com.project.creation.Exceptions.ClaimNotFoundException;
import com.project.creation.Exceptions.UserNotFoundException;
import com.project.creation.Model.Claim;
import com.project.creation.Model.Policy;
import com.project.creation.Model.User;
import com.project.creation.Repository.ClaimRepository;
import com.project.creation.Repository.PolicyRepository;
import com.project.creation.Repository.UserRepsitory;

@Service
public class AdminService {
    
    @Autowired
    UserRepsitory userrepo;

    @Autowired
    PolicyRepository policyrepo;

    @Autowired
    ClaimRepository claimrepo;

    public ResponseEntity<String> CustomerToAgent(Long CustomerId) {
        User user = userrepo.findById(CustomerId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if("ROLE_CUSTOMER".equals(user.getRole())) {
            user.setRole("ROLE_AGENT");
            userrepo.save(user);
            return ResponseEntity.ok("User role updated to AGENT");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a CUSTOMER");
        }
    }

    public ResponseEntity<String> AgentToAdmin(Long AgentId){
        User agent = userrepo.findById(AgentId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if("ROLE_AGENT".equals(agent.getRole())){
            agent.setRole("ROLE_ADMIN");
            userrepo.save(agent);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a Agent");
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

    public List<?> totalClaims(){
        List<Claim> claims = claimrepo.AdminClaimView();

        if(claims.isEmpty()){
            return claims;
        }

        List<ClaimDto> claimDtos = new ArrayList<>();
        for(Claim cdto : claims){
            ClaimDto newClaim = ClaimDto.builder()
                            .claimId(cdto.getClaimId())
                            .userEmailId(cdto.getUserPolicy().getUser().getUserEmailId())
                            .policyName(cdto.getUserPolicy().getPolicy().getPolicyName())
                            .claimAmount(cdto.getClaimAmount())
                            .claimDate(cdto.getClaimDate())
                            .adminApproval(cdto.getAdminApproval())
                            .build();

            claimDtos.add(newClaim);                
        }

        return claimDtos;

    }

    public ResponseEntity<String> ApproveClaim(Long Id){
        Claim updateClaim = claimrepo.findById(Id).orElseThrow(()-> new ClaimNotFoundException("Claim not found"));
        updateClaim.setAdminApproval(ApprovalStatus.APPROVED);
        updateClaim.setFinalStatus(ApprovalStatus.APPROVED);

        claimrepo.save(updateClaim);
        return ResponseEntity.ok("Approved Claim");
    }

    public ResponseEntity<String> RejectClaim(Long Id){
         Claim updateClaim = claimrepo.findById(Id).orElseThrow(()-> new ClaimNotFoundException("Claim not found"));
        updateClaim.setAdminApproval(ApprovalStatus.REJECTED);
        updateClaim.setFinalStatus(ApprovalStatus.REJECTED);

        claimrepo.save(updateClaim);
        return ResponseEntity.ok("Rejected Claim");
    }

}
