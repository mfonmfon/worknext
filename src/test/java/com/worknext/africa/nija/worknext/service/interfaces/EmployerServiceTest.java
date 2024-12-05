package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EditProfileRequest;
import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.LoginUserRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.*;
import com.worknext.africa.nija.worknext.data.enums.UserRole;
import com.worknext.africa.nija.worknext.data.repository.EmployersRepository;
import com.worknext.africa.nija.worknext.exceptions.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.worknext.africa.nija.worknext.data.enums.JobType.FULL_TIME;
import static com.worknext.africa.nija.worknext.data.enums.UserRole.EMPLOYERS;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class EmployerServiceTest {

    @Autowired
    private EmployersService employerService;
    @Autowired
    private EmployersRepository employersRepository;

    @Test
    void setUp(){
        employersRepository.deleteAll();
    }

   @Test
//   @Transactional
    public void testThatEmployersCanRegister() throws RequiredFieldException, WrongEmailOrPasswordException, UserAlreadyExistsException {
        EmployerRegistrationRequest employerRegistrationRequest = new EmployerRegistrationRequest();
        employerRegistrationRequest.setCompanyName("Workman");
        employerRegistrationRequest.setCompanyDescription("We build youth");
        employerRegistrationRequest.setCompanyLocation("Lagos");
        employerRegistrationRequest.setPassword("password");
        employerRegistrationRequest.setEmail("workman@gmail.com");
        employerRegistrationRequest.setRole(EMPLOYERS);
        EmployerRegistrationResponse employerRegistrationResponse = employerService.registerEmployers(employerRegistrationRequest);
        assertThat(employerRegistrationResponse).isNotNull();
        assertThat(employerRegistrationResponse.getMessage()).contains("Welcome onboard");
   }

   @Test
    public void testThatEmployersCanLoginAfterRegistering() throws RequiredFieldException, WrongEmailOrPasswordException, UserAlreadyExistsException, EmployersNotFoundException {
       EmployerRegistrationRequest employerRegistrationRequest = new EmployerRegistrationRequest();
       employerRegistrationRequest.setCompanyName("Workman");
       employerRegistrationRequest.setCompanyDescription("We build youth");
       employerRegistrationRequest.setCompanyLocation("Lagos");
       employerRegistrationRequest.setPassword("password");
       employerRegistrationRequest.setEmail("workman@gmail.com");
       employerRegistrationRequest.setRole(EMPLOYERS);
       LoginUserRequest loginUserRequest = new LoginUserRequest();
       loginUserRequest.setEmail("workman@gmail.com");
       loginUserRequest.setPassword("password");
       LoginUserResponse loginUserResponse = employerService.login(loginUserRequest);
       assertThat(loginUserResponse).isNotNull();
       assertThat(loginUserResponse.getMessage()).contains("Login successful");
   }


   @Test
    public void testThatEmployerCanPostJobOpenings() throws WrongEmailOrPasswordException, EmployersNotFoundException, JobsNotFoundException, InvalidUserException, IdNotFoundException {
       EmployerRegistrationRequest employerRegistrationRequest = new EmployerRegistrationRequest();
       employerRegistrationRequest.setCompanyName("Workman");
       employerRegistrationRequest.setCompanyDescription("We build youth");
       employerRegistrationRequest.setCompanyLocation("Lagos");
       employerRegistrationRequest.setPassword("password");
       employerRegistrationRequest.setEmail("workman@gmail.com");
       employerRegistrationRequest.setRole(EMPLOYERS);
       LoginUserRequest loginUserRequest = new LoginUserRequest();
       loginUserRequest.setEmail("workman@gmail.com");
       loginUserRequest.setPassword("password");
       LoginUserResponse loginUserResponse = employerService.login(loginUserRequest);
       UpLoadPostRequest upLoadPostRequest = buildEmployerUpLoadPostRequest();
       UpLoadPostResponse upLoadPostResponse = employerService.uploadPost(upLoadPostRequest);
       assertThat(upLoadPostResponse).isNotNull();
       assertThat(upLoadPostResponse.getMessage()).contains("Job Post uploaded successfully");

   }

    private static UpLoadPostRequest buildEmployerUpLoadPostRequest() {
        UpLoadPostRequest upLoadPostRequest = new UpLoadPostRequest();
        upLoadPostRequest.setJobTitle("Backend Java Engineer");
        upLoadPostRequest.setJobDescription("Integrate the backend and the frontend");
        upLoadPostRequest.setSalaryRange("4000000");
        upLoadPostRequest.setJobType(FULL_TIME);
        upLoadPostRequest.setPostedAt(now());
        upLoadPostRequest.setEmployerId(3L);
        return upLoadPostRequest;
    }
    @Test
    public void testThatEmployerCanEditProfile() throws WrongEmailOrPasswordException, EmployersNotFoundException {
        buildEmployerRegistrationRequest();
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("workman@gmail.com");
        loginUserRequest.setPassword("password");
        LoginUserResponse loginUserResponse = employerService.login(loginUserRequest);
        EditProfileRequest editProfileRequest = editEmployerProfileRequest();
        EditProfileResponse editProfileResponse = employerService.editProfile(editProfileRequest);
        assertThat(editProfileResponse).isNotNull();
        assertThat(editProfileResponse.getMessage()).contains("Edited profile");
    }

    private static EditProfileRequest editEmployerProfileRequest() {
        EditProfileRequest editProfileRequest = new EditProfileRequest();
        editProfileRequest.setCompanyName("Workman");
        editProfileRequest.setCompanyDescription("Hiring talent all over the world");
        editProfileRequest.setCompanyLocation("Lagos, Nigeria");
        editProfileRequest.setEmployerId(3L);
        return editProfileRequest;
    }

    private static void buildEmployerRegistrationRequest() {
        EmployerRegistrationRequest employerRegistrationRequest = new EmployerRegistrationRequest();
        employerRegistrationRequest.setCompanyName("Workman");
        employerRegistrationRequest.setCompanyDescription("We build youth");
        employerRegistrationRequest.setCompanyLocation("Lagos");
        employerRegistrationRequest.setPassword("password");
        employerRegistrationRequest.setEmail("workman@gmail.com");
        employerRegistrationRequest.setRole(EMPLOYERS);
    }

    @Test
    public void testThatEmployersCanDeleteTheirAccount() throws EmployersNotFoundException, WrongEmailOrPasswordException {
        buildEmployerRegistrationRequest();
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("workman@gmail.com");
        loginUserRequest.setPassword("password");
        LoginUserResponse loginUserResponse = employerService.login(loginUserRequest);
        Long employerId = 3L;
        DeleteUserResponse deleteUserResponse = employerService.deleteEmployer(employerId);
        assertThat(deleteUserResponse).isNotNull();
    }
}
