package com.example.demo.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUUID extends Exception implements IExceptionStatusMapping {
    public InvalidUUID() {
        super("Invalid UUID format.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
