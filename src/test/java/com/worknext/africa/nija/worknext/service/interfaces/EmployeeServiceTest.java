package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EmployeeRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployeeRegistrationResponse;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.exceptions.ApplicationNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.InvalidPasswordException;
import com.worknext.africa.nija.worknext.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.worknext.africa.nija.worknext.data.enums.UserRole.EMPLOYEE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Autowired
    private EmployeeService employeeService;


    @Test
    public void givenThatEmployeeCanRegisterOnTheApplication() throws InvalidPasswordException {
        EmployeeRegistrationRequest employeeRegistrationRequest = new EmployeeRegistrationRequest();
        employeeRegistrationRequest.setEmail("test@test.com");
        employeeRegistrationRequest.setFirstName("John");
        employeeRegistrationRequest.setLastName("Doe");
        employeeRegistrationRequest.setPassword("122024Clux!");
        employeeRegistrationRequest.setRole(EMPLOYEE);
        EmployeeRegistrationResponse employeeRegistrationResponse = employeeService.registerEmployee(employeeRegistrationRequest);
        assertThat(employeeRegistrationResponse.getMessage()).contains("Employee registered successfully");
    }

    @Test
    public void testThatEmployeeCanApplyForJob() throws UserNotFoundException, ApplicationNotFoundException {
        EmployeeRegistrationRequest employeeRegistrationRequest = new EmployeeRegistrationRequest();
        employeeRegistrationRequest.setEmail("test@test.com");
        employeeRegistrationRequest.setFirstName("John");
        employeeRegistrationRequest.setLastName("Doe");
        employeeRegistrationRequest.setPassword("122024Clux!");
        employeeRegistrationRequest.setRole(EMPLOYEE);
        JobApplicationRequest jobApplicationRequest = new JobApplicationRequest();
        jobApplicationRequest.setEmployeeId(1L);
        jobApplicationRequest.setFirstName("Blessing");
        jobApplicationRequest.setLastName("Mfon");
        jobApplicationRequest.setEmail("blessing@gmail.com");
        jobApplicationRequest.setCoverLetter("Applying for this role");
        jobApplicationRequest.setLinkedInUrl("http://blessingmfoncv.jpg");
        jobApplicationRequest.setPhoneNumber("0801234667");
        JobApplicationResponse jobApplicationResponse = employeeService.applyForJob(jobApplicationRequest);
        assertNotNull(jobApplicationResponse);
    }
}