package com.genesiscode.practicespringboot.problems;

import com.genesiscode.practicespringboot.problems.exceptions.EmailAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    public ResponseEntity<Error> handleEmailAlreadyExists(EmailAlreadyExistsException exception) {
        Error error = new ErrorResponse(LocalDateTime.now(), exception.getMessage(),
                                        exception.getClass(), HttpStatus.PRECONDITION_REQUIRED);
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    @ExceptionHandler(value = {DateTimeParseException.class})
    public ResponseEntity<Error> handleDateTimeParse(DateTimeParseException exception) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), exception.getMessage(),
                                                exception.getClass(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
