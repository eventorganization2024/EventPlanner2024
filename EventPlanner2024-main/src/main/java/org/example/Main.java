package org.example;
import java.util.*;
import java.util.Scanner;
import org.example.Functions;
import java.io.*;



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







/*
public class Main {
    public static void main(String[] args) throws  Exception {
    	

    	
    
        Functions functions = new Functions();
        Scanner scanner = new Scanner(System.in);
        Printing printing = new Printing();
        while (true){
            printing.printSomething("\n------ Welcome to Home Page ------"+"\n|                                |"+
                    "\n|          1. Sign up            |"+"\n|          2. Sign in            |"+
                    "\n|          3. Exit               |"+"\n|                                |"+
                    "\n----------------------------------\n"+"Enter your choice: ");
            while (!scanner.hasNextInt()) {
                printing.printSomething("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
            functions.choice = scanner.nextInt();
            if(functions.choice ==1 ) functions.customerSignUp();
            else if(functions.choice == 2 )functions.signInFunction();
            else if(functions.choice == 3) System.exit(0);
            else printing.printSomething("Invalid choice! Please enter a valid choice.");
        }
    }

}











	    
	     Functions functions = new Functions();
	     Scanner scanner = new Scanner(System.in);
	     functions.printEvents();
	    functions. updateEventListFromFile("event.txt");
	     functions.printEvents();
	    Customer c1 = new Customer("1222","Ahamd","057806241","Nablus","123456","ansam@gmail.com",0);
		c1.addCustomerToFile(c1, "customer.txt"); 
		functions.customers.add(c1);
		functions .customeraddevent("1222","requst.txt"); 
		// functions.viewCostomerevents("1222");
		 
		 
		  Customer c2 = new Customer("1444","Ahamd","057806241","Nablus","123456","ansam@gmail.com",0);
		  c2.addCustomerToFile(c2, "customer.txt"); 
			functions.customers.add(c2);
			functions .customeraddevent("1444","requst.txt"); 
			functions .customeraddevent("1444","requst.txt"); 
			functions .customeraddevent("1444","requst.txt"); 
			
			functions.viewCostomerevents("1444");
			functions.viewCostomerevents("1222");
			 
		 
























*/




