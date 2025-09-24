package com.project.creation.GlobalException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.creation.Exceptions.ClaimNotFoundException;
import com.project.creation.Exceptions.UserNotFoundException;
import com.project.creation.Exceptions.UserPolicyNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
@ExceptionHandler( UserNotFoundException.class)
private ResponseEntity<?> UserNotFoundHandler(UserNotFoundException except){
    return responsebody(HttpStatus.NOT_FOUND,except.getMessage());
}

@ExceptionHandler( UserPolicyNotFoundException.class)
private ResponseEntity<?> UserPolicyNotFoundHandler(UserPolicyNotFoundException except){
    return responsebody(HttpStatus.NOT_FOUND,except.getMessage());
}


@ExceptionHandler( ClaimNotFoundException.class)
private ResponseEntity<?> ClaimNotFoundHandler(ClaimNotFoundException except){
    return responsebody(HttpStatus.NOT_FOUND,except.getMessage());
}

@ExceptionHandler(Exception.class)
public ResponseEntity<?> handleGeneralException() {
    return responsebody(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}

private ResponseEntity<?> responsebody(HttpStatus statusquote,String message){

    Map<String,Object>  map = new LinkedHashMap<>();
    map.put("Status",statusquote.value());
    map.put("Body",message);
    map.put("Time",LocalDateTime.now());
    return new ResponseEntity<>(map,statusquote);
}

}
