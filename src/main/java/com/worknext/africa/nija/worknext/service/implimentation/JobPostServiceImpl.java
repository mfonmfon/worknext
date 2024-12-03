package com.worknext.africa.nija.worknext.service.implimentation;

import AppConfig.AppConfig;
import com.worknext.africa.nija.worknext.Dtos.request.EditJobPostRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EditJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.UpLoadPostResponse;
import com.worknext.africa.nija.worknext.data.model.Employers;
import com.worknext.africa.nija.worknext.data.model.JobPost;
import com.worknext.africa.nija.worknext.data.repository.EmployersRepository;
import com.worknext.africa.nija.worknext.data.repository.JobPostRepository;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;
import com.worknext.africa.nija.worknext.service.interfaces.JobPostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostServiceImpl implements JobPostService {
    private final EmployersRepository employersRepository;
    private  ModelMapper modelMapper;
    private final JobPostRepository jobPostRepository;

    @Override
    public UpLoadPostResponse uploadPost(UpLoadPostRequest uploadPostRequest) throws EmployersNotFoundException {
        Employers employers = employersRepository.findById(uploadPostRequest.getEmployerId())
                .orElseThrow(()-> new EmployersNotFoundException("Employer not found"));
        JobPost jobPost = buildJobPostOpeningUpload(uploadPostRequest, employers);
        jobPostRepository.save(jobPost);
        UpLoadPostResponse upLoadPostResponse = new UpLoadPostResponse();
        upLoadPostResponse.setMessage("Job Post uploaded successfully");
        return upLoadPostResponse;
    }

    @Override
    public EditJobPostResponse editJobPost(EditJobPostRequest uploadPostRequest) {

        return null;
    }

    private static JobPost buildJobPostOpeningUpload(UpLoadPostRequest uploadPostRequest, Employers employers) {
        JobPost jobPost = new JobPost();
        jobPost.setJobTitle(uploadPostRequest.getJobTitle());
        jobPost.setJobDescription(uploadPostRequest.getJobDescription());
        jobPost.setSalaryRange(uploadPostRequest.getSalaryRange());
        jobPost.setJobType(uploadPostRequest.getJobType());
        jobPost.setPostedAt(uploadPostRequest.getPostedAt());
        jobPost.setEmployers(employers);
        return jobPost;
    }
}
