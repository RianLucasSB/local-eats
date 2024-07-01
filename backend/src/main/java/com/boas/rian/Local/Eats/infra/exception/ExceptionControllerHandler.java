package com.boas.rian.Local.Eats.infra.exception;

import com.boas.rian.Local.Eats.domain.exceptions.InvalidCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
