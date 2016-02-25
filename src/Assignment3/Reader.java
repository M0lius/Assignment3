//import Assignment3.A3Driver.Reader; // auto-generated this I think?

/* NAMES: MARIO MOLINA
 * AND:   ETHAN MILLER
 * TA:    JO EGNER
 * ASSIGNMENT 3 :Shopping Cart One
 * */

package Assignment3;

/******************************************************************************
		* Class: Reader                                             				  *
	  	* Purpose: Reads a file and Parses it										  *
	  	* Returns: Nothing                                                               *
	  	******************************************************************************/

	  public class Reader //static class Reader 
	  {
		  
	  	public static boolean Tail(String command, int skips){
	  		int pointer = SkipInputs(command, skips);
	  		if (pointer == -1){
	  			return false;
	  		} else {
	  			char now = command.charAt(pointer);
	  			if (now == ' '){
	  				return false;
	  			} else{
	  			return true;
	  			}
	  		}
	  		//char now = command.charAt(pointer - 1);
//	  		if (pointer < command.length()){
//	  			return true;
//	  		}
//	  		return false;
	  	}
		  
		public static int SkipInputs(String command, int inputs)// assumes all previous inputs were valid and gathered 
		{//returns a pointer to the beginning of the next input after # skipped
			int pointer = 0; 
			
	  		if(pointer > (command.length() -1)){ //execption of blank line
	  			return -1; //error
	  		}
	  		
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
		  		
		  		if(now != ' '){return -1;} //reached the end prematurely 
		  		
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
	  		if (beginning == -1) {return "";}//already at the end of string
	  		
	  		int end = beginning; //end of the word
	  		
	  		if(beginning > (command.length() - 1)){return "";} //exception of blank line
	  		
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
	  		if (beginning == -1) {return "";}//already at the end of string
	  		
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
	  		if (beginning == -1) {return "";}//already at the end of string
	  		
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
	  		if (beginning == -1) {return -1;}//already at the end of string
	  		
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
	  		boolean decimalfound = false;
	  		int decimalpointer = 0;
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		if (beginning == -1) {return -1;}//already at the end of string

	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
	  		while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
	  			end += 1;
	  			
	  			if(!Character.isDigit(now) && decimalfound == false){
	  				if(now == '.' ){
	  					decimalfound = true;
	  					decimalpointer = end;
	  				} else {
	  					return -1;
	  				}
	  			} else if(decimalfound == true){
	  				if(now != '0'){
	  					return -1;
	  				}
	  			} 	  			
	  			now = command.charAt(end);
	  		}
		  	if(now != ' '){end += 1; if(!Character.isDigit(now)){return -1;}} //incase while loop left due to reaching end of string
		  	if (decimalfound == true){ return Integer.parseInt(command.substring(beginning, decimalpointer).replaceAll("[^\\d]", ""));}
		  	else {
	  		return Integer.parseInt(command.substring(beginning, end));
		  	}
	  	}
	  	
	  	public static int GetWeight (String command, int skip) //reads for....
	  	{ 
	  		boolean decimalfound = false;
	  		int decimalpointer = 0;
	  		
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		if (beginning == -1) {return -1;}//already at the end of string

	  		int end = beginning; //end of the word
	  		
	  		char now = command.charAt(beginning); //checks current char
	  		while (now != ' ' && end < (command.length() - 1)){//up too the first space it sees
	  			end += 1;
	  			
	  			if(!Character.isDigit(now) && decimalfound == false){
	  				if(now == '.' ){
	  					decimalfound = true;
	  					decimalpointer = end;
	  				} else {
	  					return -1;
	  				}
	  			} else if(decimalfound == true){
	  				if(now != '0'){
	  					return -1;
	  				}
	  			} 	  			
	  			now = command.charAt(end);
	  		}
		  	if(now != ' '){end += 1; if(!Character.isDigit(now)){return -1;}} //incase while loop left due to reaching end of string
		  	if (decimalfound == true){ return Integer.parseInt(command.substring(beginning, decimalpointer).replaceAll("[^\\d]", ""));}
		  	else {
	  		return Integer.parseInt(command.substring(beginning, end));
		  	}
	  	}
	  	
	  	public static String GetOField1 (String command, int skip) //reads for....
	  	{ 
	  		int beginning = Reader.SkipInputs(command, skip); //beginning of the word
	  		if (beginning == -1) {return "";}//already at the end of string
	  		
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
	  		if (beginning == -1) {return "";}//already at the end of string
	  		
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
