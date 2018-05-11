import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class SalesProducts extends HttpServlet{	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		MySqlDataStoreUtilities mysql= new MySqlDataStoreUtilities();
		ArrayList<Accessory> access= new ArrayList<Accessory>();
		ArrayList<Product> ProductDisplayList = new ArrayList<Product>();
		ProductDisplayList = mysql.getSales();
		access=mysql.getAccSales();
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
        out.println("<h2>Sale Products </h2>"); 
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
		
        out.println("<td><br>Product Name</td>");
        out.println("<td><br>Product Price </td>");   
		out.println("<td><br>Discount Price</td>");
		out.println("<td><br>Sale Price</td>");
        out.println("</tr");
        out.println("</thead>");
        out.println("<tbody>");


        for(Product pdisplay1: ProductDisplayList){
        double price= pdisplay1.getPrice() - pdisplay1.getDiscount();
        out.println("<tr>");
        out.println("<td>" + pdisplay1.getModel()+" </td>");
        out.println("<td>" + pdisplay1.getPrice() + " </td>");
		out.println("<td>" + pdisplay1.getDiscount() + "</td>");
		out.println("<td>" +price + "</td>");
          out.println("</tr>");
		}
		for (Accessory acc :access) {	
		out.println("<tr>");
		double accprice = acc.getPrice() - acc.getDiscount();
        out.println("<td>" + acc.getDescription()+" </td>");
        out.println("<td>" + acc.getPrice() + " </td>");
		out.println("<td>" + acc.getDiscount() + "</td>");
		out.println("<td>" +accprice + "</td>");
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