package org.example;
import java.util.*;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.example.Functions;
import java.io.*;
import java.net.PasswordAuthentication;



public class Main {
	 public static void main(String[] args) throws  Exception {	
		 
	   	 Functions functions = new Functions();
	   	functions.startApproachingUpcomingEvents();
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





