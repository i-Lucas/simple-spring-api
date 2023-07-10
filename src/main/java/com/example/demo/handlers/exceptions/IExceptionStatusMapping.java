package com.example.demo.handlers.exceptions;

import org.springframework.http.HttpStatus;

public interface IExceptionStatusMapping {
    HttpStatus getStatus();
}