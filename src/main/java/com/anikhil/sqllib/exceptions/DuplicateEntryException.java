package com.anikhil.sqllib.exceptions;

public class DuplicateEntryException extends Exception {
    public DuplicateEntryException() {
        super("Duplicate column found");
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}
