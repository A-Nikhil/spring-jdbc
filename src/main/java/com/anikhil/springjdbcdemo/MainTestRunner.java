package com.anikhil.springjdbcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anikhil.springjdbcdemo.sqltables.CourseTable;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.query.SQLQuery;
import com.anikhil.sqllib.query.SQLQueryBuilder;

public class MainTestRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MainTestRunner.class);

    public static void main(String[] args) {
        testNewLibrary();
    }

    private static boolean checkIntegerValidator() {
        return false;
    }

    private static void testNewLibrary() {
        CourseTable courseTable = new CourseTable();
        Column[] columns = {courseTable.courseId, courseTable.title, courseTable.description, courseTable.link};
        SQLQueryBuilder<CourseTable> sqlQueryBuilder = new SQLQueryBuilder<>(new CourseTable());
        SQLQuery query = sqlQueryBuilder
                .insert("course", columns)
                .build();
        LOG.info(query.getQuery());
    }
}
