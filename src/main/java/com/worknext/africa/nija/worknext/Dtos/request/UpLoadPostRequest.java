package com.worknext.africa.nija.worknext.Dtos.request;

import com.worknext.africa.nija.worknext.data.enums.JobType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class UpLoadPostRequest {
    private Long jobPostId;
    private Long employerId;
    private String jobTitle;
    private String jobDescription;
    private JobType jobType;
    private BigDecimal salaryRange;
    private LocalDateTime postedAt;

}
