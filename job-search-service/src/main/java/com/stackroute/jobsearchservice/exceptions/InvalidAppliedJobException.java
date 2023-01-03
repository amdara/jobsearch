package com.stackroute.jobsearchservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InvalidAppliedJobException extends RuntimeException{
    String message;
}
