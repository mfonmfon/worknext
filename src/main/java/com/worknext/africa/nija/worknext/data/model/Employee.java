package com.worknext.africa.nija.worknext.data.model;

import com.worknext.africa.nija.worknext.data.enums.IndustryName;
import com.worknext.africa.nija.worknext.data.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private IndustryName industryName;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "employee", cascade = {PERSIST})
    private List<Resume> resume;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<JobApplication> jobApplications;
}
