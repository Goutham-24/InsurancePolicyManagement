package com.project.creation.Model;

import java.sql.Date;

import com.project.creation.Enum.PolicyStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class UserPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPolicyId;

    @ManyToOne
    @JoinColumn(name = "policy_owner")
    private User user;

    @ManyToOne
    @JoinColumn(name = "policy_taken")
    private Policy policy;

    private Date purchaseDate;
    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;  
}
