package com.worknext.africa.nija.worknext.data.model;

import com.worknext.africa.nija.worknext.data.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@Entity
public class Employers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyLocation;
    private String companyDescription;
    private String email;
    private String password;
    @Enumerated
    private UserRole role;
    @OneToMany(mappedBy = "employers", cascade = {PERSIST})
    private List<JobPost> jobPosts;
}
