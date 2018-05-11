import java.util.ArrayList;

public interface IMySqlDataStoreUtilities {
	
	public Boolean addPerson(Person person);
	

	public ArrayList<Person> readPerson();

	public int generateNewOrderId();

	public Boolean insertOrder(ArrayList<Cart> cart);
	public Boolean insertProduct(ArrayList<Product> product);
	public void updateOrder(int order_id, int product_id);
	public int deleteOrder(int order_Id);
	// public Cart readOrder(int orderId);
	public ArrayList<Cart> readAllOrders();
	public Boolean modProduct(Product pid);
	public void delProduct(int id);
	public Boolean addProduct(Product dp);
	public Boolean resetProducts();


}
