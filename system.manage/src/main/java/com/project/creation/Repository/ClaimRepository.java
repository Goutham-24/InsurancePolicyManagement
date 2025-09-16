package com.project.creation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.creation.Model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    @Query("SELECT c FROM Claim c WHERE c.agentApproval = com.project.creation.Enum.ApprovalStatus.PENDING")
    List<Claim> AgentClaimView();

    @Query("SELECT cs FROM Claim cs WHERE cs.userPolicy.user.userEmailId = :userEmailId ")
    List<Claim> CustomerClaims(@Param("userEmailId") String userEmailId);

    @Query("SELECT a FROM Claim a WHERE a.agentApproval = com.project.creation.Enum.ApprovalStatus.APPROVED AND a.adminApproval = com.project.creation.Enum.ApprovalStatus.PENDING")
    List<Claim> AdminClaimView();
}
