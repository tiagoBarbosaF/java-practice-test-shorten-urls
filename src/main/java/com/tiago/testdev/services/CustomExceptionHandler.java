package com.tiago.testdev.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(HandlerMethodValidationException.class)
    protected ResponseEntity handleMethodArgumentNotValid(HandlerMethodValidationException ex){
        var errors = ex.getAllErrors().stream().map(MessageSourceResolvable::getArguments).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    public record ErrorValidationData(
            @JsonProperty("campo") String field, @JsonProperty("mensagem") String message) {
        public ErrorValidationData(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
