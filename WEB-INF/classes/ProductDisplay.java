

import java.util.HashMap;
import java.util.*;

public class ProductDisplay extends Product implements java.io.Serializable {

	private HashMap<Integer,Product> products = new HashMap<Integer,Product>();

	public ProductDisplay(){

	}

	public ProductDisplay(int productId, String brandName, String model, double price, double discount, double rebate, int quantity, String imageName, String type,
	                      ArrayList<Accessory> accs) {
		super(productId, brandName, model, price, discount, rebate, quantity, imageName, type, accs);	
	}


	public boolean addProduct(Product product){
		super.addNewProduct(product);
		return true;		
	}

	public boolean updateProduct(Product product){
		super.updateProduct(product);
		return true;

	}

	public boolean deleteProduct(Product product){
		super.deleteProduct(product);
		return true;
	}


	public static HashMap<String,Product> buildBasicProductDisplayList(){

		String fullPath = "C:/apache-tomcat-7.0.34/webapps/csj1/xml/ProductCatalog.xml";
		SaxParserUtility utility = new SaxParserUtility(fullPath);
		ArrayList<Product> ProductDisplayList = new ArrayList<Product>();
		ProductDisplayList = utility.parseDocument();

		HashMap<String,Product> ProductDisplayer = new HashMap<String,Product>();

		for(Product product:ProductDisplayList){
			ProductDisplayer.put(product.getModel(), product);
			if (product.getAccs().size() > 0) {
				//System.out.println("printing " + product.getModel() + " product's accessories");
				for (Accessory acc : product.getAccs()) {
					//System.out.println(acc);
				}
				//System.out.println("---");
			}
		}

		return ProductDisplayer;
	}

	
}