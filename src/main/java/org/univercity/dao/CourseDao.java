package org.univercity.dao;

import org.univercity.entity.Course;

import java.util.List;

public interface CourseDao {

    void saveCourse(Course course);

    Course getCourseById(Long id);

    List<Course> getAllCourse();

    void updateCourse(Long id, Course newCourse);

    void deleteCourseById(Long id);

    Course getCourseByName(String courseName);
}
