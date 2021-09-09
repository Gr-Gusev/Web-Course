<%@ page import="java.util.Set" %>
<%@ page import="entity.Course" %>
<%@ page import="entity.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Преподаватель</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>

<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/teachers">
        <button class="return">Назад к списку преподавателей</button>
    </form>
    <form method="get" name="teacher_schedule" action="/learning_center/teacher_schedule">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Расписание">
    </form>
    <form method="get" name="edit_teacher" action="/learning_center/teacher_edit">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Изменить">
    </form>
    <form method="get" name="delete_teacher" action="/learning_center/delete_teacher">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Удалить">
    </form>
    <%
        Teacher teacher = (Teacher) request.getAttribute("teacher");

        String id = teacher.getId().toString();
        String name =  teacher.getName();
        String company_id = "";
        String company_name = "-";
        if (teacher.getCompany() != null) {
            company_id = teacher.getCompany().getId().toString();
            company_name = teacher.getCompany().getName();
        }

        out.println("<table>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Имя</td><td>" + name + "</td></tr>");
        if (teacher.getCompany() != null) {
            out.println("<tr><td>Компания</td><td><a href=company_info?id=" +
                    company_id + '>' + company_name + "</a></td></tr>");
        } else {
            out.println("<tr><td>Компания</td><td>-</td></tr>");
        }
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");

        Set<Course> courses = (Set<Course>) request.getAttribute("courses");
        if (!courses.isEmpty()) {
            out.println("<table>");
            out.println("<caption>Список читаемых курсов</caption>");
            out.println("<tr><th>Id</th><th>Название курса</th><th>Статус курса</th></tr>");
            for (Course course : courses) {
                String c_id = course.getId().toString();
                String c_name = course.getName();
                out.println("<tr><td>" + c_id + "</td>");
                out.println("<td><a href=course_info?id=" + c_id + '>' + c_name + "</a></td>");
                if (course.isActive())
                    out.println("<td>Открыт</td>");
                else
                    out.println("<td>Завершён</td>");
            }
            out.println("</table>");
        }
    %>
    <br>
    <br>
</body>
</html>