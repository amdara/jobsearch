package com.stackroute.recruiterservice.controlleradvice;

import com.stackroute.recruiterservice.dto.Status;
import com.stackroute.recruiterservice.dto.Validation;
import com.stackroute.recruiterservice.exception.EmptyInputException;
import com.stackroute.recruiterservice.exception.EmptyValueException;
import com.stackroute.recruiterservice.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionhandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Status> idNotfound(IdNotFoundException exception) {
		return new ResponseEntity<Status>(new Status(exception.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyValueException.class)
	public ResponseEntity<Status> emptyData(EmptyValueException exception) {
		return new ResponseEntity<Status>(new Status(exception.getMessage(), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
	}


	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<Status> emptyInput(EmptyInputException exception) {
		return new ResponseEntity<Status>(new Status(exception.getMessage(), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Status> exception(Exception exception) {
		return new ResponseEntity<Status>(new Status(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Validation methodException(MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(
				error -> {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
		);
		return new Validation(errorMap, HttpStatus.BAD_REQUEST);
	}

}

