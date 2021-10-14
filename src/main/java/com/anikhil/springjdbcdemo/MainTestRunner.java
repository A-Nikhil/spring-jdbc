package com.anikhil.springjdbcdemo;

import com.anikhil.springjdbcdemo.sqlbuilders.CourseSQLBuilder;
import com.anikhil.springjdbcdemo.sqldatatypes.SQLDataType;
import com.anikhil.springjdbcdemo.sqlfields.SQLField;
import com.anikhil.springjdbcdemo.utils.TableMapping;
import com.anikhil.springjdbcdemo.sqldatatypes.SQLValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTestRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MainTestRunner.class);
    public static void main(String[] args) {
        Integer intValue = 25;
        String stringValue = "Hello World";
        TableMapping courseTableMapping = TableMapping.COURSE;
        checkIntegerValidator(courseTableMapping, intValue);
    }

    private static boolean checkIntegerValidator(TableMapping courseTableMapping, Integer intValue) {
//        SQLField title = courseTableMapping.getSqlFields()[1];
//        SQLDataType sqlDataType = title.getColumnMapping().getSqlDataType();
//        SQLValidator validator = sqlDataType.getValidator();
//        LOG.info(validator.isAcceptable(intValue) ? "True" : "False");
        LOG.info(new CourseSQLBuilder().buildInsertQueryForAll());
        return false;
    }
}
