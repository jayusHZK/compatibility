package com.jayus.smallSpring.step18.jdbc;

public class CannotGetJdbcConnectionException extends RuntimeException{

    public CannotGetJdbcConnectionException(String message) {
        super(message);
    }

    public CannotGetJdbcConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
