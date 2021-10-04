package com.anikhil.springjdbcdemo;

import com.anikhil.springjdbcdemo.dao.DAO;
import com.anikhil.springjdbcdemo.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringJdbcDemoApplication {

	private static DAO<Course> courseDAO;

	public SpringJdbcDemoApplication(DAO<Course> courseDAO) {
		this.courseDAO = courseDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);

		System.out.println("All Courses ------------");
		List<Course> courses = courseDAO.list();
		courses.forEach(System.out::println);
	}

}
