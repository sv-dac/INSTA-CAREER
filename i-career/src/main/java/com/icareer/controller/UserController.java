package com.icareer.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.icareer.dto.UserProfileRequest;
import com.icareer.entity.User;
import com.icareer.exception.InstaCareerException;
import com.icareer.service.UserProfileService;
import com.icareer.service.UserProfileStatusService;
import com.icareer.service.UserProfileStatusService.UserProfileStatus;
import com.icareer.service.UserService;

@RestController
public class UserController {
	private final UserService userService;
	private UserProfileStatusService userProfileStatusService; 	
	private final UserProfileService userProfileService;
    
	
	public UserController(UserService userService, UserProfileService userProfileService, UserProfileStatusService userProfileStatusService) {
		this.userService = userService;
		this.userProfileService = userProfileService;
		this.userProfileStatusService = userProfileStatusService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody JsonNode json) throws InstaCareerException {
	    String email = json.get("email").asText();
	    String password = json.get("password").asText();
	        
	    return userService.login(email, password);
	    
//	    return userService.login(email, password) ? "Login successful" : "Login failed";	            
	}

	@PostMapping("/register")
	public User register(@RequestBody User user) throws InstaCareerException {
		return userService.register(user);
	}

	@DeleteMapping("/delete/{id}")
	public <T> ResponseEntity<T> delete(@PathVariable String id) {
		return userService.deleteUser(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	@PatchMapping("/update/{id}")
	public User update(@RequestBody User user, @PathVariable String id) throws InstaCareerException {
		return userService.updateUser(user, id);
	}
	    
    @GetMapping("/api/profile/{correlatedId}")
    public ResponseEntity<UserProfileRequest> getProcessedProfile(@PathVariable String correlatedId) {
    	Optional<UserProfileStatus> profileStatus = userProfileStatusService.getStatus(correlatedId);
    	
    	if (profileStatus.isEmpty()) {
    		return ResponseEntity.badRequest().build();
    	}
    	
    	if (profileStatus.get().equals(UserProfileStatus.FAILED)) {
    		return ResponseEntity.internalServerError().build();
    	}
    	
    	if (profileStatus.get().equals(UserProfileStatus.PENDING)) {
    		return ResponseEntity.accepted().body(null);
    	}
    	
        Optional<UserProfileRequest> processedProfile = userProfileService.getProcessedProfile(correlatedId);

        return processedProfile
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

}