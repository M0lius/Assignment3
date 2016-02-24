package Assignment3;

public class Clothing extends Item {

	public Clothing(String name, double price, int weight, int quantity) {
		super(name, price, weight, quantity);
		// TODO Auto-generated constructor stub
	}
	
	//*******
	public double getSalesTax() {
		// sales tax rate is 10% for clothing and electronics
		return (this.getPrice() * 0.10);
	}
	
		// Clothing special attributes:
	// premium shipping is not available

}
