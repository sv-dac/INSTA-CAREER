package com.icareer.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.icareer.dto.UserProfileKafkaPayload;
import com.icareer.dto.UserProfileRequest;

@Service
public class UserProfileService {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    // use a database (like Redis, PostgreSQL, etc.).
    private final Map<String, UserProfileKafkaPayload> processedProfiles = new ConcurrentHashMap<>();

    public void processAndStoreProfile(UserProfileKafkaPayload userProfileData) {
        logger.info("Processing profile for correlatedId : {}", userProfileData.getId());
        processedProfiles.put(userProfileData.getId(), userProfileData);
        logger.info("Stored processed profile for correlatedId : {}", userProfileData.getId());
    }

    public Optional<UserProfileRequest> getProcessedProfile(String correlatedId) {
        return Optional.ofNullable(processedProfiles.get(correlatedId))
            .map(UserProfileKafkaPayload::getModelResp);
    }
}