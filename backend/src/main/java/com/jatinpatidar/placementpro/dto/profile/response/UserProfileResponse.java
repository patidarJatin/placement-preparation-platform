package com.jatinpatidar.placementpro.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

        private Long id;
        private String fullName;
        private String email;

        private LocalDate dob;
        private String mobileNumber;
        private String currentCourse;
        private String currentYear;
        private String graduationYear;
        private Float cgpa;
        private String resumeUrl;
        private String targetCompanies;

}
