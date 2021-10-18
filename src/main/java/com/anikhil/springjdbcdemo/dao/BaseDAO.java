package com.anikhil.springjdbcdemo.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {

    List<T> list();

    boolean create(T t);

    Optional<T> get(int id);

    boolean update(T t, int id);

    boolean delete(int id);
}
