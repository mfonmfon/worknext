package com.worknext.africa.nija.worknext.service.implimentation;

import com.worknext.africa.nija.worknext.Dtos.request.EditProfileRequest;
import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.LoginUserRequest;
import com.worknext.africa.nija.worknext.Dtos.request.UpLoadPostRequest;
import com.worknext.africa.nija.worknext.Dtos.response.*;
import com.worknext.africa.nija.worknext.data.enums.UserRole;
import com.worknext.africa.nija.worknext.data.model.Employees;
import com.worknext.africa.nija.worknext.data.model.Employers;
import com.worknext.africa.nija.worknext.data.model.JobPost;
import com.worknext.africa.nija.worknext.data.repository.EmployersRepository;
import com.worknext.africa.nija.worknext.data.repository.JobPostRepository;
import com.worknext.africa.nija.worknext.exceptions.*;
import com.worknext.africa.nija.worknext.service.interfaces.EmployersService;
import com.worknext.africa.nija.worknext.service.interfaces.JobPostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.worknext.africa.nija.worknext.utils.Mapper.validateAllNullAndEmptyInput;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployersServiceImpl implements EmployersService {

    private final EmployersRepository employersRepository;
    private final JobPostRepository jobPostRepository;
    private final ModelMapper modelMapper;
    private final JobPostService jobPostService;
    private final Pattern VALIDATE_USERS_EMAILS  = Pattern.compile("\"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final Pattern VALIDATE_PASSWORDS = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Override
    public EmployerRegistrationResponse registerEmployers(EmployerRegistrationRequest registrationRequest) throws UserAlreadyExistsException, RequiredFieldException, WrongEmailOrPasswordException {
        validateEmployersEmail(registrationRequest.getEmail());
        Employers employers = buildEmployersRegistrations(registrationRequest);
        if(isValidEmail(registrationRequest.getEmail()))throw new WrongEmailOrPasswordException("wrong email or password");
        if(isValidPassword(registrationRequest.getPassword()))throw new WrongEmailOrPasswordException("Wrong email or password");
        validateAllNullAndEmptyInput(registrationRequest.getEmail(),
                registrationRequest.getCompanyDescription(), registrationRequest.getCompanyLocation()
                , registrationRequest.getCompanyName(), registrationRequest.getPassword(),registrationRequest.getRole());
        employersRepository.save(employers);
        EmployerRegistrationResponse employeeResponse = new EmployerRegistrationResponse();
        employeeResponse.setMessage("Welcome onboard");
        return employeeResponse;
    }
    private boolean isValidPassword(String password) {return VALIDATE_PASSWORDS.matcher(password).matches();}
    private boolean isValidEmail(String email) {return VALIDATE_USERS_EMAILS.matcher(email).matches();}
    private boolean isInputNullOrEmpty(String userInput) {return userInput == null || userInput.isEmpty();}


    private void validateEmployersEmail(String email) throws UserAlreadyExistsException {
        boolean isEmailEmpty = employersRepository.existsByEmail(email);
        if(isEmailEmpty) throw new UserAlreadyExistsException("User with this email already exists");
    }

    @Override
    public UpLoadPostResponse uploadPost(UpLoadPostRequest upLoadPostRequest) throws EmployersNotFoundException, IdNotFoundException, JobsNotFoundException {
        UpLoadPostResponse upLoadPostResponse = jobPostService.uploadPost(upLoadPostRequest);
        JobPost jobPost = jobPostRepository.findById(upLoadPostRequest.getJobPostId())
                .orElseThrow(()-> new JobsNotFoundException("Job not found"));
        Employers employers = employersRepository.findById(upLoadPostRequest.getEmployerId())
                .orElseThrow(()-> new EmployersNotFoundException("Employer not found"));
        List<JobPost> jobPosts = employers.getJobPosts();
        jobPosts.add(jobPost);
        employers.setJobPosts(jobPosts);
        employersRepository.save(employers);
        return upLoadPostResponse;
    }

    @Override
    public EditProfileResponse editProfile(EditProfileRequest editProfileRequest) throws EmployersNotFoundException {
        Employers employers = employersRepository.findById(editProfileRequest.getEmployerId())
                .orElseThrow(()-> new EmployersNotFoundException("Employer not found"));
        modelMapper.map(editProfileRequest, Employers.class);
        employersRepository.save(employers);
        EditProfileResponse editProfileResponse = modelMapper.map(employers, EditProfileResponse.class);
        editProfileResponse.setMessage("Edited profile");
        return editProfileResponse;
    }

    @Override
    public List<Employers> getAllEmployers() {
        return employersRepository.findAll();
    }

    @Override
    public List<Employers> getEmployersByCompanyName(String companyName) {
        return employersRepository.findByCompanyName(companyName);
    }

    @Override
    public List<Employers> getEmployersByCompanyDescription(String companyDescription) {
        return employersRepository.findByCompanyDescription(companyDescription);
    }

    @Override
    public List<Employers> getEmployersByCompanyLocation(String companyLocation) {
        return employersRepository.findByCompanyLocation(companyLocation);
    }

    @Override
    public List<Employers> getEmployersByEmail(String email) {
        return employersRepository.findByEmail(email);
    }

    @Override
    public void getEmployersById(Long employerId) throws EmployersNotFoundException {
        employersRepository.findById(employerId)
                .orElseThrow(() -> new EmployersNotFoundException("Employer not found"));

    }

    @Override
    public DeleteUserResponse deleteEmployer(Long employerId) throws EmployersNotFoundException {
        Employers employee = employersRepository.findById(employerId)
                .orElseThrow(()-> new EmployersNotFoundException("Employer not found"));
        employersRepository.delete(employee);
        DeleteUserResponse deleteResponse = new DeleteUserResponse();
        deleteResponse.setMessage("Employer deleted successfully");
        return deleteResponse;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginUserRequest) throws EmployersNotFoundException, WrongEmailOrPasswordException {
        Employers employers = employersRepository.findById(loginUserRequest.getEmployersId()).
                orElseThrow(()-> new EmployersNotFoundException("You dont have an account with Worknext"));
        if (!employers.getPassword().equals(loginUserRequest.getPassword())) throw new WrongEmailOrPasswordException("Wrong email or password");
        employers.setEmail(loginUserRequest.getEmail());
        employers.setPassword(loginUserRequest.getPassword());
        LoginUserResponse loginUserResponse = modelMapper.map(employers, LoginUserResponse.class);
        loginUserResponse.setMessage("Login successful");
        return loginUserResponse;
    }

    private static Employers buildEmployersRegistrations(EmployerRegistrationRequest registrationRequest) {
        Employers employers = new Employers();
        employers.setCompanyName(registrationRequest.getCompanyName());
        employers.setEmail(registrationRequest.getEmail());
        employers.setPassword(registrationRequest.getPassword());
        employers.setCompanyLocation(registrationRequest.getCompanyLocation());
        employers.setCompanyDescription(registrationRequest.getCompanyDescription());
        employers.setRole(registrationRequest.getRole());
        return employers;
    }

}
