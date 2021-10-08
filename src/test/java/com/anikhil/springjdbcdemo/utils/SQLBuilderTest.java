package com.anikhil.springjdbcdemo.utils;

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
        String expectedSelectedQuery = "SELECT course_id, title, description, link FROM course";
        Assertions.assertEquals(expectedSelectedQuery, selectQuery);
    }

    @Test
    void buildInsertQuery() {
        String insertQuery = sqlBuilder.buildInsertQuery(tableMapping);
        String expectedInsertQuery = "INSERT INTO course(title, description, link) VALUES(title, description, link)";
        Assertions.assertEquals(expectedInsertQuery, insertQuery);
    }

    @Test
    void buildUpdateQuery() {

    }

    @Test
    void buildCreateQuery() {

    }

    @Test
    void buildDeleteQuery() {

    }

    @Test
    void select() {
    }

    @Test
    void from() {
    }
}