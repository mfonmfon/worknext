package com.worknext.africa.nija.worknext.service.implimentation;

import com.worknext.africa.nija.worknext.Dtos.request.EmployerRegistrationRequest;
import com.worknext.africa.nija.worknext.Dtos.response.EmployerRegistrationResponse;
import com.worknext.africa.nija.worknext.data.model.Employees;
import com.worknext.africa.nija.worknext.data.model.Employers;
import com.worknext.africa.nija.worknext.data.repository.EmployersRepository;
import com.worknext.africa.nija.worknext.service.interfaces.EmployersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployersServiceImpl implements EmployersService {

    private final EmployersRepository employersRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployerRegistrationResponse registerEmployers(EmployerRegistrationRequest registrationRequest) {
        Employers employers = buildEmployersRegistrations(registrationRequest);
        employersRepository.save(employers);
        EmployerRegistrationResponse employeeResponse = new EmployerRegistrationResponse();
        employeeResponse.setMessage("Welcome onboard");
        return employeeResponse;
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
