package com.project.creation.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.creation.DTO.ClaimDto;
import com.project.creation.DTO.UserPolicyDto;
import com.project.creation.DTO.UserProfile;
import com.project.creation.Enum.ApprovalStatus;
import com.project.creation.Enum.PolicyStatus;
import com.project.creation.Model.Claim;
import com.project.creation.Model.Policy;
import com.project.creation.Model.User;
import com.project.creation.Model.UserPolicy;
import com.project.creation.Repository.ClaimRepository;
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

    @Autowired
    ClaimRepository claimrepo;
    
    public ResponseEntity<String> customerProfile(UserProfile profile, String userEmailId) {
        User user = userrepo.findByUserEmailId(userEmailId).orElseThrow(() -> new RuntimeException("User not found"));
            user.setUserName(profile.getUserName());
            user.setUserAge(profile.getUserAge());
            user.setUserPhonenumber(profile.getUserPhonenumber());

        userrepo.save(user);   
         
        return ResponseEntity.ok("Profile updated successfully");
    }

    public UserProfile gatherProfile(String userEmailId){
        User user = userrepo.findByUserEmailId(userEmailId).orElseThrow(()-> new RuntimeException("user not found"));
        System.out.println(">>>data :"+user);
        UserProfile userprofile = UserProfile.builder()
                                  .userName(user.getUserName())
                                  .userAge(user.getUserAge())
                                  .userPhonenumber(user.getUserPhonenumber())
                                  .build();

        return userprofile;
                                  
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

    public ResponseEntity<String> ClaimPolicy(String userEmailId,Double Amount,Long userpolicyId){
        UserPolicy userpolicy = userpolicyrepo.findById(userpolicyId).orElseThrow(() -> new RuntimeException("UserPolicy not found"));
        if(userpolicy.getUser().getUserEmailId().equals(userEmailId) && userpolicy.getStatus() == PolicyStatus.ACTIVE) {
            if(LocalDate.now().isAfter(userpolicy.getExpiryDate())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Policy has expired, cannot claim");
            }

            Claim claim = Claim.builder()
                            .userPolicy(userpolicy)
                            .claimDate(LocalDate.now())
                            .claimAmount(Amount)
                            .agentApproval(ApprovalStatus.PENDING)
                            .adminApproval(ApprovalStatus.PENDING)
                            .finalStatus(ApprovalStatus.PENDING)
                            .build();

            claimrepo.save(claim);
            return ResponseEntity.ok("Claim submitted successfully");                
        
        } 
    
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to process claim");
    }

    public List<Policy> AllPolicies() {
        return policyrepo.findAll();
    }

    public List<UserPolicyDto> myPolicies(String userEmailId) {
       List<UserPolicy> ups = userpolicyrepo.getAllUserPolicies(userEmailId);
       if(ups.isEmpty()){
            throw new RuntimeException("No policies found for this user");
       }

       List<UserPolicyDto> dto = new ArrayList<>();
       for(UserPolicy policytaken : ups){
        UserPolicyDto updto = UserPolicyDto.builder()
                                    .userPolicyId(policytaken.getUserPolicyId())
                                    .policy(policytaken.getPolicy())
                                    .purchaseDate(policytaken.getPurchaseDate())
                                    .expiryDate(policytaken.getExpiryDate())
                                    .status(policytaken.getStatus())
                                    .build();

        dto.add(updto);                            
       }
       
       return dto;
    }

    public List<ClaimDto> acquireClaims(String userEmailId){
        List<Claim> claims = claimrepo.CustomerClaims(userEmailId);
        if(claims.isEmpty()){
            throw new RuntimeException("No policies found for this user");
       }

       List<ClaimDto> cdto = new ArrayList<>();
       for(Claim userclaims : claims){
            ClaimDto dto = ClaimDto.builder()
                            .claimId(userclaims.getClaimId())
                            .userEmailId(userclaims.getUserPolicy().getUser().getUserEmailId())
                            .policyName(userclaims.getUserPolicy().getPolicy().getPolicyName())
                            .claimDate(userclaims.getClaimDate())
                            .claimAmount(userclaims.getClaimAmount())
                            .finalStatus(userclaims.getFinalStatus())
                            .build();
           
            cdto.add(dto);                
       }

       return cdto;
    }
}
