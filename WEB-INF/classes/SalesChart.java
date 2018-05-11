import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class SalesChart extends HttpServlet{	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		MySqlDataStoreUtilities mysql= new MySqlDataStoreUtilities();
		ArrayList<Order> ProductDisplayList = new ArrayList<Order>();
		ProductDisplayList = mysql.getReport();
		System.out.println("product display size" + ProductDisplayList.size());
        /**********************/
        response.setContentType("text/html");
        Utilities utility = new Utilities();
       // utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
        PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
		out.println("<script type='text/javascript'>");
		  out.println("google.charts.load('current', {'packages':['bar']});");
		  out.println("google.charts.setOnLoadCallback(drawChart);");
		  out.println("function drawChart() {");
			out.println("var data = google.visualization.arrayToDataTable([");
			  out.println("['Product Name', 'Sales']");
			  for (Order pd: ProductDisplayList){
					out.println(",['"+pd.getModel()+"',"+pd.getSales()+"]");
					
			  }
				out.println("]);");
			out.println("var options = {");
			  out.println("chart: {");
				out.println("title: 'SmartPortable',");
				out.println("subtitle: 'Product and amount Sold',");
				//
			  out.println("},");
			  out.println("'width':1000,'height':1000,");
			  out.println("bars: 'horizontal' // Required for Material Bar Charts.");
			out.println("};");

			out.println("var chart = new google.charts.Bar(document.getElementById('barchart_material'));");
			out.println("var container = document.getElementById('barchart_material');");
			// throw error for testing
			out.println("google.visualization.events.addListener(chart, 'ready', function () {");
			  out.println("throw new Error('Test Google Error');");
			out.println("});");

			// listen for error
			out.println("google.visualization.events.addListener(chart, 'error', function (err) {");
			  // check error
			  out.println("console.log(err.id, err.message);");

			  // remove error
			  out.println("google.visualization.errors.removeError(err.id);");

			  // remove all errors
			  out.println("google.visualization.errors.removeAll(container);");
			out.println("});");

			out.println("chart.draw(data, google.charts.Bar.convertOptions(options));");
		  out.println("}");
		  out.println("</script>");
		  out.println("</head>");
		  out.println("<body>");
		  out.println("<div id='barchart_material' height=1000px width=100%></div>");
		  //out.println("<div id='chart_div' style='width:900px'; 'height:500px';'></div>");
		  out.println("</body>");
		  out.println("</html>");

        //utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        //utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        
    }


}