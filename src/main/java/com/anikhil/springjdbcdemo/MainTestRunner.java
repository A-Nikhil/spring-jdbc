package com.anikhil.springjdbcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anikhil.springjdbcdemo.sqltables.CourseTable;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.query.SQLQuery;
import com.anikhil.sqllib.query.SQLQueryBuilder;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class MainTestRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MainTestRunner.class);

    public static void main(String[] args)
            throws Exception {
        testNewLibrary();
    }

    private static boolean checkIntegerValidator() {
        return false;
    }

    private static void testNewLibrary()
            throws Exception {
        CourseTable courseTable = new CourseTable();
        Column[] columns = {courseTable.courseId, courseTable.title, courseTable.description, courseTable.link};
        SQLQueryBuilder<CourseTable> sqlQueryBuilder = new SQLQueryBuilder<>(new CourseTable());
        Map<Column, Object> paramMap = new HashMap<>();
        SQLQuery query = sqlQueryBuilder
//                .from("course")
//                .select(courseTable.courseId)
                .insert(courseTable.courseId)
                .from("course")
                .build();
        /*
        paramMap.put(courseTable.courseId, 123);
        paramMap.put(courseTable.title, "Title");
        paramMap.put(courseTable.description, "Description");
        paramMap.put(courseTable.link, "Link");
        SQLQuery query = sqlQueryBuilder
                .insert(columns)
                .values(paramMap)
                .build();
                */
        LOG.info(query.getQuery());
    }
}
