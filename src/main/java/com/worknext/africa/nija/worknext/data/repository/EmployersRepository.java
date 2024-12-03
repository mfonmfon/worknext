package com.worknext.africa.nija.worknext.data.repository;

import com.worknext.africa.nija.worknext.data.model.Employers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployersRepository extends JpaRepository<Employers, Long> {
    boolean existsByEmail(String email);

    List<Employers> findByCompanyName(String companyName);

    List<Employers> findByCompanyDescription(String companyDescription);

    List<Employers> findByCompanyLocation(String companyLocation);

    List<Employers> findByEmail(String email);

}
