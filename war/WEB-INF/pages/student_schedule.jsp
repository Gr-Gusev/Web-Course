<%@ page import="entity.Student" %>
<%@ page import="entity.Lesson" %>
<%@ page import="dao.Impl.LessonDAOImpl" %>
<%@ page import="dao.LessonDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Расписание для учащегося</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/student_info">
        <button class="return" name="id" value="${id}">Назад к странице учащегося</button>
    </form>
    <%
        Student student = (Student) request.getAttribute("student");

        String id = student.getId().toString();
        String name =  student.getName();

        out.println("<table>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Имя</td><td>" + name + "</td></tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");

        LessonDAO lessonDAO = new LessonDAOImpl();
        List<Lesson> lessons = lessonDAO.getScheduleForStudent(student.getId());
        out.println("<table>");
        out.println("<caption>Расписание</caption>");
        out.println("<tr><th>Id</th><th>Название курса</th><th>Имя преподавателя</th><th>Дата и время</th></tr>\n");
        for (Lesson lesson : lessons) {
            String l_id = lesson.getId().toString();
            String l_course = lesson.getCourse().getName();
            String l_course_id = lesson.getCourse().getId().toString();
            String l_teacher = "-";
            if (lesson.getTeacher() != null)
                l_teacher = lesson.getTeacher().getName();
            String l_date = lesson.getDateAndTime().toString().substring(0, 16);
            out.println("<tr><td><a href=lesson_info?id=" + l_id + ">" + l_id + "</a></td>");
            out.println("<td><a href=course_info?id=" + l_course_id + '>' + l_course + "</td>");
            out.println("<td>" + l_teacher + "</td>");
            out.println("<td>" + l_date + "</td></tr>");
        }
        out.println("</table>\n");
        out.println("<br>");
        out.println("<br>");
    %>
    <br>
    <br>
</body>
</html>