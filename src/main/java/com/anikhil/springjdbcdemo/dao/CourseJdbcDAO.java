package com.anikhil.springjdbcdemo.dao;

import com.anikhil.springjdbcdemo.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class CourseJdbcDAO implements DAO<Course> {

	private static final Logger LOG = LoggerFactory.getLogger(CourseJdbcDAO.class);
	private JdbcTemplate jdbcTemplate;

	public CourseJdbcDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Course> list() {
		return null;
	}

	@Override
	public void create(Course course) {

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
