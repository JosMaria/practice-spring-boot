package com.genesiscode.practicespringboot.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public interface Error {

    default LocalDateTime getTimestamp() {
        return null;
    }

    default String getMessage() {
        return null;
    }

    default Class<?> getExceptionClass() {
        return null;
    }

    default HttpStatus getHttpStatus() {
        return null;
    }
}
