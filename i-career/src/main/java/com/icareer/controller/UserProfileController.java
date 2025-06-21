package com.icareer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icareer.entity.UserProfile;
import com.icareer.repository.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository; 

    @Autowired
    private ObjectMapper objectMapper; 

    /***
     * 
     * @param userProfileData
     * @return
     */
    
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
}