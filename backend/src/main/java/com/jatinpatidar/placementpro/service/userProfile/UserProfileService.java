package com.jatinpatidar.placementpro.service.userProfile;

import com.jatinpatidar.placementpro.dto.profile.request.ProfileSetupRequest;
import com.jatinpatidar.placementpro.dto.profile.request.ProfileUpdateRequest;
import com.jatinpatidar.placementpro.dto.profile.response.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse setupProfile(ProfileSetupRequest request);

    UserProfileResponse getProfile();

    UserProfileResponse updateProfile(ProfileUpdateRequest request);
}
