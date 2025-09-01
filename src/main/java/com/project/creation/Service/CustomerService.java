package com.project.creation.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.creation.DTO.userProfile;
import com.project.creation.Model.User;
import com.project.creation.Repository.UserRepsitory;


@Service
public class CustomerService {
    @Autowired
    UserRepsitory userrepo;
    
    public ResponseEntity<String> customerProfile(userProfile profile, String userEmailId) {
        User user = userrepo.findByUserEmailId(userEmailId).orElseThrow(() -> new RuntimeException("User not found"));
            user.setUserName(profile.getUserName());
            user.setUserAge(profile.getUserAge());
            user.setUserPhonenumber(profile.getUserPhonenumber());

        userrepo.save(user);   
         
        return ResponseEntity.ok("Profile updated successfully");
    }
}
