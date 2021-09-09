package web;

import dao.*;
import dao.Impl.*;
import entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        return "index";
    }

    @RequestMapping(value="/courses", method = RequestMethod.GET)
    public String printCourses(ModelMap model) {
        return "courses";
    }

    @RequestMapping(value="/course_info", method = RequestMethod.GET)
    public String printCourseInfo(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(Long.parseLong(id));
        model.addAttribute("course", course);
        model.addAttribute("id", course.getId().toString());

        LessonDAO lessonDAO = new LessonDAOImpl();
        Collection<Lesson> lessons = course.getLessons();
        model.addAttribute("list_of_lessons", lessons);
        return "course_info";
    }

    @RequestMapping(value="/add_course", method = RequestMethod.GET)
    public String gotoCourseEdit(ModelMap model) {
        model.addAttribute("id", "-1");
        return "redirect:course_edit";
    }

    @RequestMapping(value="/course_edit", method = RequestMethod.GET)
    public String courseEdit(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = new Course();
        model.addAttribute("id", id);
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Collection<Company> companies = companyDAO.getAllCompanies();
        model.addAttribute("list_of_companies", companies);
        if (Long.parseLong(id) != -1) {
            course = courseDAO.getCourseById(Long.parseLong(id));
            model.addAttribute("course", course);
            model.addAttribute("company", course.getCompany());
        }
        return "course_edit";
    }

    @RequestMapping(value="/confirm_course", method=RequestMethod.GET)
    public String confirmCourse(@RequestParam(name="id") String id,
                                @RequestParam(name="course_name") String name,
                                @RequestParam(name="company_id") String company_id,
                                @RequestParam(name="day_of_start") String day_of_start,
                                @RequestParam(name="day_of_finish") String day_of_finish,
                                @RequestParam(name="hours_per_day") short hours_per_day,
                                ModelMap model) throws SQLException {
        CourseDAO courseDAO = new CourseDAOImpl();
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company company = companyDAO.getCompanyById(Long.parseLong(company_id));
        if (Long.parseLong(id) != -1) {
            Course old_course = new Course();
            old_course = courseDAO.getCourseById(Long.parseLong(id));
            old_course.setName(name);
            old_course.setCompany(company);
            java.sql.Date date = java.sql.Date.valueOf(day_of_start);
            old_course.setDayOfStart(date);
            date = java.sql.Date.valueOf(day_of_finish);
            old_course.setDayOfFinish(date);
            old_course.setHoursPerDay(hours_per_day);
            courseDAO.updateCourse(old_course);
            model.addAttribute("id", id);
            return "redirect:course_info";
        } else {
            Course course = new Course();
            course.setName(name);
            course.setCompany(company);
            java.sql.Date date = java.sql.Date.valueOf(day_of_start);
            course.setDayOfStart(date);
            date = java.sql.Date.valueOf(day_of_finish);
            course.setDayOfFinish(date);
            course.setHoursPerDay(hours_per_day);
            courseDAO.addCourse(course);

        }
        return "redirect:courses";
    }

    @RequestMapping(value="/delete_course", method = RequestMethod.GET)
    public String deleteCourse(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(Long.parseLong(id));
        courseDAO.deleteCourse(course);
        return "redirect:courses";
    }

    @RequestMapping(value="/course_schedule", method = RequestMethod.GET)
    public String printCourseSchedule(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(Long.parseLong(id));
        model.addAttribute("id", id);
        model.addAttribute("course", course);

        LessonDAO lessonDAO = new LessonDAOImpl();
        Collection<Lesson> lessons = course.getLessons();
        model.addAttribute("list_of_lessons", lessons);
        return "course_schedule";
    }

    @RequestMapping(value="/teachers", method = RequestMethod.GET)
    public String printTeachers(ModelMap model) {
        return "teachers";
    }

    @RequestMapping(value="/teacher_info", method = RequestMethod.GET)
    public String printTeacherInfo(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher teacher = teacherDAO.getTeacherById(Long.parseLong(id));
        model.addAttribute("id", teacher.getId().toString());
        model.addAttribute("teacher", teacher);
        Set<Lesson> lessons = teacher.getLessons();
        Set<Course> courses = new HashSet<Course>();
        for (Lesson lesson : lessons)
            courses.add(lesson.getCourse());
        model.addAttribute("courses", courses);
        return "teacher_info";
    }

    @RequestMapping(value="/add_teacher", method = RequestMethod.GET)
    public String gotoTeacherEdit(ModelMap model) {
        model.addAttribute("id", "-1");
        return "redirect:teacher_edit";
    }

    @RequestMapping(value="/teacher_edit", method = RequestMethod.GET)
    public String teacherEdit(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
        model.addAttribute("id", id);
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Collection<Company> companies = companyDAO.getAllCompanies();
        model.addAttribute("list_of_companies", companies);
        if (Long.parseLong(id) != -1) {
            TeacherDAO teacherDAO = new TeacherDAOImpl();
            Teacher teacher = teacherDAO.getTeacherById(Long.parseLong(id));
            model.addAttribute("teacher", teacher);
            model.addAttribute("company", teacher.getCompany());
        }
        return "teacher_edit";
    }

    @RequestMapping(value="/confirm_teacher", method=RequestMethod.GET)
    public String confirmTeacher(@RequestParam(name="id") String id,
                                @RequestParam(name="name") String name,
                                @RequestParam(name="company_id") String company_id,
                                ModelMap model) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company company = null;
        if (!company_id.equals(""))
            company = companyDAO.getCompanyById(Long.parseLong(company_id));
        if (Long.parseLong(id) != -1) {
            Teacher old_teacher = teacherDAO.getTeacherById(Long.parseLong(id));
            old_teacher.setName(name);
            if (company != old_teacher.getCompany()) {
                LessonDAO lessonDAO = new LessonDAOImpl();
                Set<Lesson> lessons = old_teacher.getLessons();
                for (Lesson lesson : lessons) {
                    lesson.setTeacher(null);
                    lessonDAO.updateLesson(lesson);
                }
            }
            old_teacher.setCompany(company);
            teacherDAO.updateTeacher(old_teacher);
            model.addAttribute("id", id);
            return "redirect:teacher_info";
        } else {
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setCompany(company);
            teacherDAO.addTeacher(teacher);
        }
        return "redirect:teachers";
    }

    @RequestMapping(value="/teacher_schedule", method = RequestMethod.GET)
    public String printTeacherSchedule(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher teacher = teacherDAO.getTeacherById(Long.parseLong(id));
        model.addAttribute("id", id);
        model.addAttribute("teacher", teacher);
        return "teacher_schedule";
    }

    @RequestMapping(value="/delete_teacher", method = RequestMethod.GET)
    public String deleteTeacher(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher teacher = teacherDAO.getTeacherById(Long.parseLong(id));
        teacherDAO.deleteTeacher(teacher);
        return "redirect:teachers";
    }

    @RequestMapping(value="/students", method = RequestMethod.GET)
    public String printStudents(ModelMap model) {
        return "students";
    }

    @RequestMapping(value="/student_info", method = RequestMethod.GET)
    public String printStudentInfo(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.getStudentById(Long.parseLong(id));
        model.addAttribute("student", student);
        model.addAttribute("id", student.getId().toString());
        return "student_info";
    }

    @RequestMapping(value="/add_student", method = RequestMethod.GET)
    public String gotoStudentEdit(ModelMap model) {
        model.addAttribute("id", "-1");
        return "redirect:student_edit";
    }

    @RequestMapping(value="/student_history", method = RequestMethod.GET)
    public String printStudentHistory(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.getStudentById(Long.parseLong(id));
        model.addAttribute("id", id);
        model.addAttribute("student", student);
        return "student_history";
    }

    @RequestMapping(value="/student_schedule", method = RequestMethod.GET)
    public String printStudentSchedule(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.getStudentById(Long.parseLong(id));
        model.addAttribute("id", id);
        model.addAttribute("student", student);
        return "student_schedule";
    }

    @RequestMapping(value="/student_edit", method = RequestMethod.GET)
    public String studentEdit(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = new Student();
        model.addAttribute("id", id);
        if (Long.parseLong(id) == -1) {
            model.addAttribute("name", "");
        } else {
            student = studentDAO.getStudentById(Long.parseLong(id));
            model.addAttribute("name", student.getName());
       }
        return "student_edit";
    }

    @RequestMapping(value="/confirm_student", method=RequestMethod.GET)
    public String confirmStudent(@RequestParam(name="id") String id, @RequestParam(name="name") String name, ModelMap model) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = new Student();
        if (Long.parseLong(id) != -1) {
            student = studentDAO.getStudentById(Long.parseLong(id));
        }
        if (name.equals(""))
            name = null;
        student.setName(name);
        if (Long.parseLong(id) == -1) {
            studentDAO.addStudent(student);
        } else {
            studentDAO.updateStudent(student);
        }
        return "redirect:students";
    }

    @RequestMapping(value="/delete_student", method = RequestMethod.GET)
    public String deleteStudent(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.getStudentById(Long.parseLong(id));
        studentDAO.deleteStudent(student);
        return "redirect:students";
    }

    @RequestMapping(value="/companies", method = RequestMethod.GET)
    public String printCompanies(ModelMap model) {
        return "companies";
    }

    @RequestMapping(value="/company_info", method = RequestMethod.GET)
    public String printCompanyInfo(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company company = companyDAO.getCompanyById(Long.parseLong(id));
        model.addAttribute("id", company.getId().toString());
        model.addAttribute("company", company);
        Collection<Course> courses = company.getCourses();
        model.addAttribute("list_of_courses", courses);
        Collection<Teacher> teachers = company.getTeachers();
        model.addAttribute("list_of_teachers", teachers);
        return "company_info";
    }

    @RequestMapping(value="/add_company", method = RequestMethod.GET)
    public String gotoCompanyEdit(ModelMap model) {
        model.addAttribute("id", "-1");
        return "redirect:company_edit";
    }

    @RequestMapping(value="/company_edit", method = RequestMethod.GET)
    public String companyEdit(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company company = new Company();
        model.addAttribute("id", id);
        if (!id.equals("-1"))
            company = companyDAO.getCompanyById(Long.parseLong(id));
        model.addAttribute("company", company);
        return "company_edit";
    }

    @RequestMapping(value="/confirm_company", method=RequestMethod.GET)
    public String confirmCompany(@RequestParam(name="id") String id,
                                 @RequestParam(name="company_name") String name,
                                 @RequestParam(name="address") String address,
                                 ModelMap model) throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company company = new Company();
        if (Long.parseLong(id) != -1) {
            company = companyDAO.getCompanyById(Long.parseLong(id));
        }
        company.setName(name);
        company.setAddress(address);
        if (Long.parseLong(id) == -1) {
            companyDAO.addCompany(company);
        } else {
            companyDAO.updateCompany(company);
        }
        return "redirect:companies";
    }

    @RequestMapping(value="/delete_company", method = RequestMethod.GET)
    public String deleteCompany(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        Company company = companyDAO.getCompanyById(Long.parseLong(id));
        companyDAO.deleteCompany(company);
        return "redirect:companies";
    }

    @RequestMapping(value="/lessons", method = RequestMethod.GET)
    public String printlessons(ModelMap model) {
        return "lessons";
    }

    @RequestMapping(value="/lesson_info", method = RequestMethod.GET)
    public String printLessonInfo(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson lesson = lessonDAO.getLessonById(Long.parseLong(id));
        model.addAttribute("id", lesson.getId().toString());
        model.addAttribute("lesson", lesson);
        model.addAttribute("course_id", lesson.getCourse().getId().toString());
        return "lesson_info";
    }

    @RequestMapping(value="/add_lesson", method = RequestMethod.GET)
    public String gotoLessonEdit(@RequestParam(name="course_id") String course_id, ModelMap model) {
        model.addAttribute("id", "-1");
        model.addAttribute("course_id", course_id);
        return "redirect:lesson_edit";
    }

    @RequestMapping(value="/lesson_edit", method = RequestMethod.GET)
    public String lessonEdit(@RequestParam(name="id", required=true) String id,
                             @RequestParam(name="course_id", required=true) String course_id,
                             ModelMap model) throws SQLException {
        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson lesson = new Lesson();
        model.addAttribute("id", id);
        model.addAttribute("course_id", course_id);

        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(Long.parseLong(course_id));
        Collection<Teacher> teachers = course.getCompany().getTeachers();
        model.addAttribute("list_of_teachers", teachers);

        java.sql.Date date = null;
        java.sql.Time time = null;
        if (!id.equals("-1")) {
            lesson = lessonDAO.getLessonById(Long.parseLong(id));
            date = new Date(lesson.getDateAndTime().getTime());
            time = new Time(lesson.getDateAndTime().getTime());
        }
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("lesson", lesson);
        return "lesson_edit";
    }

    @RequestMapping(value="/confirm_lesson", method=RequestMethod.GET)
    public String confirmLesson(@RequestParam(name="id") String id,
                                @RequestParam(name="course_id", required=true) String course_id,
                                @RequestParam(name="teacher_id", required=true) String teacher_id,
                                @RequestParam(name="date", required=true) String date,
                                @RequestParam(name="time", required=true) String time,
                                ModelMap model) throws SQLException {
        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson lesson = new Lesson();
        if (Long.parseLong(id) != -1) {
            lesson = lessonDAO.getLessonById(Long.parseLong(id));
        }
        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(Long.parseLong(course_id));
        lesson.setCourse(course);
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        if (!teacher_id.equals("")) {
            Teacher teacher = teacherDAO.getTeacherById(Long.parseLong(teacher_id));
            lesson.setTeacher(teacher);
        } else {
            lesson.setTeacher(null);
        }
        if (time.length() == 5)
            time += ":00";
        java.sql.Time time1 = java.sql.Time.valueOf(time);
        java.sql.Date date1 = java.sql.Date.valueOf(date);
        java.sql.Timestamp date_and_time = new java.sql.Timestamp(date1.getTime() + time1.getTime() + 3 * 60 * 60 * 1000);
        lesson.setDateAndTime(date_and_time);
        if (Long.parseLong(id) == -1) {
            lessonDAO.addLesson(lesson);
        } else {
            lessonDAO.updateLesson(lesson);
        }
        model.addAttribute("id", course_id);
        return "redirect:course_schedule";
    }

    @RequestMapping(value="/delete_lesson", method = RequestMethod.GET)
    public String deleteLesson(@RequestParam(name="id", required=true) String id,
                               @RequestParam(name="course_id", required=true) String course_id,
                               ModelMap model) throws SQLException {
        LessonDAO lessonDAO = new LessonDAOImpl();
        Lesson lesson = lessonDAO.getLessonById(Long.parseLong(id));
        lessonDAO.deleteLesson(lesson);
        model.addAttribute("id", course_id);
        return "redirect:course_schedule";
    }

    @RequestMapping(value="/add_student_to_course", method = RequestMethod.GET)
    public String addStudentToCourse(@RequestParam(name="course_id") String course_id,
                                     @RequestParam(name="student_id") String student_id,
                                     ModelMap model) throws SQLException {
        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(Long.parseLong(course_id));
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.getStudentById(Long.parseLong(student_id));
        student.addToCourse(course);
        studentDAO.updateStudent(student);
        model.addAttribute("id", course_id);
        return "redirect:course_info";
    }

    @RequestMapping(value="/delete_student_from_course", method = RequestMethod.GET)
    public String deleteStudentFromCourse(@RequestParam(name="course_id") String course_id,
                                          @RequestParam(name="student_id") String student_id,
                                          ModelMap model) throws SQLException {
        model.addAttribute("id", course_id);
        CourseDAO courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(Long.parseLong(course_id));
        StudentDAO studentDAO = new StudentDAOImpl();
        if (student_id.equals(""))
            return "redirect:course_info";
        Student student = studentDAO.getStudentById(Long.parseLong(student_id));
        student.deleteFromCourse(course);
        studentDAO.updateStudent(student);
        return "redirect:course_info";
    }
}