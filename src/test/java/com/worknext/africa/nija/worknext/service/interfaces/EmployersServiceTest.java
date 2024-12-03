package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EditProfileRequest;
import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.*;
import com.worknext.africa.nija.worknext.data.enums.UserRole;
import com.worknext.africa.nija.worknext.data.repository.EmployersRepository;
import com.worknext.africa.nija.worknext.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.worknext.africa.nija.worknext.data.enums.JobType.FULL_TIME;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployersServiceTest {

    @Autowired
    private EmployersService employerService;
    private JobPostService jobPostService;
    private EmployersRepository employersRepository;

    @Test
    void setUp(){
        employersRepository.deleteAll();
    }

    @Test
    public void testThatEmployersCanRegister() throws UserAlreadyExistsException, RequiredFieldException {
        EmployerRegistrationRequest registrationRequest = buildEmployerRegistrationRequest();
        EmployerRegistrationResponse employerRegistrationResponse = employerService.registerEmployers(registrationRequest);
        assertNotNull(employerRegistrationResponse);
        assertEquals("Welcome onboard", employerRegistrationResponse.getMessage());
    }

    private static EmployerRegistrationRequest buildEmployerRegistrationRequest() {
        EmployerRegistrationRequest registrationRequest = new EmployerRegistrationRequest();
        registrationRequest.setCompanyName("WorkNext company");
        registrationRequest.setEmail("worknext@example.com");
        registrationRequest.setPassword("password111");
        registrationRequest.setCompanyLocation("Lagos, Nigeria");
        registrationRequest.setCompanyDescription("WorkNext is a revolutionary platform that connects job seekers with employers.");
        registrationRequest.setRole(UserRole.EMPLOYERS);
        return registrationRequest;
    }

    @Test
    public void testThatEmployersCanUploadJobOpening() throws EmployersNotFoundException,
            IdNotFoundException, JobsNotFoundException, UserAlreadyExistsException, RequiredFieldException {
        buildEmployerRegistrationRequest();
        EmployerRegistrationResponse employerRegistrationResponse = employerService.registerEmployers(buildEmployerRegistrationRequest());
        assertNotNull(employerRegistrationResponse);
        UpLoadPostRequest upLoadPostRequest = buildUpLoadJobPostRequest();
        UpLoadPostResponse upLoadPostResponse = employerService.uploadPost(upLoadPostRequest);
        assertNotNull(upLoadPostResponse);
    }

    private static UpLoadPostRequest buildUpLoadJobPostRequest() {
        UpLoadPostRequest upLoadPostRequest = new UpLoadPostRequest();
        upLoadPostRequest.setJobPostId(2L);
        upLoadPostRequest.setEmployerId(1L);
        upLoadPostRequest.setJobTitle("Accountant Management");
        upLoadPostRequest.setJobDescription("Accountant Management is a demanding and challenging job that requires strong communication, problem-solving, and organizational skills.");
        upLoadPostRequest.setJobType(FULL_TIME);
        upLoadPostRequest.setPostedAt(now());
        return upLoadPostRequest;
    }

    @Test
    public void testThatEmployersCanUpdateProfile() throws JobsNotFoundException, EmployersNotFoundException, IdNotFoundException, UserAlreadyExistsException, RequiredFieldException {
        buildEmployerRegistrationRequest();
        EmployerRegistrationResponse employerRegistrationResponse = employerService.registerEmployers(buildEmployerRegistrationRequest());
        buildUpLoadJobPostRequest();
        UpLoadPostResponse upLoadPostResponse = employerService.uploadPost(buildUpLoadJobPostRequest());
        EditProfileRequest editProfileRequest = getEditProfileRequest();
        EditProfileResponse editProfileResponse = employerService.editProfile(editProfileRequest);
        assertNotNull(editProfileResponse);
        assertEquals("Edited profile",editProfileResponse.getMessage());
        assertNotNull(upLoadPostResponse);
        assertNotNull(employerRegistrationResponse);
    }

    private static EditProfileRequest getEditProfileRequest() {
        EditProfileRequest editProfileRequest = new EditProfileRequest();
        editProfileRequest.setEmployerId(1L);
        editProfileRequest.setCompanyName("WorkNext");
        editProfileRequest.setCompanyLocation("Lagos, Nigeria");
        editProfileRequest.setCompanyDescription("WorkNext is a revolutionary platform that connects job seekers with employers.");
        return editProfileRequest;
    }

    @Test
    public void testThatEmployerCanDeleteBeDeletedAccount() throws EmployersNotFoundException {
        Long employerId = 1L;
        DeleteUserResponse deleteUserResponse = employerService.deleteEmployer(employerId);
        assertNotNull(deleteUserResponse);
        assertEquals("Employer deleted successfully", deleteUserResponse.getMessage());
    }

//    @Test
//    public void testThatUserCanNotApplyWithTheSameEmail_throwUserAlreadyExistException() throws UserAlreadyExistsException{
//        buildEmployerRegistrationRequest();
//        EmployerRegistrationResponse employerRegistrationResponse = employerService.registerEmployers(buildEmployerRegistrationRequest());
//        EmployerRegistrationRequest employeeRegistrationRequest = new EmployerRegistrationRequest();
//        employeeRegistrationRequest.setEmail("worknext@gmail.com");
//        assertNotNull(employerRegistrationResponse);
//        assertThrows(UserAlreadyExistsException.class,
//                () -> employerService.registerEmployers(employeeRegistrationRequest));
//    }
}
