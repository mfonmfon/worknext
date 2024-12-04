package com.worknext.africa.nija.worknext.service.implimentation;

import com.worknext.africa.nija.worknext.Dtos.request.EmployeeRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.request.JobApplicationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployeeRegistrationResponse;
import com.worknext.africa.nija.worknext.Dtos.response.JobApplicationResponse;
import com.worknext.africa.nija.worknext.data.model.Employee;
import com.worknext.africa.nija.worknext.data.model.JobApplication;
import com.worknext.africa.nija.worknext.data.repository.EmployeeRepository;
import com.worknext.africa.nija.worknext.data.repository.JobApplicationRepository;
import com.worknext.africa.nija.worknext.exceptions.ApplicationNotFoundException;
import com.worknext.africa.nija.worknext.exceptions.InvalidEmailException;
import com.worknext.africa.nija.worknext.exceptions.InvalidPasswordException;
import com.worknext.africa.nija.worknext.exceptions.UserNotFoundException;
import com.worknext.africa.nija.worknext.service.interfaces.EmployeeService;
import com.worknext.africa.nija.worknext.service.interfaces.JobApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JobApplicationService jobApplicationService;
    private final Pattern PASSWORD_REGEX_VALIDATOR = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    private final Pattern EMAIL_REGEX_VALIDATOR = Pattern.compile("\"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final JobApplicationRepository jobApplicationRepository;

    @Override
    public EmployeeRegistrationResponse registerEmployee(EmployeeRegistrationRequest employeeRegistrationRequest) throws InvalidPasswordException {
        Employee employee = new Employee();
        log.info("employee id is  -> {}", employee.getId());
        employee.setFirstName(employeeRegistrationRequest.getFirstName());
        employee.setLastName(employeeRegistrationRequest.getLastName());
        employee.setEmail(employeeRegistrationRequest.getEmail());
        validateEmployeePassword(passwordEncoder.encode(employeeRegistrationRequest.getPassword()));
        employee.setPassword(passwordEncoder.encode(employeeRegistrationRequest.getPassword()));
        employee.setRole(employeeRegistrationRequest.getRole());
        employeeRepository.save(employee);
        EmployeeRegistrationResponse employeeRegistrationResponse = modelMapper.map(employee, EmployeeRegistrationResponse.class);
        employeeRegistrationResponse.setMessage("Employee registered successfully");
        return employeeRegistrationResponse;
    }

    @Override
    public JobApplicationResponse applyForJob(JobApplicationRequest jobApplicationRequest) throws UserNotFoundException, ApplicationNotFoundException {
        JobApplicationResponse jobApplicationResponse = jobApplicationService.applyJob(jobApplicationRequest);
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationRequest.getJobApplicationId()).orElseThrow(()-> new ApplicationNotFoundException("application not found"));
        Employee employee = employeeRepository.findById(jobApplicationRequest.getEmployeeId()).orElseThrow(()-> new UserNotFoundException("Employee not found"));
        jobApplication.setEmployee(employee);
        List<JobApplication> listOfAllJobApplications = employee.getJobApplications();
        employee.setJobApplications(listOfAllJobApplications);
        return jobApplicationResponse;
    }

    private void validateEmployeeEmail(String email) {
        if (!email.matches(EMAIL_REGEX_VALIDATOR.pattern() )) throw new InvalidEmailException("Invalid email format");
    }
    private void validateEmployeePassword(String password) throws InvalidPasswordException {
        if (passwordEncoder.encode(password).matches(password)) throw new InvalidPasswordException(
                """
                        Password must contain at least 8 characters,\
                        including at least one uppercase letter,\
                        one lowercase letter,
                        one digit,
                         and one special character""");
    }


}
