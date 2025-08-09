package com.icareer.service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icareer.repository.UserRequestRepository;

@Service
public class UserProfileStatusService {

	@Autowired
	UserRequestRepository userRequestRepository;
	
	public static enum UserProfileStatus {
		PENDING, COMPLETED, FAILED
	};
	
	private final Map<String, UserProfileStatus> userProfileStatusMap = new ConcurrentHashMap<String, UserProfileStatus>();
	
	public Optional<UserProfileStatus> getStatus(String correlatedId) {
		return Optional.ofNullable(userProfileStatusMap.get(correlatedId)).or(
				() -> {
					userRequestRepository.findById(UUID.fromString(correlatedId))
						.ifPresent(userRequest -> {
							userProfileStatusMap.put(correlatedId, userRequest.getStatus().name().equals("COMPLETED") ? UserProfileStatus.COMPLETED : userRequest.getStatus().name().equals("FAILED") ? UserProfileStatus.FAILED : UserProfileStatus.PENDING);							
						}
					);			
					return Optional.ofNullable(userProfileStatusMap.get(correlatedId));
		});
	}
	
	public void setStatus(String correlatedId, UserProfileStatus status) {
		userProfileStatusMap.put(correlatedId, status);
	}
}