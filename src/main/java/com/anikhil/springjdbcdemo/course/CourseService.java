package com.anikhil.springjdbcdemo.course;

import com.anikhil.springjdbcdemo.dao.BaseDAO;
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
	public List<Course> getCourses() {
		return courseBaseDAO.list();
	}

	@Override
	public boolean createCourse(Course course) {
		return courseBaseDAO.create(course);
	}

	@Override
	public Optional<Course> getCourseWithId(int id) {
		return courseBaseDAO.get(id);
	}

	@Override
	public boolean updateCourse(Course course, int id) {
		return courseBaseDAO.update(course, id);
	}

	@Override
	public boolean deleteCourseWithId(int id) {
		return courseBaseDAO.delete(id);
	}
}
