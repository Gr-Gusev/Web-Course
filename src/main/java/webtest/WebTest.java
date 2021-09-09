package webtest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;

import dao.*;
import dao.Impl.*;
import entity.*;

import org.xml.sax.SAXException;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.*;
import com.meterware.httpunit.WebResponse;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WebTest {

    private Student createTestStudent() throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student newStudent = new Student("Test student");
        studentDAO.addStudent(newStudent);
        return newStudent;
    }

    private void deleteTestStudent(Student student) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.deleteStudent(student);
    }

    private Company createTestCompany() throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = new Company("Test company", "Test address");
        companyDAO.addCompany(newCompany);
        return newCompany;
    }

    private void deleteTestCompany(Company company) throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        companyDAO.deleteCompany(company);
    }

    private Course createTestCourse(Company company) throws SQLException {
        long dayInMs = 24 * 60 * 60 * 1000;
        java.util.Date curDate = new java.util.Date();
        java.sql.Date start = new java.sql.Date(0);
        java.sql.Date finish = new java.sql.Date(0);
        start.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) - dayInMs);
        finish.setTime(curDate.getTime() - (curDate.getTime() % dayInMs) + dayInMs);
        CourseDAO courseDAO = new CourseDAOImpl();
        short hours = 2;
        Course newCourse = new Course(company, start, finish, hours, "Test course");
        courseDAO.addCourse(newCourse);
        return newCourse;
    }

    private void deleteTestCourse(Course course) throws SQLException {
        CourseDAO courseDAO = new CourseDAOImpl();
        courseDAO.deleteCourse(course);
    }

    private Teacher createTestTeacher(Company company) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher newTeacher = new Teacher("Test teacher", company);
        teacherDAO.addTeacher(newTeacher);
        return newTeacher;
    }

    private void deleteTestTeacher(Teacher teacher) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        teacherDAO.deleteTeacher(teacher);
    }

    private Lesson createTestLesson(Course course, Teacher teacher, int hoursShift) throws SQLException {
        LessonDAO lessonDAO = new LessonDAOImpl();
        java.util.Date curDate = new java.util.Date();
        java.sql.Timestamp time = new java.sql.Timestamp(curDate.getTime() + (long) hoursShift * 60 * 60 * 1000);
        Lesson newLesson = new Lesson(course, teacher, time);
        lessonDAO.addLesson(newLesson);
        return newLesson;
    }

    private void deleteTestLesson(Lesson lesson) throws SQLException {
        LessonDAO lessonDAO = new LessonDAOImpl();
        lessonDAO.deleteLesson(lesson);
    }

    @Test
    public void indexPageTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://127.0.0.1:8080/learning_center");
        assertEquals("Домашняя страница", new String(resp.getTitle().getBytes(StandardCharsets.UTF_8)));
        WebLink[] links = resp.getLinks();
        assertEquals(links.length, 5);
        assertEquals(links[0].getURLString(), "learning_center/courses");
        assertEquals(links[1].getURLString(), "learning_center/teachers");
        assertEquals(links[2].getURLString(), "learning_center/students");
        assertEquals(links[3].getURLString(), "learning_center/companies");
        assertEquals(links[4].getURLString(), "learning_center/lessons");
    }

    @Test
    public void courseTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse mainResp = wc.getResponse("http://127.0.0.1:8080/learning_center");
        WebLink[] links = mainResp.getLinks();

        // Create new course, company and student:
        Student newStudent = createTestStudent();
        Company newCompany = createTestCompany();
        Course newCourse = createTestCourse(newCompany);
        Long courseId = newCourse.getId();

        // Find course:
        WebResponse coursesResp = wc.getResponse("http://127.0.0.1:8080/learning_center/courses");
        links = coursesResp.getLinks();
        WebLink courseLink = null;
        for (WebLink link : links) {
            if (link.getURLString().equals("course_info?id=" + courseId)) {
                courseLink = link;
            }
        }
        assertNotNull(courseLink);

        // Open course page:
        WebResponse courseResp = courseLink.click();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/course_info?id="
                        + courseId).getURL().getPath(), courseResp.getURL().getPath());
        WebTable[] tables = courseResp.getTables();
        TableRow[] rows = tables[0].getRows();
        String[] params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + courseId.toString(), params[2]);

        // Add student to course:
        WebForm addStudentForm = courseResp.getFormWithName("add_student_to_course");
        assertNotNull(addStudentForm);
        addStudentForm.setParameter("student_id", newStudent.getId().toString());
        WebResponse addResp = addStudentForm.submit();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/course_info?id="
                + courseId).getURL().getPath(), addResp.getURL().getPath());
        tables = addResp.getTables();
        rows = tables[2].getRows();
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newStudent.getName() + ' ', params[2]);

        // Delete student from course:
        WebForm deleteStudentFrom = addResp.getFormWithName("delete_student_from_course");
        assertNotNull(deleteStudentFrom);
        WebResponse deleteResp = deleteStudentFrom.submit();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/course_info?id="
                + courseId).getURL().getPath(), addResp.getURL().getPath());
        links = deleteResp.getLinks();
        for (WebLink link : links) {
            assertNotEquals("/learning_center/student_info?id=" + newStudent.getId(), link.getURLString());
        }

        // Delete course:
        WebForm deleteCourseForm = deleteResp.getFormWithName("delete_course");
        assertNotNull(deleteCourseForm);
        WebResponse deleteCourseResp = wc.getResponse(deleteCourseForm.getRequest());
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/courses").getURL().getPath(),
                deleteCourseResp.getURL().getPath());
        links = deleteCourseResp.getLinks();
        courseLink = null;
        for (WebLink link : links) {
            if (link.getURLString().equals("course_info?id=" + courseId)) {
                courseLink = link;
            }
        }
        assertNull(courseLink);

        // Delete remaining objects:
        deleteTestStudent(newStudent);
        deleteTestCompany(newCompany);
    }

    @Test
    public void teacherTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();

        // Create new teacher and company:
        Company newCompany = createTestCompany();
        Teacher newTeacher = createTestTeacher(newCompany);
        Long teacherId = newTeacher.getId();

        // Find teacher:
        WebResponse mainResp = wc.getResponse("http://127.0.0.1:8080/learning_center/teachers");
        WebLink[] links = mainResp.getLinks();
        WebLink teacherLink = null;
        for (WebLink link : links) {
            if (link.getURLString().equals("teacher_info?id=" + teacherId)) {
                teacherLink = link;
            }
        }
        assertNotNull(teacherLink);

        // Open teacher page:
        WebResponse teacherResp = teacherLink.click();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/teacher_info?id="
                + teacherId).getURL().getPath(), teacherResp.getURL().getPath());
        WebTable[] tables = teacherResp.getTables();
        TableRow[] rows = tables[0].getRows();
        String[] params = new String(rows[0].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + teacherId.toString(), params[2]);
        params = new String(rows[2].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newCompany.getName(), params[2]);

        // Check schedule:
        Course newCourse = createTestCourse(newCompany);
        Lesson newLesson = createTestLesson(newCourse, newTeacher, 1);
        WebForm scheduleForm = teacherResp.getFormWithName("teacher_schedule");
        WebResponse scheduleResp = scheduleForm.submit();
        links = scheduleResp.getLinks();
        assertEquals(1, links.length);
        WebResponse lessonResp = links[0].click();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/lesson_info?id="
                + newLesson.getId()).getURL().getPath(), lessonResp.getURL().getPath());
        tables = lessonResp.getTables();
        rows = tables[0].getRows();
        params = new String(rows[0].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newLesson.getId().toString(), params[2]);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newCourse.getName(), params[2]);
        params = new String(rows[2].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newTeacher.getName(), params[2]);

        // Edit teacher:
        teacherResp = wc.getResponse("http://127.0.0.1:8080/learning_center/teacher_info?id=" + teacherId);
        WebForm editForm = teacherResp.getFormWithName("edit_teacher");
        assertNotNull(editForm);
        teacherResp = editForm.submit();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/teacher_edit?id="
                + teacherId).getURL().getPath(), teacherResp.getURL().getPath());
        editForm = teacherResp.getFormWithName("edit_teacher");
        editForm.setParameter("name", "New test teacher");
        editForm.setParameter("company_id", "");
        teacherResp = wc.getResponse(editForm.getRequest());

        // Check the changes:
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/teacher_info?id="
                + teacherId).getURL().getPath(), teacherResp.getURL().getPath());
        tables = teacherResp.getTables();
        rows = tables[0].getRows();
        params = new String(rows[0].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + teacherId.toString(), params[2]);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(" New test teacher", params[2]);
        params = new String(rows[2].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(" -", params[2]);

        // Delete teacher:
        WebForm deleteTeacherForm = teacherResp.getFormWithName("delete_teacher");
        assertNotNull(deleteTeacherForm);
        WebResponse deleteTeacherResp = wc.getResponse(deleteTeacherForm.getRequest());
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/teachers").getURL().getPath(),
                deleteTeacherResp.getURL().getPath());
        links = deleteTeacherResp.getLinks();
        teacherLink = null;
        for (WebLink link : links) {
            if (link.getURLString().equals("teacher_info?id=" + teacherId)) {
                teacherLink = link;
            }
        }
        assertNull(teacherLink);

        // Delete remaining objects:
        deleteTestCompany(newCompany);
    }

    @Test
    public void studentTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();

        // Create new student:
        Student newStudent = createTestStudent();
        Long studentId = newStudent.getId();

        // Create new course and add student to it:
        Company newCompany = createTestCompany();
        Course newCourse = createTestCourse(newCompany);
        Lesson newLesson = createTestLesson(newCourse, null, 1);
        newStudent.addToCourse(newCourse);
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.updateStudent(newStudent);

        // Find student:
        WebResponse mainResp = wc.getResponse("http://127.0.0.1:8080/learning_center/students");
        WebLink[] links = mainResp.getLinks();
        WebLink studentLink = null;
        for (WebLink link : links) {
            if (link.getURLString().equals("student_info?id=" + studentId)) {
                studentLink = link;
            }
        }
        assertNotNull(studentLink);

        // Open student page:
        WebResponse studentResp = studentLink.click();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/student_info?id="
                + studentId).getURL().getPath(), studentResp.getURL().getPath());
        WebTable[] tables = studentResp.getTables();
        TableRow[] rows = tables[0].getRows();
        String[] params = new String(rows[0].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + studentId.toString(), params[2]);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newStudent.getName(), params[2]);
        rows = tables[1].getRows();
        assertEquals(1, rows.length);
        params = new String(rows[0].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newCourse.getName(), params[2]);

        // Check education history:
        Course passedCourse = createTestCourse(newCompany);
        java.util.Date curDate = new java.util.Date();
        passedCourse.setDayOfFinish(new Date(curDate.getTime() - (long) 24 * 60 * 60 * 1000));
        CourseDAO courseDAO = new CourseDAOImpl();
        courseDAO.updateCourse(passedCourse);
        newStudent.addToCourse(passedCourse);
        studentDAO.updateStudent(newStudent);
        assertTrue(passedCourse.isFinished());
        WebForm historyForm = studentResp.getFormWithName("student_history");
        assertNotNull(historyForm);
        WebResponse historyResp = historyForm.submit();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/student_history?id="
                + studentId).getURL().getPath(), historyResp.getURL().getPath());
        tables = historyResp.getTables();
        rows = tables[1].getRows();
        assertEquals(2, rows.length);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + passedCourse.getId().toString() + ' ', params[1]);

        // Check schedule:
        studentResp = wc.getResponse("http://127.0.0.1:8080/learning_center/student_info?id=" + studentId);
        WebForm scheduleForm = studentResp.getFormWithName("student_schedule");
        assertNotNull(scheduleForm);
        WebResponse scheduleResp = wc.getResponse(scheduleForm.getRequest());
        assertNotNull(scheduleResp);
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/student_schedule?id="
                + studentId).getURL().getPath(), scheduleResp.getURL().getPath());
        tables = scheduleResp.getTables();
        rows = tables[1].getRows();
        assertEquals(2, rows.length);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newLesson.getId().toString() + ' ', params[1]);

        // Delete student:
        studentResp = wc.getResponse("http://127.0.0.1:8080/learning_center/student_info?id=" + studentId);
        WebForm deleteStudentForm = studentResp.getFormWithName("delete_student");
        assertNotNull(deleteStudentForm);
        WebResponse deleteStudentResp = wc.getResponse(deleteStudentForm.getRequest());
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/students").getURL().getPath(),
                deleteStudentResp.getURL().getPath());
        links = deleteStudentResp.getLinks();
        studentLink = null;
        for (WebLink link : links) {
            if (link.getURLString().equals("student_info?id=" + studentId)) {
                studentLink = link;
            }
        }
        assertNull(studentLink);

        // Delete remaining objects:
        deleteTestCompany(newCompany);
    }

    @Test
    public void companyTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();

        // Add company:
        WebResponse companiesResp = wc.getResponse("http://127.0.0.1:8080/learning_center/companies");
        WebForm addForm = companiesResp.getFormWithName("add_company");
        assertNotNull(addForm);
        WebResponse addResp = wc.getResponse(addForm.getRequest());
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/company_edit?id=-1").getURL().getPath(),
                addResp.getURL().getPath());
        addForm = addResp.getFormWithName("company_edit");
        assertNotNull(addForm);
        String testName = "Test company name";
        addForm.setParameter("company_name", testName);
        addForm.setParameter("address", "Test address");
        companiesResp = wc.getResponse(addForm.getRequest());
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/companies").getURL().getPath(),
                companiesResp.getURL().getPath());

        // Find company:
        WebTable[] tables = companiesResp.getTables();
        TableRow[] rows = tables[0].getRows();
        Long companyId = (long) -1;
        for (TableRow row : rows) {
            String[] params = new String(row.getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
            if (params[2].equals(' ' + testName))
                companyId = Long.parseLong(params[1].substring(1, params[1].length() - 1));
        }
        assertNotEquals(-1, companyId);

        // Get new company:
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company newCompany = companyDAO.getCompanyById(companyId);
        assertNotNull(newCompany);

        // Create new teacher and course:
        Teacher newTeacher = createTestTeacher(newCompany);
        Course newCourse = createTestCourse(newCompany);

        // Check info:
        WebResponse companyResp = wc.getResponse("http://127.0.0.1:8080/learning_center/company_info?id="
                + companyId.toString());
        tables = companyResp.getTables();
        rows = tables[0].getRows();
        assertEquals(3, rows.length);
        String[] params = new String(rows[0].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newCompany.getId().toString(), params[2]);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newCompany.getName(), params[2]);
        params = new String(rows[2].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newCompany.getAddress(), params[2]);

        // Check course and teacher:
        rows = tables[1].getRows();
        assertEquals(2, rows.length);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newCourse.getId().toString() + ' ', params[1]);
        rows = tables[2].getRows();
        assertEquals(2, rows.length);
        params = new String(rows[1].getText().getBytes(StandardCharsets.UTF_8)).split("\\|");
        assertEquals(' ' + newTeacher.getId().toString() + ' ', params[1]);

        // Delete company:
        WebForm deleteForm = companyResp.getFormWithName("delete_company");
        companiesResp = wc.getResponse(deleteForm.getRequest());
        assertEquals(wc.getResponse("http://127.0.0.1:8080/learning_center/companies").getURL().getPath(),
                companiesResp.getURL().getPath());
        WebLink[] links = companiesResp.getLinks();
        for (WebLink link : links) {
            assertNotEquals("company_info?id=" + companyId, link.getURLString());
        }

        // Delete remaining objects:
        deleteTestTeacher(newTeacher);
    }
}
