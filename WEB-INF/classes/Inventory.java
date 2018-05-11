import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Inventory extends HttpServlet{	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		MySqlDataStoreUtilities mysql= new MySqlDataStoreUtilities();
		ArrayList<Product> ProductDisplayList = new ArrayList<Product>();
		ArrayList<Accessory> acc= new ArrayList<Accessory>();
		ProductDisplayList = mysql.getProduct();
		acc= mysql.getAccessory();
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
        out.println("<h2>Inventory of Products </h2>"); 
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
		
        out.println("<td><br>Product Name</td>");
        out.println("<td><br>Product Price </td>");   
		out.println("<td><br>Quantity Available</td>");
        out.println("</tr");
        out.println("</thead>");
        out.println("<tbody>");


        for(Product pdisplay1: ProductDisplayList){
        out.println("<tr>");
        out.println("<td>" + pdisplay1.getModel()+" </td>");
        out.println("<td>" + pdisplay1.getPrice() + " </td>");
		out.println("<td>" + pdisplay1.getQuantity() + "</td>");
        out.println("</tr>");
		}
		for (Accessory accs : acc) {	
		out.println("<tr>");
        out.println("<td>" + accs.getDescription()+" </td>");
        out.println("<td>" + accs.getPrice() + " </td>");
		out.println("<td>" + accs.getQuantity() + "</td>");
        out.println("</tr>");
    
	}   out.println("</tbody>");
        out.println("</table>");
		out.println("<br>");        
        out.println("</fieldset>"); 
        out.println("</section>");
		out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a href='Chart' ><input class='button' type='button' value='Chart'/>");
		
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        
    }


}