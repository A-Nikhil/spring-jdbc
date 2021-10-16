package com.anikhil.springjdbcdemo.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
	List<T> get();

	boolean create(T t);

	Optional<T> getEntityWithId(int id);

	boolean update(T t, int id);

	boolean deleteEntityWithId(int id);
}
