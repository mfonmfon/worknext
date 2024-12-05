package com.worknext.africa.nija.worknext.data.repository;

import com.worknext.africa.nija.worknext.data.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployersRepository extends JpaRepository<Employer, Long> {
    boolean existsByEmail(String email);

    List<Employer> findByCompanyName(String companyName);

    List<Employer> findByCompanyDescription(String companyDescription);

    List<Employer> findByCompanyLocation(String companyLocation);

    List<Employer> findByEmail(String email);

    Optional<Employer> getUserByEmail(String email);

    Optional <Employer> findEmployersByEmail(String email);


//    Optional<Employer> findByEmail(String employerId);
}
