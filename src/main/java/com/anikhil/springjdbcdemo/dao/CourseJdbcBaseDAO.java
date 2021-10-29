package com.anikhil.springjdbcdemo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.anikhil.springjdbcdemo.mappers.CourseRowMapper;
import com.anikhil.springjdbcdemo.models.Course;
import com.anikhil.springjdbcdemo.sqltables.CourseTable;
import com.anikhil.sqllib.exceptions.SQLQueryException;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.query.SQLQuery;
import com.anikhil.sqllib.query.SQLQueryBuilder;
import com.anikhil.sqllib.query.SQLQueryCondition;
import com.anikhil.sqllib.query.SQLQueryConditionBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CourseJdbcBaseDAO implements BaseDAO<Course> {

    private static final Logger LOG = LoggerFactory.getLogger(CourseJdbcBaseDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final SQLQueryBuilder<CourseTable> sqlQueryBuilder;
    private final SQLQueryConditionBuilder<CourseTable> conditionBuilder;

    private final Column courseId;
    private final Column title;
    private final Column description;
    private final Column link;

    @Autowired
    public CourseJdbcBaseDAO(JdbcTemplate jdbcTemplate,
                             CourseTable courseTable,
                             SQLQueryBuilder<CourseTable> sqlQueryBuilder,
                             SQLQueryConditionBuilder<CourseTable> conditionBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlQueryBuilder = sqlQueryBuilder;
        this.conditionBuilder = conditionBuilder;

        // Initialize Columns
        this.courseId = courseTable.courseId;
        this.title = courseTable.title;
        this.description = courseTable.description;
        this.link = courseTable.link;
    }

    @Override
    public List<Course> list() {
        try {
            SQLQuery query = sqlQueryBuilder
                    .select(this.courseId, this.title, this.description, this.link)
                    .build();
            return jdbcTemplate.query(query.toString(), new CourseRowMapper());
        } catch (SQLQueryException e) {
            LOG.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean create(Course course) {
        int rowsInserted = 0;
        try {
            Map<Column, Object> paramMap = new LinkedHashMap<>();
            paramMap.put(this.title, course.getTitle());
            paramMap.put(this.description, course.getDescription());
            paramMap.put(this.link, course.getLink());
            SQLQuery sqlQuery = this.sqlQueryBuilder
                    .insert(this.title, this.description, this.link)
                    .values(paramMap)
                    .build();
            rowsInserted = jdbcTemplate.update(sqlQuery.toString());
        } catch (SQLQueryException e) {
            LOG.error(e.getMessage());
        }
        return rowsInserted == 1;
    }

    @Override
    public Optional<Course> get(int id) {
        Course course = null;
        try {
            SQLQueryCondition queryCondition = this.conditionBuilder
                    .equals(this.courseId, id)
                    .build();
            SQLQuery sqlQuery = this.sqlQueryBuilder
                    .select(this.courseId, this.title, this.description, this.link)
                    .where(queryCondition)
                    .build();
            course = jdbcTemplate.queryForObject(sqlQuery.toString(), new CourseRowMapper());
        } catch (DataAccessException | SQLQueryException e) {
            LOG.error(e.getMessage());
        }
        return Optional.ofNullable(course);
    }

    @Override
    public boolean update(Course course, int id) {
        int updatedRows = 0;
        try {
            Map<Column, Object> paramMap = new HashMap<>();
            paramMap.put(this.title, course.getTitle());
            paramMap.put(this.description, course.getDescription());
            paramMap.put(this.link, course.getLink());
            SQLQueryCondition queryCondition = this.conditionBuilder
                    .equals(this.courseId, id)
                    .build();
            SQLQuery sqlQuery = this.sqlQueryBuilder
                    .update(paramMap)
                    .where(queryCondition)
                    .build();
            updatedRows = jdbcTemplate.update(sqlQuery.toString());
            if (updatedRows == 1) {
                LOG.info(String.format("Successfully update course with id - %d", course.getCourseId()));
            }
        } catch (SQLQueryException e) {
            LOG.error(e.getMessage());
        }
        return updatedRows == 1;
    }

    @Override
    public boolean delete(int id) {
        int deletedRows = 0;
        try {
            SQLQueryCondition queryCondition = this.conditionBuilder
                    .equals(this.courseId, id)
                    .build();
            SQLQuery sqlQuery = this.sqlQueryBuilder
                    .delete()
                    .where(queryCondition)
                    .build();
            deletedRows = jdbcTemplate.update(sqlQuery.toString());
            if (deletedRows == 1) {
                LOG.info("Successfully deleted course with id - " + id);
            }
        } catch (SQLQueryException e) {
            LOG.error(e.getMessage());
        }
        return deletedRows == 1;
    }
}
