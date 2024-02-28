package org.example;
import java.util.*;


public class Main {
	 public static void main(String[] args) throws  Exception {

		 Scanner scanner = new Scanner(System.in);
		 Functions functions = new Functions();
		 Customer c=new Customer("12114555","Ansam","057806241","Nablus","123456","ansam@gmail.com",0);
		 c.addCustomerToFile(c, "customer.txt");
		 
		 
		 
		 
		 
		 
		// functions.viewalleventsforAdmin();
		 Event newEvent2 = new Event("Birthday Party", "2024-2-3", "10:00 AM", "A celebration", 50, "user123", "Party", "Birthday",12);
   
		 
	 
		//newEvent2.addEventToFile(newEvent2, "event.txt");
    // newEvent2.deleteevent(newEvent2,"event.txt",2000);
		// newEvent.findevent(120,"event.txt");
		//newEvent2.updateEvent(100, "event.txt");
      
	 }
	 


}






/*
public class Main {
	 public static void main(String[] args) throws  Exception {
	        Functions functions = new Functions();
	        Scanner scanner = new Scanner(System.in);
	        Printing printing = new Printing();
	         while(true){
	            printing.printSomething("\n------ Welcome to Home Page ------"+"\n|                                |"+
	                    "\n|          1. Sign up            |"+"\n|          2. Sign in            |"+
	                    "\n|          3. Exit               |"+"\n|                                |"+
	                    "\n----------------------------------\n"+"Enter your choice: ");
	            while (!scanner.hasNextInt()) {
	                printing.printSomething("Invalid input! Please enter a valid integer.");
	                scanner.next();
	            }
	            
	            functions.choice = scanner.nextInt();
	           if(functions.choice ==1 ) 
	           {   
	        	   printing.printSomething("\n------ SINGUP PAGE ------"+"\n|                                |"+
		                    "\n|          1. CUSTOMER            |"+"\n|          2. PROVIDER           |"+
		                    "\n|          3. Exit                |"+"\n|                                |"+
		                    "\n----------------------------------\n"+"Enter your choice: ");
	        	   
	        	   while (!scanner.hasNextInt()) {
		                printing.printSomething("Invalid input! Please enter a valid integer.");
		                scanner.next();
		            }
		            
		            functions.choice2 = scanner.nextInt();
	        	   
	        	   if (functions.choice2==1) { functions.customerSignUp();}
	        	   else if (functions.choice2==2) {functions.providerSignUp();}
	        	   else  if(functions.choice2 == 3) functions.choice =1; 
	           }
	           else  if(functions.choice == 2 )functions.signInFunction();
	           else  if(functions.choice == 3) System.exit(0);
	            else printing.printSomething("Invalid choice! Please enter a valid choice.");
	        }
	    }
}
*/