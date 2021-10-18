package com.anikhil.sqllib.exceptions;

public class DuplicateColumnException extends Exception {
    public DuplicateColumnException() {
        super("Duplicate column found");
    }

    public DuplicateColumnException(String message) {
        super(message);
    }
}
