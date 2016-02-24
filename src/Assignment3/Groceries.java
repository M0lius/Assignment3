package Assignment3;

import java.util.Arrays;

public class Groceries extends Item {

	static String[] validPerishCode   = new String[]{"P", "NP"};
	public boolean perishable;
	
	public Groceries(String name, double price, int weight, int quantity, String perishableString) {
		super(name, price, weight, quantity);
		// TODO Auto-generated constructor stub
		
		if(perishableString.toUpperCase() == "P")
		{
			perishable = true;
		}
		if(perishableString.toUpperCase() == "NP")
		{
			perishable = false;
		}
	}

	public boolean isPerishable() {
		return perishable;
	}

		// Groceries special attributes:
	// perishable groceries require premium shipping
	// groceries incur no sales tax
	
	//*******
	public double getFittingShippingCost() {
		
		if( this.isPerishable() ) {
			return ( this.getPremiumShippingCost() );
		}
		else if( !this.isPerishable() ) {
			return ( this.getStandardShippingCost() );
		}
		else {
			return -1; // error case
		}
	}
	
	//*******
	public double getPremiumShippingCost() {
		// premium shipping is an additional 20% over the standard charge
		return ( this.getStandardShippingCost() * 1.20 );
	}
	
	//*******
	public static boolean validPerishability(String perishIn) {
		if( Arrays.asList(validPerishCode).contains(perishIn) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
