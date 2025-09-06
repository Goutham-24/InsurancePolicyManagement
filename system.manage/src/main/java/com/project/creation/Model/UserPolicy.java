package com.project.creation.Model;
import java.time.LocalDate;

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
    @JoinColumn(name = "policy_owner",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "policy_taken",nullable = false)
    private Policy policy;

    private LocalDate purchaseDate;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;  
}
