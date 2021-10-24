package com.anikhil.sqllib.exceptions;

public class WrongDataTypeException extends Exception {
    public WrongDataTypeException() {
    }

    public WrongDataTypeException(String message) {
        super(message);
    }
}
