/* NAMES: MARIO MOLINA
 * AND:   ETHAN MILLER
 * TA:    JO EGNER
 * ASSIGNMENT 3 :Shopping Cart One
 * */

package Assignment3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.text.DecimalFormat;


public class A3Driver 
	{	  
	  public static void main(String[] args) 
	  {
		//Open file; file name specified in args (command line)
		  if (args.length != 1) 
	  		{
	  			System.err.println ("Error: Incorrect number of command line arguments");
	  			System.exit(-1);
	  		}
		  
		  try 
	  		{
	  			FileReader freader = new FileReader(args[0]);
	  			BufferedReader reader = new BufferedReader(freader);
	  			
	  			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
	  			{
	  			Doit(s);
	  			}
	  		} 
	  		catch (FileNotFoundException e) 
	  		{
	  			System.err.println ("Error: File not found. Exiting...");
	  			e.printStackTrace();
	  			System.exit(-1);
	  		} catch (IOException e) 
	  		{
	  			System.err.println ("Error: IO exception. Exiting...");
	  			e.printStackTrace();
	  			System.exit(-1);
	  		}
	  }	
	  
	  public static void Doit(String s){
		//Parse input, take appropriate actions.
		int skip = 0;
			
			String operation = Reader.GetOperation(s, skip);
			if (operation.equals(null) || operation.equals("") || !ShoppingCart.validOperation(operation)) {
				System.out.println("Invalid Operation");
				return;
			}
			skip += 1;
			
			if(operation.toUpperCase().equals("INSERT")){
				String category = Reader.GetCategory(s, skip);
				if (category.equals(null) || category.equals("")|| !ShoppingCart.validCategory(category)) {
					System.out.println("Invalid Category");
					return;
				}
				skip += 1;
				
				String name = Reader.GetName(s, skip);
				if (name.equals(null) || name.equals("")) {
					System.out.println("Invalid Name");
					return;
				}
				skip += 1;
				
				double price = Reader.GetPrice(s, skip);
				if (price <= 0) {
					System.out.println("Invalid Price");
					return;
				}
				skip += 1;
				
				int quantity = Reader.GetQuantity(s, skip);
				if (quantity < 0){
					System.out.println("Invalid Quantity");
					return;
				}
				skip += 1;
				
				int weight = Reader.GetWeight(s, skip);
				if(weight < 0){
					System.out.println("Invalid Weight");
					return;
				}
				skip += 1;
				
				String oField1 = "";
				String oField2 = "";
				if(category.toUpperCase().equals("GROCERIES")){
					oField1 = Reader.GetOField1(s, skip);
					if (oField1.equals(null) || oField1.equals("")) {
						System.out.println("Invalid Optional Field1");
						return;
					}
					skip += 1;
					
					oField2 = "";
					
				} else if(category.toUpperCase().equals("ELECTRONICS")){
					oField1 = Reader.GetOField1(s, skip);
					if (oField1.equals(null) || oField1.equals("")) {
						System.out.println("Invalid Optional Field1");
						return;
					}
					skip += 1;
					
					oField2 = Reader.GetOField2(s, skip);	
					if (oField2.equals(null) || oField2.equals("") || !Electronics.validState(oField2)) {
						System.out.println("Invalid State");
						return;
						
					}
					skip += 1;
				} 				
				
				System.out.printf("*%s*%s*%s*%.2f*%d*%d*%s*%s*\n", operation, category, name, price, quantity, weight, oField1, oField2);
				//TODO RUN INSERT
				
			} else if (operation.toUpperCase().equals("UPDATE")){
				String name = Reader.GetName(s, skip);
				if (name.equals(null) || name.equals("")) {
					System.out.println("Invalid Name");
					return;
				}
				skip += 1;
				
				int quantity = Reader.GetQuantity(s, skip);
				if (quantity < 0){
					System.out.println("Invalid Quantity");
					return;
				}
				skip += 1;
				
				System.out.printf("*%s*%s*%d*\n", operation, name, quantity);
				//TODO RUN UPDATE
				
			} else if(operation.toUpperCase().equals("PRINT")){
				System.out.println("Print!");
				//TODO run Print
				
			} else {
				String name = Reader.GetName(s, skip);
				if (name.equals(null) || name.equals("")) {
					System.out.println("Invalid Name");
					return;
				}
				skip += 1;
				
				System.out.printf("*%s*%s*\n", operation, name);
				if(operation.toUpperCase().equals("DELETE")){
					//TODO RUN DELETE
				}
				else{
					//TODO RUN SEARCH
				}
				
			}
		
				  
	  }
	
	  
	  /******************************************************************************
	  	* Class: Reader                                             				  *
	  	* Purpose: Reads a file and Parses it										  *
	  	* Returns: Nothing                                                               *
	  	******************************************************************************/

	  public static class Reader 
	  {
//	  	public static boolean Tail(String command, int skips){
//	  		int pointer = SkipInputs(command, skips);
//	  		char now = command.charAt(pointer);
//	  		if (now != ' '){
//	  			return true;
//	  		}
//	  		return false;
//	  	}
		  
		public static int SkipInputs(String command, int inputs)// assumes all previous inputs were valid and gathered 
		{//returns a pointer to the beginning of the next input after # skipped
			int pointer = 0;
	  		char now = command.charAt(pointer); //checks current char
	  		
	  		while (now == ' ' && pointer < (command.length() - 1)){ //skips initial space
	  			pointer += 1;
	  			now = command.charAt(pointer); //checks current char
	  		}
	  		
		  	for(int i = 0; i < inputs; i++){ //for inputs skips word and following spaces
		  		while (now != ' ' && pointer < (command.length() - 1)){//goes to word end
		  			pointer += 1;
		  			now = command.charAt(pointer);
		  		}
		  		while (now == ' ' && pointer < (command.length() - 1)){ //goes to word start
		  			pointer += 1;
		  			now = command.charAt(pointer); //checks current char
		  		}
		  	}	
			
			return pointer;
		}
		
	  	public static String GetOperation (String command, int skip) //reads for the Operation
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		char now = command.charAt(beginning); //checks current char

	  		while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
	  			end += 1;
	  			now = command.charAt(end);
	  		}
	  		if(now != ' '){end += 1;} //incase while loop left due to reaching end of string
	  		return command.substring(beginning, end);
	  		
	  	}
	  	public static String GetCategory (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  	}
		  	if(now != ' '){end += 1;} //incase while loop left due to reaching end of string
	  		return command.substring(beginning, end);

	  	}
	  	public static String GetName (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  	}
		  	if(now != ' '){end += 1;} //incase while loop left due to reaching end of string
	  		return command.substring(beginning, end);
	  		
	  	}
	  	public static double GetPrice (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		int decimals = 0; //amount of decimals found in the double. It shouldn't be more than 1
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			if(!Character.isDigit(now)){ //makes sure it is a digit, and no more than one decimal
		  				if(now == '.'){decimals += 1;} 
		  				else {return -1;} 
		  			}
		  			now = command.charAt(end);
		  	}
		  	if(now == '.'){decimals += 1;} //check last char since it could leave due to reachin end of string
		  	if(decimals > 1){return -1;} //return bad number if more than one decimal
		  	
		  	if(now != ' '){end += 1;} //incase while loop left due to reaching end of string
	  		return Double.parseDouble(command.substring(beginning, end));
	  	}
	  	
	  	public static int GetQuantity (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			if(!Character.isDigit(now)){return -1;}
		  			now = command.charAt(end);
		  	}
		  	if(now != ' '){end += 1; if(!Character.isDigit(now)){return -1;}} //incase while loop left due to reaching end of string
	  		return Integer.parseInt(command.substring(beginning, end));
	  	}
	  	public static int GetWeight (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			if(!Character.isDigit(now)){return -1;}
		  			now = command.charAt(end);
		  	}
		  	if(now != ' '){end += 1; if(!Character.isDigit(now)){return -1;}} //incase while loop left due to reaching end of string
	  		return Integer.parseInt(command.substring(beginning, end));
	  	}
	  	public static String GetOField1 (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  	}
		  	if(now != ' '){end += 1;} //incase while loop left due to reaching end of string
	  		return command.substring(beginning, end);
	  	}
	  	public static String GetOField2 (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  	}
		  	if(now != ' '){end += 1;} //incase while loop left due to reaching end of string
	  		return command.substring(beginning, end);
	  	}
	  }
}
