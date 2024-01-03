package com.tiago.testdev.infra.handlers;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(HandlerMethodValidationException.class)
    protected ResponseEntity handleMethodArgumentNotValid(HandlerMethodValidationException ex) {
        var errors = ex.getAllErrors().stream().map(MessageSourceResolvable::getArguments).toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
