package com.anikhil.springjdbcdemo.exceptions;

public class WrongParameterTypeException extends Exception {

    public WrongParameterTypeException() {
        super("Wrong Parameter");
    }

    public WrongParameterTypeException(String expectedParam) {
        super(String.format("Expected %s", expectedParam));
    }
}
