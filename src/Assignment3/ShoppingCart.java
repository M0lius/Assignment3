
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
	
	//*****************************************************************************
	static void insert( String category, String name, double price, int quantity, 
			int weight, String opt1, String opt2 ) {
		
		switch( category.toUpperCase() ) {
			case "GROCERIES": 	ShoppingCart.groceryList.add( new Groceries(name, price, weight, quantity, opt1) ); 		   break;
			case "ELECTRONICS": ShoppingCart.electronicList.add( new Electronics(name, price, weight, quantity, opt1, opt2) ); break;
			case "CLOTHING":	ShoppingCart.clothingList.add( new Clothing(name, price, weight, quantity) ); 				   break;
		}
	}
	
	//*******************
	static void print() {
		
		/* Print will print the contents of the shopping cart 
		 * in order by name, showing all attribute values for each object as well as the total 
		 * charges for each. This is followed by the total charges for the entire shopping cart. 
		 * Output of the shopping cart should be to the standard output stream (screen) and should be 
		 * appropriately formatted and labeled for readability.
		 * */
		
		int netPrice = 0;
	
		int grocerySize    = ShoppingCart.groceryList.size();
		int electronicSize = ShoppingCart.electronicList.size();
		int clothingSize   = ShoppingCart.clothingList.size();
		
		int electronicRange = grocerySize + electronicSize;
		
		int shoppingCartLength = grocerySize + electronicSize + clothingSize;
			
		for( int x = 0; x < shoppingCartLength; x++ ) {
			
			// quantity, name, price after tax and shipping
			// at the end: total shopping cart price
			
			String name  = "";
			double price = 0;
			int quantity = 0;
			
			if( x < grocerySize ) { 
				// Groceries
				name 	 = ShoppingCart.groceryList.get(x).getName();
				quantity = ShoppingCart.groceryList.get(x).getQuantity();		
				price	 = ShoppingCart.groceryList.get(x).getPrice()*quantity 
							+ ShoppingCart.groceryList.get(x).getFittingShippingCost()*quantity;
			}
			else if ( (x >= grocerySize) && (x < electronicRange) ) {
				// Electronics
				String state 	= ShoppingCart.electronicList.get(x).getState();
				name 	 = ShoppingCart.electronicList.get(x).getName();
				quantity = ShoppingCart.electronicList.get(x).getQuantity();
				price	 = ShoppingCart.electronicList.get(x).getPrice()*quantity
							+ ShoppingCart.electronicList.get(x).getFittingShippingCost()*quantity
							+ ShoppingCart.electronicList.get(x).getSalesTax(state)*quantity;
			}
			else if ( (x >= electronicRange) && (x < shoppingCartLength) ) {
				// Clothing
				name 	 = ShoppingCart.clothingList.get(x).getName();
				quantity = ShoppingCart.clothingList.get(x).getQuantity();
				price	 = ShoppingCart.clothingList.get(x).getPrice()*quantity
							+ ShoppingCart.clothingList.get(x).getStandardShippingCost()*quantity
							+ ShoppingCart.clothingList.get(x).getSalesTax()*quantity;
			}
			
			netPrice += price;
			String items = (quantity > 1) ? " items " : " item ";
			
			System.out.println("The " + quantity + " " + name + items + "costs $" + price + ", including shipping and tax.");
		}
		
		System.out.println("The Shopping-Cart items cost $" + netPrice + " total.");
	}
	
	//*************************************************
	static void update( String nameIn, int quantity ) {
		// updates the quantity field for the first occurrence matching name
		// output the name and new quantity value for that item on the screen
		
		try {	// Groceries
			int updateAt = ShoppingCart.groceryNameFind( nameIn, 0, ShoppingCart.groceryList.size() );
			ShoppingCart.groceryList.get( updateAt ).setQuantity( quantity );
			System.out.println("Updated first occurance of item " + nameIn + " to have quantity " 
						+ ShoppingCart.groceryList.get( updateAt ).getQuantity() + ".");
			return;
		}
		catch(java.lang.IndexOutOfBoundsException e) {} // no grocery with that name
		
		try {	// Electronics
			int updateAt = ShoppingCart.electronicNameFind( nameIn, 0, ShoppingCart.electronicList.size() );
			ShoppingCart.electronicList.get( updateAt ).setQuantity( quantity );
			System.out.println("Updated first occurance of item " + nameIn + " to have quantity " 
						+ ShoppingCart.electronicList.get( updateAt ).getQuantity() + ".");
			return;
		}
		catch(java.lang.IndexOutOfBoundsException e) {}
		
		try {	// Clothing
			int updateAt = ShoppingCart.clothingNameFind( nameIn, 0, ShoppingCart.clothingList.size() );
			ShoppingCart.clothingList.get( updateAt ).setQuantity( quantity );
			System.out.println("Updated first occurance of item " + nameIn + " to have quantity " 
						+ ShoppingCart.clothingList.get( updateAt ).getQuantity() + ".");
			return;
		}
		catch(java.lang.IndexOutOfBoundsException e) {} // no grocery with that name
	}
	
	//***********************************
	static void delete( String nameIn ) {
		// deletes all entities with a matching name (case sensitive)
		// output to the screen the number of objects deleted
		
		int deletedItemsCount = 0;

		int numGroceryMatches    = ShoppingCart.numGroceryNameMatches(    nameIn);
		int numElectronicMatches = ShoppingCart.numElectronicNameMatches( nameIn);
		int numClothingMatches   = ShoppingCart.numGroceryNameMatches(    nameIn);
		
		int lastMatchPoint		= 0;
		
		// Groceries
		for( int x = 0; x < numGroceryMatches; x++ ) {
			
			int deleteAt = ShoppingCart.groceryNameFind(nameIn, lastMatchPoint, ShoppingCart.groceryList.size() );
			
			if( deleteAt != -1 ) {
				lastMatchPoint = deleteAt;
				ShoppingCart.groceryList.remove( deleteAt );
				deletedItemsCount++;
			}
		}
		
		// Electronics
		for( int x = 0; x < numElectronicMatches; x++ ) {
			
			int deleteAt = ShoppingCart.groceryNameFind(nameIn, lastMatchPoint, ShoppingCart.electronicList.size() );
			
			if( deleteAt != -1 ) {
				lastMatchPoint = deleteAt;
				ShoppingCart.electronicList.remove( deleteAt );
				deletedItemsCount++;
			}
		}
		
		// Clothing
		for( int x = 0; x < numClothingMatches; x++ ) {
			
			int deleteAt = ShoppingCart.groceryNameFind(nameIn, lastMatchPoint, ShoppingCart.clothingList.size() );
			
			if( deleteAt != -1 ) {
				lastMatchPoint = deleteAt;
				ShoppingCart.clothingList.remove( deleteAt );
				deletedItemsCount++;
			}
		}


		System.out.println("Deleted " + deletedItemsCount + " items with the name " + nameIn + ".");
	}
	
	//***********************************
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