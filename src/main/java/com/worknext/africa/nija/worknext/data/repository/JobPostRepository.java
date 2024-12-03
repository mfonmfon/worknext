package com.worknext.africa.nija.worknext.data.repository;

import com.worknext.africa.nija.worknext.data.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}
