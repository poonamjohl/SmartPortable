import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class DataAnalyticsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		ArrayList<Reviews> reviewList = new ArrayList<Reviews>();
		//Product Name
		String productNameSelected= request.getParameter("ProductNameSelected");
		//Product Category
		String productCategorySelected = request.getParameter("ProductCategorySelected");
		//Product Price
		String productPriceSelected = request.getParameter("ProductPriceSelected");
		//Zip
		String zipcodeChecked = request.getParameter("zipcodeChecked");
		//cityChecked
		String cityChecked = request.getParameter("cityChecked");
		//Product Category
		String stateChecked = request.getParameter("stateChecked");
		//Product Category
		String onsaleChecked = request.getParameter("onsaleChecked");
		//Product Category
		String manufacturerChecked = request.getParameter("manufacturerChecked");
		//Product Category
		String userIdChecked = request.getParameter("userIdChecked");
		//Product Category
		String ageChecked = request.getParameter("ageChecked");
		//Product Category
		String genderChecked = request.getParameter("genderChecked");
		//Product Category
		String occupationChecked = request.getParameter("occupationChecked");
		//Product Category
		String ratingChecked = request.getParameter("ratingChecked");
		//Product Category
		String reviewTextChecked = request.getParameter("reviewTextChecked");

		String groupByChecked = request.getParameter("groupByChecked");

		String priceConditionSelected = request.getParameter("conditionprice");
		String ageConditionSelected = request.getParameter("conditionage");
		String ratingConditionSelected = request.getParameter("conditionrating");
		
		
		String productName =null, productCategory=null,zipCode=null, city=null, state=null, 
		onSale=null,manufacturer=null, userId=null, 
		 gender=null, occupation=null,  reviewText=null,
		 ratingCriteria=null,ageCriteria=null,priceCriteria=null;

		 String groupByOption = null;
		 String countOrDetail = null;

		Integer rating=null,age=null;
		Double price=null;

		if(productNameSelected!=null && productNameSelected.equals("1")){
			productName= request.getParameter("ProductName");			
		}
		if(productCategorySelected!=null && productCategorySelected.equals("1")){			
			productCategory = request.getParameter("ProductCategory");
		}
		if(productPriceSelected!=null && productPriceSelected.equals("1")){
			String pricex = request.getParameter("ProductPrice");
			if(pricex!=null){
				price = Double.parseDouble(pricex);
			}
			 priceCriteria = request.getParameter("conditionprice");
		}
		if(zipcodeChecked!=null && zipcodeChecked.equals("1")){
			zipCode = request.getParameter("zipcode");
		}
		if(cityChecked!=null && cityChecked.equals("1")){
			city = request.getParameter("City");
		}
		if(stateChecked!=null && stateChecked.equals("1")){
			state = request.getParameter("stateList");
		}
		if(onsaleChecked!=null && onsaleChecked.equals("1")){
			onSale = request.getParameter("productOnSale");
		}
		if(manufacturerChecked!=null && manufacturerChecked.equals("1")){
			manufacturer = request.getParameter("manufacturerName");
		}
		if(userIdChecked!=null && userIdChecked.equals("1")){
			userId = request.getParameter("users");
		}
		if(ageChecked!=null && ageChecked.equals("1")){
			String agex = request.getParameter("age");
			if(agex!=null){
				age = Integer.parseInt(agex);
			}
			 ageCriteria = request.getParameter("conditionage");
		}
		if(genderChecked!=null && genderChecked.equals("1")){
			gender = request.getParameter("Gender");
		}
		if(occupationChecked!=null && occupationChecked.equals("1")){
			occupation = request.getParameter("occupation");
		}
		if(ratingChecked!=null && ratingChecked.equals("1")){
			rating = Integer.parseInt(request.getParameter("rating"));
			 ratingCriteria = request.getParameter("conditionrating");
		}
		if(reviewTextChecked!=null && reviewTextChecked.equals("1")){
			reviewText = request.getParameter("reviewText");
		}
		if(groupByChecked!=null && groupByChecked.equals("1")){

			groupByOption = request.getParameter("groupByOption");
			countOrDetail = request.getParameter("count");

		}

		reviewList= MongoDBDataStoreUtilities.dataAnalytics(productName,productCategory,zipCode,city,state,
			onSale,manufacturer,userId,gender,occupation,rating,reviewText,price,age,priceCriteria,ageCriteria, ratingCriteria);

		response.setContentType("text/html");
		Utilities utility = new Utilities();
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
		PrintWriter out = response.getWriter();
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<fieldset>");
		out.println("<h1>&emsp;&emsp;&emsp;&emsp;Product Reviews</h1>");
		out.println("<br>");

		// out.println("<h1>" + groupByOption + countOrDetail + "</h1>");

		for (Reviews review : reviewList) {
			// out.println("<h2>" + review.getReviewText() + "</h2>");
			out.println("<table id='productReview'  >");
			out.println("<tbody>");
			out.println("<tr>");
			out.println("<td><b>Product Model Name</td>");
			out.println("<td>" + review.getProductModelName() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Product Model category</td>");
			out.println("<td>" + review.getProductCategory() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Price</td>");
			out.println("<td>" + review.getProductPrice() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Retailer Name</td>");
			out.println("<td>" + review.getRetaileName() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>City</td>");
			out.println("<td>" + review.getRetailerCity() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>State</td>");
			out.println("<td>" + review.getRetailerState() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Zip code</td>");
			out.println("<td>" + review.getRetailerZip() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Product On Sale</td>");
			out.println("<td>" + review.getProductOnSale() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Manufacturer Name</td>");
			out.println("<td>" + review.getManufacturerName() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Rebate</td>");
			out.println("<td>" + review.getManufacturerRebate() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>UserName</td>");
			out.println("<td>" + review.getUserId() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>User Age</td>");
			out.println("<td>" + review.getUserAge() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Gender</td>");
			out.println("<td>" + review.getUserGender() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Occupation</td>");
			out.println("<td>" + review.getOccupation() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Rating</td>");
			out.println("<td>" + review.getRating() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Review Date</td>");
			out.println("<td>" + review.getReviewDate() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><b>Review Text</td>");
			out.println("<td>" + review.getReviewText() + "</td>");
			out.println("</tr>");

			out.println("</tbody>");
			out.println("</table>");
			out.println("<br>");
			//out.println("<br>");
		}

		out.println("</fieldset>");
		out.println("</section>");
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HashMap<String, ArrayList<Reviews>> reviews = new HashMap<String, ArrayList<Reviews>>();
		reviews = MongoDBDataStoreUtilities.selectReview();

		ArrayList<String> ProductTypes = new ArrayList<String>();
		ArrayList<ArrayList<Reviews>> reviewList = new ArrayList<ArrayList<Reviews>>();
		ArrayList<String> productCategoryList = new ArrayList<String>();
		ArrayList<String> cityList = new ArrayList<String>();
		ArrayList<String> stateList = new ArrayList<String>();
		ArrayList<String> manufacturers = new ArrayList<String>();
		ArrayList<String> users = new ArrayList<String>();
		ArrayList<String> occupations = new ArrayList<String>();

		for (HashMap.Entry<String, ArrayList<Reviews>> myKey : reviews.entrySet()) {

			String key = myKey.getKey();
			ArrayList<Reviews> value = myKey.getValue();
			ProductTypes.add(key);
			reviewList.add(value);

		}

		// Get all product categories
		for (ArrayList<Reviews> reviewListx : reviewList) {

			for (Reviews review : reviewListx) {

				Boolean found = false;
				for (int i = 0; i < productCategoryList.size(); i++) {
					if (productCategoryList.get(i).equals(review.getProductCategory())) {
						found = true;
					}
				}
				if (!found) {
					productCategoryList.add(review.getProductCategory());
				}
			}

		}

		// Get all cities
		for (ArrayList<Reviews> reviewListx : reviewList) {

			for (Reviews review : reviewListx) {

				Boolean found = false;
				for (int i = 0; i < cityList.size(); i++) {
					if (cityList.get(i).equals(review.getRetailerCity())) {
						found = true;
					}
				}
				if (!found) {
					cityList.add(review.getRetailerCity());
				}
			}

		}

		// Get all the states
		for (ArrayList<Reviews> reviewListx : reviewList) {

			for (Reviews review : reviewListx) {

				Boolean found = false;
				for (int i = 0; i < stateList.size(); i++) {
					if (stateList.get(i).equals(review.getRetailerState())) {
						found = true;
					}
				}
				if (!found) {
					stateList.add(review.getRetailerState());
				}
			}

		}

		// Get all the manufacturers
		for (ArrayList<Reviews> reviewListx : reviewList) {

			for (Reviews review : reviewListx) {

				Boolean found = false;
				for (int i = 0; i < manufacturers.size(); i++) {
					if (manufacturers.get(i).equals(review.getManufacturerName())) {
						found = true;
					}
				}
				if (!found) {
					manufacturers.add(review.getManufacturerName());
				}
			}

		}

		// Get all the users
		for (ArrayList<Reviews> reviewListx : reviewList) {

			for (Reviews review : reviewListx) {

				Boolean found = false;
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).equals(review.getUserId())) {
						found = true;
					}
				}
				if (!found) {
					users.add(review.getUserId());
				}
			}

		}

		// Get all the occupations
		for (ArrayList<Reviews> reviewListx : reviewList) {

			for (Reviews review : reviewListx) {

				Boolean found = false;
				for (int i = 0; i < occupations.size(); i++) {
					if (occupations.get(i).equals(review.getOccupation())) {
						found = true;
					}
				}
				if (!found) {
					occupations.add(review.getOccupation());
				}
			}

		}

		response.setContentType("text/html");
		Utilities utility = new Utilities();
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Header.html",response);
		PrintWriter out = response.getWriter();
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<fieldset>");
		out.println("<br>");
		out.println("<h1>&emsp;&emsp;&emsp;&emsp;Analytics on Reviews</h1>");
		out.println("<form method='post' action='DataAnalyticsServlet'>");
		out.println("<table>");
		out.println("<thead>");

		// Product Name row
		out.println("<tr>");
		out.println("<td>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='ProductNameSelected' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Product Name</label>");
		out.println("</td>");
		out.println("<td>");
		out.println("<p><label for='ProductName'></label>");
		out.println("<select id='ProductName' name='ProductName'>");
		out.println("<option value='*'>All Products</option>");
		for (String productType : ProductTypes) {
			out.println("<option value='" + productType + "'>" + productType + "</option>");
		}
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");
		// End Product type row
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='ProductCategorySelected' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Product Category</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='ProductCategory'></label>");
		out.println("<select id='ProductCategory' name='ProductCategory'>");
		out.println("<option value='*'>All</option>");
		for (String item : productCategoryList) {
			out.println("<option value='" + item+ "'>" + item + "</option>");
		}
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<br>");
		// End Product Category
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='ProductPriceSelected' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Product Price</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<input type='text' id='ProductPrice' name='ProductPrice' value=''> </input>");
		out.println("</td>");
		out.println("<td>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<label for='gt'>Greater Than</label>");
		out.println("<input type='radio' name='conditionprice' id='conditionprice' value='gt'><br>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<label for='le'>Less Than</label>");
		out.println("<input type='radio' name='conditionprice' id='conditionprice' value='lt'><br>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<label for='eq'>Equals</label>");
		out.println("<input type='radio' name='conditionprice' checked='checked'  id='conditionprice' value='eq'><br><br>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='zipcodeChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>ZipCode</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<input id='zipcode' name='zipcode' value='' placeholder= 'Zip Code' pattern='[0-9]{5}' title='5 Number Zip Code' min='00000'> </input>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");

		// End Retailer Zip
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='cityChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>City</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='City'></label>");
		out.println("<select id='City' name='City'>");
		out.println("<option value='*'>All</option>");
		for (String city : cityList) {
			out.println("<option value='"+ city+"'>" + city + "</option>");
		}
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='stateChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>State</label>");
		out.println("</td>");
		out.println("<td align='left'>");
		out.println("<label for='stateList'></label>");
		out.println("<select id='stateList' name='stateList'>");
		out.println("<option value='*'>All</option>");
		for (String item : stateList) {
			out.println("<option value='" +item+"'>" + item + "</option>");
		}
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='onsaleChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Product On Sale</label>");
		out.println("</td>");
		out.println("<td align='left'>");
		out.println("<label for='productOnSale'></label>");
		out.println("<select id='productOnSale' name='productOnSale'>");
		out.println("<option value='Yes'>Yes</option>");
		out.println("<option value='No'>No</option>");
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");

		// End product on sale

		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='manufacturerChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Manufacturer Name</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='manufacturerName'></label>");
		out.println("<select id='manufacturerName' name='manufacturerName'>");
		out.println("<option value='*'>All</option>");
		for (String item : manufacturers) {
			out.println("<option value='" + item + "'>" + item + "</option>");
		}
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");


		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='userIdChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>User</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='users'></label>");
		out.println("<select id='users' name='users'>");
		out.println("<option value='*'>All</option>");
		for (String item : users) {
			out.println("<option value='"+item+"'>" + item + "</option>");
		}
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");

		// End user id
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='ageChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Age</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<input type='number' id='age' name='age' value=''> </input>");
		out.println("</td>");
		out.println("<td>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<label for='gt'>Greater Than</label>");
		out.println("<input type='radio' name='conditionage' id='conditionage' value='gt'><br>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<label for='le'>Less Than</label>");
		out.println("<input type='radio' name='conditionage' id='conditionage' value='lt'><br>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<label for='eq'>Equals</label>");
		out.println("<input type='radio' name='conditionage' checked='checked' id='conditionage' value='eq'><br><br>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='genderChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Gender</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='Gender'></label>");
		out.println("<select id='Gender' name='Gender'>");
		out.println("<option value='Male'>Male</option>");
		out.println("<option value='Female'>Female</option>");
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='occupationChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Occupation</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='occupation'></label>");
		out.println("<select id='occupation' name='occupation'>");
		out.println("<option value='*'>All</option>");
		for (String item : occupations) {
			out.println("<option value='"+ item+"'>" + item + "</option>");
		}
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='ratingChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Rating</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='rating'></label>");
		out.println("<select id='rating' name='rating'>");
		out.println("<option value='*'>All</option>");
		out.println("<option value='1'>1</option>");
		out.println("<option value='2'>2</option>");
		out.println("<option value='3'>3</option>");
		out.println("<option value='4'>4</option>");
		out.println("<option value='5'>5</option>");

		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<label for='gt'>Greater Than</label>");
		out.println("<input type='radio' name='conditionrating' id='conditionrating' value='gt'><br>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<label for='le'>Less Than</label>");
		out.println("<input type='radio' name='conditionrating' id='conditionrating' value='lt'><br>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<label for='eq'>Equals</label>");
		out.println("<input type='radio' checked='checked' name='conditionrating' id='conditionrating' value='eq'><br><br>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='reviewTextChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label><b>Review Text</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<input type='text' id='reviewText' name='reviewText' value=''> </input>");
		out.println("</td>");
		out.println("<td>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='checkbox' name='groupByChecked' value='1'><b> Select<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<label>Group By</label>");
		out.println("</td>");
		out.println("<td align='right'>");
		out.println("<label for='groupByOption'></label>");
		out.println("<select id='groupByOption' name='groupByOption'>");
		out.println("<option value='retailerCity'>City</option>");
		out.println("<option value='productName'>Product Name</option>");
		out.println("<option value='productType'>Product Category</option>");
		out.println("<option value='retailerZip'>Zip Code</option>");
		out.println("<option value='retailerState'>State</option>");
		out.println("<option value='manufacturerName'>Manufacturer</option>");
		out.println("<option value='gender'>Gender</option>");

		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<label for='count'>Count</label>");
		out.println("<input type='radio' name='count' id='count' value='count'><br>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<label for='detail'><b>Detail</label>");
		out.println("<input type='radio' checked='checked' name='count' id='count' value='detail'><br><br>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</td>");
		out.println("</tr>");
		//End filter criteria
		out.println("</tbody>");
		out.println("</table>");
		out.println("<br>");
		out.println("<br>");
		out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input class='button'type='submit' value='Find Data'/>");
		out.println("</form>");
		//out.println("<form action='DataVisualizationChart'>");
        out.println("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a href='DataVisualization' ><input class='button' type='button' value='Chart'/>");
		//out.println("</form>");
		out.println("</fieldset>");
		out.println("</section>");
		utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/LeftNavigationBar.html",response);
        utility.printHtml("C:/apache-tomcat-7.0.34/webapps/csj1/Footer.html",response);

	}

}