package com.cgol.exception;

public class BadConfigurationException extends RuntimeException {

    public static final String NO_DEFAULT_STATE = "Default state name cannot be empty";

    public BadConfigurationException(String errorMessage) {
        super(errorMessage);
    }
}
