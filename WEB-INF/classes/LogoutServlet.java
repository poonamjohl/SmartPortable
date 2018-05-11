import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LogoutServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(true);
        session.invalidate();
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();

        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<center>");
		out.println("<br><br><br>");
        out.println("<h4>Logout Successful!</h4>");
       
        out.println("</form>");
        out.println("</center>");
        
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}
