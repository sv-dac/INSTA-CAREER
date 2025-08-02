package com.icareer.exception;

public class ErrorMessage {
	private int errorCode;
	private String message;

    public ErrorMessage() {
    	this.errorCode = 0;
		this.message = "An error occurred";
    }

    public ErrorMessage(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}