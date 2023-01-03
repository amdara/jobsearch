package com.stackroute.jobservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> idNotFound(IdNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentValidException(MethodArgumentNotValidException exception){
        Map<String, String> result= new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
          String fieldName=  ((FieldError)error).getField();
          String message = error.getDefaultMessage();
          result.put(fieldName,message);
        });
        return new ResponseEntity<Map<String, String>>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> Exception(Exception exception, WebRequest webRequest) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setTimestamp(LocalDateTime.now());
        errorInfo.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorInfo.setMessage(exception.getMessage());
        errorInfo.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
