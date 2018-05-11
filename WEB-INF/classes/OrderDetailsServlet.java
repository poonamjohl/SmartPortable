import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class OrderDetailsServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        	
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter(); 
        int productId = 0;
        HttpSession session = request.getSession(true);
		
        try{
             
        String sessionProdId =new String("sessionProdId");
        productId = (Integer)session.getAttribute(sessionProdId);
        session.setAttribute(sessionProdId,productId);
        }    
        catch(Exception e){
            out.println(e.toString());
        }
        
       // Cart newCart = new Cart();
        ArrayList<Cart> cartList = new ArrayList<Cart>();

        String cartInSession = new String("Cart"); 
        if(null!=session.getAttribute(cartInSession)){
             cartList = (ArrayList<Cart>)session.getAttribute(cartInSession);
        }
        
        
        String userName = request.getParameter("userid");
        String password = request.getParameter("password");
        Person p1 = new Person();
        Person p2 = p1.getDefaultCustomerLoginCredentials();       
			
            out.println("<div id='body'>");
            out.println("<section id='content'>");
            out.println("<form method='post' action='CheckOutServlet'>");
            out.println("<p><h2>Check your order and enter below details:</h2></p>");        
            out.println("<table>");
			double total=0.0;
            for (Cart c1: cartList){
					System.out.println(request.getParameter("quantityval-"+c1.getProductId()));
					int quantity= Integer.parseInt(request.getParameter("quantityval-"+c1.getProductId()));
					System.out.println(quantity);
					c1.setQuantity(quantity);
					c1.setPrice(quantity * c1.getPrice());
					total = total +( quantity * c1.getPrice());
					System.out.println(" cart quantity"+c1.getQuantity());
                    out.println("<tr>");
                    
                        out.println("<td width='350' style='border: 1px solid black ;'>");
                        out.println(c1.getBrandName());
                        out.println("</td>");

                        out.println("<td width='350' style='border: 1px solid black ;'>");
                        out.println(c1.getModel());
                        out.println("</td>");

                        out.println("<td width='350' style='border: 1px solid black ;'>");
                        out.println("$" + c1.getPrice() * quantity);
                        out.println("</td>");
                    
                    out.println("</tr>"); 
	
			}
			out.println("<tr>");
						out.println("<td class='hide_left'>");
						out.println("<td width='350' bgcolor='#CEECF5' style='border: 1px solid black ;'> Total</td>");
						out.println("<td width='350'  bgcolor='#CEECF5' style='border: 1px solid black ;'>");
						out.println("$" +total);
						out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
            out.println("<p>&emsp;&emsp;&emsp;<label for='name'>Name:</label>");
            out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input name='name' id='name' value='' type='text'/></p>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<p>&emsp;&emsp;&emsp;<label for='address'>Address:</label>");
            out.println("&emsp;&emsp;&emsp;&emsp;&emsp;<input name='address' id='address' value='' type='text'/></p>");
            out.println("</tr>");
			out.println("<tr>");
			out.println("<p>&emsp;&emsp;&emsp;<label for='street:'>Street:</label>");
            out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp&nbsp&nbsp&nbsp&nbsp<input name='street' id='street' value='' type='text'/></p>"); 
			out.println("</tr>");
			out.println("<tr>");
            out.println("<p>&emsp;&emsp;&emsp;<label for='city:'>City:</label>");
            out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input name='city' id='city' value='' type='text'/></p>"); 
			out.println("</tr>");
			out.println("<tr>");
            out.println("<p>&emsp;&emsp;&emsp;&nbsp&nbsp&nbsp<label for='state:'>State:</label>");
            out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input name='state' id='state' value='' type='text'/></p>"); 
			out.println("</tr>");
			out.println("<tr>");
            out.println("<p>&emsp;&emsp;&emsp;<label for='zip:'>Zip:</label>");
            out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp&nbsp<input name='zip' id='zip' value='' type='text'/></p>"); 
			out.println("</tr>");
            out.println("<tr>");
            out.println("<p>&emsp;&emsp;<label for='ccNumber'>Credit Card Number:</label>");
            out.println("&emsp;&nbsp<input name='ccNumber' id='ccNumber' value='' type='text'/></p>");
            out.println("</tr>");


            out.println("</table>");
			if(total == 0.0){
			out.println("<center><h3><font color='red'>Cart is Empty</font></h2></center>");
			out.println("<br><center><a href='HomeServlet'><input class=button type='back' value='Back'/></a></center>");
			
			}
			else
            out.println("<br><center><input class=button type='submit' value='Submit'/></center>");
            
            out.println("</form>");        
            out.println("</section>");
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);

       
		

    }
}
