package com.example.demo.handlers;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(responseBody);
    }

    // Adicione outros métodos para tratar diferentes exceções, se necessário
}
