/* NAMES: MARIO MOLINA
 * AND:   ETHAN MILLER
 * TA:    JO EGNER
 * ASSIGNMENT 3 :Shopping Cart One
 * */

package Assignment3;

import java.util.Arrays;

public class Electronics extends Item {

	// all states arrays and related methods in Electronics because no other type is state-dependent
	static String[] stateCodes = new String[]{"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", 
	"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", 
	"ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

	static String[] nonTaxableStates = new String[]{"TX", "NM", "VA", "AZ", "AK"};
	static String[] validFrailCode   = new String[]{"F", "NF"};
	
	
	public boolean fragile;
	String state;
	
	//*****
	//Constructor
	public Electronics(String name, double price, int weight, int quantity, String fragileString, String state) {
		super(name, price, weight, quantity);
		// TODO Auto-generated constructor stub
		
		// assuming valid state input
		this.setState( state );
		
		if(fragileString.toUpperCase() == "F")
		{
			fragile = true;
		}
		else if(fragileString.toUpperCase() == "NF")
		{
			fragile = false;
		}
	}

	//*****
	//Getters & Setters
	public boolean isFragile() {
		return fragile;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
		// Electronics special attributes:
	// fragile electronics require premium shipping
	// sales tax is 10% for clothing and electronics 
	// premium shipping is and additional 20% over standard shipping
	// standard shipping is (20*weight)*quantity

	//*******
	public double getSalesTax( String stateTax ) {
		
		if( Electronics.taxableState( stateTax ) ) {
			return ( this.getPrice() * 0.10 );
		}
		else if( !Electronics.taxableState( stateTax ) ) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
	//*******
	public double getFittingShippingCost() {
		
		// must take into account state, assuming input string is a valid state (error handling class)
		if( this.isFragile() ) {
			return this.getPremiumShippingCost();
		}
		else if( !this.isFragile() ) {
			return this.getStandardShippingCost();
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
	public static boolean taxableState(String stateIn) {
		// assumes valid State input 
		if( Arrays.asList(nonTaxableStates).contains(stateIn.toUpperCase()) ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//*******
	public static boolean validState(String stateIn) {
		if( Arrays.asList(stateCodes).contains(stateIn.toUpperCase()) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//*******
	public static boolean validFrailty(String frailIn) {
		if( Arrays.asList(validFrailCode).contains(frailIn) ) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
