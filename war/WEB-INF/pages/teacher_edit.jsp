<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, java.util.*, util.*, dao.*" %>
<%@ page import="entity.Company" %>
<html>
<head>
    <title>Изменить (добавить) преподавателя</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form class="edit" method="get" name="edit_teacher" action="/learning_center/confirm_teacher">
        <input type="hidden" name="id" value="${id}">
        <p><label>Имя:
            <input required type="text" name="name" maxlength="100" value="${teacher.name}">
        </label></p>
        <%
            Collection<Company> list = (Collection<Company>) request.getAttribute("list_of_companies");
            if (list.size() != 0) {
                out.println("Компания:");
                out.println("<select name=\"company_id\">");
                long company_id = -1;
                if (!request.getAttribute("id").equals("-1")) {
                    Company company = (Company) request.getAttribute("company");
                    company_id = company.getId();
                    out.println("<option value=\"\">Выберите компанию</option>");
                } else {
                    out.println("<option selected value=\"\">Выберите компанию</option>");
                }
                for (Company tmp_company : list) {
                    if (company_id == tmp_company.getId()) {
                        out.println("<option selected value=\"" + tmp_company.getId().toString() + "\">");
                    } else {
                        out.println("<option value=\"" + tmp_company.getId().toString() + "\">");
                    }
                    out.println(tmp_company.getName() + "</option>");
                }
                out.println("</select>");
            }
        %>
        <br>
        <input class="edit" type="submit" name="confirm" value="Сохранить">
    </form>
    <%
        if (request.getAttribute("id").equals("-1")) {
            out.println("<form action=\"/learning_center/teachers\">");
            out.println("<button class=\"return\" name=\"\">Назад</button>");
            out.println("</form>");
        } else {
            out.println("<form action=\"/learning_center/teacher_info\">");
            out.println("<button class=\"return\" name=\"id\" value=\"" + request.getAttribute("id") + "\">Назад</button>");
            out.println("</form>");
        }
    %>
</body>
</html>

