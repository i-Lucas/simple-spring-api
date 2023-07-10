package com.example.demo.handlers;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.handlers.exceptions.IExceptionStatusMapping;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> responseBody = new HashMap<>();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception) {

        HttpStatus status = this.getStatus(exception);
        responseBody.putAll(Map.of("message", exception.getMessage(), "status", status));
        return new ResponseEntity<>(responseBody, status);
    }

    private HttpStatus getStatus(Exception exception) {

        if (exception instanceof IExceptionStatusMapping)
            return ((IExceptionStatusMapping) exception).getStatus();

        return HttpStatus.INTERNAL_SERVER_ERROR; // não mapeado
    }
}
