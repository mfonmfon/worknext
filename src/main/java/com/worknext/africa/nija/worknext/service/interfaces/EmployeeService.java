package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EmployeeRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployeeRegistrationResponse;
import com.worknext.africa.nija.worknext.exceptions.InvalidPasswordException;

public interface EmployeeService {
    EmployeeRegistrationResponse registerEmplyee(EmployeeRegistrationRequest employeeRegistrationRequest) throws InvalidPasswordException;

}
