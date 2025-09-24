package com.project.creation.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyDetails {
    @NotBlank(message = "Policy name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 .'-]+$", message = "Name can only contain letters, spaces, dots, hyphens, and apostrophes")
    private String policyName;

    @Max(value = 20000, message = "Premium amount must be less than 20000")
    @Min(value = 2000, message = "premium amount must be more than 2000")
    private Double premiumAmount;

    @Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2}$",
        message = "Date must be in format yyyy-MM-dd"
    )
    private String validTo;

    @Max(value = 12, message = "Validity must be less than 12 months")
    @Min(value = 3, message = "Validity must be at least 3 month")
    private int userPolicyValidity;

}
