package com.anikhil.springjdbcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anikhil.springjdbcdemo.sqltables.CourseTable;
import com.anikhil.sqllib.exceptions.SQLQueryException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.query.SQLQuery;
import com.anikhil.sqllib.query.SQLQueryBuilder;
import com.anikhil.sqllib.query.SQLQueryCondition;
import com.anikhil.sqllib.query.SQLQueryConditionBuilder;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class MainTestRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MainTestRunner.class);

    public static void main(String[] args) throws SQLQueryException {
        testSQLQueryException();
    }

    private static void testSQLQueryException() throws SQLQueryException {
        CourseTable courseTable = new CourseTable();
        SQLQueryConditionBuilder<CourseTable> conditionBuilder =
                new SQLQueryConditionBuilder<>(courseTable);

        SQLQueryCondition condition = conditionBuilder.in(courseTable.description, "APLHA", "BETA", "GAMMA")
                .build();

        System.out.println(condition.toString());
    }

    private static boolean checkIntegerValidator() {
        return false;
    }

    private static void testNewLibrary() throws SQLQueryException {
        CourseTable courseTable = new CourseTable();
        Column[] columns = {courseTable.courseId, courseTable.title, courseTable.description, courseTable.link};
        SQLQueryBuilder<CourseTable> sqlQueryBuilder = new SQLQueryBuilder<>(new CourseTable());
        Map<Column, Object> paramMap = new HashMap<>();
        paramMap.put(courseTable.courseId, 123);
        paramMap.put(courseTable.title, "Title");
        paramMap.put(courseTable.description, "Description");
        paramMap.put(courseTable.link, "Link");
        SQLQuery query = sqlQueryBuilder
                .insert(columns)
                .values(paramMap)
                .build();
        LOG.info(query.getQuery());
    }
}
