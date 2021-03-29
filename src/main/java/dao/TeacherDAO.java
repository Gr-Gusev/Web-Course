package dao;

import entity.Teacher;

import java.sql.SQLException;
import java.util.Collection;

public interface TeacherDAO {

    public void addTeacher(Teacher teacher) throws SQLException;

    public void updateTeacher(Teacher teacher) throws SQLException;

    public Teacher getTeacherById(Long teacher_id) throws SQLException;

    public Collection<Teacher> getAllTeachers() throws SQLException;

    public void deleteTeacher(Teacher teacher) throws SQLException;

}
