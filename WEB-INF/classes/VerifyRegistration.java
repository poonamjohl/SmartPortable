import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class VerifyRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = "C:/apache-tomcat-7.0.34/webapps/csj1/Files/Person.txt";
		IMySqlDataStoreUtilities dsutils = new MySqlDataStoreUtilities();
		//ArrayList<Person> users = SerializeDataStore.readPersonDataStore();
        ArrayList<Person> users = dsutils.readPerson();

        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contact");
        String userName = request.getParameter("uname");
        String password = request.getParameter("password");
        String role =  request.getParameter("role");

        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "VerifyRegistration role " + role);

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html", response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<center>");
        out.println("<form method='post' action='LoginServlet'>");

        boolean personExists = false;
        for (Person user : users) {
            if (user.getEmail().equals(email)) {
                personExists = true;
                break;
            }
        }
		
        if (personExists) {
            out.println("<br><br><br><h3>ID already exists, try again by <a href='UserRegistrationServlet'>clicking here</a>.</h3>");
        } else {
            Person person = new Person(userName, password, firstName, lastName, email, contactNumber, role);
			
			Boolean addSuccess = false;
            addSuccess = dsutils.addPerson(person);
//            users.add(person);
//            SerializeDataStore.writePersonDataStore(users);

        HttpSession sessionOld = request.getSession(true);
        String userInfoOld = new String("UserInfo");

        if (null == sessionOld.getAttribute(userInfoOld)) {        

            HttpSession session = request.getSession(true);
            String userInfo = new String("UserInfo"); 
            session.setAttribute(userInfo, person);
        }

            out.println("<br><br><h2>Welcome to SmartPortable family :). Your registration is successful</h2>");
        }

        out.println("</form>");
        out.println("</center>");
        out.println("</section>");
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html", response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html", response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}





