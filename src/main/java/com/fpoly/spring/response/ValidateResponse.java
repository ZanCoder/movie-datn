package com.fpoly.spring.response;

import java.util.Map;

public class ValidateResponse {
    private boolean validated;
    private Map<String, String> errorMessages;
    private Map<String, String> messages;
 
    public boolean isValidated() {
        return validated;
    }
 
    public void setValidated(boolean validated) {
        this.validated = validated;
    }
 
    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
 
    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
}