package com.stackroute.emailservice.exception;

public class InvalidEmailException extends RuntimeException {
    private final String message;

    public InvalidEmailException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
