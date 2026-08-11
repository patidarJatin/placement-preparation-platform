package com.jatinpatidar.placementpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profiles")
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false,unique = true)
    private User user;

    private LocalDate dob;

    private String mobileNumber;

    private String currentCourse;
    private String currentYear;
    private String graduationYear;

    private Float cgpa;

    private String resumeUrl;

    private String targetCompanies;
    private Boolean profileCompleted;

    private LocalDateTime profileCompletedAt;


}
