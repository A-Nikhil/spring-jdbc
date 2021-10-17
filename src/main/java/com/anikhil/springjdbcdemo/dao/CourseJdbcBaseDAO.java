package com.anikhil.springjdbcdemo.dao;

import com.anikhil.springjdbcdemo.mappers.CourseRowMapper;
import com.anikhil.springjdbcdemo.models.Course;
import com.anikhil.springjdbcdemo.sqltables.CourseTable;
import com.anikhil.springjdbcdemo.sqltables.TableFactory;
import com.anikhil.sqllib.fields.Column;
import com.anikhil.sqllib.query.SQLQuery;
import com.anikhil.sqllib.query.SQLQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CourseJdbcBaseDAO implements BaseDAO<Course> {

    private static final Logger LOG = LoggerFactory.getLogger(CourseJdbcBaseDAO.class);
    private final JdbcTemplate jdbcTemplate;

    public CourseJdbcBaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> list() {
        try {
            CourseTable courseTable = TableFactory.getTableByName("course", CourseTable.class);
            if (courseTable == null) {
                throw new NoClassDefFoundError("Cannot fetch CourseTable class");
            }
            Column[] columns = {courseTable.courseId, courseTable.title, courseTable.description, courseTable.link};
            SQLQuery query = new SQLQueryBuilder()
                    .select(columns)
                    .from("course")
                    .build();
            LOG.info(query.getQuery());
            String sql = query.getQuery();
            return jdbcTemplate.query(sql, new CourseRowMapper());
        } catch (NoClassDefFoundError exception) {
            LOG.error(exception.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean create(Course course) {
        String sql = "insert into course (title, description, link) values (?, ?, ?)";
        int rowsInserted = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink());
        return rowsInserted == 1;
    }

    @Override
    public Optional<Course> get(int id) {
        String sql = "select course_id, title, description, link from course where course_id = ?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CourseRowMapper());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage());
        }
        return Optional.ofNullable(course);
    }

    @Override
    public boolean update(Course course, int id) {
        String sql = "update course set title = ?, description = ?, link = ? where course_id = ?";
        int updatedRows = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink(), course.getCourseId());
        if (updatedRows == 1) {
            LOG.info("Successfully update course with id - " + course.getCourseId());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from course where course_id = ?";
        int deletedRows = jdbcTemplate.update(sql, id);
        if (deletedRows == 1) {
            LOG.info("Successfully deleted course with id - " + id);
            return true;
        }
        return false;
    }
}
