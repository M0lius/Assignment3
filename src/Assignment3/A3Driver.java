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
		//code snipped from the Translate Program
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
	  			Doit(s);//This will undergo parsing of the string and advance to calling functions when necessary
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
			
			String operation = Reader.GetOperation(s, skip); //get operation field
			if (operation.equals(null) || operation.equals("") || !ShoppingCart.validOperation(operation)) {
				System.out.println("Invalid Operation"); //invalid operations so the whole string is invalid,
				return; //leave and ask for next command, same for all the remaining if statments
			}
			skip += 1;
			
			if(operation.toUpperCase().equals("INSERT")){
				String category = Reader.GetCategory(s, skip); //get category field
				if (category.equals(null) || category.equals("")|| !ShoppingCart.validCategory(category)) {
					System.out.println("Invalid Category");
					return;
				}
				skip += 1;
				
				String name = Reader.GetName(s, skip);//get name field
				if (name.equals(null) || name.equals("")) {
					System.out.println("Invalid Name");
					return;
				}
				skip += 1;
				
				double price = Reader.GetPrice(s, skip); //get price field
				if (price <= 0) {
					System.out.println("Invalid Price");
					return;
				}
				skip += 1;
				
				int quantity = Reader.GetQuantity(s, skip); //get quantity field,... etc for all Get**** function calls
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
					if (oField1.equals(null) || oField1.equals("") || !Groceries.validPerishability(oField1)) {
						System.out.println("Invalid Optional Field1");
						return;
					}
					skip += 1;
					
					oField2 = "";
					
				} else if(category.toUpperCase().equals("ELECTRONICS")){
					oField1 = Reader.GetOField1(s, skip);
					if (oField1.equals(null) || oField1.equals("") || !Electronics.validFrailty(oField1)) {
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
				
				if(Reader.Tail(s, skip)){System.out.println("Invalid Extra Input"); return;} //invalid extra input
				
				System.out.printf("*%s*%s*%s*%.2f*%d*%d*%s*%s*\n", operation, category, name, price, quantity, weight, oField1, oField2);
				ShoppingCart.insert(category, name, price, quantity, weight, oField1, oField2);
				
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
				
				if(Reader.Tail(s, skip)){System.out.println("Invalid Extra Input"); return;} //invalid extra input
				
				System.out.printf("*%s*%s*%d*\n", operation, name, quantity);
				ShoppingCart.update(name, quantity);
				
			} else if(operation.toUpperCase().equals("PRINT")){
				
				if(Reader.Tail(s, skip)){System.out.println("Invalid Extra Input"); return;} //invalid extra input
				System.out.println("Print!");
				ShoppingCart.print();
				
			} else {
				String name = Reader.GetName(s, skip);
				if (name.equals(null) || name.equals("")) {
					System.out.println("Invalid Name");
					return;
				}
				skip += 1;
				
				if(Reader.Tail(s, skip)){System.out.println("Invalid Extra Input"); return;} //invalid extra input
				
				System.out.printf("*%s*%s*\n", operation, name);
				if(operation.toUpperCase().equals("DELETE")){
					ShoppingCart.delete(name);
				}
				else{
					ShoppingCart.search(name);
				}
				
			}
		
				  
	  }
}
	