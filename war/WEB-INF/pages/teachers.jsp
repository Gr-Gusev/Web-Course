<%@ page import="dao.Impl.TeacherDAOImpl" %>
<%@ page import="dao.TeacherDAO" %>
<%@ page import="entity.Teacher" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список преподавателей</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form method="get" name="add" action="/learning_center/add_teacher">
        <input class="standard" type="submit" name="add" value="Добавить преподавателя"/>
    </form>
    <table>
        <caption>Список преподавателей</caption>
        <tr><th>Id</th><th>Имя</th><th>Название компании</th></tr>
        <%
            TeacherDAO teacherDAO = new TeacherDAOImpl();
            Collection<Teacher> teachers = teacherDAO.getAllTeachers();
            for (Teacher teacher : teachers) {
                String id = teacher.getId().toString();
                String name = teacher.getName();
                String company_name = "-";
                if (teacher.getCompany() != null)
                    company_name = teacher.getCompany().getName();
                out.println("<tr><td>" + id + "</td>");
                out.println("<td><a href=teacher_info?id=" + id + '>' + name + "</a></td>");
                out.println("<td>" + company_name + "</td></tr>");
            }
        %>
    </table>
    <br>
    <br>
</body>
</html>