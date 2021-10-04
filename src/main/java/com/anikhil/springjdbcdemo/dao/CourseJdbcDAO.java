package com.anikhil.springjdbcdemo.dao;

import com.anikhil.springjdbcdemo.mappers.CourseRowMapper;
import com.anikhil.springjdbcdemo.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseJdbcDAO implements DAO<Course> {

	private static final Logger LOG = LoggerFactory.getLogger(CourseJdbcDAO.class);
	private JdbcTemplate jdbcTemplate;

	public CourseJdbcDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Course> list() {
		String sql = "select course_id, title, description, link from course";
		return jdbcTemplate.query(sql, new CourseRowMapper());
	}

	@Override
	public void create(Course course) {
		String sql = "insert into course (title, description, link) values (?, ?, ?)";
		int rowsInserted = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink());
		if (rowsInserted == 1) {
			LOG.info("New course created - " + course.getTitle());
		}
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
