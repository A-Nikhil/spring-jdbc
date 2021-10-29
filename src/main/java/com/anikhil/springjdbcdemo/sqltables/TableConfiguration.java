package com.anikhil.springjdbcdemo.sqltables;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anikhil.sqllib.query.SQLQueryBuilder;
import com.anikhil.sqllib.query.SQLQueryConditionBuilder;

@SuppressWarnings("ALL")
@Configuration
public class TableConfiguration {

    @Bean
    public SQLQueryBuilder<CourseTable> getCourseTableBean() {
        return new SQLQueryBuilder<>(new CourseTable());
    }

    @Bean
    public SQLQueryConditionBuilder<CourseTable> getCourseTableConditionBuilder() {
        return new SQLQueryConditionBuilder<>(new CourseTable());
    }
}
