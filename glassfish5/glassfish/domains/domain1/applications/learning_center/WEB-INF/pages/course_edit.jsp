<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, java.util.*, util.*, dao.*" %>
<%@ page import="entity.Course" %>
<%@ page import="dao.Impl.CompanyDAOImpl" %>
<%@ page import="entity.Company" %>
<html>
<head>
    <title>Изменить (добавить) курс</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form class="edit" method="get" name="course_edit" action="/learning_center/confirm_course">
        <input type="hidden" name="id" value="${id}">
        <p><label class="standard">Название курса:
            <input required type="text" name="course_name" maxlength="100" value="${course.name}">
        </label></p>
        <%
            String id = (String) request.getAttribute("id");
            if (id.equals("-1")) {
                Collection<Company> list = (Collection<Company>) request.getAttribute("list_of_companies");
                if (list.size() != 0) {
                    out.println("<select name=\"company_id\" required>");
                    long company_id = -1;
                    if (!request.getAttribute("id").equals("-1")) {
                        Company company = (Company) request.getAttribute("company");
                        company_id = company.getId();
                    } else {
                        out.println("<option selected disabled>Выберите компанию</option>");
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
            } else {
                Company company = (Company) request.getAttribute("company");
                out.println("Компания: " + company.getName() + "</a></td></tr>");
                out.print("<input type=\"hidden\" name=\"company_id\" value=");
                out.println(company.getId().toString() + '>');
            }
        %>
        <p><label class="standard">Дата начала:
            <input required type="date" name="day_of_start" value="${course.dayOfStart}">
        </label></p>
        <p><label class="standard">Дата окончания:
            <input required type="date" name="day_of_finish" value="${course.dayOfFinish}">
        </label></p>
        <p><label class="standard">Количество часов в день:
            <input required type="number" step="1" min="1" placeholder="1" name="hours_per_day"
                   value="${course.hoursPerDay}">
        </label></p>
        <input class="edit" type="submit" name="confirm" value="Сохранить">
    </form>
    <%
        if (id.equals("-1")) {
            out.println("<form action=\"/learning_center/courses\">");
            out.println("<button class=\"return\" name=\"\">Назад</button>");
            out.println("</form>");
        } else {
            out.println("<form action=\"/learning_center/course_info\">");
            out.println("<button class=\"return\" name=\"id\" value=\"" + id + "\">Назад</button>");
            out.println("</form>");
        }
    %>
</body>
</html>

