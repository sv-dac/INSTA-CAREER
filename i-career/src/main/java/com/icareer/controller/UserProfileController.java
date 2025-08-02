package com.icareer.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icareer.dto.UserProfileKafkaPayload;
import com.icareer.dto.UserProfileRequest;
import com.icareer.entity.UserProfile;
import com.icareer.repository.UserProfileRepository;
import com.icareer.service.UserProfileService;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository; 

    @Autowired
    private ObjectMapper objectMapper; 

    private final UserProfileService userProfileService;
    
    public UserProfileController(UserProfileService userProfileService) {
    	this.userProfileService = userProfileService;
    }
        
    @PostMapping("/save-profile")
    public ResponseEntity<String> saveUserProfile(@RequestBody Map<String, Object> userProfileData) {
        try {
            String jsonString = objectMapper.writeValueAsString(userProfileData);

            UserProfile userProfile = new UserProfile();
            userProfile.setProfileJson(jsonString);

            userProfileRepository.save(userProfile);

            return ResponseEntity.ok("User profile saved successfully!");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error processing JSON: " + e.getMessage());
        }
    }

    @GetMapping("/api/profile/{correlatedId}")
    public ResponseEntity<UserProfileRequest> getProcessedProfile(@PathVariable String correlatedId) {
        Optional<UserProfileRequest> processedProfile = userProfileService.getProcessedProfile(correlatedId);

        return processedProfile
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/api/profile/UserProfileKafkaPayload/{correlatedId}")
    public ResponseEntity<UserProfileKafkaPayload> getUserProfileKafkaPayload(@PathVariable String correlatedId) {
    	Optional<UserProfileKafkaPayload> processedProfile = userProfileService.getUserProfileKafkaPayload(correlatedId);
    	
    	return processedProfile
    			.map(ResponseEntity::ok)
    			.orElse(ResponseEntity.notFound().build());
    }
}