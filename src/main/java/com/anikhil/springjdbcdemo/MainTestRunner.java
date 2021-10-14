package com.anikhil.springjdbcdemo;

import com.anikhil.springjdbcdemo.sqlfields.SQLField;
import com.anikhil.springjdbcdemo.utils.TableMapping;
import com.anikhil.springjdbcdemo.sqldatatypes.SQLValidator;

public class MainTestRunner {
    public static void main(String[] args) {
        Integer intValue = 25;
        String stringValue = "Hello World";
        TableMapping courseTableMapping = TableMapping.COURSE;
        checkIntegerValidator(courseTableMapping, intValue);
    }

    private static boolean checkIntegerValidator(TableMapping courseTableMapping, Integer intValue) {
        SQLField titleField = courseTableMapping.getSqlFields()[1];
        SQLValidator validator = titleField.getColumnMapping().getValidator();
        System.out.println(validator.isAcceptable(intValue));
        return false;

    }
}
