package com.worknext.africa.nija.worknext.Dtos.request;

import com.worknext.africa.nija.worknext.data.enums.JobType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class EditJobPostRequest {
    private Long jobPostId;
    private String jobTitle;
    private String jobDescription;
    private JobType jobType;
    private BigDecimal salaryRange;
    private LocalDateTime lastModifiedAt;
}
