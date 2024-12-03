package com.worknext.africa.nija.worknext.data.repository;

import com.worknext.africa.nija.worknext.data.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface JobPostRepository extends JpaRepository<JobPost, Long> {
        List<JobPost> findByJobTitle(String title);

        List<JobPost> findByJobDescription(String description);
    }
