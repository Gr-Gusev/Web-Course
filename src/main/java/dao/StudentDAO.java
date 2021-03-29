package dao;

import entity.Course;
import entity.Student;

import java.util.Collection;
import java.sql.SQLException;

public interface StudentDAO {

    public void addStudent(Student student) throws SQLException;

    public void updateStudent(Student student) throws SQLException;

    public Student getStudentById(Long student_id) throws SQLException;

    public Collection<Student> getAllStudents() throws SQLException;

    public void deleteStudent(Student student) throws SQLException;

    public Collection<Course> getPassedCoursesForStudent(long studentId) throws SQLException;

}
