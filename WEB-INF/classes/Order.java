import java.util.*;
 
public class Order implements java.io.Serializable{

	private String model;
	private float price;
	private int quantity;
	private float sales;
	private Date date;
	public Order(){

	}
	public Order(String model, float price, int quantity, float sales,Date d ){
		
		this.model = model;
		this.price = price;
		this.quantity = quantity;
		this.sales = sales;

	}

	public String getModel(){
		return model;
	}
	public void setModel(String model){
		this.model = model;
	}
	public float getPrice(){
		return price;
	}
	public void setPrice(float price){
		this.price = price;
	}
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public float getSales(){
		return sales;
	}
	public void setSales(float price){
		this.sales = price;
	}
	public Date getDate(){
		return this.date;
	}

	public void setDate(Date dt){
		this.date=dt;
	}
	}



