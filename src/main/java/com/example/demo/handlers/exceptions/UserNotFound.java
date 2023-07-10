package com.example.demo.handlers.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFound extends RuntimeException implements IExceptionStatusMapping {

    public UserNotFound() {
        super("User not found.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
