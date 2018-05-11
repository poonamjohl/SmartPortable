import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DailySales extends HttpServlet{	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		MySqlDataStoreUtilities mysql= new MySqlDataStoreUtilities();
		ArrayList<Order> ProductDisplayList = new ArrayList<Order>();
		ProductDisplayList = mysql.getDailySales();
		System.out.println("product display size" + ProductDisplayList.size());
        /**********************/
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 
		out.println("<br>");
        out.println("<h2>Sale Report </h2>"); 
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
		
        out.println("<td><br>Date Of Sale</td>");
		out.println("<td><br>Total Sales</td>");
        out.println("</tr");
        out.println("</thead>");
        out.println("<tbody>");


        for(Order pdisplay1: ProductDisplayList){
        out.println("<tr>");
        out.println("<td>" + pdisplay1.getDate() + " </td>");
		out.println("<td>" +pdisplay1.getPrice() + "</td>");
          out.println("</tr>");
    }
        
      
        out.println("</tbody>");
        out.println("</table>");
		out.println("<br>");
        
        
        out.println("</fieldset>"); 
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        
    }


}