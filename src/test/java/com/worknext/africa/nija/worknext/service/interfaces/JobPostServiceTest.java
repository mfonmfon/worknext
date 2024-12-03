package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EditJobPostRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.DeleteJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.EditJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.UpLoadPostResponse;
import com.worknext.africa.nija.worknext.data.enums.JobType;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.IdNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.JobsNotFoundException;
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
    public void testThatPostCanBeEditedSuccessfully() throws JobsNotFoundException {
        Long jobPostId = 1L;
        EditJobPostRequest uploadPostRequest = new EditJobPostRequest();
        uploadPostRequest.setJobPostId(jobPostId);
        uploadPostRequest.setJobTitle("Junior Java Software Engineer ");
        uploadPostRequest.setJobDescription("Junior Java Software Engineer");
        uploadPostRequest.setJobType(JobType.PART_TIME);
        uploadPostRequest.setSalaryRange(BigDecimal.valueOf(80000));
        uploadPostRequest.setLastModifiedAt(now());
        EditJobPostResponse editJobPostResponse = jobPostService.editJobPost(uploadPostRequest);
        assertEquals("Job Post edited successfully", editJobPostResponse.getMessage());
        assertNotNull(editJobPostResponse);
    }

    @Test
    public void testThatPPostCanBeDeleted() throws JobsNotFoundException {
        Long jobPostId = 1L;
        DeleteJobPostResponse deleteJobPostResponse = jobPostService.deleteJobPost(jobPostId);
        assertEquals("Job Post deleted successfully", deleteJobPostResponse.getMessage());
        assertNotNull(deleteJobPostResponse);
    }

    @Test
    public void testThatCanGetAllJobPosts(){
        jobPostService.getAllJobPosts();
        assertEquals(0, jobPostService.getAllJobPosts().size());
    }

    @Test
    public void testThatCanGetJobPostsByJobTitle(){
        jobPostService.searchByJobTitle("Software engineer ");
        assertEquals(1, jobPostService.searchByJobTitle("Software engineer ").size());
    }
//    @Test
//    public void testThatCanGetJobPostsByEmployerId() throws IdNotFoundException {
//        jobPostService.searchByEmployerId(1L);
//        assertEquals(1, jobPostService.searchByEmployerId(1L));
//    }
}