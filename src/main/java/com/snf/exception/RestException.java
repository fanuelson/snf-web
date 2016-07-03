package com.snf.exception;

import javax.ws.rs.core.Response.Status;

public class RestException {

	private Status status;
	private String message;
	
	public RestException() { }
	
	public RestException(String message, Status status) {
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
