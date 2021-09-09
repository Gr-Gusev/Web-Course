package test;

import dao.CompanyDAO;
import dao.CourseDAO;
import dao.Impl.CompanyDAOImpl;
import dao.Impl.CourseDAOImpl;
import entity.Company;
import entity.Course;
import org.testng.annotations.Test;
import org.testng.Assert;

import dao.StudentDAO;
import dao.Impl.StudentDAOImpl;
import entity.Student;

import java.sql.SQLException;
import java.util.Collection;

public class StudentTest {
    
    @Test
    public void addStudent() throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student newStudent = new Student("Test student.add");
        studentDAO.addStudent(newStudent);

        Student testStudent = studentDAO.getStudentById(newStudent.getId());
        Assert.assertTrue(newStudent.fullyEquals(testStudent));
    }

    @Test
    public void updateStudent() throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student newStudent = new Student("Test student.update");
        studentDAO.addStudent(newStudent);

        newStudent.setName(newStudent.getName() + "2");
        studentDAO.updateStudent(newStudent);

        Student testStudent = studentDAO.getStudentById(newStudent.getId());
        Assert.assertTrue(newStudent.fullyEquals(testStudent));
    }

    @Test
    public void deleteStudent() throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student newStudent = new Student("Test student.delete");
        studentDAO.addStudent(newStudent);

        studentDAO.deleteStudent(newStudent);
        Collection<Student> students = studentDAO.getAllStudents();
        for (Student testStudent : students) {
            Assert.assertFalse(newStudent.fullyEquals(testStudent));
        }
    }

    @Test
    public void getPassedCoursesForStudent() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany1 = new Company("Test company1 student.getPassedCourses",
                                         "Test address1 student.getPassedCourses");
        companyDAO.addCompany(newCompany1);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start1 = new java.sql.Date(0);
        java.sql.Date finish1 = new java.sql.Date(0);
        start1.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - 10 * dayInMs);
        finish1.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - 5 * dayInMs);
        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 1;
        Course newCourse1 = new Course(newCompany1, start1, finish1, hours,
                                 "Test course1 student.getPassedCourses");
        courseDAO.addCourse(newCourse1);

        Company newCompany2 = new Company("Test company2 student.getPassedCourses",
                                         "Test address2 student.getPassedCourses");
        companyDAO.addCompany(newCompany2);
        java.sql.Date start2 = new java.sql.Date(0);
        java.sql.Date finish2 = new java.sql.Date(0);
        start2.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - 20 * dayInMs);
        finish2.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - 10 * dayInMs);
        Course newCourse2 = new Course(newCompany2, start2, finish2, hours,
                                 "Test course2 student.getPassedCourses");
        courseDAO.addCourse(newCourse2);

        Company newCompany3 = new Company("Test company3 student.getPassedCourses",
                                         "Test address3 student.getPassedCourses");
        companyDAO.addCompany(newCompany3);
        java.sql.Date start3 = new java.sql.Date(0);
        java.sql.Date finish3 = new java.sql.Date(0);
        start3.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - 2 * dayInMs);
        finish3.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + 10 * dayInMs);
        Course newCourse3 = new Course(newCompany3, start3, finish3, hours,
                                 "Test course3 student.getPassedCourses");
        courseDAO.addCourse(newCourse3);

        StudentDAO studentDAO = new StudentDAOImpl();
        Student newStudent = new Student("Test student.getPassedCourses");
        newStudent.addToCourse(newCourse1);
        newStudent.addToCourse(newCourse2);
        newStudent.addToCourse(newCourse3);
        studentDAO.addStudent(newStudent);

        Collection<Course> courses = studentDAO.getPassedCoursesForStudent(newStudent.getId());

        Assert.assertTrue(courses.contains(newCourse1));
        Assert.assertTrue(courses.contains(newCourse2));
        Assert.assertFalse(courses.contains(newCourse3));

        Assert.assertTrue(courses.size() == 2);
    }
}
