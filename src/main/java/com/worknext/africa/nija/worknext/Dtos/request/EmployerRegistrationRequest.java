package com.worknext.africa.nija.worknext.Dtos.request;

import com.worknext.africa.nija.worknext.data.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerRegistrationRequest {
    private String companyName;
    private String companyLocation;
    private String companyDescription;
    private String email;
    private String password;
    private UserRole role;

}
