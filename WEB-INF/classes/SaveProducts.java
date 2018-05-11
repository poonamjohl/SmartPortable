import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class SaveProducts extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                // ArrayList<Product> product = new ArrayList<Product>();
                response.setContentType("text/html");
                Utilities utility = new Utilities();
                PrintWriter out = response.getWriter();
				IMySqlDataStoreUtilities dsutils = new MySqlDataStoreUtilities();
                String productId = request.getParameter("prodId");
                String type = request.getParameter("prodCategory");
                String brandName = request.getParameter("brandName");
                String model = request.getParameter("model");
                String price = request.getParameter("price");
                String discount = request.getParameter("discount");
                String rebate = request.getParameter("rebate");
                String quantity = request.getParameter("quantity");
                String imagePath = request.getParameter("imagePath");
                String action = request.getParameter("action");

                Product product = new Product();
                SalesManager manager = new SalesManager();

                if (!action.equals("3")) {
                        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "entering action 3");

                        if(productId.isEmpty() || productId==null){
                            Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "entering empty");
                            response.sendRedirect("AddProducts");

                            return;

                        }
                        else{
                        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "entering not empty");
                    }

                        product = new Product(Integer.parseInt(productId), brandName, model, Double.parseDouble(price),
                                        Double.parseDouble(discount), Double.parseDouble(rebate),Integer.parseInt(quantity), imagePath,
                                        type,product.getAccs());
                        
                }
                else{
                        int productIdNew = Integer.parseInt(productId);
                        HashMap<String,Product> products = ProductDisplay.buildBasicProductDisplayList();
                        for(HashMap.Entry<String, Product> myKey: products.entrySet()){

                            String key = myKey.getKey();
                            Product ph1 = myKey.getValue();

                            if(ph1.getProductId()==productIdNew){
                                product=ph1;
                            }
                    }
                }

                if (action.equals("1")) {
                        manager.addProduct(product);
						Boolean addSuccess = false;
            
                } else if (action.equals("2")) {
                        manager.updateProduct(product);
						dsutils.modProduct(product);
                } else {
                        manager.deleteProduct(product);
						int pid= Integer.parseInt(productId);
						dsutils.delProduct(pid);
                }

                utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html", response);
                out.println("<div id='body'>");
                out.println("<section id='content'>");

                if(action.equals("1")){


                out.println("<br><p>You have successfully added below product:</p>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>");
                out.println("Product Id");
                out.println("</th>");
                out.println("<th>");
                out.println("Category");
                out.println("</th>");
                out.println("<th>");
                out.println("brandName");
                out.println("</th>");
                out.println("<th>");
                out.println("Model");
                out.println("</th>");
                out.println("<th>");
                out.println("Price");
                out.println("</th>");
                out.println("<th>");
                out.println("Discount");
                out.println("</th>");
                out.println("<th>");
                out.println("Rebate");
                out.println("</th>");
                out.println("<th>");
                out.println("Quantity");
                out.println("</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println(productId);
                out.println("</td>");
                out.println("<td>");
                out.println(type);
                out.println("</td>");
                out.println("<td>");
                out.println(brandName);
                out.println("</td>");
                out.println("<td>");
                out.println(model);
                out.println("</td>");
                out.println("<td>");
                out.println(price);
                out.println("</td>");
                out.println("<td>");
                out.println(discount);
                out.println("</td>");
                out.println("<td>");
                out.println(rebate);
                out.println("</td>");
                out.println("<td>");
                out.println(quantity);
                out.println("</td>");
				out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
        }
		else if(action.equals("2")){


                out.println("<br><p>You have successfully updated below product:</p>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>");
                out.println("Product Id");
                out.println("</th>");
                out.println("<th>");
                out.println("Category");
                out.println("</th>");
                out.println("<th>");
                out.println("Brand Name");
                out.println("</th>");
                out.println("<th>");
                out.println("Model");
                out.println("</th>");
                out.println("<th>");
                out.println("Price");
                out.println("</th>");
                out.println("<th>");
                out.println("Discount");
                out.println("</th>");
                out.println("<th>");
                out.println("Rebate");
                out.println("</th>");
                out.println("<th>");
                out.println("Quantity");
                out.println("</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println(productId);
                out.println("</td>");
                out.println("<td>");
                out.println(type);
                out.println("</td>");
                out.println("<td>");
                out.println(brandName);
                out.println("</td>");
                out.println("<td>");
                out.println(model);
                out.println("</td>");
                out.println("<td>");
                out.println(price);
                out.println("</td>");
                out.println("<td>");
                out.println(discount);
                out.println("</td>");
                out.println("<td>");
                out.println(rebate);
                out.println("</td>");
                out.println("<td>");
                out.println(quantity);
                out.println("</td>");
				out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
        }
		
        else{
				out.println("<br>");
                out.println("<center><br><p><h3>Product Deleted:<h3></p></center>");
        }
                out.println("</section>");
                utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html", response);
                utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html", response);
        }
}