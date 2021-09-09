package dao.Impl;

import dao.LessonDAO;
import dao.StudentDAO;
import dao.TeacherDAO;

import entity.Course;
import entity.Lesson;
import entity.Student;
import entity.Teacher;

import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.*;


public class LessonDAOImpl implements LessonDAO {

    public void addLesson(Lesson lesson) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(lesson);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void updateLesson(Lesson lesson) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(lesson);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Lesson getLessonById(Long lesson_id) throws SQLException {
        Session session = null;
        Lesson lesson = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        lesson = (Lesson) session.load(Lesson.class, lesson_id);
        if (session.isOpen()) {
            session.close();
        }
        return lesson;
    }

    public Collection<Lesson> getAllLessons() throws SQLException {
        Session session = null;
        List<Lesson> lessons = new ArrayList<Lesson>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Lesson> query = session.createQuery("select d from Lesson d", Lesson.class);
        lessons = (List<Lesson>)query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        Collections.sort(lessons);
        return lessons;
    }

    public void deleteLesson(Lesson lesson) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(lesson);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public List<Lesson> getScheduleForStudent(long student_id)
            throws SQLException
    {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.getStudentById(student_id);

        Collection<Course> studentCourses = student.getCourses();
        Collection<Course> curCourses = new HashSet<Course>(10);
        for (Course course : studentCourses) {
            if (!course.isFinished())
                curCourses.add(course);
        }
        Collection<Lesson> allLessons = new HashSet<Lesson>(20);
        for (Course course : curCourses) {
            allLessons.addAll(course.getLessons());
        }
        List<Lesson> ret = new ArrayList<Lesson>();
        for (Lesson lesson : allLessons) {
            if (!lesson.isPassed())
                ret.add(lesson);
        }
        Collections.sort(ret);
        return ret;
    }

    public List<Lesson> getScheduleForTeacher(long teacher_id)
            throws SQLException
    {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher teacher = teacherDAO.getTeacherById(teacher_id);

        Collection<Lesson> lessons = teacher.getLessons();
        List<Lesson> ret = new ArrayList<Lesson>();
        for (Lesson lesson : lessons) {
            if (!lesson.isPassed())
                ret.add(lesson);
        }
        Collections.sort(ret);
        return ret;
    }

}
