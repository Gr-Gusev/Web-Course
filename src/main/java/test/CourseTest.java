package test;

import org.testng.annotations.Test;
import org.testng.Assert;

import dao.CompanyDAO;
import dao.CourseDAO;
import dao.Impl.CompanyDAOImpl;
import dao.Impl.CourseDAOImpl;
import entity.Company;
import entity.Course;

import java.sql.SQLException;
import java.util.Collection;

public class CourseTest {

    @Test
    public void addCourse() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company course.add", "Test address course.add");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course.add");
        courseDAO.addCourse(newCourse);

        Course testCourse = courseDAO.getCourseById(newCourse.getId());
        Assert.assertTrue(newCourse.fullyEquals(testCourse));
    }

    @Test
    public void updateCourse() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company course.update", "Test address course.update");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course.update");
        courseDAO.addCourse(newCourse);

        newCompany.setName(newCompany.getName() + " 2");
        companyDAO.updateCompany(newCompany);
        start.setTime(start.getTime() - dayInMs);
        finish.setTime(finish.getTime() + dayInMs);

        newCourse.setCompany(newCompany);
        newCourse.setDayOfStart(start);
        newCourse.setDayOfFinish(finish);
        newCourse.setHoursPerDay(hours);
        newCourse.setName(newCourse.getName() + "2");

        courseDAO.updateCourse(newCourse);
        Course testCourse = courseDAO.getCourseById(newCourse.getId());
        Assert.assertTrue(newCourse.fullyEquals(testCourse));
    }

    @Test
    public void deleteCourse() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company course.delete", "Test address course.delete");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course.delete");
        courseDAO.addCourse(newCourse);

        courseDAO.deleteCourse(newCourse);
        Collection<Course> courses = courseDAO.getAllCourses();
        for (Course testCourse : courses) {
            Assert.assertFalse(newCourse.fullyEquals(testCourse));
        }
    }

    @Test
    public void getCurrentCourses() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company course.getCur", "Test address course.getCur");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        long remainder = curDate.getTime() % dayInMs;
        start.setTime(curDate.getTime() - remainder - dayInMs);
        finish.setTime(curDate.getTime() - remainder + dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course.getCur");
        courseDAO.addCourse(newCourse);
        newCourse = courseDAO.getCourseById(newCourse.getId());

        boolean bool1 = false;
        Collection<Course> courses = courseDAO.getCurrentCourses();
        for (Course testCourse : courses) {
            if (testCourse.fullyEquals(newCourse)) {
                bool1 = true;
                break;
            }
        }

        start.setTime(curDate.getTime() - remainder);
        newCourse.setDayOfStart(start);
        courseDAO.updateCourse(newCourse);
        newCourse = courseDAO.getCourseById(newCourse.getId());

        boolean bool2 = false;
        courses = courseDAO.getCurrentCourses();
        for (Course testCourse : courses) {
            if (testCourse.fullyEquals(newCourse)) {
                bool2 = true;
                break;
            }
        }

        start.setTime(curDate.getTime() - remainder - dayInMs);
        finish.setTime(curDate.getTime() - remainder);
        newCourse.setDayOfStart(start);
        newCourse.setDayOfFinish(finish);
        courseDAO.updateCourse(newCourse);
        newCourse = courseDAO.getCourseById(newCourse.getId());

        boolean bool3 = false;
        courses = courseDAO.getCurrentCourses();
        for (Course testCourse : courses) {
            if (testCourse.fullyEquals(newCourse)) {
                bool3 = true;
                break;
            }
        }

        Assert.assertTrue(bool1 && bool2 && bool3);
    }

    @Test
    public void getFinishedCourses() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company course.getFin", "Test address course.getFin");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        long remainder = curDate.getTime() % dayInMs;
        start.setTime(curDate.getTime() - remainder - 2 * dayInMs);
        finish.setTime(curDate.getTime() - remainder - dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course.getFin");
        courseDAO.addCourse(newCourse);
        newCourse = courseDAO.getCourseById(newCourse.getId());

        boolean bool = false;
        Collection<Course> courses = courseDAO.getFinishedCourses();
        for (Course testCourse : courses) {
            if (testCourse.fullyEquals(newCourse)) {
                bool = true;
                break;
            }
        }

        Assert.assertTrue(bool);
    }
}
