package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import entity.Course;
import entity.Student;
import java.util.Set;
import dao.Impl.StudentDAOImpl;
import dao.StudentDAO;
import java.util.Collection;
import entity.Teacher;
import entity.Lesson;
import java.util.HashSet;

public final class course_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Страница курса</title>\r\n");
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
      out.write("    <form action=\"/learning_center/courses\">\r\n");
      out.write("        <button class=\"return\">Назад к списку курсов</button>\r\n");
      out.write("    </form>\r\n");
      out.write("    <form method=\"get\" name=\"course_schedule\" action=\"/learning_center/course_schedule\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("        <input class=\"standard\" type=\"submit\" value=\"Расписание занятий\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <form method=\"get\" name=\"edit_course\" action=\"/learning_center/course_edit\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("        <input class=\"standard\" type=\"submit\" value=\"Изменить\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <form method=\"get\" name=\"delete_course\" action=\"/learning_center/delete_course\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("        <input class=\"standard\" type=\"submit\" value=\"Удалить\">\r\n");
      out.write("    </form>\r\n");
      out.write("\r\n");
      out.write("    ");

        StudentDAO studentDAO = new StudentDAOImpl();
        Collection<Student> allStudents = studentDAO.getAllStudents();
        Course course = (Course) request.getAttribute("course");
        String id = course.getId().toString();
        Set<Student> students = course.getStudents();

        out.println("<form method=\"get\" name=\"add_student_to_course\" " +
                "action=\"/learning_center/add_student_to_course\">");
        out.println("<input type=\"hidden\" name=\"course_id\" value=" + id + ">");
        out.println("<select class=\"edit\" required name=\"student_id\">");
        out.println("<option selected disabled value=\"\">Выберите учащегося</option>");
        for (Student tmp_student : allStudents) {
            if (!students.contains(tmp_student)) {
                out.println("<option value=\"" + tmp_student.getId().toString() + "\">");
                out.println(tmp_student.getName() + "</option>");
            }
        }
        out.println("</select>");
        out.println("<input class=\"standard\" type=\"submit\" value=\"Добавить учащегося\">");
        out.println("</form>");

        String name = course.getName();
        String company_name = course.getCompany().getName();
        String day_of_start = course.getDayOfStart().toString();
        String day_of_finish = course.getDayOfFinish().toString();
        String hours_per_day = course.getHoursPerDay().toString();

        out.println("<table>");
        out.println("<tr><td>Статус</td>");
        if (course.isFinished())
            out.println("<td>Завершён</td></tr>");
        else
            out.println("<td>Открыт</td></tr>");
        out.println("<tr><td>Id</td><td>" + id + "</td></tr>");
        out.println("<tr><td>Название</td><td>" + name + "</td></tr>");
        out.println("<tr><td>Компания</td><td><a href=company_info?id=" +
                        course.getCompany().getId().toString() + '>' + company_name + "</a></td></tr>");
        out.println("<tr><td>Дата начала</td><td>" + day_of_start + "</td></tr>");
        out.println("<tr><td>Дата окончания</td><td>" + day_of_finish + "</td></tr>");
        out.println("<tr><td>Нагрузка (часов в день)</td><td>" + hours_per_day + "</td></tr>");
    
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("    <caption>Список преподавателей</caption>\r\n");
      out.write("    <tr><th>Id</th><th>Имя преподавателя</th></tr>\r\n");
      out.write("    ");

        Set<Lesson> lessons = course.getLessons();
        Set<Teacher> teachers = new HashSet<Teacher>();
        for (Lesson tmp_lesson : lessons)
            if (tmp_lesson.getTeacher() != null)
                teachers.add(tmp_lesson.getTeacher());
        for (Teacher tmp_teacher : teachers) {
            String t_id = tmp_teacher.getId().toString();
            String t_name = tmp_teacher.getName();
            out.println("<tr>");
            out.println("<td>" + t_id + "</td>");
            out.println("<td><a href=teacher_info?id=" + t_id + '>' + t_name + "</a></td>");
            out.println("</tr>");
        }
    
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("    <caption>Список учащихся</caption>\r\n");
      out.write("    <tr><th>Id</th><th>Имя учащегося</th><th></th></tr>\r\n");
      out.write("    ");

        for (Student student : students) {
            String st_id = student.getId().toString();
            String st_name = student.getName();
            out.println("<tr>");
            out.println("<td>" + st_id + "</td>");
            out.println("<td><a href=student_info?id=" + st_id + '>' + st_name + "</td>");
            out.println("<td valign=\"center\">");
            out.println("<form method=\"get\" name=\"delete_student_from_course\" " +
                    "action=\"/learning_center/delete_student_from_course\">");
            out.println("<input type=\"hidden\" name=\"course_id\" value=" + id + ">");
            out.println("<input type=\"hidden\" name=\"student_id\" value=" + st_id + ">");
            out.println("<input type=\"submit\" value=\"Удалить\">");
            out.println("</form>");
            out.println("</td></tr>");
        }
    
      out.write("\r\n");
      out.write("    </table>\r\n");
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
