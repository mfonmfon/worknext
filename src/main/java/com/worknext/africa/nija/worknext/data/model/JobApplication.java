package com.worknext.africa.nija.worknext.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String coverLetter;
    private String linkedInUrl;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    @ManyToOne
//    @JoinColumn(name = "job_post_id", nullable =false)
    private JobPost jobPost;
}
