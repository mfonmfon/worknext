package com.worknext.africa.nija.worknext.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String summary;
    private String certificate;
    private String professionalExperience;
    private String skills;
    private String projects;
    private String education;
    private LocalDateTime startedAt;
    private LocalDateTime  endedAt;
    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Employee employee;


}
