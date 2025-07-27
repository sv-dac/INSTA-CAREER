package com.icareer.dto;

import java.io.Serializable;
import java.util.List;

public class UserProfileKafkaPayload implements Serializable {
    private static final long serialVersionUID = -1245079380554063265L;
    
	private String id;
    private List<String> raw_descriptions;
    private List<String> cleaned_descriptions;
    private String model_prompt;
    private UserProfileRequest model_res;

    public UserProfileKafkaPayload() {
    	super();
	}
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getRawDescriptions() {
        return raw_descriptions;
    }
    public void setRawDescriptions(List<String> raw_descriptions) {
        this.raw_descriptions = raw_descriptions;
    }
    public List<String> getCleanedDescriptions() {
        return cleaned_descriptions;
    }
    public void setCleanedDescriptions(List<String> cleaned_descriptions) {
        this.cleaned_descriptions = cleaned_descriptions;
    }
    public String getModelPrompt() {
        return model_prompt;
    }
    public void setModelPrompt(String model_prompt) {
        this.model_prompt = model_prompt;
    }
    public UserProfileRequest getModelResp() {
        return model_res;
    }
    public void setModelResp(UserProfileRequest model_res) {
        this.model_res = model_res;
    }

	@Override
	public String toString() {
		return "UserProfileKafkaPayload [id=" + id + ", raw_descriptions=" + raw_descriptions
				+ ", cleaned_descriptions=" + cleaned_descriptions + ", model_prompt=" + model_prompt + ", model_res="
				+ model_res + "]";
	}
}
