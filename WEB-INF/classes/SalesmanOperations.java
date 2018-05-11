import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class SalesmanOperations extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>"); 
        out.println("<form method='post' action='SalesmanOperations'>");
        out.println("<br><br><p><label for='email'>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Enter Emaill Id:</label>");
        out.println("<input name='email' id='email' value='' type='text'/></p>");
        out.println("<center><input class= button type='submit' value='Submit'/></center>");
        out.println("</form>");               
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String emailId = request.getParameter("email");
		IMySqlDataStoreUtilities dsutils = new MySqlDataStoreUtilities();
        //ArrayList<Person> personList = SerializeDataStore.readPersonDataStore();      
        Boolean personFound=false;
		try{
        ArrayList<Person> personList = dsutils.readPerson();
		
        if(personList.size()==0){

        }
        else{
            for(Person person:personList){
                if(person.getEmail().equals(emailId)){
                    HttpSession session = request.getSession(true);
                    String userInfo = new String("UserInfo"); 
                    person.setRole("salesman");
                    session.setAttribute(userInfo, person);
                    personFound=true;
                    break;
                }
            }
        }

        if(personFound){
            response.sendRedirect("HomeServlet");
        }
        else{
            response.setContentType("text/html");
            Utilities utility = new Utilities();
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
            PrintWriter out = response.getWriter();
            out.println("<div id='body'>");
            out.println("<section id='content'>"); 
            out.println("<form method='post' action='SalesmanOperations'>");
            out.println("<p><label for='ErrorMessage'>Invalid email!!! Please try again!!!</label>");
            out.println("<p><label for='email'>Enter Customer Emaill Id:</label>");
            out.println("<input name='email' id='email' value='' type='text'/></p>");
            out.println("<input type='submit' value='Submit'/>");
            out.println("</form>");               
            out.println("</section>");
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);

        }
    }

		catch(Exception ex){
        response.setContentType("text/html");
            Utilities utility = new Utilities();
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
            PrintWriter out = response.getWriter();
            out.println("<div id='body'>");
            out.println("<section id='content'>"); 
            out.println("<form method='post' action='SalesmanOperations'>");
            out.println("<p><label for='ErrorMessage'>MySql server is not up and running.</label>");
            out.println("</form>");               
            out.println("</section>");
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
            utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    }


}


