package com.project.creation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.creation.Model.Policy;
import java.util.List;


public interface PolicyRepository extends JpaRepository<Policy, Long> {
    Policy findByPolicyId(Long policyId);
}
