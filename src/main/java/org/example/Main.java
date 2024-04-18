package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Main {
	 static Functions functions = new Functions();   
     static Scanner scanner = new Scanner(System.in);
     static Printing printing = new Printing();
     static Admin admin = new Admin();
     private static  String adminId =admin.getAdminId();
	   
     static Calendar c=new Calendar();
     
     
    public static void main(String[] args) throws Exception {
    	c.startApproachingUpcomingEvents();
        
        boolean running = true;
        while (running) {
            printWelcomeMenu();
            int choice = getValidIntegerInput(scanner, printing);
            
            switch (choice) {
                case 1:
                    handleSignUpMenu(scanner, printing, functions);
                    break;
                case 2:
                	 signInFunction();
                    break;
                case 3:
                    running = false; // Set running to false to exit the loop
                    break;
                default:
                    printing.printSomething("Invalid choice! Please enter a valid choice.");
                    break;
            }
        }
    }
    
    private static void printWelcomeMenu() {
        Printing printing = new Printing();
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
    }
    
    private static int getValidIntegerInput(Scanner scanner, Printing printing) {
        while (!scanner.hasNextInt()) {
            printing.printSomething("Invalid input! Please enter a valid integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static void handleSignUpMenu(Scanner scanner, Printing printing, Functions functions) throws Exception {
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

        int choice2 = getValidIntegerInput(scanner, printing);
        
        switch (choice2) {
            case 1:
                Customer.customerSignUp();
                break;
            case 2:
                Provider.providerSignUp();
                break;
            case 3:
                // Exit the loop
                break;
            default:
                printing.printSomething("Invalid choice! Please enter a valid choice.");
                break;
        }

        }
//////////////////////////////////////////////////////////////////////////////////////    
    public static void signInFunction() throws Exception {
        Functions.signInPageList();
        printing.printSomething(Functions.ENTER_CHOICE);
        int choice = scanner.nextInt();
        printing.printSomething("Enter Id: ");
        String id = scanner.next();
        
       
        
        switch (choice) {
            case 1:
            	
                signInAdmin(id);
                break; 
            case 2:
            Functions.	customerId=id;
                signInCustomer( Functions.customerId);
                break;
            case 3: 
            	 Functions.provideId=id;
                signInProvider( Functions.provideId);
                break;
            default:
                printing.printSomething("\n" +Functions. INVALID_CHOICE);
        }
    }
///////////////////////////////////////////////////////////////////////////////////
    public static void signInAdmin(String id) throws Exception {
  	  printing.printSomething(Functions.ENTER_PASSWORD);
  	    String password = scanner.next();
      if (id.equals(admin.getAdminId()) && password.equals(admin.getAdminPassword())) {
      adminPage(adminId);
      } else {
          printing.printSomething("\nSomething went wrong!, Try again.");
          signInFunction();
      }
  }
//////////////////////////////////////////////////////////////////////////////////////
    static  int x =1;
    public static  void adminPage(String adminId) throws Exception{
    	Scanner scan=new Scanner(System.in);
    	   while (x > 0) {
    		Functions.adminList();
    	    printing.printSomething(Functions.ENTER_CHOICE);
    	    int c = scan.nextInt();

    	    switch (c) {
    	        case 1:
    	        	Functions. userManagementAdminPageList();
    	        	 int j=scan.nextInt();
    	            switch(j) {
    	            case 1:Customer.viewCustomer();
    	            break;
    	            case 2:Customer.viewCustomersanddelete();	    	            
    	            break;
    	            case 3:signInFunction();
    	            break;
    	            default:
    	        	printing.printSomething(Functions.INVALID_CHOICE);
    	            }
    	            break;
    	        case 2:
    	        	//Functions.discountManagementadminList();
    	        	//int l = scan.nextInt();
    	        	//Functions.discountManagementOptions(l);
    	            break;
    	        case 3:
    	        	Functions.eventManagementAdminPageList();
    	        	int customerEvent=scan.nextInt();
    	        	Functions.eventManagementOptions(adminId,customerEvent);
    	            break;
    	        case 4:
    	        	Functions.venueManagementadminList();
    	        	int cusVenue=scan.nextInt();
    	        	Functions.venueManagementOptions(cusVenue);
    	            break;
    	         
    	        case 5:
    	        	Functions.providerManagementAdminPageList();
    	        	int providerMan=scan.nextInt();
    	        	Functions.providerAdminManagementOptions(providerMan);
    	            break;
    	        case 6:
    	        	//Functions.packageManagementadminList();
    	        	//int packageMan=scan.nextInt();
    	        	//Functions.packageManagementOptions(packageMan);
    	            
    	            break;
    	        case 7:
    	        	Functions. viewBusinessReports();
    		            break;
    	        case 8:
    	        	signInFunction();
    	            break;
              case 0000:x=0;break;
    	        
    	        
    	        
    	        
    	        default:
    	        	printing.printSomething(Functions.INVALID_CHOICE);
    	     	    
    	    }
       }
    }
/////////////////////////////////////////////////////////////////////////////////////////
    public static void signInCustomer(String custId) throws Exception {
        boolean found = false;
        Functions. updateCustomersList(); 
        printing.printSomething(Functions. ENTER_PASSWORD);
        String password = scanner.next();
        try (BufferedReader br = new BufferedReader(new FileReader(Functions.CUSTOMER_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] customerData = line.split(",");
                String customerIdFromFile = customerData[0].trim();
                String passwordFromFile = customerData[5].trim(); 
                if (custId.equals(customerIdFromFile) && password.equals(passwordFromFile)) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            printing.printSomething("Error reading customer data: " + e.getMessage());
        }

        if (found) {
            while (x > 0) {
            	Functions.customerPageList();
                int c = scanner.nextInt();
                customerOptions(custId,c);
            }
        } else {
            printing.printSomething("\nThis account does not exist or the password is incorrect. Please check your inputs.\n");
            signInFunction();
        }
    }

    public static void signInProvider(String provId) throws Exception {
    	
        boolean found = false;
        Functions. updateProvidersList(); 
        printing.printSomething(Functions. ENTER_PASSWORD);
        String password = scanner.next();
        try (BufferedReader br = new BufferedReader(new FileReader(Functions.PROVIDER_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] providerData = line.split(",");
                String providerIdFromFile = providerData[0].trim();
                String passwordFromFile = providerData[5].trim(); 
                if (provId.equals(providerIdFromFile) && password.equals(passwordFromFile)) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            printing.printSomething("Error reading provider data: " + e.getMessage());
        }

        if (found) {
            while (x > 0) {
            	Functions. providerPageList();
                int c = scanner.nextInt();
                 providerOptions(provId,c);
            }
        } else {
            printing.printSomething("\nThis account does not exist or the password is incorrect. Please check your inputs.\n");
            signInFunction(); 
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////
	  public static void customerOptions(String customerId,int x) throws Exception {
	  
	        switch (x){
	        case 1:
	        	 Functions.updateCustomersList();
              printing.printSomething("\nWhich info you want to update?\n1. Name  2.Phone  3. Address  4.Email "+"\n"+ Functions.ENTER_CHOICE);
              Customer. updateCustomerProfile(scanner.nextInt());
	            break;
	            case 2:
	            	 Functions. updateCustomersList(); 
	            	 Functions.addevent(customerId, Functions.REQUEST_FILE_NAME);
	               break;
	            case 3:
	            	printing.printSomething("\n");
	                boolean f =  Functions.viewCostomerevents(customerId, Functions. EVENT_FILE_NAME);
	                if (f) {
	                    boolean continueUpdating = true;
	                    while (continueUpdating) {
	                        printing.printSomething("\nPlease enter the Event ID you want to update (or enter 'done' to finish):\n");
	                        String eventid = scanner.next();
	                        
	                        if ("done".equalsIgnoreCase(eventid)) {
	                            continueUpdating = false; // Exit the loop if the user enters 'done'
	                        } else {
	                        	 Functions. event1.updateEvent(eventid, Functions. EVENT_FILE_NAME);
	                        
	                            printing.printSomething("Event updated successfully.\n");
	                        }
	                    }
	                }
	                break;
               
	             
	            case 4:
	                printing.printSomething("\n");
	                boolean f2 =  Functions.viewCostomerevents(customerId,  Functions.EVENT_FILE_NAME);
	                if (f2) {
	                    boolean continueDeleting = true;
	                    while (continueDeleting) {
	                        printing.printSomething("\nPlease enter the Event ID of the event you want to delete (or enter 'done' to finish):\n");
	                        String eventidd = scanner.next();
	                        
	                        if ("done".equalsIgnoreCase(eventidd)) {
	                            continueDeleting = false; // Exit the loop if the user enters 'done'
	                        } else {
	                        	 Functions. event1.deleteEvent(  Functions.EVENT_FILE_NAME, eventidd);
	                            printing.printSomething("\n");
	                            continueDeleting = false;
	                            printing.printSomething("Event deleted successfully.\n");
	 	                       
	                      
	                        }
	                    }
	                }
	                break;

	            case 5:
	                printing.printSomething("\n");
	                Functions. userSearchPageList();
	                int s = scanner.nextInt();
	                
	                if (s == 1) {
	                    printing.printSomething("\n" + "Please enter the Event Name you want to see it : ");
	                    String eventN = scanner.next();
	                    String usId = customerId;
	                    Event. searchEvent(usId, eventN, 0);
	                } else if (s == 2) {
	                    printing.printSomething("\n");
	                    printing.printSomething("\n" + "Please enter the Venue Name of the event you want to see it : ");
	                    String venueN = scanner.next();
	                    String userId = customerId;
	                    Event. searchEvent(userId, venueN, 8);
	                }
	                break;
            
	          case 6:
	               // printing.printSomething("\t\t\n--- Delete profile! ---\n\nUr info will be Deleted & ur orders will be cancelled!!\nAre you sure? ");
	              //  String str = scanner.next();
	            // Customer. deleteCustomerProfile(str);
	                break;
	          case 7:
	        	  printing.printSomething("\n");   
	        	  Functions.showAdminMessage(customerId);
	               break;	               
	          case 8:
	        	   
	        	    break;

	        case 9:

	        	 Functions.events.clear();
	        	if(  Functions. viewCostomerevents(customerId, Functions.EVENT_FILE_NAME)) {
	        		

		          	 
	          	 while (true) {
	          		 
	          		 printing.printSomething("\nEnter the Event ID you want to view details for (or enter 'done' to finish):\n ");
	          	     String eventIDToView = scanner.next();
	          	    if ("done".equalsIgnoreCase(eventIDToView)) {
	          	       break;
	          	    } else {
	          	    	 Functions. updateEventList( Functions.EVENT_FILE_NAME);
	          			   Event e=Event.findeventID(eventIDToView, Functions.EVENT_FILE_NAME);
	          	    	
	          			 printing.printSomething(e.toString2());
	          			printing.printSomething("\n");
	          	    
	          	       }
	              	 }
	        	}
	              break;
	        	 	        	 	        		        	  	        	   	        	   
	        case 10: 

	        	 printing.printSomething("\n");
	        	 Functions.events.clear();   	        	  
	        	if( Functions.viewCostomerevents(customerId, Functions.REQUEST_FILE_NAME)){
	        		


      	     while (true) {
      		 
      		 printing.printSomething("\nEnter the Event ID you want to view details for (or enter 'done' to finish):\n ");
      	     String eventIDToView = scanner.next();
      	    if ("done".equalsIgnoreCase(eventIDToView)) {
      	       break;
      	    } else {
      	    	 Functions. updateEventList( Functions.REQUEST_FILE_NAME);
      			   Event e=Event.findeventID(eventIDToView, Functions.REQUEST_FILE_NAME);
      	    	
      			 printing.printSomething(e.toString2());
      	    	 
      	    	
      	       }
          	 }
	        	}
          break;
	        case 11:



	        	Calendar calendar =Calendar. loadEventsForCustomerInCalendar(customerId);


	        	if (calendar != null) {
	        		Calendar. displayAllCustomerEvents(calendar);
	        	} else {
	        		printing.printSomething("Calendar is null. Unable to display events.");
	        	}      	
	        	break;
	        case 12:
	        Invoice.readInvoiceFile( Functions.INVOICE_FILE_NAME,  customerId) ;
	   	     break;
	       case 13:	
	    	   
	    	   Main. signInFunction();
	                break;
	            default: printing.printSomething( Functions.INVALID_CHOICE);
	        }
	    }
	  //////////////////////////////////////////////////////////////////////////////////////////////////////
	  public static  void providerOptions(String provideId,int choice) throws Exception {
	        switch (choice) {
	            case 1:
	            	 Functions.updateProvidersList();
	                printing.printSomething("Which info you want to update?\n1. Name  2.Phone  3. Address "+"\n"+ Functions.ENTER_CHOICE);
	               Provider.   updateProviderProfile(scanner.nextInt());
	              
	                break;
	            case 2:
	            	 Functions. updateProvidersList();
	            	 Functions.addService();
	                break;
	            case 3:
	            	 printing.printSomething("\n");   
	            	 if ( Functions.viewproviderservice(provideId)) {
	                     printing.printSomething("\n"+"Please enter the Event ID of the service you want to update : ");
	                     String serviceIdIn=scanner.next();
	                	
	                     Functions.updateProviderAndServiceList(provideId);
	                     Functions.updateServiceList();
	                    Service.updateServiceDetails(serviceIdIn);
	                    printing.printSomething("\nService updated successfully.");
	                    Functions.viewproviderservice(provideId);
	                     }
                 break;
	            case 4:
	            	 printing.printSomething("\n");   
	                 if ( Functions.viewproviderservice(provideId)) {
	                 printing.printSomething("\n"+"Please enter the Event ID of the service you want to delete: ");
	                 String servisIdIn=scanner.next();
	                 Service.deleteServiceFromFile( Functions.SERVICE_FILE_NAME, servisIdIn);
	                 printing.printSomething("\nService deleted successfully.");
	                 }
	                break;
	          
	            case 5: Functions. viewproviderservice(provideId);
	            	break;
	            case 6:
	            	Main.signInFunction();
	            break;
	            	
	            default:
	                printing.printSomething( Functions.INVALID_CHOICE);
	                break;
	        }
	    }
}


