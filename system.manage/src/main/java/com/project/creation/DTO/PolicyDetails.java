package com.project.creation.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyDetails {
    private String policyName;
    private Double premiumAmount;
    private String validTo;
    private int userPolicyValidity;

}
