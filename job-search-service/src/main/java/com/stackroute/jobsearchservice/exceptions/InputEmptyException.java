package com.stackroute.jobsearchservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputEmptyException extends RuntimeException{
    String message;
}
