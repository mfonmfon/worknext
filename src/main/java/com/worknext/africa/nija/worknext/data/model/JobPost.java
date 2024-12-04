package com.worknext.africa.nija.worknext.data.model;

import com.worknext.africa.nija.worknext.data.enums.JobType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private JobType jobType;
    private BigDecimal salaryRange;
    private LocalDateTime postedAt;
    private LocalDateTime lastModified;
    @ManyToOne
    private Employer employer;
}
