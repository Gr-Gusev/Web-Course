<%@ page import="entity.Lesson" %>
<%@ page import="dao.Impl.LessonDAOImpl" %>
<%@ page import="dao.LessonDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Расписание для преподавателя</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/teacher_info">
        <button class="return" name="id" value="${id}">Назад к странице преподавателя</button>
    </form>
    <%
        Teacher teacher = (Teacher) request.getAttribute("teacher");
        String id = teacher.getId().toString();
        String name = teacher.getName();
    %>
    <table>
    <%
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Имя</td><td>" + name + "</td></tr>");
    %>
    </table>
    <br>
    <br>
    <%
        LessonDAO lessonDAO = new LessonDAOImpl();
        List<Lesson> lessons = lessonDAO.getScheduleForTeacher(teacher.getId());
        out.println("<table>");
        out.println("<caption>Расписание</caption>");
        out.println("<tr><th>Id</th><th>Название курса</th><th>Дата и время</th></tr>\n");
        for (Lesson lesson : lessons) {
            String l_id = lesson.getId().toString();
            String l_course = lesson.getCourse().getName();
            String l_date = lesson.getDateAndTime().toString().substring(0, 16);
            out.println("<tr><td><a href=lesson_info?id=" + l_id + ">" + l_id + "</a></td>");
            out.println("<td>" + l_course + "</td>");
            out.println("<td>" + l_date + "</td></tr>");
        }
        out.println("</table>");
    %>
    <br>
    <br>
</body>
</html>