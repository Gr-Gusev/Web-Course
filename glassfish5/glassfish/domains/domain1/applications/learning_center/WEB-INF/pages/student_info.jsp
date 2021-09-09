<%@ page import="java.util.Set" %>
<%@ page import="entity.Student" %>
<%@ page import="entity.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница учащегося</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/students">
        <button class="return">Назад к списку учащихся</button>
    </form>
    <form method="get" name="student_history" action="/learning_center/student_history">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="История обучения">
    </form>
    <form method="get" name="student_schedule" action="/learning_center/student_schedule">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Расписание">
    </form>
    <form method="get" name="edit_student" action="/learning_center/student_edit">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Изменить">
    </form>
    <form method="get" name="delete_student" action="/learning_center/delete_student">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Удалить">
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

        out.println("<table>");
        out.println("<caption>Список проходимых курсов</caption>");
        Set<Course> courses = student.getCourses();
        for (Course course : courses) {
            String c_id = course.getId().toString();
            String c_name = course.getName();
            if (!course.isFinished()) {
                out.println("<tr><td>" + c_id + "</td>");
                out.println("<td><a href=course_info?id=" + c_id + '>' + c_name + "</a></td>" + "</tr>");
            }
        }
        out.println("</table>\n");
        out.println("<br>");
        out.println("<br>");
    %>
    <br>
    <br>
</body>
</html>