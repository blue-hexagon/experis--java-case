package com;

public class AlreadyInitializedException extends Exception {
    public AlreadyInitializedException(String errorMessage) {
        super(errorMessage);
    }
}