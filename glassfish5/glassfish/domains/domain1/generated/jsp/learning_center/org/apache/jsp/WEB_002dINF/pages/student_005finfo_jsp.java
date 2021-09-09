package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Set;
import entity.Student;
import entity.Course;

public final class student_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/styles.css");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Страница учащегося</title>\r\n");
      out.write("    <style>");
      out.write("body {\r\n");
      out.write("    margin: 0 auto;\r\n");
      out.write("}\r\n");
      out.write("header {\r\n");
      out.write("    text-align: left;\r\n");
      out.write("    position: fixed;\r\n");
      out.write("    height: 100px;\r\n");
      out.write("    top: 0;\r\n");
      out.write("    width: 100%;\r\n");
      out.write("    background-color: #adc7c7;\r\n");
      out.write("    border-bottom: 1px solid black;\r\n");
      out.write("}\r\n");
      out.write("main {\r\n");
      out.write("    margin-top: 100px;\r\n");
      out.write("    margin-bottom: 20px;\r\n");
      out.write("    padding: 8px 8px 8px 8px;\r\n");
      out.write("}\r\n");
      out.write("table {\r\n");
      out.write("    width: 70%;\r\n");
      out.write("    margin: 0 15%;\r\n");
      out.write("    border: 1px solid black;\r\n");
      out.write("    font-size: 18px;\r\n");
      out.write("    align-items: center;\r\n");
      out.write("}\r\n");
      out.write("caption {\r\n");
      out.write("    border: 2px #191970;\r\n");
      out.write("    background-color: #708090;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    padding: 13px;\r\n");
      out.write("    font-size: 24px;\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("}\r\n");
      out.write("th {\r\n");
      out.write("    border: 1px solid black;\r\n");
      out.write("    background-color: #f1f1f1;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    padding: 10px;\r\n");
      out.write("}\r\n");
      out.write("td {\r\n");
      out.write("    border: 1px solid black;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write("tr {\r\n");
      out.write("    border: 1px solid black;\r\n");
      out.write("}\r\n");
      out.write("tr:hover {\r\n");
      out.write("    background-color: #f1f1f1;\r\n");
      out.write("}\r\n");
      out.write("h1.header {\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    background-color: #4169E1;\r\n");
      out.write("    padding: 10px 20px;\r\n");
      out.write("}\r\n");
      out.write("h3.header {\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write("button.go_home {\r\n");
      out.write("    font-size: 20px;\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    border: 2px solid black;\r\n");
      out.write("    border-radius: 3px;\r\n");
      out.write("    background-color: #6495ED;\r\n");
      out.write("    padding: 10px;\r\n");
      out.write("    margin: 15px 0 0 15px;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write("button.return {\r\n");
      out.write("    font-size: 16px;\r\n");
      out.write("    border: 1px solid black;\r\n");
      out.write("    border-radius: 3px;\r\n");
      out.write("    background-color: #B0C4DE;\r\n");
      out.write("    margin: 3px 15px;\r\n");
      out.write("    padding: 4px;\r\n");
      out.write("}\r\n");
      out.write("input.standard {\r\n");
      out.write("    width: 200px;\r\n");
      out.write("    font-size: 16px;\r\n");
      out.write("    border: 1px solid black;\r\n");
      out.write("    border-radius: 4px;\r\n");
      out.write("    margin: 3px 15px;\r\n");
      out.write("    padding: 4px 0;\r\n");
      out.write("}\r\n");
      out.write("input.edit {\r\n");
      out.write("    width: 170px;\r\n");
      out.write("    font-size: 16px;\r\n");
      out.write("    border: 1px solid black;\r\n");
      out.write("    border-radius: 4px;\r\n");
      out.write("    padding: 4px;\r\n");
      out.write("    margin: 4px 0;\r\n");
      out.write("}\r\n");
      out.write("label.standard {\r\n");
      out.write("    font-size: 16px;\r\n");
      out.write("    margin: 3px 0;\r\n");
      out.write("    padding: 3px;\r\n");
      out.write("}\r\n");
      out.write("form.edit {\r\n");
      out.write("    margin: 15px 15px;\r\n");
      out.write("}\r\n");
      out.write("select.edit {\r\n");
      out.write("    font-size: 15px;\r\n");
      out.write("    margin: 3px 15px;\r\n");
      out.write("    padding: 3px;\r\n");
      out.write("}");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <form action=\"/learning_center\">\r\n");
      out.write("        <button class=\"go_home\">Домашняя страница</button>\r\n");
      out.write("    </form>\r\n");
      out.write("    <form action=\"/learning_center/students\">\r\n");
      out.write("        <button class=\"return\">Назад к списку учащихся</button>\r\n");
      out.write("    </form>\r\n");
      out.write("    <form method=\"get\" name=\"student_history\" action=\"/learning_center/student_history\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("        <input class=\"standard\" type=\"submit\" value=\"История обучения\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <form method=\"get\" name=\"student_schedule\" action=\"/learning_center/student_schedule\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("        <input class=\"standard\" type=\"submit\" value=\"Расписание\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <form method=\"get\" name=\"edit_student\" action=\"/learning_center/student_edit\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("        <input class=\"standard\" type=\"submit\" value=\"Изменить\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <form method=\"get\" name=\"delete_student\" action=\"/learning_center/delete_student\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("        <input class=\"standard\" type=\"submit\" value=\"Удалить\">\r\n");
      out.write("    </form>\r\n");
      out.write("\r\n");
      out.write("    ");

        Student student = (Student) request.getAttribute("student");
        String id = student.getId().toString();
        String name =  student.getName();

        out.println("<table>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Имя</td><td>" + name + "</td></tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");

        out.println("<table>");
        out.println("<caption>Список проходимых курсов</caption>");
        Set<Course> courses = student.getCourses();
        for (Course course : courses) {
            String c_id = course.getId().toString();
            String c_name = course.getName();
            if (!course.isFinished()) {
                out.println("<tr><td>" + c_id + "</td>");
                out.println("<td><a href=course_info?id=" + c_id + '>' + c_name + "</a></td>" + "</tr>");
            }
        }
        out.println("</table>\n");
        out.println("<br>");
        out.println("<br>");
    
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
