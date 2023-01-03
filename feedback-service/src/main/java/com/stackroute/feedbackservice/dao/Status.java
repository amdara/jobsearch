package com.stackroute.feedbackservice.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Status {
	
	private String message;
    private HttpStatus httpStatus;
    

//	public Status(String message, HttpStatus httpStatus) {
//		super();
//		this.message = message;
//		this.httpStatus = httpStatus;
//	}
    
    
}
