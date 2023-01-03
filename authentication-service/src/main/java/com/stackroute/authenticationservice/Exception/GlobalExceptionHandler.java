package com.stackroute.authenticationservice.Exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlesRoleNotFoundException(RoleNotFoundException exception, WebRequest webRequest) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(new Date());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setPath(webRequest.getDescription(false));

		return ResponseEntity.ok(errorResponse);

	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handlesException(Exception exception, WebRequest webRequest) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(new Date());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setPath(webRequest.getDescription(false));

		return ResponseEntity.ok(errorResponse);

	}
}
