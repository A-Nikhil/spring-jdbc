package com.anikhil.springjdbcdemo.sqldatatypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringSQLValidator implements SQLValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringSQLValidator.class);

    @Override
    public boolean isAcceptable(Object object) {
        /*
        todo - add wrong parameter exception here
        if (!(object instanceof String)) {
            throw new WrongParameterTypeException("String");
        }
        */
        LOGGER.info(object.getClass().getName());
        return object instanceof String;
    }
}
