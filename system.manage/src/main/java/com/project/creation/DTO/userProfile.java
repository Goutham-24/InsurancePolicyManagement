package com.project.creation.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfile {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    String userName;
    
    @Max(value = 100, message = "Age must be less than 100")
    @Min(value = 18, message = "Age must be more than 18")
    Integer userAge;
    
    @Pattern(regexp="^(\\+91[\\-\\s]?|91[\\-\\s]?|0)?[6-9]\\d{9}$", message="Enter valid phone number")
    String userPhonenumber;
}
