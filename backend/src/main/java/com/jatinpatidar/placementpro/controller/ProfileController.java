package com.jatinpatidar.placementpro.controller;

import com.jatinpatidar.placementpro.dto.profile.request.ProfileSetupRequest;
import com.jatinpatidar.placementpro.dto.profile.request.ProfileUpdateRequest;
import com.jatinpatidar.placementpro.dto.profile.response.UserProfileResponse;
import com.jatinpatidar.placementpro.service.user.UserService;
import com.jatinpatidar.placementpro.service.userProfile.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class ProfileController {
    private final UserProfileService userProfileService;

    public ProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfileResponse> setupProfile(
            @RequestBody ProfileSetupRequest request) {

        UserProfileResponse response =
                userProfileService.setupProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
