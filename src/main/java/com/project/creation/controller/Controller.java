package com.project.creation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.creation.Model.User;
import com.project.creation.Repository.UserRepsitory;
import com.project.creation.Service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/mainController")
public class Controller {
    @Autowired
    UserRepsitory userrepo;

    @Autowired
    UserService userservice;
    
    @GetMapping("/getter")
    public String getMethodName() {
        return "hello dheva epdi iruka";
    }

    @GetMapping("/getobject")
    public User getobjs() {
        return User.builder()
                .userName("coolie")
                .userAge(70)
                .userPhonenumber("9876543210")
                .userPassword("12334")
                .userEmailId("ThalaivarThimingalam@gmail.com")
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posttodatabase")
    public void postMethodName(@RequestBody User useradata) {
        userrepo.save(useradata);
    
    }
    

    @GetMapping("/fromDatabase/{idnumber}")
    public User getMethodName(@PathVariable Long idnumber) {
        User inps = userrepo.findById(idnumber).orElseThrow(()-> new RuntimeException("User not found"));
        return inps;
    }

    @GetMapping("/test")
    public String gettest() {
        return "test successful";
    }
    
    @GetMapping("/getUserName")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public String getusername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/AdminAccess-CustomerToAgent/{CustomerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> CustomerConversion(@PathVariable Long CustomerId) {
        return userservice.CustomerToAgent(CustomerId);
    }

    @GetMapping("/AdminAccess-AgentToAdmin/{AgentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> AgentConversion(@PathVariable Long AgentId) {
        return userservice.AgentToAdmin(AgentId);
    }
    
    
    
    
    
}
