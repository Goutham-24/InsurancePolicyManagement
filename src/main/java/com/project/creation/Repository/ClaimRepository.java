package com.project.creation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.creation.Model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    
}
