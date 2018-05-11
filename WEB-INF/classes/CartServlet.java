
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class CartServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		HttpSession session = request.getSession(true);
		Boolean userLoggedIn = false;

		String prodIdString = request.getParameter("productId");
		String removeProdIds = request.getParameter("removeProduct");
		//String quantity= request.getParameter("quantity");
		String type= request.getParameter("type");
		Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id string" + removeProdIds);	
		String sessionProdId = new String("sessionProdId") ;		
		int prodId =0;
		int removeProdId=0;
		int index=0;
		//int quant=0;
		if(prodIdString != null){
			prodId = Integer.parseInt(prodIdString);
			session.setAttribute(sessionProdId, prodId);
			//quant= Integer.parseInt(quantity);
		}

		if(removeProdIds != null){
			removeProdId = Integer.parseInt(removeProdIds);
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id" + removeProdId);
		}

		Person person = new Person();

		String userInfo = new String("UserInfo");  
		if(null!=session.getAttribute(userInfo)){
			userLoggedIn = true;
			person = (Person)session.getAttribute(userInfo);
		}


		ProductDisplay pDisplay = new ProductDisplay();
		HashMap<String,Product> ProductDisplays = pDisplay.buildBasicProductDisplayList(); 

		response.setContentType("text/html");
		Utilities utility = new Utilities();
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
		PrintWriter out = response.getWriter();
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<br><br><br>");
		out.println("<center><h2>Cart</h2></center>");
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
				
		out.println("<td>Brand</td>");
		out.println("<td>Model</td>");
		out.println("<td>Price</td>");
		out.println("<td>Quantity</td>");
		out.println("<td>Buy Warranty</td>");
		out.println("<td>Remove Item</td>");		
		
		out.println("</tr>");
		out.println("</thead>");
		
		System.out.println("type" + type);
		
		ArrayList<Cart> myCart = new ArrayList<Cart>();
		
		for(HashMap.Entry<String, Product> myKey: ProductDisplays.entrySet()){
			String key = myKey.getKey();
			Product ph1 = myKey.getValue();
			
			if((ph1.getProductId() == prodId) ){//&& (type == ph1.getType())) {
				Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 14);
				dt = c.getTime();
				

				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime first insert into cart" + dt);
				Cart cart = new Cart(ph1.getBrandName(), ph1.getModel(),(ph1.getPrice()-ph1.getDiscount()), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,dt,prodId,ph1.getAccs());	

				myCart.add(cart); 
			
			}
			if((ph1.getProductId() == removeProdId)){// && (type == ph1.getType())){
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id at index" + index);
				Cart cart = new Cart(ph1.getBrandName(), ph1.getModel(),ph1.getPrice(), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,null,removeProdId,null);
				// out.println("<h1>"+cart+"</h1>");
				myCart.remove(index);
			}
			else
			{
				for (Accessory acc : ph1.getAccs()) {
				if ((acc.getProductId() == prodId) && (acc.getType() != type)){
				Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 14);
				dt = c.getTime();


				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime first insert into cart" + dt);
				Cart cart = new Cart(acc.getType(), acc.getDescription(), (acc.getPrice()-acc.getDiscount()), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,dt,prodId);	

				myCart.add(cart); 
			
				}
				if((acc.getProductId() == removeProdId) && (acc.getType() != type)){
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id at index" + index);
				Cart cart = new Cart(acc.getType(), acc.getDescription(), acc.getPrice(), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,null,removeProdId,null);
				// out.println("<h1>"+cart+"</h1>");
				myCart.remove(index);
			}
			}
			}
			index++;
	
	}

		String cartInSession = new String("Cart"); 
		if(null!=session.getAttribute(cartInSession)){
			ArrayList<Cart> priorCart = (ArrayList<Cart>)session.getAttribute(cartInSession);
			myCart.addAll(priorCart);
			Collections.reverse(myCart);
		}

		session.setAttribute(cartInSession, myCart);

		if(!userLoggedIn){
			out.println("<form action='LoginServlet'>");
		}
		else{
			out.println("<form action='OrderDetailsServlet' method='post'>");
		}
		for (Cart c1: myCart){


			out.println("<tr>");

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
			out.println("<input type='text' name='quantityval-"+c1.getProductId()+"' value='1'><br>");
			out.println("</td>");
			
			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("<input type='checkbox' name='buyWarranty' value='buyWarranty'><br>");
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			//out.println("<form action='CartServlet?removeProduct=" + c1.getProductId() + " 'method='post' >");
			out.println("<button formaction='CartServlet?removeProduct=" + c1.getProductId() + "' type='submit' class=button>Remove Item</button>");
			//out.println("</form>");
			out.println("</td>");
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "rc1.getProductId()" + c1.getProductId());
			out.println("</tr>");	    			

		}


		out.println("</table>");

		
		out.println("<br>");
		out.println("<center><button class=button type='submit'>Check Out</button></center>");
		out.println("</form>");
		out.println("</a>");
		
		if (userLoggedIn && myCart != null) {
			/* out.println("<br><br><br><br>");
			out.println("<h5>Suggestion</h5>");
			
			out.println("<br><br><br><br>"); */
			out.println("<div class='smp-crsl'>");
			out.println("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
			out.println("<ol class='carousel-indicators'>");
			out.println("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
			out.println("<li data-target='#myCarousel' data-slide-to='1'></li>");
			out.println("<li data-target='#myCarousel' data-slide-to='2'></li>");
			out.println("</ol>");
			out.println("<div class='carousel-inner'>");
			int ccount=0;
			
			List<Accessory> crslList = new ArrayList<Accessory>();
			for (Cart c1: myCart){ 
			if (myCart != null && !myCart.isEmpty()) {
				c1=myCart.get(myCart.size()-1);
				}		
				System.out.println("inside acc. build for carousel / product being - " + c1.getBrandName());
				if(c1.getAccs() != null)
				crslList.addAll(c1.getAccs());
				break;
			}		
			
			for ( Accessory acc : crslList) {
				String name = acc.getType();
				if(acc.getType().equals(name)) {						
					ccount++;
					System.out.println(ccount);
					String ctext="";
					if(ccount==1) {
						out.println("<div class='item active'>");
					} else {
						out.println("<div class='item'>");
					}
					
					out.println("<img height=200 width=300 src='Images/" + acc.getImageName() + ".jpg' >");
					out.println("<div class='carousel-caption d-none d-md-block' style='color:black;'>");
					out.println("<h3>"+acc.getDescription()+"</h3>");
					out.println("<p>$"+acc.getPrice()+"</p>");
					out.println("<form method = 'get' action = 'AddToCart'>");
					out.println("<br>");
					out.println("<br>");
					out.println("<a href='CartServlet?productId="+acc.getProductId()+"'> <input class='button' type='button' value=' Add to Cart '/>");
					out.println("<input type='hidden' name='productName' value='"+acc.getType()+"'>");
					out.println("<input type='hidden' name='image' value='"+acc.getImageName()+"'>");
					out.println("<input type='hidden' name='price' value='"+acc.getPrice()+"'>");
					out.println("<input type='hidden' name='quantity' value='"+1+"'>");
					out.println("</form>");
					out.println("</div>");
					out.println("</div>");
				}
			}
					
			
			out.println("</div>");
			
			out.println("<a class='carousel-control left' href='#myCarousel' data-slide='prev'>");
			out.println("<span class='glyphicon glyphicon-chevron-left'></span>");
			out.println("</a>");
			out.println("<a class='carousel-control right' href='#myCarousel' data-slide='next'>");
			out.println("<span class='glyphicon glyphicon-chevron-right'></span>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
			
		}
		out.println("</section>");
		
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		HttpSession session = request.getSession(true);
		Boolean userLoggedIn = false;

		String type = request.getParameter("type");
		//String quantity= request.getParameter("quantity");
		String prodIdString = request.getParameter("productId");
		String removeProdIds = request.getParameter("removeProduct");	
		Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id string" + removeProdIds);	
		String sessionProdId = new String("sessionProdId") ;		
		int prodId =0;
		int removeProdId=0;
		int index=0;
		//int quant=0;
		if(prodIdString!=null){
			prodId = Integer.parseInt(prodIdString);
			session.setAttribute(sessionProdId, prodId);
			//quant= Integer.parseInt(quantity);
		}

		if(removeProdIds!=null){
			removeProdId = Integer.parseInt(removeProdIds);
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id" + removeProdId);
		}

		Person person = new Person();

		String userInfo = new String("UserInfo");  
		if(null!=session.getAttribute(userInfo)){
			userLoggedIn = true;
			person = (Person)session.getAttribute(userInfo);
		}


		ProductDisplay pDisplay = new ProductDisplay();
		HashMap<String,Product> ProductDisplays = pDisplay.buildBasicProductDisplayList(); 

		response.setContentType("text/html");
		Utilities utility = new Utilities();
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
		PrintWriter out = response.getWriter();
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<br>");
		out.println("<br>");
		out.println("<center><h2>Cart</h2></center>");
		out.println("<table >");
		out.println("<thead>");
		
		out.println("<tr>");
		out.println("<td>Brand</td>");
		out.println("<td>Model</td>");
		out.println("<td>Price</td>");
		out.println("<td>Quantity</td>");
		out.println("<td>Buy Warranty</td>");
		out.println("<td>Remove Item</td>");
		out.println("</tr>");
		
		out.println("</thead>");

		ArrayList<Cart> myCart = new ArrayList<Cart>();
		String cartInSession = new String("Cart"); 
		if(null!=session.getAttribute(cartInSession)){
			ArrayList<Cart> priorCart = (ArrayList<Cart>)session.getAttribute(cartInSession);

			myCart.addAll(priorCart);
		}
		for(HashMap.Entry<String, Product> myKey: ProductDisplays.entrySet()){

			String key = myKey.getKey();
			Product ph1 = myKey.getValue();
			if(ph1.getProductId() == prodId){
				Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 14);
				 dt = c.getTime();


				 Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime first insert into cart" + dt);
				Cart cart = new Cart(ph1.getBrandName(), ph1.getModel(), ph1.getPrice(), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,dt,prodId,ph1.getAccs());	

				myCart.add(cart); 
			}
			if(ph1.getProductId() == removeProdId){
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id at index" + index);

				Iterator<Cart> it = myCart.iterator();
				while(it.hasNext()){
					Cart cartx=it.next();
					if(cartx.getProductId()==removeProdId){
						it.remove();
					}
				}
			}
			else
			{
				for (Accessory acc : ph1.getAccs()) {
				if ((acc.getProductId() == prodId) && (acc.getType() != type)){
				Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 14);
				dt = c.getTime();


				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime first insert into cart" + dt);
				Cart cart = new Cart(acc.getType(), acc.getDescription(), (acc.getPrice()-acc.getDiscount()), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,dt,prodId);	

				myCart.add(cart); 
			
				}
				if((acc.getProductId() == removeProdId) && (acc.getType() != type)){
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id at index" + index);
				Iterator<Cart> it = myCart.iterator();
				while(it.hasNext()){
					Cart cartx=it.next();
					if(cartx.getProductId()==removeProdId){
						it.remove();
					}
				}
				}	
				}
			}
			index++;
		}

		

		session.setAttribute(cartInSession, myCart);
		if(!userLoggedIn)
			out.println("<form action='LoginServlet' >");
		
			else
			out.println("<form action='OrderDetailsServlet' method='post'>");
		
		for (Cart c1: myCart){


			out.println("<tr>");

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
			out.println("<input type='text' name='quantityval-"+c1.getProductId()+"' value='1'><br>");
			out.println("</td>");
			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("<input type='checkbox' name='buyWarranty' value='buyWarranty'><br>");
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			//out.println("<form action='CartServlet?removeProduct=" + c1.getProductId() + " 'method='post' >");
			out.println("<button formaction='CartServlet?removeProduct=" + c1.getProductId() + "' type='submit' class=button>Remove Item</button>");
			//out.println("</form>");
			out.println("</td>");
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "rc1.getProductId()" + c1.getProductId());
			out.println("</tr>");	    			
		}


		out.println("</table>");
		
		
		

		System.out.println("hi");
		out.println("<br><br><center><button class=button type='submit'>Check Out</button></center>");
		out.println("</form>");
		//out.println("</a>");
		if (userLoggedIn && myCart != null) {
			/* out.println("<br><br><br><br>");
			out.println("<h5>Suggestion</h5>");
			
			out.println("<br><br><br><br>"); */
			out.println("<div class='smp-crsl'>");
			out.println("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
			out.println("<ol class='carousel-indicators'>");
			out.println("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
			out.println("<li data-target='#myCarousel' data-slide-to='1'></li>");
			out.println("<li data-target='#myCarousel' data-slide-to='2'></li>");
			out.println("</ol>");
			out.println("<div class='carousel-inner'>");
			int ccount=0;
			
			List<Accessory> crslList = new ArrayList<Accessory>();
			for (Cart c1: myCart) {
				System.out.println("inside acc. build for carousel / product being - " + c1.getBrandName());
				if(c1.getAccs() != null)
				crslList.addAll(c1.getAccs());
				break;
			}		
			
			for ( Accessory acc : crslList) {
				String name = acc.getType();
				if(acc.getType().equals(name)) {						
					ccount++;
					System.out.println(ccount);
					String ctext="";
					if(ccount==1) {
						out.println("<div class='item active'>");
					} else {
						out.println("<div class='item'>");
					}
					
					out.println("<img src='Images/" + acc.getImageName() + ".jpg' height='300px' width='300px' >");
					out.println("<div class='carousel-caption d-none d-md-block' style='color:black;'>");
					out.println("<h3>"+acc.getDescription()+"</h3>");
					out.println("<p>$"+acc.getPrice()+"</p>");
					out.println("<form method = 'get' action = 'AddToCart'>");
					out.println("<br>");
					out.println("<br>");
					out.println("<a href='CartServlet?productId="+acc.getProductId()+"'> <input class='button' type='button' value=' Add to Cart '/>");
					out.println("<input type='hidden' name='productName' value='"+acc.getType()+"'>");
					out.println("<input type='hidden' name='image' value='"+acc.getImageName()+"'>");
					out.println("<input type='hidden' name='price' value='"+acc.getPrice()+"'>");
					out.println("<input type='hidden' name='quantity' value='"+1+"'>");
					out.println("</form>");
					out.println("</div>");
					out.println("</div>");
				}
			}
					
			
			out.println("</div>");
			
			out.println("<a class='carousel-control left' href='#myCarousel' data-slide='prev'>");
			out.println("<span class='glyphicon glyphicon-chevron-left'></span>");
			out.println("</a>");
			out.println("<a class='carousel-control right' href='#myCarousel' data-slide='next'>");
			out.println("<span class='glyphicon glyphicon-chevron-right'></span>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
			
		}
		out.println("</section>");
		out.println("</section>");
		
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
	}
}