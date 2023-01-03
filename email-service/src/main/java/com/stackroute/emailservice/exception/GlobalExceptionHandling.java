package com.stackroute.emailservice.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.stackroute.emailservice.util.Constants.FAILURE_MESSAGE;

@ControllerAdvice
public class GlobalExceptionHandling {

    Log log = LogFactory.getLog(GlobalExceptionHandling.class);

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> invalidEmailException(InvalidEmailException exception) {
        log.error(FAILURE_MESSAGE + " " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception exception) {
        log.error(FAILURE_MESSAGE + " " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}