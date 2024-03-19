package com.practice.springbootpracticejdbctemplate.Exception;

public class DuplicateConflictException extends RuntimeException{
    public DuplicateConflictException(String message) {
        super(message);
    }
}
