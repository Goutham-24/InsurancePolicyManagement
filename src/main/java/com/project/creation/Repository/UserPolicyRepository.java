package com.project.creation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.creation.Model.UserPolicy;

public interface UserPolicyRepository extends JpaRepository<UserPolicy, Long> {
    @Query("SELECT up FROM UserPolicy up WHERE up.user.userEmailId = :userEmailId")
    List<UserPolicy> getAllUserPolicies(@Param("userEmailId") String userEmailId);
}
