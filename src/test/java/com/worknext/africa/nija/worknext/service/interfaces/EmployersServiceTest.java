package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployerRegistrationResponse;
import com.worknext.africa.nija.worknext.data.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmployersServiceTest {

    @Autowired
    private EmployersService employerService;

    @Test
    public void testThatEmployersCanRegister(){
        EmployerRegistrationRequest registrationRequest = new EmployerRegistrationRequest();
        registrationRequest.setCompanyName("WorkNext company");
        registrationRequest.setEmail("worknext@example.com");
        registrationRequest.setPassword("password111");
        registrationRequest.setCompanyLocation("Lagos, Nigeria");
        registrationRequest.setCompanyDescription("WorkNext is a revolutionary platform that connects job seekers with employers.");
        registrationRequest.setRole(UserRole.EMPLOYERS);
        EmployerRegistrationResponse employerRegistrationResponse = employerService.registerEmployers(registrationRequest);
        assertNotNull(employerRegistrationResponse);
        assertEquals("Welcome onboard", employerRegistrationResponse.getMessage());
    }
}
