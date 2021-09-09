<%@ page import="dao.StudentDAO" %>
<%@ page import="dao.Impl.StudentDAOImpl" %>
<%@ page import="entity.Student" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список учащихся</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form method="get" name="add" action="/learning_center/add_student">
        <input class="standard" type="submit" name="add" value="Добавить учащегося"/>
    </form>
    <table>
        <caption>Список учащихся</caption>
        <tr><th>Id</th><th>Имя учащегося</th></tr>
    <%
        StudentDAO studentDAO = new StudentDAOImpl();
        Collection<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            String id = student.getId().toString();
            String name = student.getName();
            out.println("<tr><td>" + id + "</td><td><a href=student_info?id=" + id + '>' + name + "</a></td>");
        }
    %>
    </table>
    <br>
    <br>
</body>
</html>