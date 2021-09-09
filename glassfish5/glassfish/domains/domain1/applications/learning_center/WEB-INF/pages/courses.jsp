<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, java.util.*, util.*, dao.*" %>
<%@ page import="dao.Impl.CourseDAOImpl, entity.Course" %>
<html>
<head>
    <title>Список курсов</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form method="get" name="add" action="/learning_center/add_course">
        <input class="standard" type="submit" name="add" value="Добавить курс"/>
    </form>
    <table>
        <caption>Список открытых курсов</caption>
        <tr><th>Id</th><th>Название курса</th><th>Название компании</th></tr>
        <%
            CourseDAO courseDAO = new CourseDAOImpl();
            Collection<Course> courses = courseDAO.getAllCourses();
            ArrayList<Course> active_courses = new ArrayList<Course>();
            ArrayList<Course> finished_courses = new ArrayList<Course>();
            for (Course course : courses) {
                if (course.isFinished())
                    finished_courses.add(course);
                else
                    active_courses.add(course);
            }
            for (Course course : active_courses) {
                String id = course.getId().toString();
                String name = course.getName();
                String company_name = course.getCompany().getName();
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td><a href=course_info?id=" + id + '>' + name + "</a></td>");
                out.println("<td>" + company_name + "</td>");
                out.println("</tr>");
            }
        %>
    </table>
    <br>
    <br>
    <table>
        <caption>Список завершённых курсов</caption>
        <tr><th>Id</th><th>Название курса</th><th>Название компании</th></tr>
        <%
            for (Course course : finished_courses) {
                String id = course.getId().toString();
                String name = course.getName();
                String company_name = course.getCompany().getName();
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td><a href=course_info?id=" + id + '>' + name + "</a></td>");
                out.println("<td>" + company_name + "</td>");
                out.println("</tr>");
            }
        %>
    </table>
    <br>
    <br>
</body>
</html>
