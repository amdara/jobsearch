package com.stackroute.authenticationservice.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ErrorResponse {

	private Date timestamp;
	private String message;
	private String path;


}