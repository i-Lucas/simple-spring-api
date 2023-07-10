package com.example.demo.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyRegistered extends RuntimeException implements IExceptionStatusMapping {

    public EmailAlreadyRegistered() {
        super("This email already registered.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}