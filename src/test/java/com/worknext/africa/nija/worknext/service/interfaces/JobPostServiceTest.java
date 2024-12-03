package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EditJobPostRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EditJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.UpLoadPostResponse;
import com.worknext.africa.nija.worknext.data.enums.JobType;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobPostServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Autowired
    private JobPostService jobPostService;

    @Test
    public void testCanUploadJobPost() throws EmployersNotFoundException {
        UpLoadPostRequest uploadPostRequest = new UpLoadPostRequest();
        uploadPostRequest.setEmployerId(1L);
        uploadPostRequest.setJobTitle("Software engineer ");
        uploadPostRequest.setJobDescription("Software Engineer");
        uploadPostRequest.setJobType(JobType.INTERNSHIP);
        uploadPostRequest.setSalaryRange(BigDecimal.valueOf(100));
        uploadPostRequest.setPostedAt(now());
        UpLoadPostResponse upLoadPostResponse = jobPostService.uploadPost(uploadPostRequest);
        assertNotNull(upLoadPostResponse);
    }

    @Test
    public void testThatPostCanBeEditedSuccessfully(){
        Long jobPostId = 1L;
        EditJobPostRequest uploadPostRequest = new EditJobPostRequest();
        uploadPostRequest.setJobPostId(jobPostId);
        uploadPostRequest.setJobTitle("Senior Software Engineer ");
        EditJobPostResponse editJobPostResponse = jobPostService.editJobPost(uploadPostRequest);
    }
}