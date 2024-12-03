package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EditJobPostRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.DeleteJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.EditJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.UpLoadPostResponse;
import com.worknext.africa.nija.worknext.data.model.JobPost;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.IdNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.JobsNotFoundException;

import java.util.List;

public interface JobPostService {
    UpLoadPostResponse uploadPost(UpLoadPostRequest uploadPostRequest) throws EmployersNotFoundException;

    EditJobPostResponse editJobPost(EditJobPostRequest uploadPostRequest) throws JobsNotFoundException;

    DeleteJobPostResponse deleteJobPost(Long jobPostId) throws JobsNotFoundException;

    List<JobPost> getAllJobPosts();

    List<JobPost> searchByJobTitle(String title);

    JobPost searchByEmployerId(Long id) throws IdNotFoundException;

    List<JobPost> searchByDescription(String description);

}
