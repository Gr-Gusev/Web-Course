import dao.Impl.*;
import entity.*;
import dao.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String args[]) throws SQLException {
        System.out.println("=== Companies ===");
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Collection companies = companyDAO.getAllCompanies();
        Iterator iterator = companies.iterator();
        while (iterator.hasNext()) {
            Company company = (Company) iterator.next();
            System.out.print(company.getId());
            System.out.print("  -  ");
            System.out.print(company.getName());
            System.out.print("  -  ");
            System.out.println(company.getAddress());
        }


        System.out.println("=== Students ===");
        StudentDAO studentDAO = new StudentDAOImpl();
        Collection students = studentDAO.getAllStudents();
        iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println(student.getName());
        }

        TeacherDAO teacherDAO = new TeacherDAOImpl();
        long te = 3;
        Teacher teacher = teacherDAO.getTeacherById(te);
        System.out.print("Teacher " + teacher.getId() + "  ");
        System.out.println(teacher.getCompany().getName());


        System.out.println("=== Lessons ===");
        LessonDAO lessonDAO = new LessonDAOImpl();
        Collection lessons = lessonDAO.getAllLessons();
        iterator = lessons.iterator();
        while (iterator.hasNext()) {
            Lesson lesson = (Lesson) iterator.next();
            System.out.print(lesson.getId());
            System.out.print(' ');
            System.out.print(lesson.getCourse().getId());
            System.out.print(' ');
            System.out.print(lesson.getTeacher().getId());
            System.out.print(' ');
            System.out.println(lesson.getDateAndTime());
        }


        System.out.println("=== Teachers ===");
        teacherDAO = new TeacherDAOImpl();
        Collection teachers = teacherDAO.getAllTeachers();
        iterator = teachers.iterator();
        while (iterator.hasNext()) {
            teacher = (Teacher) iterator.next();
            System.out.println(teacher.getName());
        }

        System.out.println("=== Courses ===");
        CourseDAO courseDAO = new CourseDAOImpl();
        Collection courses = courseDAO.getAllCourses();
        iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = (Course) iterator.next();
            System.out.print(course.getName());
            System.out.print(' ');
            System.out.print(course.getCompany().getId());
            System.out.print(' ');
            System.out.println(course.getDayOfStart());
        }

        System.out.println("---Checking schedule of student---");
        long stud_id = 2;
        Student student = studentDAO.getStudentById(stud_id);
        System.out.println("Courses of " + student.getName() + ":");
        courses = student.getCourses();
        iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = (Course) iterator.next();
            System.out.println(course.getName());
            lessons = course.getLessons();
            for (Object o : lessons) {
                Lesson lesson = (Lesson) o;
                System.out.print(lesson.getId());
                System.out.print(' ');
                System.out.print(lesson.getTeacher().getId());
                System.out.print(' ');
                System.out.println(lesson.getTeacher().getCompany().getId());
            }
        }

        System.out.println("---Updating company---");
        long comp_id = 3;
        Company company = companyDAO.getCompanyById(comp_id);
        String name = company.getName();
        System.out.println("Old name: " + name);
        company.setName("TEST NAME");
        companyDAO.updateCompany(company);
        Company company1 = companyDAO.getCompanyById(comp_id);
        System.out.println("New name: " + company1.getName());
        company.setName(name);
        companyDAO.updateCompany(company);
        System.out.println("Old name returned");


        System.out.println("---Adding student to course---");
        long course_id = 5;
        stud_id = 3;
        Course course = courseDAO.getCourseById(course_id);
        System.out.println("Course: " + course.getId() + " " + course.getName());
        System.out.println("Students:");
        students = course.getStudents();
        iterator = students.iterator();
        while (iterator.hasNext()) {
            student = (Student) iterator.next();
            System.out.println(student.getId() + " " + student.getName());
        }
        student = studentDAO.getStudentById(stud_id);
        System.out.println("Student: " + student.getName());
        System.out.println("Courses:");
        courses = student.getCourses();
        iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course1 = (Course) iterator.next();
            System.out.println(course1.getId() + " " + course1.getName());
        }
        System.out.println(student.addToCourse(course));
        studentDAO.updateStudent(student);
        courses = student.getCourses();
        iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course1 = (Course) iterator.next();
            System.out.println(course1.getId() + " " + course1.getName());
        }
        course = courseDAO.getCourseById(course_id);
        System.out.println("Course: " + course.getId() + " " + course.getName());
        System.out.println("Students:");
        students = course.getStudents();
        iterator = students.iterator();
        while (iterator.hasNext()) {
            student = (Student) iterator.next();
            System.out.println(student.getId() + " " + student.getName());
        }



        System.out.println("---Current courses:---");
        courses = courseDAO.getCurrentCourses();
        iterator = courses.iterator();
        while (iterator.hasNext()) {
            course = (Course) iterator.next();
            System.out.println(course.getId() + " " + course.getName());
        }

        System.out.println("---Finished courses:---");
        courses = courseDAO.getFinishedCourses();
        iterator = courses.iterator();
        while (iterator.hasNext()) {
            course = (Course) iterator.next();
            System.out.println(course.getId() + " " + course.getName());
        }

        System.out.println("---Schedule for student:---");
        stud_id = 3;
        System.out.println("id: " + stud_id);
        List<Lesson> lessonsList = (List<Lesson>) lessonDAO.getScheduleForStudent(stud_id);
        iterator = lessonsList.iterator();
        while (iterator.hasNext()) {
            Lesson lesson = (Lesson) iterator.next();
            System.out.print(lesson.getId() + " " + lesson.getCourse().getId());
            System.out.println(" " + lesson.getDateAndTime().toString());
        }

        System.out.println("---Schedule for teacher:---");
        long teacher_id = 1;
        System.out.println("id: " + teacher_id);
        lessonsList = lessonDAO.getScheduleForTeacher(teacher_id);
        iterator = lessonsList.iterator();
        while (iterator.hasNext()) {
            Lesson lesson = (Lesson) iterator.next();
            System.out.print(lesson.getId() + " " + lesson.getCourse().getId());
            System.out.println(" " + lesson.getDateAndTime().toString());
        }

        System.out.println("---Adding teacher:---");
        long company_id = 1;
        System.out.println("Company: " + company_id);
        company = companyDAO.getCompanyById(company_id);
        iterator = company.getTeachers().iterator();
        while (iterator.hasNext()) {
            Teacher teacher1 = (Teacher) iterator.next();
            System.out.println(teacher1.getName());
        }
        teacher = new Teacher("Albert Ein", company);
        System.out.println("Adding: ");
        teacherDAO.addTeacher(teacher);
        company = companyDAO.getCompanyById(company_id);
        iterator = company.getTeachers().iterator();
        while (iterator.hasNext()) {
            Teacher teacher1 = (Teacher) iterator.next();
            System.out.println(teacher1.getName());
        }
    }
}
