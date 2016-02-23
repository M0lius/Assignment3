package Assignment3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;


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
		
		
		//Stub for arraylist.
		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice(); 
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		}		
	  }
	  
	  public static void Doit(String s){
		//Parse input, take appropriate actions.
			
			String operation = Reader.GetOperation(s);
			if (operation.equals(null) || operation.equals("")) {
				System.out.println("Invalid Operation");
				return;
			}
			
			String category = Reader.GetCategory(s);
			if (category.equals(null) || category.equals("")) {
				System.out.println("Invalid Category");
				return;
			}
			
			String name = Reader.GetName(s);
			if (name.equals(null) || name.equals("")) {
				System.out.println("Invalid Name");
				return;
			}
			
			double price = Reader.GetPrice(s);
			if (price <= 0) {
				System.out.println("Invalid Price");
				return;
			}
			
			int quantity = Reader.GetQuantity(s);
			if (quantity <= 0){
				System.out.println("Invalid Quantity");
				return;
			}
			
			double weight = Reader.GetWeight(s);
			if(weight <= 0){
				System.out.println("Invalid Weight");
				return;
			}
			String oField1 = Reader.GetOField1(s);
			if (oField1.equals(null) || oField1.equals("")) {
				System.out.println("Invalid Optional Field1");
				return;
			}
			
			String oField2 = Reader.GetOField2(s);	
			if (oField2.equals(null) || oField2.equals("")) {
				System.out.println("Invalid Optional Field2");
				return;
			}
			
		
			System.out.printf("*%s*%s*%s*%.2f*%d*%.2f*%s*%s*\n", operation, category, name, price, quantity, weight, oField1, oField2);		  
	  }
	
	  
	  /******************************************************************************
	  	* Class: Reader                                             				  *
	  	* Purpose: Reads a file and Parses it										  *
	  	* Returns: Nothing                                                               *
	  	******************************************************************************/

	  public static class Reader 
	  {
	  	
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
		
	  	public static String GetOperation (String command) //reads for the Operation
	  	{ 
	  		int beginning = 0; //beginning of the word
	  		int end = 0; //end of the word
	  		char now = command.charAt(beginning); //checks current char
	  		
	  		while (now == ' ' && end < (command.length() - 1)){ //goes to first non space
	  			beginning += 1;
	  			now = command.charAt(beginning); //checks current char
	  		}
	  		end = beginning;
	  		while (now != ' '){//up too the first space it sees
	  			end += 1;
	  			now = command.charAt(end);
	  			//TODO what makes a command valid
	  		}
	  		
	  		return command.substring(beginning, end);
	  		
	  	}
	  	public static String GetCategory (String command) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, 1); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  			//TODO what makes a category valid
		  	}
	  		return command.substring(beginning, end);

	  	}
	  	public static String GetName (String command) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, 2); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  			//TODO what makes a Name valid
		  	}
	  		return command.substring(beginning, end);
	  		
	  	}
	  	public static double GetPrice (String command) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, 3); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  			//TODO what makes a category valid
		  	}
	  		return Double.parseDouble(command.substring(beginning, end));
	  	}
	  	
	  	public static int GetQuantity (String command) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, 4); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  			//TODO what makes a category valid
		  	}
	  		return Integer.parseInt(command.substring(beginning, end));
	  	}
	  	public static double GetWeight (String command) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, 5); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  			//TODO what makes a category valid
		  	}
	  		return Double.parseDouble(command.substring(beginning, end));
	  	}
	  	public static String GetOField1 (String command) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, 6); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  			//TODO what makes a category valid
		  	}
	  		return command.substring(beginning, end);
	  	}
	  	public static String GetOField2 (String command) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, 7); //beginning of the word
	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
		  	while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
		  			end += 1;
		  			now = command.charAt(end);
		  			//TODO what makes a category valid
		  	}
	  		return command.substring(beginning, end);
	  	}
	  }
}
