import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class VerifyLogin extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        
        String userName = request.getParameter("userid");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "User entered role " + role);
		
        // ArrayList<Person> personList = SerializeDataStore.readPersonDataStore();      
	   
	    IMySqlDataStoreUtilities dsu = new MySqlDataStoreUtilities();
		ArrayList<Person> personList = dsu.readPerson(); 
		
        Boolean personFound=false;
        if(personList.size()==0){

        }
        else{
            for(Person person:personList){
                if(person.getUserId().equals(userName) && person.getPassword().equals(password)
                    && person.getRole().equals(role)
                    ){
                    HttpSession session = request.getSession(true);
                    String userInfo = new String("UserInfo"); 
                    session.setAttribute(userInfo, person);
                    personFound=true;
                    break;
                }
            }
        }
        
        
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter(); 
        
        if(personFound){
            
            response.sendRedirect("HomeServlet");
        }
            

        else{            
            out.println("<div id='body'>");
            out.println("<section id='content'>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
            out.println("<center><h3>Hey Stranger!!<br><br><a href = 'LoginServlet'> Sign Up.</h3></center></a>");                               
            out.println("</section>");
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
        }


    }
}
