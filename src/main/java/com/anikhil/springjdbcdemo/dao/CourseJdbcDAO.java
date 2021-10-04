package com.anikhil.springjdbcdemo.dao;

import com.anikhil.springjdbcdemo.mappers.CourseRowMapper;
import com.anikhil.springjdbcdemo.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		return Optional.empty();
	}

	@Override
	public boolean update(Course course, int id) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}
}
