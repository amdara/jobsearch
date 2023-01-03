package com.stackroute.recruiterservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdNotFoundException extends RuntimeException{
    String message;
}



