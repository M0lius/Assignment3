package Assignment3;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?

// You will need a constructor (Why?). Create it here.
	public String name;
	public double price;
	public double weight;
	
	public int quantity;
	
	
	float calculatePrice () 
	{
		float final_price = 0;
		// Insert price calculation here
		return final_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getWeight() {
		return weight;
	}
	
	public double getStandardShippingCost() {
		
		// Standard shipping cost is calculated: (20*(weight)) * quantity;
		return ( (20*this.getWeight()) * this.getQuantity() );
	}

	public Item(String name, double price, double weight, int quantity) {
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.quantity = quantity;
	}

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
		System.out.print("Name: " + name + " Weight: " + weight + "\nPrice: " + price);
	}

}
