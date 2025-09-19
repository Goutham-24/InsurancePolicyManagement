package com.project.creation.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.creation.DTO.ClaimDto;
import com.project.creation.Enum.ApprovalStatus;
import com.project.creation.Exceptions.ClaimNotFoundException;
import com.project.creation.Model.Claim;
import com.project.creation.Repository.ClaimRepository;

@Service
public class AgentService {
    @Autowired
    ClaimRepository claimrepo;

    public List<?> customerClaims() {
        List<Claim> claims = claimrepo.AgentClaimView();

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
                            .agentApproval(cdto.getAgentApproval())
                            .build();

            claimDtos.add(newClaim);                
        }

        return claimDtos;
    }

    public ResponseEntity<String> agentApprove(Long claimId){
        Claim claim = claimrepo.findById(claimId).orElseThrow(() -> new ClaimNotFoundException("Claim not found"));
        claim.setAgentApproval(ApprovalStatus.APPROVED);
        claimrepo.save(claim);
        return ResponseEntity.ok("Claim approved by agent");
    }

    public ResponseEntity<String> agentReject(Long claimId){
        Claim claim = claimrepo.findById(claimId).orElseThrow(() -> new ClaimNotFoundException("Claim not found"));
        claim.setAgentApproval(ApprovalStatus.REJECTED);
        claimrepo.save(claim);
        return ResponseEntity.ok("Claim approved by agent");
    }
    
}
