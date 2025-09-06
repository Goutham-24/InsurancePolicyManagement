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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/mainController")
public class Controller {
    @Autowired
    UserRepsitory userrepo;

    @Autowired
    UserService userservice;
    

    @GetMapping("/test")
    public String gettest() {
        return "test successful";
    }
    
    @GetMapping("/getUserName")
    public String getusername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
    
}
