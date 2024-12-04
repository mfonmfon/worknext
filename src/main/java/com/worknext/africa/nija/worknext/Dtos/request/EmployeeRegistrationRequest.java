package com.worknext.africa.nija.worknext.Dtos.request;

import com.worknext.africa.nija.worknext.data.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
}
