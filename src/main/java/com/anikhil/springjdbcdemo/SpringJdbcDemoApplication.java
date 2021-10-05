package com.anikhil.springjdbcdemo;

import com.anikhil.springjdbcdemo.dao.DAO;
import com.anikhil.springjdbcdemo.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringJdbcDemoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);
	private static DAO<Course> courseDAO;

	public SpringJdbcDemoApplication(DAO<Course> courseDAO) {
		this.courseDAO = courseDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);

//		System.out.println("Inserting new Course --------");
//		Course course = new Course("Spring JDBC", "Spring JDBC", "https://github.com/a-nikhil/spring-jdbc");
//		courseDAO.create(course);

		LOG.info("All Courses ------------");
		List<Course> courses = courseDAO.list();
		courses.forEach(course -> LOG.info(course.toString()));
	}

}
