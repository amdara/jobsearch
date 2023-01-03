package com.stackroute.jobservice.exception;

public class IdNotFoundException extends RuntimeException{
    String message;

    public IdNotFoundException(String message) {
        super();
        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }

    public IdNotFoundException() {
        super();
    }
}
