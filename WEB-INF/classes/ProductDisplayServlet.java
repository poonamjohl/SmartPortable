import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;

public class ProductDisplayServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String brandName = request.getParameter("brandName");
        String type=request.getParameter("type");
        ProductDisplay pd1 = new ProductDisplay();
        HashMap<String,Product> displayProducts = pd1.buildBasicProductDisplayList();
		//System.out.println("printing displayProducts - " + displayProducts);
        int index=0;

        response.setContentType("text/html");   
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);      
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        

        out.println("<table>");
		
		HttpSession session = request.getSession(true);
		
		String userInfo = new String("UserInfo");
         Person person = new Person();
		
		if (null != session.getAttribute(userInfo)) {

             person = (Person) session.getAttribute(userInfo);
        }

        for(HashMap.Entry<String, Product> myKey: displayProducts.entrySet()){

            String key = myKey.getKey();
            Product pdisplay1 = myKey.getValue();
			
            if((pdisplay1.getBrandName().equals(brandName) || brandName==null) && pdisplay1.getType().equalsIgnoreCase(type)){
                if(index%3==0){
                    out.println("<tr >");
                }
				out.println("<td>");  
				out.println("<p>");				
				out.println("<font color='blue'>");				
				out.println(pdisplay1.getModel());
				out.println("</font>");
				out.println("</p>");

				out.println("<img height=70 width=70 class='header-image' src='Images/" + pdisplay1.getImageName() + ".jpg'");     
                out.println("/>");
				out.println("</a>");
				out.println("<br>");
				out.println("<br>");
                out.println("<strike>Price: $"+pdisplay1.getPrice());
				out.println("</strike>");
                out.println("<br><label for='discount'>Discount: $"+ pdisplay1.getDiscount()+"</label>");
                double finalPrice = pdisplay1.getPrice()-pdisplay1.getDiscount();
                out.println("<br><label for='afterDiscount'>Final Price: $"+ finalPrice+"</label>");                  
				out.println("<br>");	
				out.println("<br>");	
				out.println("<a href='CartServlet?productId="+pdisplay1.getProductId()+"&type="+pdisplay1.getType()+"&quantity="+pdisplay1.getQuantity()+"'> <input class='button' type='button' value=' Add to Cart '/>");       
                out.println("</a>");
				out.println("&nbsp");
				out.println("</p>");
				out.println("<p>");
                out.println("<form action='WriteReviewsServlet' method='get'>");
                out.println("<button class='button' type='submit'>Write Reviews</button>");
                
                out.println("<input type='hidden' name='model' value='" + pdisplay1.getModel() + "'/>");
                out.println("<input type='hidden' name='category' value='" + pdisplay1.getType() + "'/>");
                out.println("<input type='hidden' name='price' value='" + pdisplay1.getPrice() + "'/>");
                out.println("<input type='hidden' name='manufacturer' value='" + pdisplay1.getBrandName() + "'/>");
                out.println("<input type='hidden' name='rebate' value='" + pdisplay1.getRebate() + "'/>");
                out.println("<input type='hidden' name='userId' value='" + person.getUserId() + "'/>");
                out.println("</form>");                
                out.println("</p>");
				out.println("<p>");
				out.println("<form action='ViewReviewsServlet' method='post'>");
				out.println("<button class='button' type='submit'>View Reviews</button>");
                 out.println("<input type='hidden' name='model' value='" + pdisplay1.getModel() + "'/>");
                out.println("<input type='hidden' name='category' value='" + pdisplay1.getType() + "'/>");                
                out.println("<input type='hidden' name='manufacturer' value='" + pdisplay1.getBrandName() + "'/>");                
                out.println("</form>");        
                out.println("</p>");
                out.println("</td>");
                index++;
                if(index%3==0){  
                    out.println("</tr>");
                    index=0;
                }
                
            } 
			 
			
			
		}
		out.println("</table>");
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }
}
