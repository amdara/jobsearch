package com.stackroute.jobservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorInfo {
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private String message;
    private String details;

}
