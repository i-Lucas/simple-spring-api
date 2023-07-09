package com.example.demo.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("status", this.getStatus(exception));
        return ResponseEntity.status(this.getStatus(exception)).body(responseBody);
    }

    private HttpStatus getStatus(Exception exception) {

        if (exception instanceof UserNotFoundException) {
            return ((UserNotFoundException) exception).getStatus();

        } else if (exception instanceof EmailAlreadyRegisteredException) {
            return ((EmailAlreadyRegisteredException) exception).getStatus();
        }

        return HttpStatus.INTERNAL_SERVER_ERROR; // não mapeado
    }
}
