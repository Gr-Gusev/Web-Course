<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, java.util.*, util.*, dao.*" %>
<html>
<head>
    <title>Изменить (добавить) учащегося</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form class="edit" method="get" name="edit" action="/learning_center/confirm_student">
        <input type="hidden" name="id" value="${id}">
        <p><label class="standard">Имя:
            <input required type="text" name="name" maxlength="100" value="${name}"> <br>
        </label></p>
        <input class="edit" type="submit" name="confirm" value="Сохранить">
    </form>
    <%
        if (request.getAttribute("id").equals("-1")) {
            out.println("<form action=\"/learning_center/students\">");
            out.println("<button class=\"return\" name=\"\">Назад</button>");
            out.println("</form>");
        } else {
            out.println("<form action=\"/learning_center/student_info\">");
            out.println("<button class=\"return\" name=\"id\" value=\"" + request.getAttribute("id") + "\">Назад</button>");
            out.println("</form>");
        }
    %>
</body>
</html>

