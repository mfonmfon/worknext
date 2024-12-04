package com.worknext.africa.nija.worknext.service.implimentation;

import com.worknext.africa.nija.worknext.Dtos.request.EditJobPostRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.DeleteJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.EditJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.UpLoadPostResponse;
import com.worknext.africa.nija.worknext.data.model.Employer;
import com.worknext.africa.nija.worknext.data.model.JobPost;
import com.worknext.africa.nija.worknext.data.repository.EmployersRepository;
import com.worknext.africa.nija.worknext.data.repository.JobPostRepository;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.IdNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.JobsNotFoundException;
import com.worknext.africa.nija.worknext.service.interfaces.JobPostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostServiceImpl implements JobPostService {
    private final EmployersRepository employersRepository;
    private  ModelMapper modelMapper;
    private final JobPostRepository jobPostRepository;

    @Override
    public UpLoadPostResponse uploadPost(UpLoadPostRequest uploadPostRequest) throws EmployersNotFoundException {
        Employer employer = employersRepository.findById(uploadPostRequest.getEmployerId())
                .orElseThrow(()-> new EmployersNotFoundException("Employer not found"));
        JobPost jobPost = buildJobPostOpeningUpload(uploadPostRequest, employer);
        jobPostRepository.save(jobPost);
        UpLoadPostResponse upLoadPostResponse = new UpLoadPostResponse();
        upLoadPostResponse.setJobPostId(jobPost.getId());
        upLoadPostResponse.setMessage("Job Post uploaded successfully");
        return upLoadPostResponse;
    }

    @Override
    public EditJobPostResponse editJobPost(EditJobPostRequest uploadPostRequest) throws JobsNotFoundException {
        JobPost jobPost = buildEditJobPostRequest(uploadPostRequest);
        jobPostRepository.save(jobPost);
        EditJobPostResponse editJobPostResponse = new EditJobPostResponse();
        editJobPostResponse.setMessage("Job Post edited successfully");
        return editJobPostResponse;
    }

    @Override
    public DeleteJobPostResponse deleteJobPost(Long jobPostId) throws JobsNotFoundException {
        JobPost jobPost =  jobPostRepository.findById(jobPostId).orElseThrow(()-> new JobsNotFoundException("Job post not found"));
        jobPostRepository.delete(jobPost);
        DeleteJobPostResponse deleteJobPostResponse = new DeleteJobPostResponse();
        deleteJobPostResponse.setMessage("Job Post deleted successfully");
        return deleteJobPostResponse;
    }

    @Override
    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    @Override
    public List<JobPost> searchByJobTitle(String title) {
        return jobPostRepository.findByJobTitle(title);
    }

    @Override
    public JobPost searchByEmployerId(Long id) throws IdNotFoundException {
        return jobPostRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("No such job found"));
    }

    @Override
    public List<JobPost> searchByDescription(String description) {
        return jobPostRepository.findByJobDescription(description);
    }

    private JobPost buildEditJobPostRequest(EditJobPostRequest uploadPostRequest) throws JobsNotFoundException {
        JobPost jobPost =  jobPostRepository.findById(uploadPostRequest.getJobPostId())
                .orElseThrow(()-> new JobsNotFoundException("Job post not found"));
        jobPost.setJobTitle(uploadPostRequest.getJobTitle());
        jobPost.setJobDescription(uploadPostRequest.getJobDescription());
        jobPost.setSalaryRange(uploadPostRequest.getSalaryRange());
        jobPost.setJobType(uploadPostRequest.getJobType());
        jobPost.setLastModified(uploadPostRequest.getLastModifiedAt());
        return jobPost;
    }

    private static JobPost buildJobPostOpeningUpload(UpLoadPostRequest uploadPostRequest, Employer employer) {
        JobPost jobPost = new JobPost();
        jobPost.setJobTitle(uploadPostRequest.getJobTitle());
        jobPost.setJobDescription(uploadPostRequest.getJobDescription());
        jobPost.setSalaryRange(uploadPostRequest.getSalaryRange());
        jobPost.setJobType(uploadPostRequest.getJobType());
        jobPost.setPostedAt(uploadPostRequest.getPostedAt());
        jobPost.setEmployer(employer);
        return jobPost;
    }
}
