package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JobApplicationServiceTest {

    @Autowired
    private JobApplicationService jobApplicationService;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void testThatCanApplyForJob() throws UserNotFoundException {
        JobApplicationRequest application = new JobApplicationRequest();
        application.setEmployeeId(1L);
        application.setFirstName("Lotanna");
        application.setLastName("Aunuforo");
        application.setEmail("lotanna@gmail.com");
        application.setPhoneNumber("08012345678");
        application.setCoverLetter("cover letter");
        application.setLinkedInUrl("http://lotannacv.jpg");
        JobApplicationResponse applicationResponse = jobApplicationService.applyJob(application);
        assertNotNull(applicationResponse);
        assertEquals("Application submitted successfully",applicationResponse.getMessage());
    }
}
