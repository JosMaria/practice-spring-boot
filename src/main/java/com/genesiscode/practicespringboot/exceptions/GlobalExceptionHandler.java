package com.genesiscode.practicespringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    public ResponseEntity<Error> handleEmailAlreadyExists(EmailAlreadyExistsException exception) {
        Error error = new ErrorResponse(LocalDateTime.now(), exception.getMessage(),
                                        exception.getClass(), HttpStatus.PRECONDITION_REQUIRED);
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
}
