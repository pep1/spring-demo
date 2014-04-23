package com.gentics.spring.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorStatus {

	private String message;

	private Exception exception;

	public ErrorStatus(String message, Exception e) {
		super();
		this.message = message;
		this.exception = e;
	}

	public ErrorStatus() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception.getMessage();
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
