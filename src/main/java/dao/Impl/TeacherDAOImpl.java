package dao.Impl;

import dao.TeacherDAO;

import entity.Teacher;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;


public class TeacherDAOImpl implements TeacherDAO {

    public void addTeacher(Teacher teacher) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(teacher);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public Teacher getTeacherById(Long teacher_id) throws SQLException {
        Session session = null;
        Teacher teacher = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        teacher = (Teacher) session.load(Teacher.class, teacher_id);
        if (session.isOpen()) {
            session.close();
        }
        return teacher;
    }

    public Collection<Teacher> getAllTeachers() throws SQLException {
        Session session = null;
        List<Teacher> teachers = new ArrayList<Teacher>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Teacher> query = session.createQuery("select d from Teacher d", Teacher.class);
        teachers = (List<Teacher>)query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        teachers.sort(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                return (o1.getId().compareTo(o2.getId()));
            }
        });
        return teachers;
    }

    public void deleteTeacher(Teacher teacher) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(teacher);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

}
