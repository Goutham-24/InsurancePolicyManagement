package com.project.creation.Service;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.creation.DTO.userProfile;
import com.project.creation.Enum.PolicyStatus;
import com.project.creation.Model.Policy;
import com.project.creation.Model.User;
import com.project.creation.Model.UserPolicy;
import com.project.creation.Repository.PolicyRepository;
import com.project.creation.Repository.UserPolicyRepository;
import com.project.creation.Repository.UserRepsitory;


@Service
public class CustomerService {
    @Autowired
    UserRepsitory userrepo;

    @Autowired
    PolicyRepository policyrepo;

    @Autowired
    UserPolicyRepository userpolicyrepo;
    
    public ResponseEntity<String> customerProfile(userProfile profile, String userEmailId) {
        User user = userrepo.findByUserEmailId(userEmailId).orElseThrow(() -> new RuntimeException("User not found"));
            user.setUserName(profile.getUserName());
            user.setUserAge(profile.getUserAge());
            user.setUserPhonenumber(profile.getUserPhonenumber());

        userrepo.save(user);   
         
        return ResponseEntity.ok("Profile updated successfully");
    }

    public ResponseEntity<String> PurchasePolicy(String userEmailId, Long policyId){
        User user = userrepo.findByUserEmailId(userEmailId).orElseThrow(() -> new RuntimeException("User not found"));
        Policy policy = policyrepo.findByPolicyId(policyId);
        UserPolicy UP = UserPolicy.builder()
                        .user(user)
                        .policy(policy)
                        .purchaseDate(LocalDate.now())
                        .expiryDate(LocalDate.now().plusMonths(policy.getUserPolicyValidity()))
                        .status(PolicyStatus.ACTIVE)
                        .build();

        userpolicyrepo.save(UP);
        return ResponseEntity.ok("Added user policy successfully");
    }
}
