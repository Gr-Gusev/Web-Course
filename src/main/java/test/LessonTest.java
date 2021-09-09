package test;

import dao.*;
import dao.Impl.*;
import entity.*;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.Collection;

public class LessonTest {

    @Test
    public void addLesson() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company lesson.add", "Test address lesson.add");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + 2 * dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course lesson.add");
        courseDAO.addCourse(newCourse);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher lesson.add", newCompany);
        teacherDAO.addTeacher(newTeacher);

        java.sql.Timestamp lessonTime = new java.sql.Timestamp(curDate.getTime() + dayInMs);

        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson newLesson = new Lesson(newCourse, newTeacher, lessonTime);
        lessonDAO.addLesson(newLesson);

        Lesson testLesson = lessonDAO.getLessonById(newLesson.getId());
        Assert.assertTrue(newLesson.fullyEquals(testLesson));
    }

    @Test
    public void updateLesson() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company lesson.update", "Test address lesson.update");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + 3 * dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course lesson.update");
        courseDAO.addCourse(newCourse);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher lesson.update", newCompany);
        teacherDAO.addTeacher(newTeacher);

        java.sql.Timestamp lessonTime = new java.sql.Timestamp(curDate.getTime() + dayInMs);

        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson newLesson = new Lesson(newCourse, newTeacher, lessonTime);
        lessonDAO.addLesson(newLesson);

        lessonTime.setTime(lessonTime.getTime() + dayInMs);
        newLesson.setDateAndTime(lessonTime);
        lessonDAO.updateLesson(newLesson);

        Lesson testLesson = lessonDAO.getLessonById(newLesson.getId());
        Assert.assertTrue(newLesson.fullyEquals(testLesson));
    }

    @Test
    public void deleteLesson() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company lesson.delete", "Test address lesson.delete");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + 2 * dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course lesson.delete");
        courseDAO.addCourse(newCourse);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher lesson.delete", newCompany);
        teacherDAO.addTeacher(newTeacher);

        java.sql.Timestamp lessonTime = new java.sql.Timestamp(curDate.getTime() + dayInMs);

        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson newLesson = new Lesson(newCourse, newTeacher, lessonTime);
        lessonDAO.addLesson(newLesson);

        lessonDAO.deleteLesson(newLesson);
        Collection<Lesson> lessons = lessonDAO.getAllLessons();
        for (Lesson testLesson : lessons) {
            Assert.assertFalse(newLesson.fullyEquals(testLesson));
        }
    }

    @Test
    public void getScheduleForStudent() throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student newStudent = new Student("Test student lesson.schedForStudent");
        studentDAO.addStudent(newStudent);

        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company lesson.schedForStudent",
                                        "Test address lesson.schedForStudent");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + 10 * dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course lesson.schedForStudent");

        courseDAO.addCourse(newCourse);

        newStudent.addToCourse(newCourse);
        studentDAO.updateStudent(newStudent);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher lesson.schedForStudent", newCompany);
        teacherDAO.addTeacher(newTeacher);

        java.sql.Timestamp lessonTime1 = new java.sql.Timestamp(curDate.getTime());
        java.sql.Timestamp lessonTime2 = new java.sql.Timestamp(curDate.getTime());
        lessonTime2.setTime(lessonTime2.getTime() + dayInMs);

        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson newLesson1 = new Lesson(newCourse, newTeacher, lessonTime1);
        lessonDAO.addLesson(newLesson1);

        Lesson newLesson2 = new Lesson(newCourse, newTeacher, lessonTime2);
        lessonDAO.addLesson(newLesson2);

        Collection<Lesson> schedule = lessonDAO.getScheduleForStudent(newStudent.getId());

        Assert.assertTrue(schedule.contains(newLesson1));
        Assert.assertTrue(schedule.contains(newLesson2));

        Assert.assertTrue(schedule.size() == 2);
    }

    @Test
    public void getScheduleForTeacher() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company lesson.schedForTeacher",
                "Test address lesson.schedForTeacher");
        companyDAO.addCompany(newCompany);
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + 10 * dayInMs);

        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(newCompany, start, finish, hours, "Test course lesson.schedForTeacher");

        courseDAO.addCourse(newCourse);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher lesson.schedForTeacher", newCompany);
        teacherDAO.addTeacher(newTeacher);

        java.sql.Timestamp lessonTime1 = new java.sql.Timestamp(curDate.getTime());
        java.sql.Timestamp lessonTime2 = new java.sql.Timestamp(curDate.getTime());
        lessonTime2.setTime(lessonTime2.getTime() + dayInMs);

        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson newLesson1 = new Lesson(newCourse, newTeacher, lessonTime1);
        lessonDAO.addLesson(newLesson1);

        Lesson newLesson2 = new Lesson(newCourse, newTeacher, lessonTime2);
        lessonDAO.addLesson(newLesson2);

        Collection<Lesson> schedule = lessonDAO.getScheduleForTeacher(newTeacher.getId());

        Assert.assertTrue(schedule.contains(newLesson1));
        Assert.assertTrue(schedule.contains(newLesson2));

        Assert.assertTrue(schedule.size() == 2);
    }

}
