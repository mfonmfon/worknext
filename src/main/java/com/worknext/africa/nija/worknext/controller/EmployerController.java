package com.worknext.africa.nija.worknext.controller;

import com.worknext.africa.nija.worknext.Dtos.request.EditProfileRequest;
import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.*;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;
import com.worknext.africa.nija.worknext.service.interfaces.EmployersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/employers")
@RestController
@RequiredArgsConstructor
public class EmployerController {
    private final EmployersService employerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody EmployerRegistrationRequest employerRegistrationRequest){
        try{
            EmployerRegistrationResponse employerRegistrationResponse  = employerService.registerEmployers(employerRegistrationRequest);
            return new ResponseEntity<>(new ApiResponse(true, employerRegistrationResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/uploadPost")
    public ResponseEntity<?> uploadPost(@RequestBody UpLoadPostRequest upLoadPostRequest){
        try {
            UpLoadPostResponse upLoadPostResponse = employerService.uploadPost(upLoadPostRequest);
            return new ResponseEntity<>( new ApiResponse(true, upLoadPostResponse), HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/editPost")
    public ResponseEntity<?> editPost(@RequestBody EditProfileRequest editProfileRequest){
        try{
            EditProfileResponse editProfileResponse = employerService.editProfile(editProfileRequest);
            return new ResponseEntity<>(new ApiResponse(true, editProfileResponse), HttpStatus.OK);
        } catch (EmployersNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletePost/{jobPostId}")
    public ResponseEntity<?> deletePost(@PathVariable Long jobPostId){
        try{
            DeleteUserResponse deleteUserResponse = employerService.deleteEmployer(jobPostId);
            return new ResponseEntity<>(new ApiResponse(true, deleteUserResponse), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
