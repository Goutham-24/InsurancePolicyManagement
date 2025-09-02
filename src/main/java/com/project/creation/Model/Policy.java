package com.project.creation.Model;
import java.time.LocalDate;

import com.project.creation.Enum.PolicyStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    private String policyName;
    private Double premiumAmount;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Integer userPolicyValidity;

    @Enumerated(EnumType.STRING) 
    private PolicyStatus status;
}
