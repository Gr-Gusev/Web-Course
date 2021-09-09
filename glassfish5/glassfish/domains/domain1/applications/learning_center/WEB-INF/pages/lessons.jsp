<%@ page import="java.util.Collection" %>
<%@ page import="entity.Lesson" %>
<%@ page import="dao.LessonDAO" %>
<%@ page import="dao.Impl.LessonDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список занятий</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <table>
    <caption>Список занятий</caption>
    <%
        LessonDAO lessonDAO = new LessonDAOImpl();
        Collection<Lesson> lessons = lessonDAO.getAllLessons();
        out.println("<tr><th>Id</th><th>Название курса</th><th>Дата и время проведения</th></tr>");
        for (Lesson lesson : lessons) {
            String id = lesson.getId().toString();
            String course_name = lesson.getCourse().getName();
            String date = lesson.getDateAndTime().toString().substring(0, 16);
            out.println("<tr><td><a href=lesson_info?id=" + id + '>' + id + "</a></td>");
            out.println("<td>" + course_name + "</td><td>" + date + "</td></tr>");
        }
    %>
    </table>
    <br>
    <br>
</body>
</html>