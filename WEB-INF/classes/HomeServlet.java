import java.util.Map;

import javax.servlet.*;

import javax.servlet.http.*;

import java.io.*;

import java.util.logging.*;

import java.util.*;


public class HomeServlet extends HttpServlet {
	ArrayList<Product> products= new ArrayList<>();
		SaxParserUtility sp = new SaxParserUtility("C:/apache-tomcat-7.0.34/webapps/csj1/xml/ProductCatalog.xml");

	public void init()
	{
		try{
		System.out.println("Sending request to SAXParser");	
		sp.loadDataStore();
		}
		catch(Exception E)
		{
			System.out.println("Exception");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
 {

HttpSession session = request.getSession(true);
		
String userInfo = new String("UserInfo");
		
Person person = null;
		
int cartItemCount=0;
		
String fname="";

		
if (null != session.getAttribute(userInfo)) 
{
			
person = (Person) session.getAttribute(userInfo);
		
fname = person.getFName();
		
}

	
Cart newCart = new Cart();
       
ArrayList<Cart> cartList = new ArrayList<Cart>();

   
String cartInSession = new String("Cart"); 
       
if(null!=session.getAttribute(cartInSession))
{
             
cartList = (ArrayList<Cart>)session.getAttribute(cartInSession);
      
  }

        
cartItemCount= cartList.size();

	
response.setContentType("text/html");

		
Utilities utility = new Utilities();
		
PrintWriter out = response.getWriter();
		
utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html", response);
 
if (person !=null)
{
out.println("<nav> ");	
out.println("<ul>");
out.println("<li><a href='CartServlet'>Cart(" + cartItemCount+ ")<span class='glyphicon glyphicon-shopping-cart'></span></a><li> ");

out.println("<li><a href='LogoutServlet'>Logout</a><li>"); 
  if (person != null && person.getRole().equalsIgnoreCase("StoreManager"))
 {
//out.println("<ul>"); 
out.println("<li><a>Welcome, " + fname + "&#9786</a> ");
out.println("<ul class='navh'>");	      	
out.println("<li><a href='AddProducts'>Add Products</a></li>");			
out.println("<li><a href='UpdateProduct'>Update Products</a></li>");
out.println("<li><a href='DeleteProducts'>Delete Products</a></li>");
out.println("<li class='dropdown'>");
out.println("<a class='dropbtn'>Inventory</a>");
out.println("<div class='dropdown-content'>");
out.println("<a href='Inventory'>Available Products</a>");
out.println("<a href='SalesProducts'>Products On Sale</a>");
out.println("<a href='RebateProducts'>Manufacturer Rebates</a>");
out.println("</div>");
out.println("</li>");

out.println("<li class='dropdown'>");
out.println("<a class='dropbtn'>Sales Report</a>");
out.println("<div class='dropdown-content'>");
out.println("<a href='SalesReport'>Products Sold</a>");
out.println("<a href='DailySales'>Daily Sales</a>");
out.println("</div>");
out.println("</li>");

out.println("</ul");
out.println("</li>");
//out.println("</ul>");
out.println("</ul>");
out.println("</nav>");		
}        
  if (person != null && person.getRole().equalsIgnoreCase("Customer"))
 {
out.println("<ul class='nav'>"); 
out.println("<li><a>Welcome, " + fname + "&#9786</a> ");   
 }
		
if (person != null && person.getRole().equalsIgnoreCase("SalesMan")) 
{
	
out.println("<ul class='navh'>");
out.println("<li><a style='padding:21px 15px'>Welcome, " + fname + "&#9786</a> ");	
out.println("<ul>");		
out.println("<li><a href='UserRegistrationServlet'>Create Customer Account</a></li>");
out.println("<li><a href='SalesmanOperations'>Add Customer Orders</a></li>");
out.println("<li><a href='Salesman_Up_CustOrder'>Update Customer Orders</a></li>");
out.println("<li><a href='Salesman_Up_CustOrder'>Delete Customer Orders</a></li>");
out.println("</ul");
out.println("</li>");
out.println("</ul>");
out.println("</ul>");
out.println("</nav>");		
}
out.println("</ul>");
 
out.println("</nav>");

}
utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Content.html",response);
utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html", response);	
/*if (person != null && person.getRole().equalsIgnoreCase("StoreManager"))
 {
			
out.println("<a href='Inventory'>Inventory</a>");
			
out.println("<br/>");
			
out.println("<a href='SalesReport'>Sales Report</a>");
			
out.println("<br/>");		
}*/


		
	
utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html", response);
	
}

}
