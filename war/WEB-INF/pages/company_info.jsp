<%@ page import="entity.Course, entity.Student" %>
<%@ page import="java.util.Set" %>
<%@ page import="entity.Company" %>
<%@ page import="entity.Teacher" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница компании</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form action="/learning_center/companies">
        <button class="return">Назад к списку компаний</button>
    </form>
    <form method="get" name="edit_company" action="/learning_center/company_edit">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Изменить">
    </form>
    <form method="get" name="delete_company" action="/learning_center/delete_company">
        <input type="hidden" name="id" value="${id}">
        <input class="standard" type="submit" value="Удалить">
    </form>

    <%
        Company company = (Company) request.getAttribute("company");

        String id = company.getId().toString();
        String name =  company.getName();
        String address = company.getAddress();

        out.println("<table>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Название</td><td>" + name + "</td></tr>");
        out.println("<tr><td>Адрес</td><td>" + address + "</td></tr>");
        out.println("</table>");

        out.println("<br>");
        out.println("<br>");
        Collection<Course> courses = (Collection<Course>) request.getAttribute("list_of_courses");
        out.println("<table>");
        out.println("<caption>Список проводимых курсов:</caption>");
        out.println("<tr><th>Id</th><th>Название курса</th><th>Статус курса</th></tr>");
        for (Course course : courses) {
            String c_id = course.getId().toString();
            String c_name = course.getName();
            out.println("<tr><td>" + c_id + "</td>");
            out.println("<td><a href=course_info?id=" + c_id + '>' + c_name + "</td>");
            if (course.isFinished())
                out.println("<td>Завершён</td></tr>");
            else
                out.println("<td>Открыт</td></tr>");
        }
        out.println("</table>");

        out.println("<br>");
        out.println("<br>");
        Collection<Teacher> teachers = (Collection<Teacher>) request.getAttribute("list_of_teachers");
        out.println("<table>");
        out.println("<caption>Список преподавателей:</caption>");
        out.println("<tr><th>Id</th><th>Имя преподавателя</th></tr>");
        for (Teacher teacher : teachers) {
            String t_id = teacher.getId().toString();
            String t_name = teacher.getName();
            out.println("<tr><td>" + t_id + "</td>");
            out.println("<td><a href=teacher_info?id=" + t_id + '>' + t_name + "</a></td></tr>");
        }
        out.println("</table>");
    %>
    <br>
    <br>
</body>
</html>