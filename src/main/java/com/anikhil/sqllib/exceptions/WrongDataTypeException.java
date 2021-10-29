package com.anikhil.sqllib.exceptions;

public class WrongDataTypeException extends SQLQueryException {
    public WrongDataTypeException() {
    }

    public WrongDataTypeException(String message) {
        super(message);
    }
}
