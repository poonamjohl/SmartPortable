import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;


public class DisplayAccessoriesServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String accType = request.getParameter("acctype");
		System.out.println("acctype "+accType);
		ProductDisplay pDisplay = new ProductDisplay();
        HashMap<String,Product> ProductDisplays = pDisplay.buildBasicProductDisplayList(); 
        int index=0;
		HttpSession session = request.getSession(true);
        response.setContentType("text/html");   
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);     
		Person person = new Person();
		String userInfo = new String("UserInfo");
		if (null != session.getAttribute(userInfo)) {

             person = (Person) session.getAttribute(userInfo);
        }
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        

        out.println("<table>");


        for(HashMap.Entry<String, Product> myKey: ProductDisplays.entrySet()){

            String key = myKey.getKey();
            Product pdisplay1 = myKey.getValue();
			for (Accessory acc : pdisplay1.getAccs()) {			
            if(acc.getType().equals(accType) ){
                if(index%3==0){
                    out.println("<tr >");
                }
				out.println("<td>");  
				out.println("<p>");				
				out.println("<font color='blue'>");				
				out.println(acc.getDescription());
				out.println("</font>");
				out.println("</p>");

				out.println("<img height=70 width=70 class='header-image' src='Images/" + acc.getImageName() + ".jpg'");     
                out.println("/>");
				out.println("</a>");
				out.println("<br>");
				out.println("<br>");
				//out.println("<p>");
                out.println("Price: $"+acc.getPrice());
                out.println("<br><label for='discount'>Discount: $"+ acc.getDiscount()+"</label>");
                double finalPrice = acc.getPrice()-acc.getDiscount();
                out.println("<br><label for='afterDiscount'>Final Price: $"+ finalPrice+"</label>");                  
                //out.println("</p>");                
                //out.println("<p>"); 
				out.println("<br>");	
				out.println("<br>");						
                out.println("<a href='CartServlet?productId="+acc.getProductId()+"'> <input class='button' type='button' value=' Add to Cart '/>");       
                out.println("</a>");
				out.println("&nbsp");
				out.println("</p>");
               out.println("<p>");
                out.println("<form action='WriteReviewsServlet' method='get'>");
                out.println("<button class='button' type='submit'>Write Reviews</button>");
                
                out.println("<input type='hidden' name='category' value='" + acc.getDescription() + "'/>");
                out.println("<input type='hidden' name='model' value='" + acc.getType() + "'/>");
                out.println("<input type='hidden' name='price' value='" + acc.getPrice() + "'/>");
                out.println("<input type='hidden' name='discount' value='" + pdisplay1.getDiscount() + "'/>");
                out.println("<input type='hidden' name='userId' value='" + person.getUserId() + "'/>");
                out.println("</form>");                
                out.println("</p>");
				out.println("<p>");
				out.println("<form action='ViewReviewsServlet' method='post'>");
				out.println("<button class='button' type='submit'>View Reviews</button>");
                 out.println("<input type='hidden' name='category' value='" + acc.getDescription() + "'/>");
                out.println("<input type='hidden' name='model' value='" + acc.getType() + "'/>");                
                //out.println("<input type='hidden' name='manufacturer' value='" + acc.getBrandName() + "'/>");                
                out.println("</form>");        
                out.println("</p>");
                out.println("</td>");
                index++;
                if(index%3==0){  
                    out.println("</tr>");
                    index=0;
                }
                
            } 
			 
			
			
		}}

        out.println("</table>");
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }
}

