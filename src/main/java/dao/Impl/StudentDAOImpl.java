package dao.Impl;

import dao.CourseDAO;
import dao.StudentDAO;
import entity.Course;
import entity.Student;

import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.query.Query;


public class StudentDAOImpl implements StudentDAO {

    public void addStudent(Student student) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Student getStudentById(Long student_id) throws SQLException {
        Session session = null;
        Student student = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        student = (Student) session.load(Student.class, student_id);
        if (session.isOpen()) {
            session.close();
        }
        return student;
    }

    public Collection<Student> getAllStudents() throws SQLException {
        Session session = null;
        List<Student> students = new ArrayList<Student>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Student> query = session.createQuery("select d from Student d", Student.class);
        students = (List<Student>)query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return students;
    }

    public void deleteStudent(Student student) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Collection<Course> getPassedCoursesForStudent(long studentId) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.getStudentById(studentId);
        CourseDAO courseDAO = new CourseDAOImpl();
        Collection<Course> finishedCourses = courseDAO.getFinishedCourses();
        Collection<Course> studentCourses = student.getCourses();

        studentCourses.retainAll(finishedCourses);
        return studentCourses;
    }
}
