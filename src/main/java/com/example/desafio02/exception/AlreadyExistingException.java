package com.example.desafio02.exception;

public class AlreadyExistingException extends RuntimeException{
    public AlreadyExistingException(String message) {
        super(message);
    }
}
