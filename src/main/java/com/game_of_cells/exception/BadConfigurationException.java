package com.game_of_cells.exception;

public class BadConfigurationException extends RuntimeException {

    public BadConfigurationException(String errorMessage) {
        super(errorMessage);
    }
}
