<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, java.util.*, util.*, dao.*" %>
<%@ page import="dao.Impl.CompanyDAOImpl" %>
<%@ page import="entity.Company" %>
<html>
<head>
    <title>Список компаний</title>
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>
<body>
    <form action="/learning_center">
        <button class="go_home">Домашняя страница</button>
    </form>
    <form method="get" name="add_company" action="/learning_center/add_company">
        <input class="standard" type="submit" name="add" value="Добавить компанию"/>
    </form>
    <table>
        <caption>Список компаний</caption>
        <tr><th>Id</th><th>Название компании</th></tr>
        <%
            CompanyDAO companyDAO = new CompanyDAOImpl();
            Collection<Company> companies = companyDAO.getAllCompanies();
            for (Company company : companies) {
                String c_id = company.getId().toString();
                String c_name = company.getName();
                out.println("<tr><td>" + c_id + "</td>");
                out.println("<td><a href=company_info?id=" + c_id + '>' + c_name + "</a></td></tr>");
            }
        %>
    </table>
    <br>
    <br>
</body>
</html>
