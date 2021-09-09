<%@ page import="entity.Course, entity.Student" %>
<%@ page import="java.util.Set" %>
<%@ page import="dao.Impl.StudentDAOImpl" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="java.util.Collection" %>
<%@ page import="entity.Teacher" %>
<%@ page import="entity.Lesson" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница курса</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/courses">
        <button class="return">Назад к списку курсов</button>
    </form>
    <form method="get" name="course_schedule" action="/learning_center/course_schedule">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Расписание занятий">
    </form>
    <form method="get" name="edit_course" action="/learning_center/course_edit">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Изменить">
    </form>
    <form method="get" name="delete_course" action="/learning_center/delete_course">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Удалить">
    </form>

    <%
        StudentDAO studentDAO = new StudentDAOImpl();
        Collection<Student> allStudents = studentDAO.getAllStudents();
        Course course = (Course) request.getAttribute("course");
        String id = course.getId().toString();
        Set<Student> students = course.getStudents();

        out.println("<form method=\"get\" name=\"add_student_to_course\" " +
                "action=\"/learning_center/add_student_to_course\">");
        out.println("<input type=\"hidden\" name=\"course_id\" value=" + id + ">");
        out.println("<select class=\"edit\" required name=\"student_id\">");
        out.println("<option selected disabled value=\"\">Выберите учащегося</option>");
        for (Student tmp_student : allStudents) {
            if (!students.contains(tmp_student)) {
                out.println("<option value=\"" + tmp_student.getId().toString() + "\">");
                out.println(tmp_student.getName() + "</option>");
            }
        }
        out.println("</select>");
        out.println("<input class=\"standard\" type=\"submit\" value=\"Добавить учащегося\">");
        out.println("</form>");

        String name = course.getName();
        String company_name = course.getCompany().getName();
        String day_of_start = course.getDayOfStart().toString();
        String day_of_finish = course.getDayOfFinish().toString();
        String hours_per_day = course.getHoursPerDay().toString();

        out.println("<table>");
        out.println("<tr><td>Статус</td>");
        if (course.isFinished())
            out.println("<td>Завершён</td></tr>");
        else
            out.println("<td>Открыт</td></tr>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Название</td><td>" + name + "</td></tr>");
        out.println("<tr><td>Компания</td><td><a href=company_info?id=" +
                        course.getCompany().getId().toString() + '>' + company_name + "</a></td></tr>");
        out.println("<tr><td>Дата начала</td><td>" + day_of_start + "</td></tr>");
        out.println("<tr><td>Дата окончания</td><td>" + day_of_finish + "</td></tr>");
        out.println("<tr><td>Нагрузка (часов в день)</td><td>" + hours_per_day + "</td></tr>");
    %>
    </table>
    <br>
    <br>

    <table>
    <caption>Список преподавателей</caption>
    <tr><th>Id</th><th>Имя преподавателя</th></tr>
    <%
        Set<Lesson> lessons = course.getLessons();
        Set<Teacher> teachers = new HashSet<Teacher>();
        for (Lesson tmp_lesson : lessons)
            if (tmp_lesson.getTeacher() != null)
                teachers.add(tmp_lesson.getTeacher());
        for (Teacher tmp_teacher : teachers) {
            String t_id = tmp_teacher.getId().toString();
            String t_name = tmp_teacher.getName();
            out.println("<tr>");
            out.println("<td>" + t_id + "</td>");
            out.println("<td><a href=teacher_info?id=" + t_id + '>' + t_name + "</a></td>");
            out.println("</tr>");
        }
    %>
    </table>
    <br>
    <br>

    <table>
    <caption>Список учащихся</caption>
    <tr><th>Id</th><th>Имя учащегося</th><th></th></tr>
    <%
        for (Student student : students) {
            String st_id = student.getId().toString();
            String st_name = student.getName();
            out.println("<tr>");
            out.println("<td>" + st_id + "</td>");
            out.println("<td><a href=student_info?id=" + st_id + '>' + st_name + "</td>");
            out.println("<td valign=\"center\">");
            out.println("<form method=\"get\" name=\"delete_student_from_course\" " +
                    "action=\"/learning_center/delete_student_from_course\">");
            out.println("<input type=\"hidden\" name=\"course_id\" value=" + id + ">");
            out.println("<input type=\"hidden\" name=\"student_id\" value=" + st_id + ">");
            out.println("<input type=\"submit\" value=\"Удалить\">");
            out.println("</form>");
            out.println("</td></tr>");
        }
    %>
    </table>
    <br>
    <br>
</body>
</html>