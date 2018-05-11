import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DeleteProducts extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>");         
   
        out.println("<form method='post' action='SaveProducts?action=3'>");

        out.println("<p><label for='productId'>&emsp;&emsp;Enter Product ID:</label>");
        out.println("&emsp;&emsp;<input name='prodId' id='prodId' value='' type='text'/></p>");
        out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input class=button type='submit' value='Submit'/>");
        out.println("</form>");
        out.println("</fieldset>"); 
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        String productIdString = request.getParameter("prodId");
        int productId = Integer.parseInt(productIdString);
        out.println("<div id='body'>");
        out.println("<section id='content'>");          
        out.println("<p>You have successfully deleted the product.</p>");                     
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }


}