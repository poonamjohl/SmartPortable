import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CancelOrderServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
                
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        String orderIdString = request.getParameter("OrderId");
        int orderId = Integer.parseInt(orderIdString);
 
        // SerializeDataStore.updateCartDataStore(orderId);
		
		IMySqlDataStoreUtilities sdsu = new MySqlDataStoreUtilities();
        int deleteSuccess = sdsu.deleteOrder(orderId);  

        out.println("<div id='body'>");
        out.println("<section id='content'>");
		if(deleteSuccess == 1){
			out.println("<br>");
			out.println("<br>");
            out.println("<p><center><p><h3>Your order has been cancelled.<h3></p></center>");    
        }
        else{
			out.println("<br>");
			out.println("<br>");
            out.println("<center><p><h3>MySql server is not up and running.<h3></p></center>");    
        }
				                
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }


}