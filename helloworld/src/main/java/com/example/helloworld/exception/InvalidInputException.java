package com.example.helloworld.exception;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException() {
        super("Invalid Input");
    }
}
