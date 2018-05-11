import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class UpdateCustomerOrders extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{       

        String userInfo = new String("UserInfo");  
        HttpSession session = request.getSession(true);
        Person person = new Person();
        if (null != session.getAttribute(userInfo)){                        
            person = (Person)session.getAttribute(userInfo);
        }
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        String orderIdString = request.getParameter("orderId");
        int orderId = Integer.parseInt(orderIdString);
        String filePath = "C:/apache-tomcat-7.0.34/webapps/csj1/Orders/Orders.txt";
        //ArrayList<Cart> cartList = SerializeDataStore.readCartDataStore(filePath);
		IMySqlDataStoreUtilities sdsu = new MySqlDataStoreUtilities();

        int removeProdId=0;
        String removeProdIds = request.getParameter("removeProduct");
        if(removeProdIds!=null){
            removeProdId = Integer.parseInt(removeProdIds);
            Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id" + removeProdId);
        }

        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 

        boolean orderFound = false;
        out.println("<table>");
        sdsu.updateOrder(orderId, removeProdId);  
		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
                    " Update: deleted item " + removeProdId + " from order# " +orderId);		
		
		ArrayList<Cart> cartList = sdsu.readAllOrders();

        Date dt = null;
        for(Cart c1: cartList) {
            if(c1.getOrderId() == orderId && 
                    ( c1.getUserId().equals(person.getUserId()) || person.getRole().equalsIgnoreCase("SalesMan"))){

                dt=c1.getDeliveryDate();
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime read from cart" + c1.getDeliveryDate());

                orderFound = true;

                out.println("<br><br><tr>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println(c1.getBrandName());
                out.println("</td>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println(c1.getModel());
                out.println("</td>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println("$" + c1.getPrice());
                out.println("</td>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println("<form action='UpdateCustomerOrders?removeProduct=" + 
                    c1.getProductId() + "&orderId=" +orderId+ " 'method='post' >");
                out.println("<button class=button type='submit'>Remove Item</button>");
                out.println("</form>");
                out.println("</td>");

                out.println("</tr>");          
            }  
        

        }
        out.println("</table>");
        if(dt!=null){
        out.println("<br><br><br><h2>Expected Delivery Date :" + dt + "</h2>");
    }
        out.println("<form method='post' action='CancelOrderServlet?OrderId=" + orderIdString +"'>");
        if(!orderFound){
            out.println("<h2>Sorry! This order does not exist<h2>");
        }
        else{
            out.println("<center><input class= button type='submit' value='Cancel Order'/></center>");
        }
        out.println("</form>");
        out.println("</fieldset>"); 
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }


}