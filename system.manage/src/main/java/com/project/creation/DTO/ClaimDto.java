package com.project.creation.DTO;

import java.time.LocalDate;

import com.project.creation.Enum.ApprovalStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClaimDto {
    private Long claimId;
    private String userEmailId;
    private String policyName;
    private LocalDate claimDate;
    private Double claimAmount;
    private ApprovalStatus agentApproval;
    private ApprovalStatus adminApproval;
    private ApprovalStatus finalStatus; 
}
