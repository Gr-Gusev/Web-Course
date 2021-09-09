<%@ page import="entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Занятие</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/course_schedule">
        <button class="return" name="id" value="${course_id}">Назад к расписанию занятий для курса</button>
    </form>
    <form action="/learning_center/lessons">
        <button class="return">Назад к списку занятий</button>
    </form>
    <form method="get" name="edit" action="/learning_center/lesson_edit">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="course_id" value="${course_id}">
        <input class="standard" type="submit" value="Изменить">
    </form>
    <form method="get" name="delete" action="/learning_center/delete_lesson">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="course_id" value="${course_id}">
        <input class="standard" type="submit" value="Удалить">
    </form>
    <%
        Lesson lesson = (Lesson) request.getAttribute("lesson");
        String id = lesson.getId().toString();
        String course_name =  lesson.getCourse().getName();
        String teacher_id = "";
        String teacher_name = "-";
        if (lesson.getTeacher() != null) {
            teacher_id = lesson.getTeacher().getId().toString();
            teacher_name = lesson.getTeacher().getName();
        }
        String date = lesson.getDateAndTime().toString().substring(0, 16);

        out.println("<table>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Название курса</td><td>" + course_name + "</td></tr>");
        out.println("<tr><td>Имя преподавателя</td><td><a href=teacher_info?id=" + teacher_id + '>'
                + teacher_name + "</a></td></tr>");
        out.println("<tr><td>Дата и время проведения</td><td>" + date + "</td></tr>");
        out.println("</table>");
    %>
    <br>
    <br>
</body>
</html>