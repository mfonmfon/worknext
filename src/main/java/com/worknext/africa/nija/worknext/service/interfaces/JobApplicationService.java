package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.exceptions.UserNotFoundException;

public interface JobApplicationService {
    JobApplicationResponse applyJob(JobApplicationRequest application) throws UserNotFoundException;

}
