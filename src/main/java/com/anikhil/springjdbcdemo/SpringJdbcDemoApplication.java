package com.anikhil.springjdbcdemo;

import com.anikhil.springjdbcdemo.models.Course;
import com.anikhil.springjdbcdemo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcDemoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringJdbcDemoApplication.class);

		LOG.info("All Courses ------------");
		CourseService courseService = applicationContext.getBean(CourseService.class);
		List<Course> courses = courseService.get();
		courses.forEach(course -> LOG.info(course.toString()));
	}

}
