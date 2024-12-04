package com.worknext.africa.nija.worknext.Dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationRequest {
    private Long jobApplicationId;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String coverLetter;
    private String linkedInUrl;
}

