
package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ShoppingCart
{
	//*******
	static String[] operations = new String[]{"INSERT", "DELETE", "SEARCH", "UPDATE", "PRINT"};
	static String[] categories = new String[]{"GROCERIES", "ELECTRONICS", "CLOTHING"};
	
	//*******
	static List<Groceries>   groceryList	 = new ArrayList<Groceries>();
	static List<Electronics> electronicList = new ArrayList<Electronics>();
	static List<Clothing>    clothingList	 = new ArrayList<Clothing>();
	
	
	/*
	 * <operation> <category> <name> <price> <quantity> <weight> <optional field1> <optional field2>
	 * 
	 * Examples:
	 * insert clothing shirt 20.50 1 1
	 * insert electronics PS3 300 1 5.2 F NM -- must indicate F/NF and State
	 * insert groceries cabbage 2.00 5 1 NP  -- must indicate P/NP
	 */
	
	static void insert( String operation, String category, String name, double price, int quantity, 
			double weight, String opt1, String opt2 ) {
		
		switch( operation.toUpperCase() ) {
		
		//public Groceries(String name, double price, double weight, int quantity, String perishableString) {

		//public Electronics(String name, double price, double weight, int quantity, String fragileString) {

		//public Clothing(String name, double price, double weight, int quantity) {
		
		case "GROCERIES": 	ShoppingCart.groceryList.add( new Groceries(name, price, weight, quantity, opt1) ); break;
		//case "ELECTRONICS": ShoppingCart.electronicList.add( new Electronics(name, price, weight, quantity, opt1, opt2) ); break;
		case "CLOTHING":	ShoppingCart.clothingList.add( new Clothing(name, price, weight, quantity) ); break;
		
		}
		
	}
	
	//*******
	static void print() {
		
		/* Print will print the contents of the shopping cart 
		 * in order by name, showing all attribute values for each object as well as the total 
		 * charges for each. This is followed by the total charges for entire the shopping cart. 
		 * Output of the shopping cart should be to the standard output stream (screen) and should be 
		 * appropriately formatted and labeled for readability.
		 * */
	}
	
	//*******
	static void update( String name, int quantity ) {
		// updates the quantity field for the first occurrence matching name
		// output the name and new quantity value for that item on the screen
	}
	
	//*******
	static void delete( String name ) {
		// deletes all entities with a matching name (case sensitive)
		// output to the screen the number of objects deleted
	}
	
	//********
	static void search( String name ) { // names are case sensitive
		// searches for all objects with the same name and outputs the number of objects found on the screen
	}
	
	//********
	static boolean validOperation( String operationIn ) {
		if( Arrays.asList(operations).contains( operationIn.toUpperCase() ) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//*******
	static boolean validCategory( String categoryIn ) {
		if( Arrays.asList(categories).contains( categoryIn.toUpperCase() ) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
}