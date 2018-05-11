

import java.io.File;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.*;
import java.io.FileNotFoundException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.logging.*;

public class SaxParserUpdate{

   public static void writeToxml(ArrayList<Product> products){
		ArrayList<Accessory> acc ;
      Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "Entering write method");
         
      try {

        String fullPath = "C:/apache-tomcat-7.0.34/webapps/csj1/xml/ProductCatalog.xml";

        //Empty the file
        PrintWriter writer = new PrintWriter(new File(fullPath));
        writer.print("");
        writer.close();

        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "Emptied xml file");

         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();
           // write the content into xml file
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         
         StreamResult result = new StreamResult(new File(fullPath));

         Element rootElement = doc.createElement("ProductCatalog");
         doc.appendChild(rootElement);
         for(Product product:products){
			 Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "writing product to new xml" + product.getModel());
         // root elements
		 Element staff = doc.createElement("product");
         rootElement.appendChild(staff);
		 acc=product.getAccs();
		 Attr attr = doc.createAttribute("productId");
         attr.setValue(Integer.toString(product.getProductId()));
         staff.setAttributeNode(attr);
		 Element firstname = doc.createElement("brandName");
         firstname.appendChild(doc.createTextNode(product.getBrandName()));
         staff.appendChild(firstname);

         Element lastname = doc.createElement("model");
         lastname.appendChild(doc.createTextNode(product.getModel()));
         staff.appendChild(lastname);

         Element price = doc.createElement("price");
         price.appendChild(doc.createTextNode(Double.toString(product.getPrice())));
         staff.appendChild(price);

         Element discount = doc.createElement("discount");
         discount.appendChild(doc.createTextNode(Double.toString(product.getDiscount())));
         staff.appendChild(discount);

         Element rebate = doc.createElement("rebate");
         rebate.appendChild(doc.createTextNode(Double.toString(product.getRebate())));
         staff.appendChild(rebate);

         Element quantity = doc.createElement("quantity");
         quantity.appendChild(doc.createTextNode(Integer.toString(product.getQuantity())));
         staff.appendChild(quantity);

         Element imageName = doc.createElement("imageName");
         imageName.appendChild(doc.createTextNode(product.getImageName()));
         staff.appendChild(imageName);

         Element type = doc.createElement("type");
         type.appendChild(doc.createTextNode(product.getType()));
         staff.appendChild(type);
		
		 
		 if(acc!=null)
		 {
		 for(Accessory accessories:acc){
        
		Element staff1=doc.createElement("accessory");
		staff.appendChild(staff1);
         
		Attr attr1 = doc.createAttribute("productId");
         attr1.setValue(Integer.toString(accessories.getProductId()));
         staff1.setAttributeNode(attr1);
		 
		 Element accprice=doc.createElement("accprice");
		 accprice.appendChild(doc.createTextNode(Double.toString(accessories.getPrice())));
		 staff1.appendChild(accprice);
		 
	   	 Element accdiscount=doc.createElement("accdiscount");
		 accdiscount.appendChild(doc.createTextNode(Double.toString(accessories.getDiscount())));
		 staff1.appendChild(accdiscount);
		 
		 Element description = doc.createElement("description");
         description.appendChild(doc.createTextNode(accessories.getDescription()));
         staff1.appendChild(description);
		 
		 Element accimageName = doc.createElement("accimageName");
         accimageName.appendChild(doc.createTextNode(accessories.getImageName()));
         staff1.appendChild(accimageName);
		 
		 Element acctype = doc.createElement("acctype");
         acctype.appendChild(doc.createTextNode(accessories.getType()));
         staff1.appendChild(acctype);
		 
		 Element accquantity = doc.createElement("accquantity");
         accquantity.appendChild(doc.createTextNode(Integer.toString(accessories.getQuantity())));
         staff1.appendChild(accquantity);
		 
		
		 
         
         }}
     	 
       
	  }transformer.transform(source, result);
   }
         

       catch (ParserConfigurationException pce) {
         pce.printStackTrace();
      } catch (TransformerException tfe) {
         tfe.printStackTrace();
      }
      catch (FileNotFoundException fe){
        fe.printStackTrace();
      }
      catch(Exception e){
        e.printStackTrace();
      }
}
}
