import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class RebateProducts extends HttpServlet{	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		MySqlDataStoreUtilities mysql= new MySqlDataStoreUtilities();
		ArrayList<Product> ProductDisplayList = new ArrayList<Product>();
		ProductDisplayList = mysql.getRebates();
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
        out.println("<h2>Products with Rebate</h2>"); 
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
		
        out.println("<td><br>Product Name</td>");
        out.println("<td><br>Product Price </td>");   
		out.println("<td><br>Manuf. Rebate Price</td>");
		out.println("<td><br> Final Price</td>");
        out.println("</tr");
        out.println("</thead>");
        out.println("<tbody>");


        for(Product pdisplay1: ProductDisplayList){
        double price= pdisplay1.getPrice() - pdisplay1.getRebate();
        out.println("<tr>");
        out.println("<td>" + pdisplay1.getModel()+" </td>");
        out.println("<td>" + pdisplay1.getPrice() + " </td>");
		out.println("<td>" + pdisplay1.getRebate() + "</td>");
		out.println("<td>" +price + "</td>");
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