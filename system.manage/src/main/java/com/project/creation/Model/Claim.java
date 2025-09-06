package com.project.creation.Model;

import java.time.LocalDate;

import com.project.creation.Enum.ApprovalStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @ManyToOne
    @JoinColumn(name = "user_policy_id", nullable = false)  
    private UserPolicy userPolicy;

    private LocalDate claimDate;

    private Double claimAmount;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus agentApproval;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus adminApproval;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus finalStatus; 
}
