package com.practice.springbootpracticejdbctemplate.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateConflictException extends RuntimeException{
    public DuplicateConflictException(String message) {
        super(message);
    }
}
