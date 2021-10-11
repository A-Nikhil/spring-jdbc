package com.anikhil.springjdbcdemo.service;

import com.anikhil.springjdbcdemo.dao.BaseDAO;
import com.anikhil.springjdbcdemo.models.Course;
import com.anikhil.springjdbcdemo.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements BaseService<Course> {

	private final BaseDAO<Course> courseBaseDAO;

	public CourseService(BaseDAO<Course> courseBaseDAO) {
		this.courseBaseDAO = courseBaseDAO;
	}

	@Override
	public List<Course> get() {
		return courseBaseDAO.list();
	}

	@Override
	public boolean create(Course course) {
		return courseBaseDAO.create(course);
	}

	@Override
	public Optional<Course> getEntityWithId(int id) {
		return courseBaseDAO.get(id);
	}

	@Override
	public boolean update(Course course, int id) {
		return courseBaseDAO.update(course, id);
	}

	@Override
	public boolean deleteEntityWithId(int id) {
		return courseBaseDAO.delete(id);
	}
}
