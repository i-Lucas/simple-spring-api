package com.example.demo.handlers;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    // Adicione outros construtores, se necessário
}
