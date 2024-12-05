package com.worknext.africa.nija.worknext.controller;

import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.ApiResponse;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.service.interfaces.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jobapplication")
@RestController
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody JobApplicationRequest jobApplicationRequest){
        try{
            JobApplicationResponse jobApplicationResponse = jobApplicationService.applyJob(jobApplicationRequest);
            return new ResponseEntity<>(new ApiResponse(true, jobApplicationResponse), HttpStatus.CREATED);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
