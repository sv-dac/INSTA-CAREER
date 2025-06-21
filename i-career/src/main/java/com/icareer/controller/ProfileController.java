package com.icareer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;
import java.util.List;

@RestController
public class ProfileController {

    @PostMapping("/process-profile")
    public String processProfile(@RequestBody Map<String, Object> payload) {
        System.out.println("Received Payload: " + payload);

        List<String> interests = (List<String>) payload.get("interests");
        System.out.println("Interests: " + interests);

        Map<String, Object> careerJustifications = (Map<String, Object>) payload.get("career_justifications");
        if (careerJustifications != null) {
            String dataScientistJustification = (String) careerJustifications.get("Data Scientist");
            System.out.println("Data Scientist Justification: " + dataScientistJustification);
        }

        System.out.println("Confidence Scores: " + payload.get("confidence_scores"));

        return "Profile data received and processed!";
    }
    
    @PostMapping("/process-user-profile")
    public ResponseEntity<String> processUserProfile(@RequestBody Map<String, Object> userProfileData) {

        List<String> interests = (List<String>) userProfileData.get("interests");
        
        List<String> careerSuggestions = (List<String>) userProfileData.get("career_suggestions");
        
        Map<String, List<String>> mappedInterestToCareers = (Map<String, List<String>>) userProfileData.get("mapped_interest_to_careers");
        
        Map<String, String> careerJustifications = (Map<String, String>) userProfileData.get("career_justifications");
        
        Map<String, List<String>> interestTraits = (Map<String, List<String>>) userProfileData.get("interest_traits");
        
        Map<String, Double> confidenceScores = (Map<String, Double>) userProfileData.get("confidence_scores");
        
        List<String> values = (List<String>) userProfileData.get("values");
        
        List<String> emotionalPatterns = (List<String>) userProfileData.get("emotional_patterns");
        
        List<String> selfConcept = (List<String>) userProfileData.get("self_concept");
        
        Map<String, Map<String, Object>> oceanTraits = (Map<String, Map<String, Object>>) userProfileData.get("ocean_traits");
        
        List<String> contentThemes = (List<String>) userProfileData.get("content_themes");
        
        List<String> psychologicalInsights = (List<String>) userProfileData.get("psychological_insights");

        if (oceanTraits != null && oceanTraits.containsKey("openness")) {
            Map<String, Object> opennessTrait = oceanTraits.get("openness");
            Integer opennessScore = (Integer) opennessTrait.get("score");
            String opennessDescription = (String) opennessTrait.get("description");
            System.out.println("Openness Score: " + opennessScore);
            System.out.println("Openness Description: " + opennessDescription);
        }

        System.out.println("Received interests: " + interests);
        System.out.println("Received career suggestions: " + careerSuggestions);

        return ResponseEntity.ok("User profile data received and processed successfully!");
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