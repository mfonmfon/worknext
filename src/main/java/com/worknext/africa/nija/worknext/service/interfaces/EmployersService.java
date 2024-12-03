package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployerRegistrationResponse;

public interface EmployersService {
    EmployerRegistrationResponse registerEmployers(EmployerRegistrationRequest registrationRequest);

}
