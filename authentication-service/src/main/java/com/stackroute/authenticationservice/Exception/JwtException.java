package com.stackroute.authenticationservice.Exception;

import org.springframework.http.HttpStatus;


public class JwtException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private String message;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JwtException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}

	@Override
	public String toString() {
		return "JwtException [httpStatus=" + httpStatus + ", message=" + message + "]";
	}
	
	

}
