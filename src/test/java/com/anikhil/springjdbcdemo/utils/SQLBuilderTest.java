package com.anikhil.springjdbcdemo.utils;

import com.anikhil.springjdbcdemo.entityMappings.TableMapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SQLBuilderTest {

    private TableMapping tableMapping;

    private SQLBuilder sqlBuilder;

    @BeforeEach
    void setUp() {
        this.sqlBuilder = new SQLBuilder();
        this.tableMapping = TableMapping.COURSE;
    }

    @Test
    void buildSelectQuery() {
        String selectQuery = sqlBuilder.buildSelectQuery(tableMapping);
        System.out.println(selectQuery);
        Assertions.assertEquals("SELECT course_id, title, description, link FROM course", selectQuery);
    }

    @Test
    void select() {
    }

    @Test
    void from() {
    }
}