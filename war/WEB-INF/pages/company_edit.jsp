<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, java.util.*, util.*, dao.*" %>
<html>
<head>
    <title>Изменить (добавить) компанию</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form class="edit" method="get" name="company_edit" action="/learning_center/confirm_company">
        <input type="hidden" name="id" value="${id}">
        <p><label>Название компании:
            <input required type="text" name="company_name" maxlength="100" value="${company.name}">
        </label></p>
        <p><label>Адрес:
            <input required type="text" name="address" maxlength="150" value="${company.address}">
        </label></p>
        <input class="edit" type="submit" name="confirm" value="Сохранить">
    </form>
    <%
        if (request.getAttribute("id").equals("-1")) {
            out.println("<form action=\"/learning_center/companies\">");
            out.println("<button class=\"return\" name=\"\">Назад</button>");
            out.println("</form>");
        } else {
            out.println("<form action=\"/learning_center/company_info\">");
            out.println("<button class=\"return\" name=\"id\" value=\"" + request.getAttribute("id") + "\">Назад</button>");
            out.println("</form>");
        }
    %>
</body>
</html>

