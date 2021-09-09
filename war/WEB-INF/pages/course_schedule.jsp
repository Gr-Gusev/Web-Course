<%@ page import="entity.Course" %>
<%@ page import="entity.Lesson" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Расписание курса</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/course_info">
        <button class="return" name="id" value="${id}">Назад к странице курса</button>
    </form>
    <form method="get" name="add_lesson" action="/learning_center/add_lesson">
        <input type="hidden" name="course_id" value="${id}">
        <input class="standard" type="submit" value="Добавить занятие">
    </form>
    <%
        Course course = (Course) request.getAttribute("course");

        String id = course.getId().toString();
        String name = course.getName();

        out.println("<table>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Имя</td><td>" + name + "</td></tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");

        Collection<Lesson> lessons = (Collection<Lesson>) request.getAttribute("list_of_lessons");
        out.println("<table>");
        out.println("<caption>Расписание</caption>");
        out.println("<tr><th>Id</th><th>Дата и время</th><th>Имя преподавателя</th><th>Статус занятия</th><th></th></tr>");
        for (Lesson lesson : lessons) {
            String l_id = lesson.getId().toString();
            String l_date = lesson.getDateAndTime().toString().substring(0, 16);
            String l_teacher = "";
            if (lesson.getTeacher() != null)
                l_teacher = lesson.getTeacher().getName();
            out.println("<tr><td><a href=lesson_info?id=" + l_id + '>' + l_id + "</a></td>");
            out.println("<td>" + l_date + "</td>");
            out.println("<td>" + l_teacher + "</td>");
            if (lesson.isPassed())
                out.println("<td>Завершено</td>");
            else
                out.println("<td></td>");
            out.println("<td><a href=delete_lesson?id=" + l_id + "&course_id=" + id + '>' + "Удалить</a></td></tr>");
        }
        out.println("</table>\n");
    %>
    <br>
    <br>
</body>
</html>