package com.jatinpatidar.placementpro.service.userProfile;

import com.jatinpatidar.placementpro.dto.profile.request.ProfileSetupRequest;
import com.jatinpatidar.placementpro.dto.profile.request.ProfileUpdateRequest;
import com.jatinpatidar.placementpro.dto.profile.response.UserProfileResponse;
import com.jatinpatidar.placementpro.entity.User;
import com.jatinpatidar.placementpro.entity.UserProfile;
import com.jatinpatidar.placementpro.exceptions.EmailAlreadyExistsException;
import com.jatinpatidar.placementpro.exceptions.ProfileAlreadyExistsException;
import com.jatinpatidar.placementpro.repository.UserProfileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfileResponse setupProfile(ProfileSetupRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        Optional<UserProfile> existingProfile =
                userProfileRepository.findByUser(user);

        if(existingProfile.isPresent()){
            throw new ProfileAlreadyExistsException("Profile already exists");
        }

        UserProfile userProfile = new UserProfile();

        userProfile.setUser(user);
        userProfile.setDob(request.getDob());
        userProfile.setMobileNumber(request.getMobileNumber());
        userProfile.setCurrentCourse(request.getCurrentCourse());
        userProfile.setCurrentYear(request.getCurrentYear());
        userProfile.setGraduationYear(request.getGraduationYear());
        userProfile.setCgpa(request.getCgpa());
        userProfile.setResumeUrl(request.getResumeUrl());
        userProfile.setTargetCompanies(request.getTargetCompanies());

        UserProfile savedProfile = userProfileRepository.save(userProfile);

        UserProfileResponse response = new UserProfileResponse(savedProfile.getId(), user.getFullName(),user.getEmail(),
                savedProfile.getDob(), savedProfile.getMobileNumber(), savedProfile.getCurrentCourse(), savedProfile.getCurrentYear(), savedProfile.getGraduationYear(), savedProfile.getCgpa(), savedProfile.getResumeUrl(), savedProfile.getTargetCompanies());

        return response;
    }

    @Override
    public UserProfileResponse getProfile(){

    return null;
    }

    @Override
    public UserProfileResponse updateProfile(ProfileUpdateRequest request){
  return null;
    }
}
