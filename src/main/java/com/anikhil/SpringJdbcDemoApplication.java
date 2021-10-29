package com.anikhil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.anikhil.springjdbcdemo.models.Course;
import com.anikhil.springjdbcdemo.service.CourseService;

import java.util.List;

@SuppressWarnings("ALL")
@SpringBootApplication
public class SpringJdbcDemoApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(SpringJdbcDemoApplication.class);

        LOG.info("All Courses ------------");
        CourseService courseService = applicationContext.getBean(CourseService.class);
        Course course = new Course("Maven", "Maven advanced", "mvnrepository.com");
        int id = 8;

//        testInsert(course, courseService);
//        testSelect(courseService);
//        testUpdate(course, courseService, id);
//        testDelete(courseService, id);
    }

    private static void testDelete(CourseService courseService, int id) {
        boolean b = courseService.deleteEntityWithId(id);
        LOG.info(b ? "Done" : "Not Done");
    }

    private static void testSelect(CourseService courseService) {
        List<Course> courses = courseService.get();
        if (courses != null) {
            String courseOutput = courses.toString();
            LOG.info(courseOutput);
        }
    }

    private static void testUpdate(Course course, CourseService courseService, int id) {
        boolean update = courseService.update(course, id);
        LOG.info(update ? "Done" : "Not Done");
    }

    private static void testInsert(Course course, CourseService courseService) {
        boolean b = courseService.create(course);
        LOG.info(b ? "Done" : "Not Done");
    }

}
