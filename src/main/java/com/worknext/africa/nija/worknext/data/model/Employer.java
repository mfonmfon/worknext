package com.worknext.africa.nija.worknext.data.model;

import com.worknext.africa.nija.worknext.data.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyLocation;
    private String companyDescription;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isLoggedIn;
    @Enumerated
    private UserRole role;
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPost> jobPosts;
}
