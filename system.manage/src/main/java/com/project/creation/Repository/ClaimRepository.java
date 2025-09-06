package com.project.creation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.creation.Model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    @Query("SELECT c FROM Claim c WHERE c.agentApproval = com.project.creation.Enum.ApprovalStatus.PENDING")
    List<Claim> AgentClaimView();
}
