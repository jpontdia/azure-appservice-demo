package com.demo.creditservices.exceptionhandling;

public class InvalidParameterException extends Exception {
    public InvalidParameterException(String message) {
        super(message);
    }
}
