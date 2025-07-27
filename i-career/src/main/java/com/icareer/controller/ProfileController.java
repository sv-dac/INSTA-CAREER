package com.icareer.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.icareer.dto.UserProfileRequest;

import jakarta.validation.Valid;

@RestController
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    /*
    @PostMapping("/process-profile")
    public ResponseEntity<String> processProfile(@Valid @RequestBody ProfileRequest payload) {
        logger.info("Received Payload: {}", payload);
        logger.info("Interests: {}", payload.getInterests());
        
        if (payload.getCareerJustifications() != null) {
            String dataScientistJustification = payload.getCareerJustifications().get("Data Scientist");
            logger.info("Data Scientist Justification: {}", dataScientistJustification);
        }
        
        logger.info("Confidence Scores: {}", payload.getConfidenceScores());
        return ResponseEntity.ok("Profile data received and processed!");
    }
    */
    
    @PostMapping("/process-user-profile")
    public ResponseEntity<String> processUserProfile(@Valid @RequestBody UserProfileRequest userProfileData) {
        // Log all major fields for traceability
        logger.info("Interests: {}", userProfileData.getInterests());
        logger.info("Career Suggestions: {}", userProfileData.getCareerSuggestions());
        logger.info("Mapped Interest to Careers: {}", userProfileData.getMappedInterestToCareers());
        logger.info("Career Justifications: {}", userProfileData.getCareerJustifications());
        logger.info("Interest Traits: {}", userProfileData.getInterestTraits());
        logger.info("Confidence Scores: {}", userProfileData.getConfidenceScores());
        logger.info("Values: {}", userProfileData.getValues());
        logger.info("Emotional Patterns: {}", userProfileData.getEmotionalPatterns());
        logger.info("Self Concept: {}", userProfileData.getSelfConcept());
        logger.info("OCEAN Traits: {}", userProfileData.getOceanTraits());
        logger.info("Content Themes: {}", userProfileData.getContentThemes());
        logger.info("Psychological Insights: {}", userProfileData.getPsychologicalInsights());

        String topCareer = null;
        Double topConfidence = null;
        String topJustification = null;
        
        if (userProfileData.getConfidenceScores() != null && !userProfileData.getConfidenceScores().isEmpty()) {
            topCareer = userProfileData.getConfidenceScores().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
            topConfidence = userProfileData.getConfidenceScores().get(topCareer);
            if (userProfileData.getCareerJustifications() != null) {
                topJustification = userProfileData.getCareerJustifications().get(topCareer);
            }
        }

        StringBuilder oceanSummary = new StringBuilder();
        if (userProfileData.getOceanTraits() != null) {
            for (Map.Entry<String, Map<String, Object>> entry : userProfileData.getOceanTraits().entrySet()) {
                String trait = entry.getKey();
                Map<String, Object> details = entry.getValue();
                oceanSummary.append(String.format("%s: score=%s, desc=%s. ",
                    trait,
                    details != null ? details.get("score") : "N/A",
                    details != null ? details.get("description") : "N/A"
                ));
            }
        }

        StringBuilder response = new StringBuilder();
        response.append("Processed user profile.\n");
        if (topCareer != null) {
            response.append(String.format("Top Career Suggestion: %s (Confidence: %.2f)\nJustification: %s\n",
                topCareer, topConfidence, topJustification != null ? topJustification : "N/A"));
        }
        if (oceanSummary.length() > 0) {
            response.append("OCEAN Traits Summary: ").append(oceanSummary).append("\n");
        }
        response.append(String.format("Interests: %d, Career Suggestions: %d, Values: %d, Content Themes: %d, Insights: %d",
            userProfileData.getInterests() != null ? userProfileData.getInterests().size() : 0,
            userProfileData.getCareerSuggestions() != null ? userProfileData.getCareerSuggestions().size() : 0,
            userProfileData.getValues() != null ? userProfileData.getValues().size() : 0,
            userProfileData.getContentThemes() != null ? userProfileData.getContentThemes().size() : 0,
            userProfileData.getPsychologicalInsights() != null ? userProfileData.getPsychologicalInsights().size() : 0
        ));

        return ResponseEntity.ok(response.toString());
    }

    @PostMapping("/process-profile-jsonnode")
    public String processProfileJsonNode(@RequestBody JsonNode payload) {
        System.out.println("Received Payload as JsonNode: " + payload.toPrettyString());

        JsonNode interestsNode = payload.get("interests");
        if (interestsNode != null && interestsNode.isArray()) {
            for (JsonNode interest : interestsNode) {
                System.out.println("Interest: " + interest.asText());
            }
        }

        JsonNode careerJustificationsNode = payload.get("career_justifications");
        if (careerJustificationsNode != null && careerJustificationsNode.isObject()) {
            JsonNode dataScientistJustificationNode = careerJustificationsNode.get("Data Scientist");
            if (dataScientistJustificationNode != null) {
                System.out.println("Data Scientist Justification: " + dataScientistJustificationNode.asText());
            }
        }

        JsonNode oceanTraitsNode = payload.get("ocean_traits");
        if (oceanTraitsNode != null) {
            JsonNode opennessScoreNode = oceanTraitsNode.get("openness").get("score");
            if (opennessScoreNode != null) {
                System.out.println("Openness Score: " + opennessScoreNode.asInt());
            }
        }

        return "Profile data received and processed using JsonNode!";
    }
}