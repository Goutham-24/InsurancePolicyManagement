package com.project.creation.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfile {
    String userName;
    Integer userAge;
    String userPhonenumber;
}
