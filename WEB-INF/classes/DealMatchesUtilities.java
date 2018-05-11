import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.*;
import java.util.*;

public class DealMatchesUtilities extends HttpServlet { 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	

		HttpSession session = request.getSession(true);
		String userInfo = new String("UserInfo");
		Person person = null;
		int cartItemCount=0;
		String fname="";

		if (null != session.getAttribute(userInfo)) {
			person = (Person) session.getAttribute(userInfo);
			fname = person.getFName();
		}

		Cart newCart = new Cart();
        ArrayList<Cart> cartList = new ArrayList<Cart>();

        String cartInSession = new String("Cart"); 
        if(null!=session.getAttribute(cartInSession)){
             cartList = (ArrayList<Cart>)session.getAttribute(cartInSession);
        }

        cartItemCount= cartList.size();

		response.setContentType("text/html");

		Utilities utility = new Utilities();
		PrintWriter out = response.getWriter();
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html", response);

		out.println("<nav id='welcome'>");
    	out.println("<ul>");        	
        out.println("<li><a>Welcome" + fname + "</a><li> ");
        out.println("<li><a href='CartServlet'>Cart(" + cartItemCount+ ")</a><li> ");
        out.println("</ul>");
    	out.println("</nav>");
    	out.println("<div id='body'>");
        out.println("<section id='content'>");

    	MySqlDataStoreUtilities dbUtil = new MySqlDataStoreUtilities();
    	HashMap<String,Product> productmap = new HashMap<String,Product>();
    	HashMap<String,Product> selectedproducts = new HashMap<String,Product>();
    	productmap=dbUtil.readProducts();	
		String line=null;    	
 
		out.print("<h2 class='title'>");
		out.println("<br>");
		out.print("<a href='#'>Welcome to SmartPortable </a></h2>");
		out.println("<p>Welcome to SmartPortable, Smarter Way to Buy Smart Products !</p>");	
	    out.println("<p>We bring you the best portable products at the best prices available in America. With the world class service, we guarantee customer satisfaction</p>");
		out.print("<div class='entry'>");
		out.print("<h2>Price-Match Guaranteed</h2>");		
		
		/* Iterate through Hashmap to find tweets */
		for(Map.Entry<String, Product> entry : productmap.entrySet()) {
			if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey())) {
				BufferedReader reader = new BufferedReader(new FileReader (new File("C:/apache-tomcat-7.0.34/webapps/csj1/DealMatch/DealMatches.txt")));
				line=reader.readLine(); 
				if(line==null || line.isEmpty())
				{ 
					out.print("<h2 align='center'>No Offers Found</h2>");
					break;
				} 
				else
				{ 
					do {
						if(line.toLowerCase().contains(entry.getKey().toLowerCase())) {
							out.print("<h3>"+line+"</h3>");
							out.print("<br>"); 
							selectedproducts.put(entry.getKey(),entry.getValue()); 
							break;
						}
					}while((line = reader.readLine()) != null);
				}
			}
		}

		/* To display matching products */
		out.println("<h2>Deal Matches</h2>");
		out.println("<table>");
		if(selectedproducts.size() > 0){
			for(HashMap.Entry<String, Product> myKey: selectedproducts.entrySet()){

				String key = myKey.getKey();
	            Product product = myKey.getValue();

	            // Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "selected products: " + key);	

				out.println("<td>");
	            out.println("<p><a href='ProductDisplayServlet'>");
	            out.println(product.getModel());
	            out.println("</a></p");
	            out.println("<p>");
	            out.println("Original Price: $"+product.getPrice());

	            // Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "product price: " + String.valueOf(product.getPrice()));

	            out.println("<br><label for='discount'>Discount: $"+ product.getDiscount()+"</label>");
	            double finalPrice = product.getPrice()-product.getDiscount();
	            out.println("<br><label for='afterDiscount'>Price after discount: $"+ finalPrice+"</label>");                  
	            out.println("</p>");
	            out.println("<img height=70 width=70 class='header-image' src='Images/" + product.getImageName() + ".jpg'");  
	            out.println("/>");
	            out.println("<p>"); 
				out.println("<br>");				
	            out.println("<br>");
				out.println("<a href='CartServlet?productId="+product.getProductId()+"'> <input class='button' type='button' value='Add to cart'/>");  
				out.println("<br>");
	            out.println("</a>");
	            out.println("</p>");
	            out.println("<p>");
	            out.println("<form action='WriteReviewsServlet' method='get'>");
	            out.println("<button class='button' type='submit'>Write Reviews</button>");
	         
	            out.println("<input type='hidden' name='model' value='" + product.getModel() + "'/>");
	            out.println("<input type='hidden' name='category' value='" + product.getType() + "'/>");
	            out.println("<input type='hidden' name='price' value='" + product.getPrice() + "'/>");
	            out.println("<input type='hidden' name='manufacturer' value='" + product.getBrandName() + "'/>");
	            out.println("<input type='hidden' name='rebate' value='" + product.getRebate() + "'/>");
	            out.println("</form>");                
	            out.println("</p>");
	            out.println("<p>");
	            out.println("<form action='ViewReviewsServlet' method='post'>");
	            out.println("<button class='button' type='submit'>View Reviews</button>");
	            out.println("<input type='hidden' name='model' value='" + product.getModel() + "'/>");
	            out.println("<input type='hidden' name='category' value='" + product.getType() + "'/>");                
	            out.println("<input type='hidden' name='manufacturer' value='" + product.getBrandName() + "'/>");                
	            out.println("</form>");        
	            out.println("</p>");
	            out.println("</td>");
	         }
	    }
	    else{
	    	out.println("<h2 align='center'>No Deals Found</h2>");
	    }
					
		if (person != null && person.getRole().equalsIgnoreCase("StoreManager")) {
			out.println("<a href='AddProducts'>Add Products</a>");
			out.println("<br/>");
			out.println("<a href='UpdateProduct'>Update Products</a>");
			out.println("<br/>");
			out.println("<a href='DeleteProducts'>Delete Products</a>");
			out.println("<br/>");
			out.println("<a href='DataAnalyticsServlet'>Data Analytics</a>");
		}
		if (person != null && person.getRole().equalsIgnoreCase("SalesMan")) {
			out.println("<a href='UserRegistrationServlet'>Create Customer Account</a><br/>");
			out.println("<a href='SalesmanOperations'>Add Customer Orders</a><br/>");
			out.println("<a href='Salesman_Up_CustOrder'>Update Customer Orders</a><br/>");
			out.println("<a href='Salesman_Up_CustOrder'>Delete Customer Orders</a>");
		}

		out.println("</table>");
		out.println("</section>");
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html", response);
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html", response);
	}
}

