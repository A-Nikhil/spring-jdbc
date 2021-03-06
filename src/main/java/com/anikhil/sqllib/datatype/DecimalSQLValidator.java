package com.anikhil.sqllib.datatype;

public class DecimalSQLValidator implements SQLValidator {

    @Override
    public boolean isAcceptable(Object object) {
        return (object instanceof Double) || (object instanceof Float);
    }
}
