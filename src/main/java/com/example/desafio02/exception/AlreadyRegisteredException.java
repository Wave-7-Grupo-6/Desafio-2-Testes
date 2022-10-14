package com.example.desafio02.exception;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
