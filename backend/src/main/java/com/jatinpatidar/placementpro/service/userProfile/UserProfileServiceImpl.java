package com.jatinpatidar.placementpro.service.userProfile;

import com.jatinpatidar.placementpro.dto.profile.request.ProfileSetupRequest;
import com.jatinpatidar.placementpro.dto.profile.request.ProfileUpdateRequest;
import com.jatinpatidar.placementpro.dto.profile.response.UserProfileResponse;
import com.jatinpatidar.placementpro.entity.User;
import com.jatinpatidar.placementpro.entity.UserProfile;
import com.jatinpatidar.placementpro.exceptions.EmailAlreadyExistsException;
import com.jatinpatidar.placementpro.exceptions.ProfileAlreadyExistsException;
import com.jatinpatidar.placementpro.exceptions.ProfileNotFoundException;
import com.jatinpatidar.placementpro.repository.UserProfileRepository;
import com.jatinpatidar.placementpro.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    @Override
    public UserProfileResponse setupProfile(ProfileSetupRequest request) {
        User user = getCurrentUser();
        Optional<UserProfile> existingProfile =
                userProfileRepository.findByUser(user);

        if (existingProfile.isPresent()) {
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

        return toResponse(user, savedProfile);
    }

    @Override
    public UserProfileResponse getProfile() {
        User user = getCurrentUser();
        Optional<UserProfile> userProfile =
                userProfileRepository.findByUser(user);
        if (userProfile.isEmpty()) {
            throw new ProfileNotFoundException("Profile not found");
        }
        UserProfile profile = userProfile.get();
        return toResponse(user, profile);
    }

    @Override
    public UserProfileResponse updateProfile(ProfileUpdateRequest request) {
        User user = getCurrentUser();
        Optional<UserProfile> userProfile =
                userProfileRepository.findByUser(user);

        if(userProfile.isEmpty()){
            throw new ProfileNotFoundException("Profile not found");
        }
        UserProfile profile = userProfile.get();

        user.setFullName(request.getFullName());
        profile.setDob(request.getDob());
        profile.setMobileNumber(request.getMobileNumber());
        profile.setCurrentCourse(request.getCurrentCourse());
        profile.setCurrentYear(request.getCurrentYear());
        profile.setGraduationYear(request.getGraduationYear());
        profile.setCgpa(request.getCgpa());
        profile.setResumeUrl(request.getResumeUrl());
        profile.setTargetCompanies(request.getTargetCompanies());


        userRepository.save(user);
        UserProfile savedProfile = userProfileRepository.save(profile);

        return toResponse(user, savedProfile);
    }
    private UserProfileResponse toResponse(User user, UserProfile profile) {
        return new UserProfileResponse(
                profile.getId(),
                user.getFullName(),
                user.getEmail(),
                profile.getDob(),
                profile.getMobileNumber(),
                profile.getCurrentCourse(),
                profile.getCurrentYear(),
                profile.getGraduationYear(),
                profile.getCgpa(),
                profile.getResumeUrl(),
                profile.getTargetCompanies()
        );
    }
}
