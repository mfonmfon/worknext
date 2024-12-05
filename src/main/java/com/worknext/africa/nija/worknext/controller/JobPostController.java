package com.worknext.africa.nija.worknext.controller;

import com.worknext.africa.nija.worknext.Dtos.request.EditJobPostRequest;
import com.worknext.africa.nija.worknext.Dtos.request.EditProfileRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.ApiResponse;
import com.worknext.africa.nija.worknext.Dtos.response.DeleteJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.EditJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.UpLoadPostResponse;
import com.worknext.africa.nija.worknext.data.model.JobPost;
import com.worknext.africa.nija.worknext.service.interfaces.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("jobpost")
@RestController
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @PostMapping("postJob")
    public ResponseEntity<?> postJob(@RequestBody UpLoadPostRequest upLoadPostRequest){
        try{
            UpLoadPostResponse upLoadPostResponse = jobPostService.uploadPost(upLoadPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, upLoadPostResponse), HttpStatus.CREATED);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateJob")
    public ResponseEntity<?> updateJob(@RequestBody EditJobPostRequest editJobPostRequest){
        try{
            EditJobPostResponse editJobPostResponse = jobPostService.editJobPost(editJobPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, editJobPostResponse), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletePost/{jobPostId}")
    public ResponseEntity<?> deletePost(@PathVariable Long jobPostId){
        try{
            DeleteJobPostResponse deleteJobPostResponse = jobPostService.deleteJobPost(jobPostId);
            return new ResponseEntity<>(new ApiResponse(true, deleteJobPostResponse), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getAllJobs() {
        try {
            List<JobPost> getAllJobs = jobPostService.getAllJobPosts();
            return new ResponseEntity<>(new ApiResponse(true, getAllJobs), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
