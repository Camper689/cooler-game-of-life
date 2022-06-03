package com.cgol.exception;

public class BadConfigurationException extends RuntimeException {

    public BadConfigurationException(String errorMessage) {
        super(errorMessage);
    }
}
