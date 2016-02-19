package Assignment3;

public class Groceries extends Item {

	public boolean perishable;
	
	public Groceries(String name, double price, double weight, int quantity, String perishableString) {
		super(name, price, weight, quantity);
		// TODO Auto-generated constructor stub
		
		if(perishableString == "P")
		{
			perishable = true;
		}
		if(perishableString == "NP")
		{
			perishable = false;
		}
	}

	public boolean isPerishable() {
		return perishable;
	}

}
