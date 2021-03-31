package test;

import dao.CompanyDAO;
import dao.Impl.CompanyDAOImpl;
import dao.Impl.TeacherDAOImpl;
import dao.TeacherDAO;
import entity.Company;
import org.testng.annotations.Test;
import org.testng.Assert;

import entity.Teacher;
import java.sql.SQLException;
import java.util.Collection;

public class TeacherTest {

    @Test
    public void addTeacher() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company teacher.add", "Test address teacher.add");
        companyDAO.addCompany(newCompany);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher.add", newCompany);
        teacherDAO.addTeacher(newTeacher);

        Teacher testTeacher = teacherDAO.getTeacherById(newTeacher.getId());
        Assert.assertTrue(newTeacher.fullyEquals(testTeacher));
    }

    @Test
    public void updateTeacher() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany1 = new Company("Test company teacher.update1", "Test address1 teacher.update");
        companyDAO.addCompany(newCompany1);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher.update", newCompany1);
        teacherDAO.addTeacher(newTeacher);

        newTeacher.setName(newTeacher.getName() + "2");
        Company newCompany2 = new Company("Test company teacher.update2", "Test address2 teacher.update");
        companyDAO.addCompany(newCompany2);
        newTeacher.setCompany(newCompany2);
        teacherDAO.updateTeacher(newTeacher);

        Teacher testTeacher = teacherDAO.getTeacherById(newTeacher.getId());
        Assert.assertTrue(newTeacher.fullyEquals(testTeacher));
    }

    @Test
    public void deleteTeacher() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company teacher.delete", "Test address teacher.delete");
        companyDAO.addCompany(newCompany);

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher.delete", newCompany);
        teacherDAO.addTeacher(newTeacher);

        teacherDAO.deleteTeacher(newTeacher);
        Collection<Teacher> teachers = teacherDAO.getAllTeachers();
        for (Teacher testTeacher : teachers) {
            Assert.assertFalse(newTeacher.fullyEquals(testTeacher));
        }
    }

}
