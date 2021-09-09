<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, java.util.*, util.*, dao.*" %>
<%@ page import="entity.Course" %>
<%@ page import="dao.Impl.CourseDAOImpl" %>
<%@ page import="entity.Teacher" %>
<%@ page import="entity.Lesson" %>
<html>
<head>
    <title>Изменить (добавить) занятие</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form class="edit" method="get" name="course_edit" action="/learning_center/confirm_lesson">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="course_id" value="${course_id}">
    <p><label>Дата и время проведения:
        <input required type="date"
        <%
            CourseDAO courseDAO = new CourseDAOImpl();
            long course_id = Long.parseLong((String) request.getAttribute("course_id"));
            Course course = courseDAO.getCourseById(course_id);
            String start_day = course.getDayOfStart().toString();
            String finish_day = course.getDayOfFinish().toString();
            out.println(" name=\"date\" min=\"" + start_day + "\" max=\"" + finish_day + "\"");
        %>
        value="${date}">
        <input required type="time" name="time" step="900" value="${time}">
    </label></p>
    <%
        Collection<Teacher> list = (Collection<Teacher>) request.getAttribute("list_of_teachers");
        out.println("<select name=\"teacher_id\">");
        long teacher_id = -1;
        Lesson lesson = (Lesson) request.getAttribute("lesson");
        if (lesson.getTeacher() != null) {
            teacher_id = lesson.getTeacher().getId();
        } else {
            out.println("<option selected value=\"\">Выберите преподавателя</option>");
        }
        for (Teacher tmp_teacher : list) {
            if (teacher_id == tmp_teacher.getId()) {
                out.println("<option selected value=\"" + tmp_teacher.getId().toString() + "\">");
            } else {
                out.println("<option value=\"" + tmp_teacher.getId().toString() + "\">");
            }
            out.println(tmp_teacher.getName() + "</option>");
        }
        out.println("</select>");
    %>
    <input class="edit" type="submit" name="confirm" value="Сохранить">
    </form>
    <%
        if (request.getAttribute("id").equals("-1")) {
            out.println("<form action=\"/learning_center/course_schedule\">");
            out.println("<button class=\"return\" name=\"id\" value=\"" + request.getAttribute("course_id") + "\">Назад</button>");
            out.println("</form>");
        } else {
            out.println("<form action=\"/learning_center/lesson_info\">");
            out.println("<button class=\"return\" name=\"id\" value=\"" + request.getAttribute("id") + "\">Назад</button>");
            out.println("</form>");
        }
    %>
</body>
</html>

