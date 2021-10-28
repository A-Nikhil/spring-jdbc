package com.anikhil.sqllib.exceptions;

public class DuplicateEntryException extends SQLQueryException {
    public DuplicateEntryException() {
        super("Duplicate column found");
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}
