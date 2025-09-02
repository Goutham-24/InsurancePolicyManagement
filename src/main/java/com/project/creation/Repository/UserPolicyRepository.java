package com.project.creation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.creation.Model.UserPolicy;

public interface UserPolicyRepository extends JpaRepository<UserPolicy, Long> {
    
}
