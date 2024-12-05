package com.worknext.africa.nija.worknext.controller;

import com.worknext.africa.nija.worknext.Dtos.request.EmployeeRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.ApiResponse;
import com.worknext.africa.nija.worknext.Dtos.response.EmployeeRegistrationResponse;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.service.interfaces.EmployeeService;
import com.worknext.africa.nija.worknext.service.interfaces.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final JobApplicationService jobApplicationService;


    @PostMapping("/register ")
    public ResponseEntity<?> register(@RequestBody EmployeeRegistrationRequest employeeRegistrationRequest){
        try{
            EmployeeRegistrationResponse employeeRegistrationResponse = employeeService.registerEmployee(employeeRegistrationRequest);
            return new ResponseEntity<>(new ApiResponse(true, employeeRegistrationResponse ), HttpStatus.CREATED);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody JobApplicationRequest jobApplicationRequest){
        try {
            JobApplicationResponse jobApplicationResponse = jobApplicationService.applyJob(jobApplicationRequest);
            return new ResponseEntity<>(new ApiResponse(true, jobApplicationResponse), HttpStatus.CREATED);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
