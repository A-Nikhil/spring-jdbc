package com.anikhil.springjdbcdemo.sqltables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anikhil.sqllib.query.SQLQueryBuilder;

@SuppressWarnings("ALL")
@Configuration
public class TableConfiguration {

    @Bean
    public SQLQueryBuilder<CourseTable> getCourseTableBean() {
        return new SQLQueryBuilder<>(new CourseTable());
    }
}
