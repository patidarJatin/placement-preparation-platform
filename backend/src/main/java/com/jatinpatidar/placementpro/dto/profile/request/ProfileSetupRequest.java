package com.jatinpatidar.placementpro.dto.profile.request;


import com.jatinpatidar.placementpro.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSetupRequest {

    private LocalDate dob;
    private String mobileNumber;
    private String currentCourse;
    private String currentYear;
    private String graduationYear;
    private Float cgpa;
    private String resumeUrl;
    private String targetCompanies;

}
