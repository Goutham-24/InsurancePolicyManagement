package com.project.creation.DTO;

import java.time.LocalDate;

import com.project.creation.Enum.PolicyStatus;
import com.project.creation.Model.Policy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPolicyDto {
    private Long userPolicyId;
    private Policy policy;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private PolicyStatus status;
}
