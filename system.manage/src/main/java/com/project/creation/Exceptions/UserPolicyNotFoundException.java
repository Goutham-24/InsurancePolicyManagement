package com.project.creation.Exceptions;

public class UserPolicyNotFoundException extends RuntimeException{
    public UserPolicyNotFoundException(String Message){
        super(Message);
    }
}
