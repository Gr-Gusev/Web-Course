<%@ page import="entity.Student" %>
<%@ page import="entity.Course" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="dao.Impl.StudentDAOImpl" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История обучения</title>
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

        StudentDAO studentDAO = new StudentDAOImpl();
        Collection<Course> courses = studentDAO.getPassedCoursesForStudent(student.getId());
        out.println("<table>");
        out.println("<caption>Список пройденных курсов</caption>");
        out.println("<tr><th>Id</th><th>Название курса</th><th>Дата начала</th><th>Дата окончания</th></tr>");
        for (Course course : courses) {
            String c_name = course.getName();
            String c_id = course.getId().toString();
            String c_start = course.getDayOfStart().toString();
            String c_finish = course.getDayOfFinish().toString();
            if (!course.isActive()) {
                out.println("<tr><td>" + c_id + "</td>" + "<td><a href=course_info?id=" + c_id + ">" + c_name + "</a></td>");
                out.println("<td>" + c_start + "</td>" + "<td>" + c_finish + "</td>" + "</tr>");
            }
        }
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");
    %>
    <br>
    <br>
</body>
</html>