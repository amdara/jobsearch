package com.stackroute.jobsearchservice.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorInfo {

	private LocalDateTime timestamp;
	private HttpStatus httpStatus;
	private String message;
	private String details;
}
