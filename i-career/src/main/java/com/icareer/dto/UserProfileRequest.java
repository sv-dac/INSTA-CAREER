package com.icareer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserProfileRequest implements Serializable {
	private static final long serialVersionUID = 2572900171853663268L;	
	/**
	 * List of user's interests (required)
	 */
	@NotNull
	@NotEmpty
	@Size(min = 1)
	private List<String> interests;
	/**
	 * List of suggested careers for the user
	 */
	private List<String> careerSuggestions;
	/**
	 * Mapping from interest to possible careers
	 */
	private Map<String, List<String>> mappedInterestToCareers;
	/**
	 * Justifications for each career
	 */
	private Map<String, String> careerJustifications;
	/**
	 * Traits mapped to each interest
	 */
	private Map<String, List<String>> interestTraits;
	/**
	 * Confidence scores for each career
	 */
	private Map<String, Double> confidenceScores;
	/**
	 * User's values
	 */
	private List<String> values;
	/**
	 * User's emotional patterns
	 */
	private List<String> emotionalPatterns;
	/**
	 * User's self-concept descriptors
	 */
	private List<String> selfConcept;
	/**
	 * OCEAN (Big Five) personality traits
	 */
	private Map<String, Map<String, Object>> oceanTraits;
	/**
	 * Content themes from user's data
	 */
	private List<String> contentThemes;
	/**
	 * Psychological insights
	 */
	private List<String> psychologicalInsights;

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public List<String> getCareerSuggestions() {
		return careerSuggestions;
	}

	public void setCareerSuggestions(List<String> careerSuggestions) {
		this.careerSuggestions = careerSuggestions;
	}

	public Map<String, List<String>> getMappedInterestToCareers() {
		return mappedInterestToCareers;
	}

	public void setMappedInterestToCareers(Map<String, List<String>> mappedInterestToCareers) {
		this.mappedInterestToCareers = mappedInterestToCareers;
	}

	public Map<String, String> getCareerJustifications() {
		return careerJustifications;
	}

	public void setCareerJustifications(Map<String, String> careerJustifications) {
		this.careerJustifications = careerJustifications;
	}

	public Map<String, List<String>> getInterestTraits() {
		return interestTraits;
	}

	public void setInterestTraits(Map<String, List<String>> interestTraits) {
		this.interestTraits = interestTraits;
	}

	public Map<String, Double> getConfidenceScores() {
		return confidenceScores;
	}

	public void setConfidenceScores(Map<String, Double> confidenceScores) {
		this.confidenceScores = confidenceScores;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public List<String> getEmotionalPatterns() {
		return emotionalPatterns;
	}

	public void setEmotionalPatterns(List<String> emotionalPatterns) {
		this.emotionalPatterns = emotionalPatterns;
	}

	public List<String> getSelfConcept() {
		return selfConcept;
	}

	public void setSelfConcept(List<String> selfConcept) {
		this.selfConcept = selfConcept;
	}

	public Map<String, Map<String, Object>> getOceanTraits() {
		return oceanTraits;
	}

	public void setOceanTraits(Map<String, Map<String, Object>> oceanTraits) {
		this.oceanTraits = oceanTraits;
	}

	public List<String> getContentThemes() {
		return contentThemes;
	}

	public void setContentThemes(List<String> contentThemes) {
		this.contentThemes = contentThemes;
	}

	public List<String> getPsychologicalInsights() {
		return psychologicalInsights;
	}

	public void setPsychologicalInsights(List<String> psychologicalInsights) {
		this.psychologicalInsights = psychologicalInsights;
	}

	@Override
	public String toString() {
		return "UserProfileRequest{" +
				"interests=" + interests +
				", careerSuggestions=" + careerSuggestions +
				", mappedInterestToCareers=" + mappedInterestToCareers +
				", careerJustifications=" + careerJustifications +
				", interestTraits=" + interestTraits +
				", confidenceScores=" + confidenceScores +
				", values=" + values +
				", emotionalPatterns=" + emotionalPatterns +
				", selfConcept=" + selfConcept +
				", oceanTraits=" + oceanTraits +
				", contentThemes=" + contentThemes +
				", psychologicalInsights=" + psychologicalInsights +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserProfileRequest that = (UserProfileRequest) o;
		return Objects.equals(interests, that.interests) &&
				Objects.equals(careerSuggestions, that.careerSuggestions) &&
				Objects.equals(mappedInterestToCareers, that.mappedInterestToCareers) &&
				Objects.equals(careerJustifications, that.careerJustifications) &&
				Objects.equals(interestTraits, that.interestTraits) &&
				Objects.equals(confidenceScores, that.confidenceScores) &&
				Objects.equals(values, that.values) &&
				Objects.equals(emotionalPatterns, that.emotionalPatterns) &&
				Objects.equals(selfConcept, that.selfConcept) &&
				Objects.equals(oceanTraits, that.oceanTraits) &&
				Objects.equals(contentThemes, that.contentThemes) &&
				Objects.equals(psychologicalInsights, that.psychologicalInsights);
	}

	@Override
	public int hashCode() {
		return Objects.hash(interests, careerSuggestions, mappedInterestToCareers, careerJustifications, interestTraits, confidenceScores, values, emotionalPatterns, selfConcept, oceanTraits, contentThemes, psychologicalInsights);
	}
}