package com.genesiscode.practicespringboot.problems;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse implements Error {

    private final LocalDateTime timestamp;
    private final String message;
    private final Class<?> exceptionClass;
    private final HttpStatus httpStatus;

    public ErrorResponse(LocalDateTime timestamp, String message, Class<?> exceptionClass, HttpStatus httpStatus) {
        this.timestamp = timestamp;
        this.message = message;
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Class<?> getExceptionClass() {
        return exceptionClass;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
