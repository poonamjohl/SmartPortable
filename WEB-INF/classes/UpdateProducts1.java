import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UpdateProducts1 extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>");         
   
        out.println("<form method='post' action='UpdateProducts2'>");
        out.println("<p><label for='productId'>Enter Product ID:</label>");
        out.println("<input name='prodId' id='prodId' value='' type='text'/></p>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");

        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}