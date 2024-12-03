package com.worknext.africa.nija.worknext.Dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
    private Long employersId;
    private String email;
    private String password;
}
