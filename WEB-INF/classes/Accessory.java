import java.util.*;
import java.util.logging.*;
import java.io.Serializable;

public class Accessory implements Serializable {

    private int productId;
    private String description;
    private double price;
    private double discount;
    private String imageName;
    private String type;
	private int quantity;
    public Accessory(){

    }

    public Accessory(int productId, String description, double price, double discount, String accType, String accimageName,int quantity) {
        
        this.productId = productId;
		this.description=description;
        this.price = price;
        this.discount = discount;
        this.imageName = accimageName;
        this.type = accType;
		this.quantity= quantity;

    }

	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description=description;
	}
	
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
  
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
	public void setQuantity(int q)
	{
		this.quantity=q;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	@Override
	/*public String toString() {
		return  String.valueOf("productId" + getProductId() + "|"+ "description"+ getDescription() + "|" +"price"+ getPrice() + "|" +"discount"+ getDiscount() + "|" +"accTtype"+ getType() + "|" + "accimageName"+getImageName()) ;
	}*/
	public String toString() {
		return  String.valueOf( getProductId() + "|"+  getDescription() + "|" + getPrice() + "|" + getDiscount() + "|" + getType() + "|" +getImageName() + "|" +getQuantity()) ;
	}

    public  void addNewProduct(Product product){
        String fullPath = "C:/apache-tomcat-7.0.34/webapps/csj1/xml/ProductCatalog.xml";

        SaxParserUtility utility = new SaxParserUtility(fullPath);

        ArrayList<Product> products = utility.parseDocument();       

        products.add(product);

        SaxParserUpdate.writeToxml(products);
    }

    public boolean updateProduct(Product product){
        String fullPath = "C:/apache-tomcat-7.0.34/webapps/csj1/xml/ProductCatalog.xml";

        SaxParserUtility utility = new SaxParserUtility(fullPath);

        ArrayList<Product> products = utility.parseDocument();

        ArrayList<Product> newProducts = new ArrayList<Product>();

        for(Product productOld:products){
            if(productOld.getProductId()!=product.getProductId())
            {
                newProducts.add(productOld);
            }
        }

        newProducts.add(product);

        SaxParserUpdate.writeToxml(newProducts);
        return true;
    }

    public boolean deleteProduct(Product product){
        String fullPath = "C:/apache-tomcat-7.0.34/webapps/csj1/xml/ProductCatalog.xml";

        SaxParserUtility utility = new SaxParserUtility(fullPath);

        ArrayList<Product> products = utility.parseDocument();

        ArrayList<Product> newProducts = new ArrayList<Product>();

        for(Product productOld:products){
            if(productOld.getProductId()!=product.getProductId())
            {
                newProducts.add(productOld);
            }
        }

        SaxParserUpdate.writeToxml(newProducts);
        return true;
    }

}

