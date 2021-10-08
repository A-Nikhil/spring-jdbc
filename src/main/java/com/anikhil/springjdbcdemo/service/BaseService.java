package com.anikhil.springjdbcdemo.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
	List<T> getCourses();

	boolean createCourse(T t);

	Optional<T> getCourseWithId(int id);

	boolean updateCourse(T t, int id);

	boolean deleteCourseWithId(int id);
}
