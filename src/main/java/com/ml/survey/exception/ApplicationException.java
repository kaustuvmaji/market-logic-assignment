package com.ml.survey.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 2652652490033282102L;
	private String message, code;
	public ApplicationException(String code, String message) {
		super(message);
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public String getCode() {
		return code;
	}
}
