package com.anikhil.springjdbcdemo.sqldatatypes;

public class IntegerSQLValidator implements SQLValidator {

    @Override
    public boolean isAcceptable(Object object) {
        if (!(object instanceof Integer)) {
            return false;
        }
        return true;
    }
}
