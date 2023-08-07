package com.jayus.smallSpring.step18.jdbc;

public class UncategorizedSqlException extends RuntimeException {

    public UncategorizedSqlException(String message) {
        super(message);
    }

    public UncategorizedSqlException(String task,String sql, Throwable cause) {
        super(sql, cause);
    }
}
