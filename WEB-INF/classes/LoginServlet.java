import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LoginServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(true);
        String sessionProdId =new String("sessionProdId");
        if(null != session.getAttribute(sessionProdId)){
            int prodId = (Integer)session.getAttribute(sessionProdId);
            session.setAttribute(sessionProdId,prodId);
        }


        String userInfo = new String("UserInfo");        

        if(null!=session.getAttribute(userInfo)){
            response.sendRedirect("HomeServlet");
        }

         response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
		out.println("<br>");
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<center>");
        out.println("<form method='post' action='VerifyLogin'>");
        out.println("<h2><font color='#4076AB'> Sign In:</h2></font>");
		out.println("<br>");
		out.println("<br>");
        out.println("<table cellpadding='3' cellspacing='2'>");
        out.println("<tr>");
        out.println("<td>User Name</td>");
        out.println("<td><input type='TEXT' size='15' name='userid'></input></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Password</td>");
        out.println("<td><input type='PASSWORD' size='15' name='password'/></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Role</td>");
        out.println("<td><select id='role' name='role'>");
        out.println("<option value='customer'>Customer</option>"); 
        out.println("<option value='storeManager'>Store Manager</option>"); 
        out.println("<option value='salesman'>Sales Man</option>");   
        out.println("</select></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td colspan='2'>");
        out.println("<center><input class=button type='submit' value='Login'/></center>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("</center>");
		out.println("<br>");
        out.println("<br>");  
		out.println("<br>");
		out.println("<br>");
        out.println("<center><a href='UserRegistrationServlet'>Join SmartPortable Today!.</a></center>");
        
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}
