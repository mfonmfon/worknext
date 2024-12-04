package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EmployeeRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployeeRegistrationResponse;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.exceptions.ApplicationNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.InvalidPasswordException;
import com.worknext.africa.nija.worknext.exceptions.UserNotFoundException;

public interface EmployeeService {
    EmployeeRegistrationResponse registerEmployee(EmployeeRegistrationRequest employeeRegistrationRequest) throws InvalidPasswordException;

    JobApplicationResponse applyForJob(JobApplicationRequest jobApplicationRequest) throws UserNotFoundException, ApplicationNotFoundException;

}
