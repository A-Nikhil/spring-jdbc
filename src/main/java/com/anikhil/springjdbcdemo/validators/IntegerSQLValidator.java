package com.anikhil.springjdbcdemo.validators;

public class IntegerSQLValidator implements SQLValidator {

    @Override
    public boolean isAcceptable(Object object) {
        if (!(object instanceof Integer)) {
            return false;
        }
        return true;
    }
}
