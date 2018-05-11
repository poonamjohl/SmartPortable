import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddProducts extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>");
		out.println("<br>");
        out.println("<h2><center><b>Product Specifications</center></b></h2>");     
        out.println("<form method='post' action='SaveProducts?action=1'>");

        out.println("<p><label for='productId'>Product ID:</label>");
        out.println("<input name='prodId' id='prodId' value='' type='text'/></p>");

        
        out.println("<p>Product Category");
        out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<select id='prodCategory' name='prodCategory'>");
		out.println("<option value='smartwatch'>smartwatch</option>"); 
        out.println("<option value='speaker'>speaker</option>"); 
        out.println("<option value='headphone'>headphone</option>");
		out.println("<option value='phone'>phone</option>"); 
		out.println("<option value='laptop'>laptop</option>"); 
        out.println("<option value='external storage'>external storage</option>");    
        out.println("</select></p>");

        out.println("<p>Brand");
        out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<select id='brandName' name='brandName'>");
        out.println("<option value='Samsung'>Samsung</option>"); 
        out.println("<option value='Apple'>Apple</option>"); 
        out.println("<option value='LG'>LG</option>"); 
        out.println("<option value='Sony'>Sony</option>");
        out.println("<option value='Dell'>Dell</option>"); 
        out.println("<option value='Google'>Google</option>"); 
        out.println("<option value='Sennheiser'>Sennheiser</option>"); 
		out.println("<option value='Logitech'>Logitech</option>"); 
		out.println("<option value='Sea Gate'>Sea Gate</option>"); 
		out.println("<option value='Asus'>Asus</option>");    
		out.println("<option value='Western Digital'>Western Digital</option>");
		out.println("<option value='Bose'>Bose</option>");	
		out.println("<option value='JBL'>JBL</option>");	
		out.println("<option value='Lenovo'>Lenovo</option>");	
		out.println("<option value='San Disk'>San Disk</option>");
        out.println("</select></p>");


        out.println("<p><label for='model'>Model:</label>");
        out.println("<input name='model' id='model' value='' type='text'/></p>");

        out.println("<p><label for='price'>Price:</label>");
        out.println("<input name='price' id='price' value='' type='text'/></p>");

        out.println("<p><label for='discount'>Discount:</label>");
        out.println("<input name='discount' id='discount' value='' type='text'/></p>");

        out.println("<p><label for='rebate'>Rebate:</label>");
        out.println("<input name='rebate' id='rebate' value='' type='text' /></p>");

        out.println("<p><label for='quantity'>Quantity:</label>");
        out.println("<input name='quantity' id='quantity' value='' type='text' /></p>");

        out.println("<p><label for='imagePath'>Image Path:</label>");
        out.println("<input name='imagePath' id='imagePath' value='' type='text' /></p>");

        out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type='submit' class=button value='Add'/>");
        out.println("</form>");
        out.println("</fieldset>"); 
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}