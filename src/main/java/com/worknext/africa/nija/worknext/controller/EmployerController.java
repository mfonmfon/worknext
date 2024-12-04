package com.worknext.africa.nija.worknext.controller;

import com.worknext.africa.nija.worknext.Dtos.request.EditProfileRequest;
import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.LoginUserRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.*;
import com.worknext.africa.nija.worknext.data.model.Employer;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;
import com.worknext.africa.nija.worknext.service.interfaces.EmployersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest){
        try{
            LoginUserResponse loginUserResponse = employerService.login(loginUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, loginUserResponse), HttpStatus.OK);
        }
        catch(Exception exception){
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
    @PatchMapping("/editProfile")  //this endpoint is responding but is not changing the state of the fields yet int the db
    public ResponseEntity<?> editProfile(@RequestBody EditProfileRequest editProfileRequest){
        try{
            EditProfileResponse editProfileResponse = employerService.editProfile(editProfileRequest);
            return new ResponseEntity<>(new ApiResponse(true, editProfileResponse), HttpStatus.OK);
        } catch (EmployersNotFoundException exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAccount/{jobPostId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long jobPostId){
        try{
            DeleteUserResponse deleteUserResponse = employerService.deleteEmployer(jobPostId);
            return new ResponseEntity<>(new ApiResponse(true, deleteUserResponse), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllEmployers(){
       try {
           List<Employer> getAllEmployers =employerService.getAllEmployers();
           return new ResponseEntity<>(new ApiResponse(true, getAllEmployers), HttpStatus.OK);
       }
       catch(Exception exception){
           return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmployersByCompanyName/{companyName}")
    public ResponseEntity<?> getEmployersByCompanyName(@PathVariable String companyName){
        try{
            List<Employer> findEmployerByCompanyName = employerService.getEmployersByCompanyName(companyName);
            return new ResponseEntity<>(new ApiResponse(true, findEmployerByCompanyName), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getEmployerByCompanyLocation")
    public ResponseEntity<?> getEmployerByCompanyLocation(@RequestParam String companyLocation){
        try{
            List<Employer> findEmployerByCompanyLocation = employerService.getEmployersByCompanyLocation(companyLocation);
            return new ResponseEntity<>(new ApiResponse(true, findEmployerByCompanyLocation), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getEmployerByCompanyDescription")
    public ResponseEntity<?> getEmployerByCompanyDescription(@RequestParam String companyDescription){
        try{
            List<Employer> findEmployerByCompanyDescription = employerService.getEmployersByCompanyDescription(companyDescription);
            return new ResponseEntity<>(new ApiResponse(true, findEmployerByCompanyDescription), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getEmployerByEmail")
    public ResponseEntity<?> getEmployerByEmail(@RequestParam String email){
        try{
            List<Employer> findEmployerByEmail = employerService.getEmployersByEmail(email);
            return new ResponseEntity<>(new ApiResponse(true, findEmployerByEmail), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getEmployerById")
    public ResponseEntity<?> getEmployerById(@RequestParam Long employerId){
       try{
             employerService.getEmployersById(employerId);
           return new ResponseEntity<>(new ApiResponse(true, "Employer found"), HttpStatus.OK);
       }
       catch(EmployersNotFoundException exception){
           return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.NOT_FOUND);
       }
    }
}
