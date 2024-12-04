package com.worknext.africa.nija.worknext.service.implimentation;

import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.data.model.Employee;
import com.worknext.africa.nija.worknext.data.model.JobApplication;
import com.worknext.africa.nija.worknext.data.repository.EmployeeRepository;
import com.worknext.africa.nija.worknext.data.repository.EmployersRepository;
import com.worknext.africa.nija.worknext.data.repository.JobApplicationRepository;
import com.worknext.africa.nija.worknext.exceptions.UserNotFoundException;
import com.worknext.africa.nija.worknext.service.interfaces.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public JobApplicationResponse applyJob(JobApplicationRequest application) throws UserNotFoundException {
        Employee employee = employeeRepository.findById(application.getEmployeeId()).orElseThrow(()-> new UserNotFoundException("Employee not found"));
        JobApplication jobApplication = modelMapper.map(application, JobApplication.class);
        jobApplication.setEmployee(employee);
        jobApplicationRepository.save(jobApplication);
        JobApplicationResponse jobApplicationResponse = modelMapper.map(jobApplication, JobApplicationResponse.class);
        jobApplicationResponse.setMessage("Application submitted successfully");
        return jobApplicationResponse;
    }
}
