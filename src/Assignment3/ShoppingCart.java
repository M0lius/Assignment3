
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
	static List<Electronics> electronicList  = new ArrayList<Electronics>();
	static List<Clothing>    clothingList	 = new ArrayList<Clothing>();
	
	
	/*
	 * <operation> <category> <name> <price> <quantity> <weight> <optional field1> <optional field2>
	 * 
	 * Examples:
	 * insert clothing shirt 20.50 1 1
	 * insert electronics PS3 300 1 5.2 F NM -- must indicate F/NF and State
	 * insert groceries cabbage 2.00 5 1 NP  -- must indicate P/NP
	 */
	
	//*******
	static void insert( String operation, String category, String name, double price, int quantity, 
			int weight, String opt1, String opt2 ) {
		
		switch( operation.toUpperCase() ) {
			case "GROCERIES": 	ShoppingCart.groceryList.add( new Groceries(name, price, weight, quantity, opt1) ); 		   break;
			case "ELECTRONICS": ShoppingCart.electronicList.add( new Electronics(name, price, weight, quantity, opt1, opt2) ); break;
			case "CLOTHING":	ShoppingCart.clothingList.add( new Clothing(name, price, weight, quantity) ); 				   break;
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
		
		try {
			int updateAt = ShoppingCart.groceryNameFind(nameIn, searchStart, searchEnd)
		}
		catch(java.)
	}
	
	//*******
	static void delete( String nameIn ) {
		// deletes all entities with a matching name (case sensitive)
		// output to the screen the number of objects deleted
		
		int deletedItems = 0;
		

		try {
			int removeAt = ShoppingCart.groceryNameFind( nameIn );
			groceryList.remove( removeAt );
			deletedItems++;
		}
		catch(java.lang.IndexOutOfBoundsException e) {} // no grocery found  --- test these well
		
		try {
			int removeAt = ShoppingCart.electronicNameFind( nameIn );
			electronicList.remove( removeAt );
			deletedItems++;
		}
		catch(java.lang.IndexOutOfBoundsException e) {} // no electronic found
		
		try {
			int removeAt = ShoppingCart.clothingNameFind( nameIn );
			clothingList.remove( removeAt );
			deletedItems++;
		}
		catch(java.lang.IndexOutOfBoundsException e) {} // no grocery found
		
		
		System.out.println("Deleted " + deletedItems + " items with the name " + nameIn + ".");
	}
	
	//********
	static void search( String nameIn ) { 
		// names are case sensitive
		// searches for all objects with the same name and outputs the number of objects found on the screen
		
		int objectsNameFound = 0;
		
		objectsNameFound += ShoppingCart.numGroceryNameMatches(    nameIn );
		objectsNameFound += ShoppingCart.numElectronicNameMatches( nameIn );
		objectsNameFound += ShoppingCart.numGroceryNameMatches(    nameIn );
		
		System.out.println("Found " + objectsNameFound + " items with the name " + nameIn + ".");
	}
	
	
	
	//*************************************************
	static int numGroceryNameMatches( String nameIn ) {
		
		for( int x = 0; x < groceryList.size(); x++ ) {
			
			if( groceryList.get(x).name == nameIn ) {
				return x;
			}
		}
		
		return 0; // no grocery by that name
	}
	
	//********
	static int groceryNameFind( String nameIn, int searchStart, int searchEnd ) {
		
		// check .size() behavior
		for( int x = searchStart; x < searchEnd; x++ ) {
		
			if( groceryList.get(x).name == nameIn ) {
				return x;
			}
		}
		
		return -1; // no grocery found in range
	}
	
	//********
	static int numElectronicNameMatches( String nameIn ) {
		
		for( int x = 0; x < electronicList.size(); x++ ) {
			
			if( electronicList.get(x).name == nameIn ) {
				return x;
			}
		}
		
		return 0; // no electronic by that name
	}
	
	//*******
	static int electronicNameFind( String nameIn, int searchStart, int searchEnd ) {
		for( int x = searchStart; x < searchEnd; x++ ) {
					
			if( electronicList.get(x).name == nameIn ) {
				return x;
			}
		}
		
		return -1; // no electronic found in range
	}
	
	//********
	static int numClothingNameMathches( String nameIn ) {
		
		for( int x = 0; x < clothingList.size(); x++ ) {
			
			if( clothingList.get(x).name == nameIn ) {
				return x;
			}
		}
		
		return 0; // no clothing by that name
	}
	
	//*******
	static int clothingNameFind( String nameIn, int searchStart, int searchEnd ) {
		for( int x = searchStart; x < searchEnd; x++ ) {
					
			if( clothingList.get(x).name == nameIn ) {
				return x;
			}
		}
		
		return -1; // no clothing found in range
	}
	
	//***************************************************
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