
package Assignment3;

import java.util.Arrays;

class ShoppingCart
{
	static String[] operations = new String[]{"INSERT", "DELETE", "SEARCH", "UPDATE", "PRINT"};
	static String[] categories = new String[]{"CLOTHING", "ELECTRONICS", "GROCERIES"};
	

	
	// validOperation
	static boolean validOperation( String operationIn ) {
		if( Arrays.asList(operations).contains( operationIn.toUpperCase() ) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// validCategory
	static boolean validCategory( String categoryIn ) {
		if( Arrays.asList(categories).contains( categoryIn.toUpperCase() ) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
}