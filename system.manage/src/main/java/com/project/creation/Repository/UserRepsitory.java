package com.project.creation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.creation.Model.User;

public interface UserRepsitory extends JpaRepository<User, Long> {
    
    Optional<User> findByUserEmailId(String userEmailId);
}
