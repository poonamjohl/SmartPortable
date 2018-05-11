import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Date;


public class MySqlDataStoreUtilities implements IMySqlDataStoreUtilities {

	private Connection conn = null;
	private PreparedStatement pst,ps = null;

	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SmartPortable", "root", "root123");
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}
	}

	public Boolean addPerson(Person person) {
		Boolean addSuccess = false;
		try {
			getConnection();

			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(firstName,lastName,email,contactNumber,userName,password,role) "
					+ "VALUES (?,?,?,?,?,?,?);";
			pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1, person.getFName());
			pst.setString(2, person.getlName());
			pst.setString(3, person.getEmail());
			pst.setString(4, person.getContactNumber());
			pst.setString(5, person.getUserId());
			pst.setString(6, person.getPassword());
			pst.setString(7, person.getRole());
			addSuccess = pst.execute();

			pst.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return addSuccess;
	}

	public ArrayList<Person> readPerson() {
		ArrayList<Person> personList = null;
		java.sql.Statement stm = null;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getpersonData = "SELECT userName, password, firstName, lastName, email, contactNumber, role FROM Registration";

			ResultSet rst;
			rst = stm.executeQuery(getpersonData);
			personList = new ArrayList<>();
			while (rst.next()) {
				Person person = new Person(rst.getString("userName"), rst.getString("password"),
						rst.getString("firstName"), rst.getString("lastName"), rst.getString("email"),
						rst.getString("contactNumber"), rst.getString("role"));
				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
						person.toString() + "read from db");
				personList.add(person);
			}

			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return personList;
	}
	public Boolean insertProduct(ArrayList<Product> products)
	{ Boolean insertSuccess = false;
		try
		{
			getConnection();
			ProductDisplay pDisplay = new ProductDisplay();
			HashMap<String,Product> ProductDisplays = pDisplay.buildBasicProductDisplayList(); 
			for(HashMap.Entry<String, Product> myKey: ProductDisplays.entrySet()){
            String key = myKey.getKey();
            Product dp = myKey.getValue();
			String insertIntoProductDetails = "INSERT INTO ProductDetails(productId,brandName, model, price,discount,rebate,quantity, imageName,type,accs) " + "VALUES (?,?,?,?,?,?,?,?,?,?);";
			pst = conn.prepareStatement(insertIntoProductDetails);
			pst.setInt(1,dp.getProductId());
			pst.setString(2,dp.getBrandName());
			pst.setString(3,dp.getModel());
			pst.setDouble(4,dp.getPrice());
			pst.setDouble(5,dp.getDiscount());
			pst.setDouble(6,dp.getRebate());
			pst.setInt(7,dp.getQuantity());
			pst.setString(8,dp.getImageName());
			pst.setString(9,dp.getType());
			pst.setString(10,dp.getAccs().toString());
			pst.execute();
			for (Accessory acc : dp.getAccs()) 
			{String BrandName=dp.getBrandName();
            if(acc.getType().equals(BrandName)) {
			String insertIntoAccessory= "Insert Into Accessory(productId,description,price,discount,acctype,accimageName,accquantity)" + "VALUES(?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(insertIntoAccessory);
			ps.setInt(1,acc.getProductId());
			ps.setString(2,acc.getDescription());
			ps.setDouble(3,acc.getPrice());
			ps.setDouble(4,acc.getDiscount());
			ps.setString(5,acc.getType());
			ps.setString(6,acc.getImageName());
			ps.setInt(7,acc.getQuantity());

			ps.execute();
			//System.out.println("insert products: " +pst.execute());
			}
			}
			//pst.execute();
		}	//ps.execute();
			//pst.execute();
			} 
			
			catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return insertSuccess;

	}
	public Boolean addProduct(Product dp)
	{ Boolean insertSuccess = false;
		try
		{
			getConnection();
			String insertIntoProductDetails = "INSERT INTO ProductDetails(productId,brandName, model, price,discount,rebate,quantity, imageName,type,accs) " + "VALUES (?,?,?,?,?,?,?,?,?,?);";
			pst = conn.prepareStatement(insertIntoProductDetails);
			pst.setInt(1,dp.getProductId());
			pst.setString(2,dp.getBrandName());
			pst.setString(3,dp.getModel());
			pst.setDouble(4,dp.getPrice());
			pst.setDouble(5,dp.getDiscount());
			pst.setDouble(6,dp.getRebate());
			pst.setInt(7,dp.getQuantity());
			pst.setString(8,dp.getImageName());
			pst.setString(9,dp.getType());
			pst.setString(10,dp.getAccs().toString());
			pst.execute();
			pst.close();
			conn.close();
		}
		
		catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return insertSuccess;

	}
	// Get Products
		public Boolean modProduct(Product dp)
	{
		try
		{
			getConnection();
			int pid = dp.getProductId();
			String delold= " Delete from productdetails where productId=?;";
			ps=conn.prepareStatement(delold);
			ps.setInt(1,pid);
			ps.execute();
			String modifyProduct = "INSERT INTO ProductDetails(productId,brandName, model, price,discount,rebate,quantity, imageName,type,accs)" + "VALUES (?,?,?,?,?,?,?,?,?,?);";
			pst = conn.prepareStatement(modifyProduct);
			pst.setInt(1,dp.getProductId());
			pst.setString(2,dp.getBrandName());
			pst.setString(3,dp.getModel());
			pst.setDouble(4,dp.getPrice());
			pst.setDouble(5,dp.getDiscount());
			pst.setDouble(6,dp.getRebate());
			pst.setInt(7,dp.getQuantity());
			pst.setString(8,dp.getImageName());
			pst.setString(9,dp.getType());
			pst.setString(10,dp.getAccs().toString());
			pst.execute();
			pst.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void delProduct(int productId)
	{
		try
		{
			
			getConnection();
			String qq = "Delete from ProductDetails where productId=?; ";			
			pst=conn.prepareStatement(qq);
			pst.setInt(1,productId);
			
			pst.execute();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public Boolean insertOrder(ArrayList<Cart> cartList) {
		Boolean insertSuccess = false;
		java.sql.Statement stm = null;
		try {
			getConnection();

			String insertIntoCustomerOrdersQuery = "INSERT INTO CustomerOrders(orderId,brandName,model,price,userId,buyWarranty,deliveryDateTime, productId,quantity) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);";
			int id=0;
			pst = conn.prepareStatement(insertIntoCustomerOrdersQuery);
			for (Cart cart : cartList) {
				pst.setInt(1, cart.getOrderId());
				pst.setString(2, cart.getBrandName());
				pst.setString(3, cart.getModel());
				pst.setDouble(4, cart.getPrice());
				pst.setString(5, cart.getUserId());
				pst.setBoolean(6, cart.isBuyWarranty());
				pst.setDate(7, new java.sql.Date(cart.getDeliveryDate().getTime()));
				pst.setInt(8, cart.getProductId());
				id= cart.getProductId();
				pst.setInt(9, cart.getQuantity());
				 pst.execute();
				 insertSuccess = true;
			}
			
			pst.close();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			stm.executeQuery(" select productid from productdetails where productId = "	+id );
			ResultSet rst = stm.getResultSet();
			int getid =0;
			while( rst.next())
			{
				getid=rst.getInt(1);			
			}
			System.out.println(" get id is "+getid);
			String modifyQuantity;
			if(id ==  getid){
			modifyQuantity = "update productdetails p right join customerorders c on p.productId=c.productId set p.quantity=p.quantity-c.quantity;";
			}
			else{
			modifyQuantity = "update accessory p right join customerorders c on p.productId=c.productId set p.accquantity=p.accquantity-c.quantity;";
			
			}
			ps =conn.prepareStatement(modifyQuantity);
			ps.execute();
			ps.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return insertSuccess;

	}

	
	public ArrayList<Product> getProduct()
	{
		Product sp = null;
		java.sql.Statement stm = null;
		java.sql.Statement sm = null;
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Accessory> access = new ArrayList<Accessory>();
		String brandName, model,imageName,type,accessories;
		int productId, quantity;
		float price, discount,rebate;
		try
		{
			getConnection();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sm=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			stm.executeQuery ("SELECT * FROM productdetails order by productId");
			ResultSet rst = stm.getResultSet();
			 while(rst.next()){

                sp = new Product();
				sp.setProductId(rst.getInt(1));
                sp.setBrandName(rst.getString(2));
				sp.setModel(rst.getString(3));
                sp.setPrice(rst.getFloat(4));
				sp.setDiscount(rst.getFloat(5));
				sp.setRebate(rst.getFloat(6));
                sp.setQuantity(rst.getInt(7));
				sp.setImageName(rst.getString(8));
				sp.setType(rst.getString(9));
				String accsStr = rst.getString(10);
		
				List<Accessory> accsList = new ArrayList<Accessory>();
				List<String> accs = Arrays.asList(accsStr.replace("[", "").replace("]", "").split(","));
				
				for (String s : accs) {
					if (s.trim().length() == 0)
						continue;
					
					System.out.println(s);
					String[] accAttributes = s.split("\\|");
					for (int i = 0; i < accAttributes.length; i++) {
						System.out.println(accAttributes[i].length() + " - " + accAttributes[i]);
					}
					Accessory a = new Accessory(Integer.parseInt(accAttributes[0].trim()), accAttributes[1].trim(), Double.parseDouble(accAttributes[2].trim()), Double.parseDouble(accAttributes[3].trim()), accAttributes[4].trim(), accAttributes[5].trim(), Integer.parseInt(accAttributes[6].trim()));
					accsList.add(a);
				}
				
			 sp.setAccs((ArrayList)accsList);
		products.add(sp);
		}	
			} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return products;
	}
	
	public ArrayList<Accessory> getAccessory()
	{
		Accessory sp = null;
		java.sql.Statement stm = null;
		ArrayList<Accessory> access = new ArrayList<Accessory>();
		try
		{
			getConnection();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			stm.executeQuery ("SELECT * FROM accessory order by productId");
			ResultSet rst = stm.getResultSet();
			 while(rst.next()){

                sp = new Accessory();
				sp.setProductId(rst.getInt(1));
                sp.setDescription(rst.getString(2));
                sp.setPrice(rst.getFloat(3));
				sp.setDiscount(rst.getFloat(4));
				sp.setType(rst.getString(5));
				sp.setImageName(rst.getString(6));
                sp.setQuantity(rst.getInt(7));
				access.add(sp);
		}	
			} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return access;
	}
	public ArrayList<Accessory> getAccSales()
	{
		Accessory sp = null;
		java.sql.Statement stm = null;
		ArrayList<Accessory> access = new ArrayList<Accessory>();
		try
		{
			getConnection();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			stm.executeQuery ("SELECT * FROM accessory order by productId");
			ResultSet rst = stm.getResultSet();
			 while(rst.next()){

                sp = new Accessory();
				sp.setProductId(rst.getInt(1));
                sp.setDescription(rst.getString(2));
                sp.setPrice(rst.getFloat(3));
				sp.setDiscount(rst.getFloat(4));
				sp.setType(rst.getString(5));
				sp.setImageName(rst.getString(6));
                sp.setQuantity(rst.getInt(7));
				access.add(sp);
		}	
			} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return access;
	}
	
	
	public int generateNewOrderId() {
		java.sql.Statement stm = null;
		int orderId;
		int newOrderId = 0;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getMaxOrderId = "SELECT MAX(orderId) AS newId FROM CustomerOrders;";
			ResultSet rst;
			rst = stm.executeQuery(getMaxOrderId);
			if (!rst.next()) {
				orderId = 0;
			} else {
				orderId = rst.getInt("newId");
			}
			newOrderId = orderId + 1;

			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}
		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}
		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, newOrderId + " returned value");
		return newOrderId;
	}
	

	public int deleteOrder(int order_id) {
		int deleteCount=0;
		java.sql.Statement stm = null;
		java.sql.Statement sm = null;
		java.sql.Statement sm1 = null;
		java.sql.Statement sm2 = null;
		int id=0,quant=0;
		try {
			getConnection();
			sm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rst,rs;
			String getid = "select productid from customerorders where orderId =" +order_id;
			rst = sm.executeQuery(getid);
			while(rst.next())
			{
				id= rst.getInt(1);
			}
			sm1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String quantity = "select quantity from customerorders where orderId =" + order_id + " and productId ="+id ;
			rs= sm1.executeQuery(quantity);
			rs.next();
			quant = rs.getInt(1);
			String updateQuantity = "update productdetails set quantity = quantity + "+quant+" where productId = " +id;
			
			sm2 = conn.createStatement();
			sm2.execute(updateQuantity);
			String deleteOrderQuery = "Delete from CustomerOrders where orderId =" + order_id;

			stm = conn.createStatement();
			deleteCount = stm.executeUpdate(deleteOrderQuery);
			sm.close();
			sm1.close();
			sm2.close();
			//stm.close();
			//conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return deleteCount;
	}

	public ArrayList<Cart> readAllOrders() {
		ArrayList<Cart> cartList = null;
		java.sql.Statement stm = null;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getOrderData = "SELECT orderId, brandName, model, price, userId, buyWarranty, deliveryDateTime,productId FROM CustomerOrders";

			ResultSet rst;
			rst = stm.executeQuery(getOrderData);
			cartList = new ArrayList<>();
			while (rst.next()) {
				Cart cart = new Cart(rst.getString("brandName"), rst.getString("model"), rst.getDouble("price"),
						rst.getInt("orderId"), rst.getString("userId"), rst.getBoolean("buyWarranty"),
						rst.getDate("deliveryDateTime"), rst.getInt("productId"));
				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
						cart.toString() + "read from db");
				cartList.add(cart);
			}

			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return cartList;
	}

	public void updateOrder(int order_id, int product_id) {

		java.sql.Statement stm = null;
		try {
			getConnection();

			String updateOrderQuery = "Delete from CustomerOrders where orderId =" + order_id + " and productId ="
					+ product_id ;

			stm = conn.createStatement();
			stm.execute(updateOrderQuery);
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
					" deleted item from an order - db");
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

	}
	
	public ArrayList<Product> getSales()
	{
		Product sp = null;
		java.sql.Statement stm = null;
		java.sql.Statement sm = null;
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Accessory> access = new ArrayList<Accessory>();
		String brandName, model,imageName,type,accessories;
		int productId, quantity;
		float price, discount,rebate;
		try
		{
			getConnection();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sm=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			stm.executeQuery ("select * from productdetails where discount != 0 ;");
			ResultSet rst = stm.getResultSet();
			 while(rst.next()){

                sp = new Product();
				sp.setProductId(rst.getInt(1));
                sp.setBrandName(rst.getString(2));
				sp.setModel(rst.getString(3));
                sp.setPrice(rst.getFloat(4));
				sp.setDiscount(rst.getFloat(5));
				sp.setRebate(rst.getFloat(6));
                sp.setQuantity(rst.getInt(7));
				sp.setImageName(rst.getString(8));
				sp.setType(rst.getString(9));
				String accsStr = rst.getString(10);
		
				List<Accessory> accsList = new ArrayList<Accessory>();
				List<String> accs = Arrays.asList(accsStr.replace("[", "").replace("]", "").split(","));
				
				for (String s : accs) {
					if (s.trim().length() == 0)
						continue;
					
					System.out.println(s);
					String[] accAttributes = s.split("\\|");
					for (int i = 0; i < accAttributes.length; i++) {
						System.out.println(accAttributes[i].length() + " - " + accAttributes[i]);
					}
					Accessory a = new Accessory(Integer.parseInt(accAttributes[0].trim()), accAttributes[1].trim(), Double.parseDouble(accAttributes[2].trim()), Double.parseDouble(accAttributes[3].trim()), accAttributes[4].trim(), accAttributes[5].trim(), Integer.parseInt(accAttributes[6].trim()));
					accsList.add(a);
				}
				
			 sp.setAccs((ArrayList)accsList);
		products.add(sp);
		}	
			} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return products;
	}
	public ArrayList<Product> getRebates()
	{
		Product sp = null;
		java.sql.Statement stm = null;
		java.sql.Statement sm = null;
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Accessory> access = new ArrayList<Accessory>();
		String brandName, model,imageName,type,accessories;
		int productId, quantity;
		float price, discount,rebate;
		try
		{
			getConnection();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sm=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			stm.executeQuery ("select * from productdetails where rebate != 0 ;");
			ResultSet rst = stm.getResultSet();
			 while(rst.next()){

                sp = new Product();
				sp.setProductId(rst.getInt(1));
                sp.setBrandName(rst.getString(2));
				sp.setModel(rst.getString(3));
                sp.setPrice(rst.getFloat(4));
				sp.setDiscount(rst.getFloat(5));
				sp.setRebate(rst.getFloat(6));
                sp.setQuantity(rst.getInt(7));
				sp.setImageName(rst.getString(8));
				sp.setType(rst.getString(9));
				String accsStr = rst.getString(10);
		
				List<Accessory> accsList = new ArrayList<Accessory>();
				List<String> accs = Arrays.asList(accsStr.replace("[", "").replace("]", "").split(","));
				
				for (String s : accs) {
					if (s.trim().length() == 0)
						continue;
					
					System.out.println(s);
					String[] accAttributes = s.split("\\|");
					for (int i = 0; i < accAttributes.length; i++) {
						System.out.println(accAttributes[i].length() + " - " + accAttributes[i]);
					}
					Accessory a = new Accessory(Integer.parseInt(accAttributes[0].trim()), accAttributes[1].trim(), Double.parseDouble(accAttributes[2].trim()), Double.parseDouble(accAttributes[3].trim()), accAttributes[4].trim(), accAttributes[5].trim(), Integer.parseInt(accAttributes[6].trim()));
					accsList.add(a);
				}
				
			 sp.setAccs((ArrayList)accsList);
		products.add(sp);
		}	
			} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return products;
	}
	public ArrayList<Order> getReport() {
		ArrayList<Order> orderList = new ArrayList<Order>();
		java.sql.Statement stm = null;
		Order sp = null;
		try {
			getConnection();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stm.executeQuery( "select model, price, count(quantity) as amount, sum(price) as total from customerorders group by productId;");
			ResultSet rst = stm.getResultSet();
			 while(rst.next()){
				sp= new Order();
				sp.setModel(rst.getString(1));
                sp.setPrice(rst.getFloat(2));
                sp.setQuantity(rst.getInt(3));
				System.out.println(rst.getInt(3));
				sp.setSales(rst.getFloat(4));
				sp.setDate(null);
			orderList.add(sp);
			System.out.println("cart itmes"+sp);
			}
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return orderList;
	}
	public ArrayList<Order> getDailySales() {
		ArrayList<Order> orderList = new ArrayList<Order>();
		java.sql.Statement stm = null;
		Order sp = null;
		try {
			getConnection();
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stm.executeQuery( "select deliveryDateTime as daily, sum(price) as Total from customerorders group by deliveryDateTime ;");
			ResultSet rst = stm.getResultSet();
			 while(rst.next()){
				sp= new Order();
				sp.setModel("");
				sp.setDate(rst.getDate(1));
                sp.setPrice(rst.getFloat(2));
                sp.setQuantity(0);
				sp.setSales(0.0f);
				
			orderList.add(sp);
			System.out.println("cart itmes"+sp);
			}
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return orderList;
	}
	
	public Boolean resetProducts() {
		Boolean resetSuccess = true;

		java.sql.Statement stm = null;
		try {
			getConnection();

			String resetProductQuery = "Delete from productdetails";

			stm = conn.createStatement();
			stm.execute(resetProductQuery);
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
			resetSuccess+" deleted from db");
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
			resetSuccess=false;
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return resetSuccess;
	}
	public HashMap<String,Product> readProducts(){
		Product sp = null;
		HashMap<String, Product> productList = new HashMap<>();
		java.sql.Statement stm = null;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getProductData = "SELECT productId, brandName, model, price, discount, rebate, quantity,imageName, type, accs FROM productdetails";

			ResultSet rst;
			rst = stm.executeQuery(getProductData);
			productList = new HashMap<>();
			while(rst.next()){

                sp = new Product();
				sp.setProductId(rst.getInt(1));
                sp.setBrandName(rst.getString(2));
				sp.setModel(rst.getString(3));
                sp.setPrice(rst.getFloat(4));
				sp.setDiscount(rst.getFloat(5));
				sp.setRebate(rst.getFloat(6));
                sp.setQuantity(rst.getInt(7));
				sp.setImageName(rst.getString(8));
				sp.setType(rst.getString(9));
				String accsStr = rst.getString(10);
		
				List<Accessory> accsList = new ArrayList<Accessory>();
				List<String> accs = Arrays.asList(accsStr.replace("[", "").replace("]", "").split(","));
				
				for (String s : accs) {
					if (s.trim().length() == 0)
						continue;
					
					//System.out.println(s);
					String[] accAttributes = s.split("\\|");
					for (int i = 0; i < accAttributes.length; i++) {
						System.out.println(accAttributes[i].length() + " - " + accAttributes[i]);
					}
					Accessory a = new Accessory(Integer.parseInt(accAttributes[0].trim()), accAttributes[1].trim(), Double.parseDouble(accAttributes[2].trim()), Double.parseDouble(accAttributes[3].trim()), accAttributes[4].trim(), accAttributes[5].trim(), Integer.parseInt(accAttributes[6].trim()));
					accsList.add(a);
				}
				
			 sp.setAccs((ArrayList)accsList);
		productList.put(sp.getModel(), sp);
		}	
			} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage()+"error while reading from db",
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return productList;
	}

}