
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.util.logging.*;

public class SaxParserUtility extends DefaultHandler {
    Product product;
	Accessory acc;
    ArrayList<Product> products;
    String productXmlFileName;
    String elementValueRead;

   public SaxParserUtility(String productXmlFileName) {
        this.productXmlFileName = productXmlFileName;
        products = new ArrayList<Product>();
        
    }


    public ArrayList<Product> parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(productXmlFileName, this);

        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
            System.out.println(e);
            Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
            System.out.println(e);
             Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
        } catch (IOException e) {
            System.out.println("IO error");
            System.out.println(e);
             Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
        }

        return products;
    }

	public void loadDataStore() {
        MySqlDataStoreUtilities ms = new MySqlDataStoreUtilities();
		
		products= parseDocument();
		ms.insertProduct(products);
    }

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("product")) {
            product = new Product();
            product.setProductId(Integer.parseInt(attributes.getValue("productId")));            
        }
		
		if (elementName.equalsIgnoreCase("accessory")) {
            acc = new Accessory();
            acc.setProductId(Integer.parseInt(attributes.getValue("productId")));            
        }
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equalsIgnoreCase("product")) {
            products.add(product);
	    return;
        }
		
		if (element.equalsIgnoreCase("accessory")) {
            product.getAccs().add(acc);
	    return;
        }
		
		if (element.equalsIgnoreCase("accType")) {
            acc.setType(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("description")) {
            acc.setDescription(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("accprice")) {
            acc.setPrice(Double.parseDouble(elementValueRead));
	    return;
        }
		if (element.equalsIgnoreCase("accdiscount")) {
            acc.setDiscount(Double.parseDouble(elementValueRead));
	    return;
        }
		if (element.equalsIgnoreCase("accType")) {
            acc.setType(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("accimagename")) {
            acc.setImageName(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("accquantity")) {
            acc.setQuantity(Integer.parseInt(elementValueRead));
	    return;
        }
		
        if (element.equalsIgnoreCase("brandName")) {
            product.setBrandName(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("model")) {
            product.setModel(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("price")) {
            product.setPrice(Double.parseDouble(elementValueRead));
        return;
        }
        
        if(element.equalsIgnoreCase("discount")){
            product.setDiscount(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("rebate")){
            product.setRebate(Double.parseDouble(elementValueRead));
        return;
        }
        if(element.equalsIgnoreCase("quantity")){
            product.setQuantity(Integer.parseInt(elementValueRead));
        return;
        }
        if(element.equalsIgnoreCase("imageName")){
            product.setImageName(elementValueRead);
        return;
        }
        if(element.equalsIgnoreCase("type")){
            product.setType(elementValueRead);
        return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }
	
	public static void main(String[] args) {
		

		 SaxParserUtility sp = new SaxParserUtility("C:/apache-tomcat-7.0.34/webapps/csj1/xml/ProductCatalog.xml");

         sp.loadDataStore();

    }

	

}
