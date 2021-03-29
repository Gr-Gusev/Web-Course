package dao;

import entity.Course;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseDAO {

    public void addCourse(Course course) throws SQLException;

    public void updateCourse(Course course) throws SQLException;

    public Course getCourseById(Long course_id) throws SQLException;

    public Collection<Course> getAllCourses() throws SQLException;

    public void deleteCourse(Course course) throws SQLException;

    public Collection<Course> getCurrentCourses() throws SQLException;

    public Collection<Course> getFinishedCourses() throws SQLException;
}
