package com.worknext.africa.nija.worknext.service.interfaces;

import com.worknext.africa.nija.worknext.Dtos.request.EditProfileRequest;
import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.LoginUserRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.*;
import com.worknext.africa.nija.worknext.data.model.Employer;
import com.worknext.africa.nija.worknext.exceptions.*;

import java.util.List;

public interface EmployersService {
    EmployerRegistrationResponse registerEmployers(EmployerRegistrationRequest registrationRequest) throws UserAlreadyExistsException, RequiredFieldException, WrongEmailOrPasswordException;

    UpLoadPostResponse uploadPost(UpLoadPostRequest upLoadPostRequest) throws EmployersNotFoundException, IdNotFoundException, JobsNotFoundException, InvalidUserException;

    EditProfileResponse editProfile(EditProfileRequest editProfileRequest) throws EmployersNotFoundException;

    List<Employer> getAllEmployers();

    List<Employer> getEmployersByCompanyName(String companyName);

    List<Employer> getEmployersByCompanyDescription(String companyDescription);

    List<Employer> getEmployersByCompanyLocation(String companyLocation);

    List<Employer> getEmployersByEmail(String email);

    void getEmployersById(Long employerId) throws EmployersNotFoundException;

    DeleteUserResponse deleteEmployer(Long jobPostId) throws EmployersNotFoundException;

    LoginUserResponse login(LoginUserRequest loginUserRequest) throws EmployersNotFoundException, WrongEmailOrPasswordException;
}
