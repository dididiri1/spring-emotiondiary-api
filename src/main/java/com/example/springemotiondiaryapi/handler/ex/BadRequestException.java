package com.example.springemotiondiaryapi.handler.ex;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}