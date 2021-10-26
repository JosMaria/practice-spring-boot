package com.genesiscode.practicespringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class EmailAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
