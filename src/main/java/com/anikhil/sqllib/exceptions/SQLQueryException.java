package com.anikhil.sqllib.exceptions;

/**
 * Root exception class for all
 */
public class SQLQueryException extends Exception {

    public SQLQueryException() {
    }

    public SQLQueryException(String message) {
        super(message);
    }
}
