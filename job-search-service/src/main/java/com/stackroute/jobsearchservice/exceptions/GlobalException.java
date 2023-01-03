package com.stackroute.jobsearchservice.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
//Global Exceptional Handler
@RestControllerAdvice
public class GlobalException {

	/*
	 * Handling Custom Exception
	 */
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorInfo> idNotFound(IdNotFoundException exception, WebRequest webRequest) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND);
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);

	}

	/*
	 * Handling Application Exceptions
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> Exception(Exception exception, WebRequest webRequest) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setDetails(webRequest.getDescription(false));

		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * Checking validations for Model class
	 */
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArguments(MethodArgumentNotValidException exception){
		Map<String,String> errorMap=new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error->{				
			errorMap.put(error.getField(),error.getDefaultMessage());
		});
		return errorMap;
	}

	/*
	 * Handling Custom ValueEmptyException
	 */
	@ExceptionHandler(ValueEmptyException.class)
	public ResponseEntity<ErrorInfo> emptyData(ValueEmptyException exception,WebRequest webRequest) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setHttpStatus(HttpStatus.NO_CONTENT);
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}

	/*
	 * Handling Custom InputEmptyException
	 */
	@ExceptionHandler(InputEmptyException.class)
	public ResponseEntity<ErrorInfo> emptyInput(ValueEmptyException exception,WebRequest webRequest) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}

	/*
	 * Handling Custom InvalidAppliedJobException
	 */
	@ExceptionHandler(InvalidAppliedJobException.class)
	public ResponseEntity<ErrorInfo> appliedJob(InvalidAppliedJobException exception, WebRequest webRequest) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND);
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}
}
