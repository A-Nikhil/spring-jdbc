package com.anikhil.springjdbcdemo.validators;

public class StringSQLValidator implements SQLValidator {

    @Override
    public boolean isAcceptable(Object object) {
        /*
        todo - add wrong parameter exception here
        if (!(object instanceof String)) {
            throw new WrongParameterTypeException("String");
        }
        */
        return object instanceof String;
    }
}
