package Assignment3;

import java.util.Arrays;

public class Electronics extends Item {

	// all states arrays and related methods in Electronics because no other type is state-dependent
	static String[] stateCodes = new String[]{"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", 
	"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", 
	"ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

	static String[] nonTaxableStates = new String[]{"TX", "NM", "VA", "AZ", "AK"};
	
	public boolean fragile;
	
	public Electronics(String name, double price, double weight, int quantity, String fragileString) {
		super(name, price, weight, quantity);
		// TODO Auto-generated constructor stub
		
		if(fragileString == "F")
		{
			fragile = true;
		}
		else if(fragileString == "NF")
		{
			fragile = false;
		}
	}

	public boolean isFragile() {
		return fragile;
	}
	
	// state specific methods
	public static boolean taxableState(String stateIn)
	{
		// assumes valid State input 
		if( Arrays.asList(nonTaxableStates).contains(stateIn.toUpperCase()) )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean validState(String stateIn)
	{
		if( Arrays.asList(stateCodes).contains(stateIn.toUpperCase()) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
