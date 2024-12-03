package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EditJobPostRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EditJobPostResponse;
import com.worknext.africa.nija.worknext.Dtos.response.UpLoadPostResponse;
import com.worknext.africa.nija.worknext.exceptions.EmployersNotFoundException;

public interface JobPostService {
    UpLoadPostResponse uploadPost(UpLoadPostRequest uploadPostRequest) throws EmployersNotFoundException;

    EditJobPostResponse editJobPost(EditJobPostRequest uploadPostRequest);

}
