package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EmployeeRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployeeRegistrationResponse;
import com.worknext.africa.nija.worknext.data.enums.UserRole;
import com.worknext.africa.nija.worknext.exceptions.InvalidPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.worknext.africa.nija.worknext.data.enums.UserRole.EMPLOYEE;
import static org.assertj.core.api.Assertions.assertThat;

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
        EmployeeRegistrationResponse employeeRegistrationResponse = employeeService.registerEmplyee(employeeRegistrationRequest);
        assertThat(employeeRegistrationResponse.getMessage()).contains("Employee registered successfully");
    }
}