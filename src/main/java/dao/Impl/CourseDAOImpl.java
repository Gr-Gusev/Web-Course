package dao.Impl;

import dao.CourseDAO;
import entity.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {

    public void addCourse(Course course) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void updateCourse(Course course) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(course);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Course getCourseById(Long course_id) throws SQLException {
        Session session = null;
        Course course = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        course = (Course) session.load(Course.class, course_id);
        if (session.isOpen()) {
            session.close();
        }
        return course;
    }

    public Collection<Course> getAllCourses() throws SQLException {
        Session session = null;
        List<Course> courses = new ArrayList<Course>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Course> query = session.createQuery("select d from Course d", Course.class);
        courses = (List<Course>)query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return courses;
    }

    public void deleteCourse(Course course) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(course);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Collection<Course> getCurrentCourses() throws SQLException {
        Collection<Course> courses = this.getAllCourses();
        Collection<Course> ret = new HashSet<Course>(5);
        for (Course course : courses) {
            if (course.isActive())
                ret.add(course);
        }
        return ret;
    }

    public Collection<Course> getFinishedCourses() throws SQLException {
        Collection<Course> courses = this.getAllCourses();
        Collection<Course> ret = new HashSet<Course>(5);
        for (Course course : courses) {
            if (course.isFinished())
                ret.add(course);
        }
        return ret;
    }
}
