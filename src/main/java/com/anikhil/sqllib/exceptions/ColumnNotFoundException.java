package com.anikhil.sqllib.exceptions;

public class ColumnNotFoundException extends SQLQueryException {

    public ColumnNotFoundException(String message) {
        super(message);
    }

    public ColumnNotFoundException(String column, String inTable) {
        super(String.format("Column %s not found in Table %s", column, inTable));
    }
}
