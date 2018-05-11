import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import com.google.gson.*;

public class DataVisualization extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
	try
	{
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> reviews = MongoDBDataStoreUtilities.selectReviewForChart();
		//ArrayList product = MongoDBDataStoreUtilities.selectReviewForChart();
		//ArrayList topReviewsPerCity= getTop3InEveryCity(reviews);
		String reviewJson= new Gson().toJson(reviews);
		
		response.setContentType("application/JSON");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(reviewJson);
		System.out.println("JSON" + reviewJson);
	}
	catch(Exception e)
	
	{
		System.out.println(e.getMessage());
	}
			}
}