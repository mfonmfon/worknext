package com.worknext.africa.nija.worknext.data.repository;

import com.worknext.africa.nija.worknext.data.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository  extends JpaRepository<JobApplication, Long> {
}
