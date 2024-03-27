package org.example;
import java.util.Scanner;



public class Main {
	 public static void main(String[] args) throws  Exception {	
		 
	   	 Functions functions = new Functions();
	   	functions.startApproachingUpcomingEvents();
	        Scanner scanner = new Scanner(System.in);
	        Printing printing = new Printing();
	        
	    	
	    	
	        while(true) {
	        	printing.printSomething("""
    		    ------ Welcome to Home Page ------
    		    |                                |
    		    |          1. Sign up            |
    		    |          2. Sign in            |
    		    |          3. Exit               |
    		    |                                |
    		    ----------------------------------
    		    Enter your choice: 
	        	""");

	            while (!scanner.hasNextInt()) {
	                printing.printSomething("Invalid input! Please enter a valid integer.");
	                scanner.next();
	            }
	            
	            Functions.choice = scanner.nextInt();
	            if (Functions.choice == 1) {   
	            	printing.printSomething("""
        		    ----------- SINGUP PAGE ----------
        		    |                                |
        		    |          1. CUSTOMER           |
        		    |          2. PROVIDER           |
        		    |          3. Exit               |
        		    |                                |
        		    ----------------------------------
        		    Enter your choice: 
        		    """);

	                while (!scanner.hasNextInt()) {
	                    printing.printSomething("Invalid input! Please enter a valid integer.");
	                    scanner.next();
	                }
	                
	                functions.choice2 = scanner.nextInt();
	                
	                if (functions.choice2 == 1) {
	                    functions.customerSignUp();
	                } else if (functions.choice2 == 2) {
	                    functions.providerSignUp();
	                } else if (functions.choice2 == 3) {
	                    break; // Exit the loop
	                }
	            } else if (Functions.choice == 2) {
	                Functions.signInFunction();
	            } else if (Functions.choice == 3) {
	                System.exit(0);
	            } else {
	                printing.printSomething("Invalid choice! Please enter a valid choice.");
	            }
	        }

	    }
}





