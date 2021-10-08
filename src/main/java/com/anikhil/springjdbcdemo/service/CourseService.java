package com.anikhil.springjdbcdemo.service;

import com.anikhil.springjdbcdemo.dao.DAO;
import com.anikhil.springjdbcdemo.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements BaseService<Course> {

	private final DAO<Course> courseDAO;

	public CourseService(DAO<Course> courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public List<Course> getCourses() {
		return courseDAO.list();
	}

	@Override
	public boolean createCourse(Course course) {
		return courseDAO.create(course);
	}

	@Override
	public Optional<Course> getCourseWithId(int id) {
		return courseDAO.get(id);
	}

	@Override
	public boolean updateCourse(Course course, int id) {
		return courseDAO.update(course, id);
	}

	@Override
	public boolean deleteCourseWithId(int id) {
		return courseDAO.delete(id);
	}
}
