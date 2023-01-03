package com.stackroute.recruiterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Validation {
    private Map<String,String> message;
    private HttpStatus httpStatus;
}
