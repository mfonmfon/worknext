package com.worknext.africa.nija.worknext.Dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileRequest {
    private Long employerId;
    private String companyName;
    private String companyDescription;
    private String companyLocation;
}
