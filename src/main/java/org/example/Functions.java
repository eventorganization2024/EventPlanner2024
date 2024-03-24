package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.example.*;



public class Functions {
	  static Printing printing = new Printing();
	    Scanner scanner = new Scanner(System.in);
	    Customer customer_obj;
	    Provider provider_obj;
	    Event event_obj;
	    int choice;
	    int choice2;
	    boolean found;
	    String tmp;
	    String d;
	    double pricee;
	    Admin admin = new Admin();
	    Paackage p = new Paackage();
	    static Customer customer1 = new Customer();
	    static Event event1=new Event();
	    static Provider provider1=new Provider();
	   public final ArrayList<Customer> customers = new ArrayList<>();
	   private final ArrayList<Provider> providers = new ArrayList<>();
	   private final ArrayList<Event> events = new ArrayList<>();
	   public List<ServiceDetails> serviceDetails = new ArrayList<>();

///////////////////////////////////////////////////////////////////////////////////////	    
	    static final String CUSTOMER_FILE_NAME = "customer.txt";
	    static final String PROVIDER_FILE_NAME = "provider.txt";
	    private static final String ENTER_NAME = "Enter New Name: ";
	    static final String SPACE = "|                                       |";
	    static final String ENTER_CHOICE = "Enter your choice: ";
	    static final String ENTER_PASSWORD= "\nEnter Password ";
	    static final String INVALID_CHOICE = "Invalid choice! Please enter a valid choice.";
	    static final String LINE = "----------------------------------------";
	    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	    
	    private String id1;
		  
	    private String id;
	    private String password;
	    
///////////////////////////////////////////////////////////////////////////////////////
	    void inputs(){
	    	printing.printSomething("Enter Id: ");
	    	id = scanner.next();
	    	printing.printSomething(ENTER_PASSWORD);
	    	password = scanner.next();
	    	}
///////////////////////////////////////////////////////////////////////////////////////
	    void signInFunction() throws Exception {
	        int c;
	        signInPageList();
	        printing.printSomething(ENTER_CHOICE);
	        choice = scanner.nextInt();
	        printing.printSomething("\nEnter Id: ");
	        id = scanner.next();
	        printing.printSomething("Enter password: ");
	        password = scanner.next();
	        switch (choice){
	            case 1:
	                if (id.equals(admin.getAdminId()) && password.equals(admin.getAdminPassword())) 
	                {
	                    adminPage();
	                } else {
	                    printing.printSomething("\nSomething went wrong!, Try again.");
	                    inputs();
	                }
	                break;
               case 2:
                    boolean found1 = false;
		            updateCustomersList(); // Update method to read from file
		            try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE_NAME))) {
		                String line;
		                while ((line = br.readLine()) != null) {
		                    String[] customerData = line.split(",");
		                    String customerIdFromFile = customerData[0].trim();
		                    String passwordFromFile = customerData[5].trim(); // Assuming password is at index 5
		                    if (id.equals(customerIdFromFile) && password.equals(passwordFromFile)) {
		                        found1 = true;
		                        break;
		                    }
		                }
		            } catch (IOException e) { printing.printSomething("Error reading customer data: " + e.getMessage()); }
                      if (found1) {
		                while (x > 0) {
		                    customerPageList();
		                    c = scanner.nextInt();
		                    customerOptions(c);
		                }
		            } else {
		                printing.printSomething("\nThis account does not exist or the password is incorrect. Please check your inputs.\n");
		                signInFunction(); // Allow the user to retry login
		            }
                   break;    
               case 3:
	            	 boolean foundp = false;
	            	 updateProvidersList(); // Update method to read from file
                            try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE_NAME))) {
			                String line;
			                while ((line = br.readLine()) != null) {
			                    String[] ProviderData = line.split(",");
			                    String ProviderIdFromFile = ProviderData[0].trim();
			                    String passwordFromFile = ProviderData[5].trim(); // Assuming password is at index 5
			                    if (id.equals(ProviderIdFromFile) && password.equals(passwordFromFile)) {
			                        foundp = true;
			                        break;
			                    }
			                }
			            } catch (IOException e) { printing.printSomething("Error reading Povider data: " + e.getMessage());}
			            if (foundp) {
			                while (x > 0) {
			                	providerPageList();
			                    c = scanner.nextInt();
			                   providerOptions(c);
			                }
			            } else {
			                printing.printSomething("\nThis account does not exist or the password is incorrect. Please check your inputs.\n");
			                signInFunction(); // Allow the user to retry login
			            }
			           
		            	break; 
	            	          
	            default:printing.printSomething("\n"+INVALID_CHOICE);}}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    int x =1;
	    void adminPage() throws IOException, Exception
	    { 

	    	while (x > 0) {
	    	    adminList();
	    	    printing.printSomething(ENTER_CHOICE);
	    	    int c = scanner.nextInt();

	    	    switch (c) {
	    	        case 1:
	    	        	 UserManagementAdminPageList();
	    	        	 int j=scanner.nextInt();
	    	            switch(j) {
	    	            case 1:viewCustomer();
	    	            break;
	    	            case 2:viewCustomersanddelete();	    	            
	    	            break;
	    	            case 3:signInFunction();
	    	            break;
	    	            default:
	    	        	printing.printSomething(INVALID_CHOICE);
	    	            }
	    	            break;
	    	        case 2:
	    	        	DiscountManagementadminList();
	    	        	int l = scanner.nextInt();
	    	        	DiscountManagementOptions(l);
	    	            break;
	    	        case 3:
	    	        	EventManagementAdminPageList();
	    	        	int CE=scanner.nextInt();
	    	        	EventManagementOptions(CE);
	    	            break;
	    	        case 4:
	    	        	VenueManagementadminList();
	    	        	int C=scanner.nextInt();
	    	        	VenueManagementOptions(C);
	    	            break;
	    	         
	    	        case 5:
	    	        	ProviderManagementAdminPageList();
	    	        	int PR=scanner.nextInt();
	    	        	ProviderAdminManagementOptions(PR);
	    	            break;
	    	        case 6:
	    	        	PackageManagementadminList();
	    	        	int Cr=scanner.nextInt();
	    	        	PackageManagementOptions(Cr);
	    	            
	    	            break;
	    	        case 7:
	    		          viewBusinessReports();
	    		            break;
	    	        case 8:
	    	            
	    	            break;
                    case 9:
	    	        	signInFunction();
	    	            break;
	    	        
	    	        
	    	        default:
	    	        	printing.printSomething(INVALID_CHOICE);
	    	     	    
	    	    }
	         }
	      }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    public void customerOptions(int x) throws Exception {
	    	
	        switch (x){
	            case 1:
	                updateCustomersList();
	                printing.printSomething("Which info you want to update?\n1. Name  2.Phone  3. Address  4. Email"+"\n"+ENTER_CHOICE);
	                updateCustomerProfile(scanner.nextInt());
	                break;
	            case 2:
	               updateCustomersList(); 
	               addevent("requst.txt");
	               break;
	            case 3:
	            	printing.printSomething("\n");
	                boolean f = viewCostomerevents(id, "event.txt");
	                if (f) {
	                    boolean continueUpdating = true;
	                    while (continueUpdating) {
	                        printing.printSomething("\nPlease enter the Event ID you want to update (or enter 'done' to finish): ");
	                        String eventid = scanner.next();
	                        
	                        if ("done".equalsIgnoreCase(eventid)) {
	                            continueUpdating = false; // Exit the loop if the user enters 'done'
	                        } else {
	                            event1.updateEvent(eventid, "event.txt");
	                           // viewCostomerevents(id, "event.txt");
	                            printing.printSomething("Event updated successfully.\n");
	                        }
	                    }
	                }
	                break;
                   
	             
	            case 4:
	                printing.printSomething("\n");
	                boolean f2 = viewCostomerevents(id, "event.txt");
	                if (f2) {
	                    boolean continueDeleting = true;
	                    while (continueDeleting) {
	                        printing.printSomething("\n Please enter the Event ID of the event you want to delete (or enter 'done' to finish): ");
	                        String eventidd = scanner.next();
	                        
	                        if ("done".equalsIgnoreCase(eventidd)) {
	                            continueDeleting = false; // Exit the loop if the user enters 'done'
	                        } else {
	                            event1.delete_event_from_file_and_arraylist(event1, "event.txt", eventidd);
	                            printing.printSomething("\n");
	                            continueDeleting = false;
	                            printing.printSomething("Event deleted successfully.\n");
	 	                       
	                            // viewCostomerevents(id, "event.txt");
	                        }
	                    }
	                }
	                break;

	           case 5:
	            	   printing.printSomething("\n");   
	            	   UserSearchPageList();
	            	   int s=scanner.nextInt();
	            	   switch(s) {
	            	   case 1: printing.printSomething("\n"+"Please enter the Event Name you want to see it : ");
	            	   String EventN=scanner.next();
	                   String usId=id;
	                   searchEvent(usId, EventN, 0);
	                   break;
	            	   case 2:  printing.printSomething("\n");   
	                   printing.printSomething("\n"+"Please enter the Venue Name of the event you want to see it : ");
	                   String VenueN=scanner.next();
	                   String userId=id;
	                   searchEvent(userId, VenueN, 8);
	            	   }
	            	 break;	                 
	          case 6:
	                printing.printSomething("\t\t\n--- Delete profile! ---\n\nUr info will be Deleted & ur orders will be cancelled!!\nAre you sure? ");
	                String str = scanner.next();
	                deleteCustomerProfile(str);
	                break;
	          case 7:
	        	  printing.printSomething("\n");   
	        	  showAdminMessage(id);
	               break;	               
	          case 8:
	            	 if (viewAllPackagesFromFile("package.txt"))
	            	selectpackagee();
	            	 
	            	break;
	        case 9:
	        	  
	        	if(  viewCostomerevents(id,"event.txt")) { /// to String 
		          	 boolean show=true;
	          	 while (show) {
	          		 
	          		 printing.printSomething("Enter the Event ID you want to view details for (or enter 'done' to finish):\n ");
	          	     String eventIDToView = scanner.next();
	          	    if ("done".equalsIgnoreCase(eventIDToView)) {
	          	       break;
	          	    } else {
	          	    	  updateEventList("event.txt");
	          			   Event e=Event.findeventID(eventIDToView,"event.txt");
	          	    	
	          			  System.out.println(e.toString2());// to String2
	          	    	 show =false;
	          	    	break;
	          	       }
	              	 }
	        	}
	              break;
	        	 	        	 	        		        	  	        	   	        	   
	        case 10: 
	        	 printing.printSomething("\n");   	        	  
	        	if(viewCostomerevents(id,"requst.txt")){/// to String
	        	boolean show2=true;
          	     while (show2) {
          		 
          		 printing.printSomething("Enter the Event ID you want to view details for (or enter 'done' to finish): ");
          	     String eventIDToView = scanner.next();
          	    if ("done".equalsIgnoreCase(eventIDToView)) {
          	       break;
          	    } else {
          	    	  updateEventList("requst.txt");
          			   Event e=Event.findeventID(eventIDToView,"requst.txt");
          	    	
          			  System.out.println(e.toString2());// to String2
          	    	 show2 =false;
          	    	break;
          	       }
              	 }
	        	}
              break;
	        case 11:
	        	// Load events from the event.txt file
            	Calendar calendar =loadEventsForCustomerInCalendar(id);
                displayAllCustomerEvents(calendar);	        	
	        	break;
	       case 12:	
	    	   
	                signInFunction();
	                break;
	            default: printing.printSomething(INVALID_CHOICE);
	        }
	    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    public void providerOptions(int choice) throws Exception {
	        switch (choice) {
	            case 1:
	            	updateProvidersList();
	                printing.printSomething("Which info you want to update?\n1. Name  2.Phone  3. Address  4. Email"+"\n"+ENTER_CHOICE);
	               updateProviderProfile(scanner.nextInt());
	              
	                break;
	            case 2:
	            	 updateProvidersList();
	            	addService();
	                break;
	            case 3:
	            	 printing.printSomething("\n");   
	            	 if (viewproviderservice(id)) {
	                     printing.printSomething("\n"+"Please enter the Event ID of the service you want to update : ");
	                     String Sid=scanner.next();
	                	
	                     updateProviderAndServiceList();
	                     updateServiceList();
	                    provider1.updateServiceDetails(Sid,"service.txt");
	                    printing.printSomething("\nService updated successfully.");
	                    viewproviderservice(id);
	                     }
                   break;
	            case 4:
	            	 printing.printSomething("\n");   
	                 if (viewproviderservice(id)) {
	                 printing.printSomething("\n"+"Please enter the Event ID of the service you want to delete: ");
	                 String Sid=scanner.next();
	                 provider1.deleteService(Sid);
	                 printing.printSomething("\nService deleted successfully.");
	                 }
	                break;
	            case 5:
	               
	                break;
	            case 6:
	                
	                break;
	            default:
	                printing.printSomething("Invalid choice.");
	                break;
	        }
	    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   private void VenueManagementOptions(int C) {
	  	  Scanner scanner = new Scanner(System.in);
	  	switch (C)
	  	{
	  	case 2:
	  		  addVenue(scanner, "venue.txt");
	  		
	            break;
	  	 case 4:
	           editVenuefrom(scanner, "venue.txt");
	           
	           break;
	       case 3:
	           deleteVenueById( scanner,"venue.txt");
	           break;
	       case 1:
	           viewAllVenues("venue.txt");
	           break;
	       case 5:
	           System.out.println("Exiting...");
	           break;
	       default:
	           printing.printSomething(INVALID_CHOICE);
	    	     break;
	  		
	  	}
	  }     
                            /////////////////////////////////////////////////////////////////////
       private void EventManagementOptions(int cE) throws Exception {
       switch (cE) {
        case 1:
        if( viewalleventsforAdmin("requst.txt")) {
      	boolean continuee = true;
        while (continuee) {     	
        printing.printSomething("\nEnter the Event ID of the event you want to accept or reject (or enter 'done' to finish): ");
         String eventID = scanner.next();
         if ("done".equalsIgnoreCase(eventID)) {
        	continuee = false; // Exit the loop if the user enters 'done'
          break; // Exit the switch case
        	}
        		     
          printing.printSomething("\nEnter 'Y' to accept the event or 'N' to reject: ");
         String choice = scanner.next().toUpperCase();        
         if (choice.equals("Y")) {         	
             event1= event1.findeventID(eventID, "requst.txt");
         	event1.delete_event_from_file_and_arraylist(event1, "requst.txt", eventID);
         	event1.addEventToFile(event1, "event.txt");
            printing.printSomething("\nEvent accepted.");             
             ///to send Notification:
            SendmsgtoCustomer("has been approved",event1);  
            break;
            } else if (choice.equals("N")) {
         	  ///to send Notification:
         	  event1= event1.findeventID(eventID, "requst.txt");
         	SendmsgtoCustomer("has been Rejected",event1);
         	event1.delete_event_from_file_and_arraylist(event1, "requst.txt", eventID);
         	printing.printSomething("\nEvent rejected.");
         	break;
        } else {
           printing.printSomething(INVALID_CHOICE);
      	     }
         
        	}
        }
        break;        
     case 2:
     	 viewalleventsforAdmin("event.txt"); /// to String 
     	 boolean show=true;
     	 while (show) {
     		 
     		 printing.printSomething("\nEnter the Event ID you want to view details for (or enter 'done' to finish): ");
     	     String eventIDToView = scanner.next();
     	    if ("done".equalsIgnoreCase(eventIDToView)) {
     	       break;
     	    } else {
     	    	 updateEventList("event.txt");
     			   Event e=Event.findeventID(eventIDToView,"event.txt");
     	    	
     			  System.out.println(e.toString2());// to String2
     	    	 show =false;
     	    	break;
     	       }
         	 }
     	 
         break;
     case 3:
       addevent("event.txt");
       break;
     case 4:
     	if( viewalleventsforAdmin("event.txt")) {
     		boolean continueDeleting = true;
            while (continueDeleting) {
            	
            	 printing.printSomething("\nEnter the Event ID of the event you want to delete (or enter 'done' to finish): ");
                  String eventID = scanner.next();
                  if ("done".equalsIgnoreCase(eventID)) {
                      continueDeleting = false; // Exit the loop if the user enters 'done'
                  } 
                  else {
                      event1.delete_event_from_file_and_arraylist(event1, "event.txt", eventID);
     	              printing.printSomething("\nEvent with ID " + eventID + " successfully deleted .");
     	              continueDeleting = false;
                  }
     	       }
            }
     	break;
     case 5:
     	 if (viewalleventsforAdmin("event.txt")) {
     		 boolean continueUpdating = true;
             while (continueUpdating) {
      	    printing.printSomething("Please enter the Event ID you want to update: ");
             String eventid=scanner.next();
             if ("done".equalsIgnoreCase(eventid)) {
                 continueUpdating = false; // Exit the loop if the user enters 'done'
             } else {
             event1.updateEvent(eventid, "event.txt"); 
             continueUpdating = false;            
             viewalleventsforAdmin("event.txt"); 
             printing.printSomething("\nEvent with ID " + eventid + " successfully updated.");
             }}}
        break;
     case 6:adminPage();
     default:
         printing.printSomething(INVALID_CHOICE);
  	     break;
 }
}
                           /////////////////////////////////////////////////////////////////////// 
      private void ProviderAdminManagementOptions(int p) throws Exception {
    		switch (p) {
    	    case 1:
    	    	 viewallprovider("provider.txt");
    	    	 break;
    	    case 2:
    	    	  if(  viewallprovider("provider.txt")) {
    	              printing.printSomething("\nEnter the Provider ID  you want to delete it: ");
    	              String providerID = scanner.next();  
    	             provider1.delete_provider_from_file_and_arraylist(provider1, "provider.txt", providerID);
    	      	  printing.printSomething("\nProvider with ID " + providerID + " successfully deleted .");}

    	      	break;    
            }}    
                          /////////////////////////////////////////////////////////////////////// 
      private void DiscountManagementOptions(int C) {
    	  Scanner scanner = new Scanner(System.in);
    	switch (C)
    	{
    	case 2:
    		addDiscount(scanner, "discounts.txt");
    		
            break;
    	 case 4:
    		 editDiscountfrom(scanner, "discounts.txt");
           
           break;
       case 3:
    	   removeDiscount( scanner,"discounts.txt");
           break;
       case 1:
    	   viewAllDiscounts("discounts.txt");
           break;
       case 5:
           System.out.println("Exiting...");
           break;
       default:
           printing.printSomething(INVALID_CHOICE);
    	     break;
    		
    	}
    }
	                     ///////////////////////////////////////////////////////////////////////    
      private void PackageManagementOptions(int r)
      {
      	
      	Scanner scanner = new Scanner(System.in);
      	switch (r)
      	{
      	case 1:
      		viewAllPackagesFromFile( "package.txt");
              break;
      	 case 2:
               addPackage(scanner, "package.txt");              
               break;
           case 3:
          	 deletePackageById( scanner,"package.txt");
               break;
           case 4:
               updatePackage(scanner,"package.txt");
               break;
           case 5:
               System.out.println("Exiting...");
               break;
           default:
               printing.printSomething(INVALID_CHOICE);
        	     break;     		
      	}
      }
                        /////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      public void viewCustomersanddelete() {
          updateCustomersList();
          printing.printSomething("List of Customers: \n");
          for (Customer customer1 : customers) {            
              tmp = customer1.getId() + "\t  "+customer1.getUsername() + "  "+customer1.getaddress() + "  "+customer1.getphone() + "  "+customer1.getEmail()  + "  \n";
              printing.printSomething(tmp);
          }
          printing.printSomething("Please enter the ID of the customer you want to delete:");
          String customerIdToDelete = scanner.next();

          if (deleteCustomer(customerIdToDelete)) {
              printing.printSomething("Customer deleted successfully.");
          
              updateCustomerFile();
          } else {
              printing.printSomething("Customer not found.");
          }
      }
      
      public void viewCustomer() {
          updateCustomersList();
          printing.printSomething("List of Customers: \n");
          for (Customer customer1 : customers) {            
              tmp = customer1.getId() + "\t  "+customer1.getUsername() + "  "+customer1.getaddress() + "  "+customer1.getphone() + "  "+customer1.getEmail()  + "  \n";
              printing.printSomething(tmp);
          }}
      
                        /////////////////////////////////////////////////////////////////////////
      public boolean viewallprovider(String filename) {
 		 List<Provider> prov = new ArrayList<>();
 	 		  Provider provider2 = new Provider();
 		   		    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
 		        String line;
 		       while ((line = reader.readLine()) != null) {
 		           
 		         provider2=provider2.getProviderFromLine(line);
 		           
 		           prov.add(provider2);
 		        }
 		    } catch (Exception e) {
 		       e.printStackTrace();
 		    }
 		    if (prov.isEmpty()) {
 		        printing.printSomething("No provider found.\n");
 		        return false;
 		    } 		    
 		    printing.printSomething("List of providers: \n");
           for (Provider providers2 : prov) {
 		        System.out.println(providers2); 
 		    }
 		   return true;}
                       /////////////////////////////////////////////////////////////////////////			
      public boolean viewallservice(String filename) {
      	updateServiceList();
  		    if (serviceDetails.isEmpty()) {
  		        printing.printSomething("No Services found.\n");
  		        return false;}
    		    printing.printSomething("List of Services : \n");
    		    for (ServiceDetails service : serviceDetails) {
  		    	printing.printSomething(service.toString ());
  		       }
  		     return true;}
                    /////////////////////////////////////////////////////////////////////////			
  
      /*
       * public  boolean view_service_accordingIDs(String filename,List<String> serviceIds) {
    	  updateServiceList();
    	    if (serviceDetails.isEmpty()) {
    	        printing.printSomething("No Services found.\n");
    	        return false;
    	    }
    	    
    	    StringBuilder sb = new StringBuilder();
    	  for (String serviceId : serviceIds) {
    	        serviceId = serviceId.replaceAll("\\[\\[|\\]\\]", "");
    	       
    	        for (ServiceDetails service : serviceDetails) {
    	        	if (serviceId.equalsIgnoreCase("[No service]")) {
    	        		sb.append("No service");
    	        	    break;
    	        	}
    	        	
    	            if (service.getServiceID().equals(serviceId)) {
    	            	sb.append(service.getServiceName()).append(".");
    	                break;
    	            }
    	        }
    	    }
    	    
    	    printing.printSomething(sb.toString());
    	    
    	    return true;}
      */

                      /////////////////////////////////////////////////////////////////////////			
      public boolean viewalleventsforAdmin(String filename) {
		  updateEventList(filename);
		    if (events.isEmpty()) {
		        printing.printSomething("No events found.\n");
		        return false; }
		    printing.printSomething("List of Events: \n");		    
		    for (Event event22 : events) {
		   System.out.println(event22.toString());} 
           return true;}
                     /////////////////////////////////////////////////////////////////////////			
      public static void viewAllVenues(String filename) {
          List<Venue> venues = readVenuesFromFile(filename);

          if (venues.isEmpty()) {
              System.out.println("No venues found.");
          } else {
              System.out.println("All Venues:");
              for (Venue venue : venues) {
                  System.out.print("Venue ID: " + venue.getId());
                  System.out.print("  Name: " + venue.getName());
                  System.out.print("  Address: " + venue.getAddress());
                  System.out.print("  Image: " + venue.getImage());
                  System.out.print("  Capacity: " + venue.getCapacity());
                  System.out.println("  Price: " + venue.getPrice());
                
                  System.out.println();
              }}
          }
                   //////////////////////////////////////////////////////////////////////////
      public static void viewAllVenuesCustomer(String filename) {
    	    List<Venue> venues = readVenuesFromFile(filename);
    	    if (venues.isEmpty()) {
    	        System.out.println("No venues found.");
    	    } else {
    	    	System.out.println("\033[0;35mAll Venues:\033[0m");
                for (Venue venue : venues) {  	           
                	  System.out.print("Name: " + venue.getName() + ", ");
                      System.out.print("Address: " + venue.getAddress() + ", ");
                      System.out.print("Capacity: " + venue.getCapacity() + ", ");
                       System.out.println("Price: " + venue.getPrice());
                   // System.out.println();
    	        }
    	    }}
 	                  //////////////////////////////////////////////////////////////////////
    public boolean viewCostomerevents( String Cid,String filename) throws Exception {
		 boolean foundd = false; 
		    updateeventandcustomer(filename); 
		    for (Customer customer : customers) {
		        if (customer.getId().equals(Cid)) {
		            List<Event> customerEvents = customer.getEvents();
		           
		               if (!customerEvents.isEmpty()){
		                System.out.println("\nHere are all your events:");
		                for (Event event : customerEvents)
		                {System.out.println(event.toString());
		               // view_service_accordingIDs(Cid, event.getServiceIds());
		                foundd=true;}
		                }
		                		                
		                else { System.out.println("Customer found, but has no events.");}
		       }
                       // else  { System.out.println("Customer not found or has no events."); }
		               //return foundd;
		    }
		    
			return foundd;}
                 //////////////////////////////////////////////////////////////////////////
  	private boolean viewproviderservice(String id2) throws FileNotFoundException, IOException {
  		 boolean found = false; 

  		  updateProviderAndServiceList(); 

  		    for (Provider PROV  : providers) {
  		        if ( PROV.getId().equals(id2)) {
  		            List<ServiceDetails> providersservices = PROV.getServiceDetailsList();

  		            if (! providersservices .isEmpty()) {
  		               
  		            	 PROV. displayServiceDetails() ;
  		                
  		            } else {
  		                System.out.println("provider found, but has Servics no .");
  		            }

  		            found = true; 
  		            break; 
  		        }
  		    }

  		   if (!found) {
  		        System.out.println("provider not found or has Servics no .");
  		    }

  		    return found;
  	}
                 //////////////////////////////////////////////////////////////////////////
  	private void viewBusinessReports() {
  	    updateCustomersList();
  	    updateEventList("event.txt");
  	    tmp = "=================Reports================="+"\nThe  number of Customers " + customers.size()+
  	            "\nThe  number of Event " + events.size()+ "\nThe  number of Provider " + Provider.providers.size()+
  	            "\nThe  number of Venues " + countLines("venue.txt")+
  	            "\n==========================================\n\n";
  	    printing.printSomething(tmp);
  	} 
                ///////////////////////////////////////////////////////////////////////////
  	public static void viewAllDiscounts(String filename) {
  	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
  	        String line;
  	        while ((line = reader.readLine()) != null) {
  	            String[] data = line.split(",");
  	            if (data.length == 4) {
  	                String discountId = data[0];
  	                String discountCode = data[1];
  	                String discountPercentage = data[2];
  	                String validityPeriod = data[3];
  	                System.out.println("ID: " + discountId +
  	                        "\t Code: " + discountCode +
  	                        "\t Percentage: " + discountPercentage +
  	                        "\t Period: " + validityPeriod);
  	 }else {
  	                System.err.println("Invalid discount entry: " + line);
  	            }
  	        }
  	    } catch (IOException e) {
  	        System.err.println("Error reading file: " + e.getMessage());
  	    }
  	}
  	           /////////////////////////////////////////////////////////////////////////////
    public static boolean viewAllPackagesFromFile(String filename) {
  	    List<Paackage> paackages = readPackagesFromFile(filename);

  	    if (paackages.isEmpty()) {
  	        System.out.println("No packages found in the file.");
  	        return false;
  	    }

  	    System.out.println("All Packages:");
  	    for (Paackage p : paackages) {
  	        System.out.println("ID: " + p.getId() +
  	                "  Name: " + p.getTitle() +
  	                "  Description: " + p.getDescription() +
  	                "  Price: " + p.getPrice() +
  	                "  Validity Date: " + p.getValidityPeriod());
  	    }
		return true;
  	    
  	}
              //////////////////////////////////////////////////////////////////////////////
    public void showAdminMessage(String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Msg.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2 && parts[parts.length - 1].equals(userId)) {
                    found = true;
                    int end = line.lastIndexOf(' '); 
                    String message = line.substring(0, end);
                    if (message.endsWith(",")) {
                        message = message.substring(0, message.length() - 1); 
                    }
                    System.out.println("Admin Message: " + message);
                }
            }
            if (!found) {
                System.out.println("There is no message for you.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
  	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void deleteLineByValue(String filePath, String value) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(value)) {
                    sb.append(line).append("\n");
                }
            }

            writer.write(sb.toString());
        }
    } 
    public static void updateFile(String filePath, String oldValue, String newValue) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        String line;
        long lastPos = 0;
        while ((line = file.readLine()) != null) {
            if (line.contains(oldValue)) {
                String updatedLine = line.replace(oldValue, newValue);
                file.seek(lastPos);
                file.writeBytes(updatedLine);
            }
            lastPos = file.getFilePointer();
        }
        file.close();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    public static boolean isValidDate(String date) {
      try {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          sdf.setLenient(false);
          sdf.parse(date);
          return true;
      } catch (ParseException e) {
          return false;
      }

  } 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
    public int countLines(String filePath) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////////////////////////// ///////////////////////////////////////////////////
    public Event addevent (String filename) throws Exception {
        updateEventList("requst.txt");
        updateEventList("event.txt");
         event_obj = new Event();	            
         printing.printSomething("\n"+"Enter event Id:");
         id1 = scanner.next();	               	              
         if (searchIdE(id1, "requst.txt")|| searchIdE(id1, "event.txt")) { found =true ;}else found=false;	              
         if (found)
         { printing.printSomething("This account is already existed, Please Sign in."); 
         addevent( filename);return null; }
    	    else  {   	            	  
         event_obj.setEID(id1);
         event_obj.setUID(this.id);
         printing.printSomething("Enter event name:");  
         event_obj.setName(scanner.next());
         printing.printSomething("Enter event date (yyyy-MM-dd):");
         String dateInput = scanner.next();
         Date date;
         try {
             date = DATE_FORMAT.parse(dateInput);
         } catch (ParseException e) {

             e.printStackTrace();
             return null;
         }
         event_obj.setDate(date);	               
         printing.printSomething("Enter event time:");
         event_obj.setTime(scanner.next());
         printing.printSomething("Enter event description:");
         event_obj.setDescription(scanner.next());
         printing.printSomething("Enter event attendee count:");
         event_obj.setAttendeeCount(scanner.next());
         printing.printSomething("Enter event theme :");
         event_obj.setTheme(scanner.next());   
         printing.printSomething("Enter event category:");
         event_obj.setCategory(scanner.next());		           		           
//////////////////////         
         viewAllVenuesCustomer("venue.txt");
         boolean venueAvailable = false;
         String venueName;
         do {
             // Check venue availability
             printing.printSomething("Enter Venue name:");
             venueName = scanner.next();
             if (checkAvailability(venueName, dateInput)) {
                 if (Integer.parseInt(event_obj.getAttendeeCount()) <= getVenueCapacity(venueName)) {
                     venueAvailable = true;
                     event_obj.setVenuename(venueName);
                 } else {
                     printing.printSomething("The attendee count exceeds the capacity of the venue. Please choose another venue.\n");
                 }
             } else {
          	  // printing.printSomething("\n choose another venue \n");
               }
         } while (!venueAvailable );

         pricee=getPriceByVenue(venueName);
           printing.printSomething("\n Do you have discount code ? [Y or N] : ");	               
          String yorn = scanner.next();
         String codee;
         if (yorn.equalsIgnoreCase("y"))
         {
           printing.printSomething("\n Enter the code  : ");	
           codee = scanner.next();
           pricee =applyDiscount(pricee,codee);

          }
         //  printing.printSomething("\n The price is : "+pricee);	               
           addToInvoice (id,id1,event_obj.getName(),pricee);
          printing.printSomething("\n done successfully\n");	 		           		          		           
        addBookingVenue(getVenueIdByName(event_obj.getVenuename()),d,dateInput,"Reserved",id1);
         
///////////////////////	               
         List<String> service_IDs_List = new ArrayList<>();
         ServiceDetails service =new ServiceDetails();
         printing.printSomething("\nDo you want to add a service to your event? (yes/no)\n");
         if (scanner.next().equalsIgnoreCase("yes"))
         {
             do {
                 viewallservice("service.txt");
                 printing.printSomething("\nEnter service ID you want:\n");
                 String SID = scanner.next();
                 ServiceDetails s = Provider.findServiceByID(SID, "service.txt");
                 if (s.getAvailability().equals("available"))
                 {
                     service_IDs_List.add(SID);
                     event_obj.setServiceIds(service_IDs_List);
                     events.add(event_obj);
                 }
                 else 
                 {
                     printing.printSomething("The service is not available.\n");
                 }
                 printing.printSomething("\nDo you want to add another service? (yes/no)\n");
                 }
             while (scanner.next().equalsIgnoreCase("yes"));
             printing.printSomething("\nDone successfully\n");
             double price=service.calculateTotalPrice(service_IDs_List);
            // printing.printSomething("the price is :"+price);
         } else
         {
            // event_obj.addEventToFile(event_obj, filename);
         }
       }
         
     event_obj.addEventToFile(event_obj, filename);
     return event_obj;
 }               
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ServiceDetails addService() throws Exception {
        updateServiceList();
       ServiceDetails service = new ServiceDetails();

       printing.printSomething("\n"+"Enter Service Id:");
       id1 = scanner.next();	               
      
       if (searchIdS(id,"service.txt" )) { found =true ;}else found=false;	              
       if (found)
       { printing.printSomething("This service ID is already in use. Please enter a different Service ID.");
        addService( );return null; }
      else  {   
    	  
   	   service.setServiceID(id1);
   	   service.setProviderID(id);
   	   
        printing.printSomething("Enter service type:");
       service.setServiceType(scanner.next());

       printing.printSomething("Enter service name:");
       service.setServiceName(scanner.next());

       printing.printSomething("Enter service description:");
       service.setDescription(scanner.next());

       printing.printSomething("Enter service price:");
       double price = scanner.nextDouble();
       service.setPrice(price);

       while (true) {
           printing.printSomething("\nEnter service availability (available/not_available): ");
           String availabilityInput = scanner.next().toLowerCase();
           if (availabilityInput.equals("available") || availabilityInput.equals("not_available")) {
               service.setAvailability(availabilityInput);
               break;
           } else {
               // Handle invalid input
               printing.printSomething("Invalid input. Please enter either 'available' or 'not available'.");
           }
       }
       serviceDetails.add(service);
     ServiceDetails. addServiceToFile(service);
      printing.printSomething("\nService added successfully.");
       return service;}
       
       
   }
////////////////////////////////////////////////////////////////////////////////////////////////////////	    
    public static void addDiscount(Scanner scanner, String filename) {
        System.out.println("Adding a discount...");
        System.out.print("Enter discount ID: ");
        int discountId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter discount code: ");
        String discountCode = scanner.nextLine();
        System.out.print("Enter discount percentage: ");
        double discountPercentage = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter validity period (YYYY-MM-DD): ");
        String validityPeriod = scanner.nextLine();


        Discount newDiscount = new Discount( discountPercentage,discountId, validityPeriod, discountCode);
        String discountDetails = newDiscount.toString();

        addDiscountToFile(filename, discountDetails);
        System.out.println("Discount successfully added.");
    } 
////////////////////////////////////////////////////////////////////////////////////////////////////////	    
    public static void addPackage(Scanner scanner, String filename) {
        System.out.println("Adding a new package...");
        int id;
        while (true) {
            System.out.print("Enter package ID: ");
            id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (!isPackageIdExists(filename, id)) {
                break;
            }

            System.out.println("Package ID already exists in the file. Please enter a new ID.");
        }

        System.out.print("Enter package name: ");
        String name = scanner.nextLine();

        System.out.print("Enter package description: ");
        String description = scanner.nextLine();

        System.out.print("Enter package price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter package validity period (YYYY-MM-DD): ");
        String validityPeriod = scanner.nextLine();

        String packageDetails = String.format("%d,%s,%s,%.2f,%s", id, name, description, price, validityPeriod);
        addPackageToFile(filename, packageDetails);
        System.out.println("Package successfully added.");
    }
///////////////////////////////////////////////////////////////////////////////////////	    
    public static void addVenue(Scanner scanner, String filename) {
        System.out.println("Adding a new venue...");
        String venueId;
        do {
            System.out.print("Enter venue ID: ");
            venueId = scanner.nextLine();
            if (!venueId.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid venue ID (numeric value): ");
            }
        } while (!venueId.matches("\\d+"));
        System.out.print("Enter venue name: ");
        String name = scanner.nextLine();
        System.out.print("Enter venue address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Image : ");
        String Image = scanner.nextLine();
       int capacity;
        do {
            System.out.print("Enter venue capacity: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid venue capacity (numeric value): ");
                scanner.next(); // Clear the buffer
            }
            capacity = scanner.nextInt();
        } while (capacity <= 0);
        scanner.nextLine(); // Consume newline
       double price;
        do {
            System.out.print("Enter venue price: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid venue price (numeric value): ");
                scanner.next(); // Clear the buffer
            }
            price = scanner.nextDouble();
        } while (price <= 0);
        scanner.nextLine(); // Consume newline
        Venue newVenue = new Venue(venueId, name, address, capacity, price, Image);
        String venueDetails = newVenue.toFileString();
        addVenueToFile(filename, venueDetails);
        System.out.println("Venue successfully added.");
        }
    public void addBookingVenue(String venid, String custid, String date, String status, String eventid) {
        try (FileWriter writer = new FileWriter("venuebook.txt", true)) {
            writer.write(venid + "," + custid + "," + date + "," + status +","+eventid+ "\n");
            System.out.println("Booking added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the booking: " + e.getMessage());
        }
    }
///////////////////////////////////////////////////////////////////////////////////////    
    public static void addToInvoice(String customerId, String eventId, String eventName, double price) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("invoice.txt", true))) {
            writer.write(customerId + "," + eventId + "," + eventName + "," + price);
            writer.newLine();           
        } catch (IOException e) {e.printStackTrace();}
    } 
/////////////////////////////////////////////////////////////////////////////
   
	 
    
////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////
     private void SendmsgtoCustomer(String msg,Event event) {
  	  try {
  		  FileWriter  File = new FileWriter("Msg.txt", true);
          File.append("The Event ")
          .append(event.getName()).append(" ")
                    .append(msg).append(" , ")
                    .append(event.getUID()).append("\n");
                   File.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
  	
  }
///////////////////////////////////////////////////////////////////////////////////////	    
    public static void updatePackage(Scanner scanner, String filename) {
        List<Paackage> packages = readPackagesFromFile(filename);

        if (packages.isEmpty()) {
            System.out.println("No packages found.");
            return;
        }

        while (true) {
            System.out.println("All Packages:");
            viewAllPackagesFromFile("package.txt");
            

            System.out.print("\nEnter ID for package you want to update (or 'exit' to quit): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            int packageId = Integer.parseInt(userInput);

            // Find the package with the specified ID
            Paackage packageToUpdate = null;
            for (Paackage p : packages) {
                if (p.getId() == packageId) {
                    packageToUpdate = p;
                    break;
                }
            }

            if (packageToUpdate == null) {
                System.out.println("Package with ID " + packageId + " not found.");
                continue; // Prompt for another ID
            } else {
                System.out.println("1. ID");
                System.out.println("2. Title");
                System.out.println("3. Price");
                System.out.println("4. Validity Date");
                System.out.println("5. Description");
                System.out.println("6. Exit");

                System.out.print("Select a number: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        int newId;
                        do {
                            System.out.print("Enter a new ID: ");
                            newId = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            if (isPackageIdExists(filename, newId)) {
                                System.out.println("ID already exists in the file. Please enter a new ID.");
                            } else {
                                packageToUpdate.setId(newId);
                                System.out.println("ID is updating successfully.");
                                break;
                            }
                        } while (true);
                        break;
                    case 2:
                        System.out.print("Enter a new title: ");
                        String newTitle = scanner.nextLine();
                        packageToUpdate.setTitle(newTitle);
                        System.out.println("The title is updating successfully.");
                        break;
                    case 3:
                        System.out.print("Enter a new price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        packageToUpdate.setPrice(newPrice);
                        System.out.println("Price is updating successfully.");
                        break;
                    case 4:
                        System.out.print("Enter a new validity date: ");
                        String newValidityDate = scanner.nextLine();
                        packageToUpdate.setValidityPeriod(newValidityDate);
                        System.out.println("Validity date is updating successfully.");
                        break;
                    case 5:
                        System.out.print("Enter a new description: ");
                        String newDescription = scanner.nextLine();
                        packageToUpdate.setDescription(newDescription);
                        System.out.println("Description is updating successfully.");
                        break;
                    case 6:
                        System.out.println("Exit.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                // Save updated packages to file
                savePackagesToFile(filename, packages);

                System.out.print("Do you want to update another package? (yes/no): ");
                userInput = scanner.nextLine();
                if (!userInput.equalsIgnoreCase("yes")) {
                    break;
                }
            }}
        }
///////////////////////////////////////////////////////////////////////////////////////////////
    public static void editVenuefrom(Scanner scanner, String filename) {
        List<Venue> venues = readVenuesFromFile(filename);
       // List<Venue> venues = readVenuesFromFile(filename);

        if (venues.isEmpty()) {
            System.out.println("No venues found.");
        } else {
            System.out.println("All Venues:");
            for (Venue venue : venues) {
                System.out.print("Venue ID: " + venue.getId());
                System.out.print("  Name: " + venue.getName());
                System.out.print("  Address: " + venue.getAddress());
                System.out.print("  Image: " + venue.getImage());
                System.out.print("  Capacity: " + venue.getCapacity());
                System.out.println("  Price: " + venue.getPrice());
              
                System.out.println();
            }
        }
        System.out.print("Enter the ID of the venue to edit: ");
        String venueId = scanner.nextLine();

        Venue oldVenue = null;
        for (Venue venue : venues) {
            if (venue.getId().equals(venueId)) {
                oldVenue = venue;
                break;
            }
        }

        if (oldVenue == null) {
            System.out.println("Venue not found.");
            return;
        }

        System.out.println("Enter new venue details:");
        System.out.print("Venue ID: ");
        String newVenueId = scanner.nextLine();
        System.out.print("Venue name: ");
        String newVenueName = scanner.nextLine();
        System.out.print("Venue address: ");
        String newVenueAddress = scanner.nextLine();
        System.out.print("Image : ");
        String newImage = scanner.nextLine();
        System.out.print("Venue capacity: ");
        int newVenueCapacity = scanner.nextInt();
        System.out.print("Venue price: ");
        double newVenuePrice = scanner.nextDouble();

        Venue newVenue = new Venue(newVenueId, newVenueName, newVenueAddress, newVenueCapacity, newVenuePrice,newImage);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < venues.size(); i++) {
                Venue venue = venues.get(i);
                if (venue.equals(oldVenue)) {
                    writer.write(newVenue.toFileString());
                } else {
                    writer.write(venue.toFileString());
                }
                if (i != venues.size() - 1) {
                    writer.newLine();
                }
            }
            System.out.println("Venue successfully edited.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void editDiscountfrom(Scanner scanner, String filename) {
	    List<Discount> discounts = readDiscountsFromFile(filename);
	    if (discounts.isEmpty()) {
	        System.out.println("No discounts available to edit.");
	        return;
	    }
	    viewAllDiscounts("discounts.txt");
	    System.out.print("Enter the ID of the discount to edit: ");
	    int discountId = scanner.nextInt();
	    scanner.nextLine(); // Consume newline

	    Discount oldDiscount = null;
	    for (Discount discount : discounts) {
	        if (discount.getDiscountId() == discountId) {
	            oldDiscount = discount;
	            break;
	        }
	    }

	    if (oldDiscount == null) {
	        System.out.println("Discount not found.");
	        return;
	    }

	    System.out.println("Enter new discount details:");
	    System.out.print("Discount ID: ");
	    int newDiscountId = scanner.nextInt();
	    scanner.nextLine(); // Consume newline
	    System.out.print("Discount code: ");
	    String newDiscountCode = scanner.nextLine();
	    System.out.print("Discount percentage: ");
	    double newDiscountPercentage = scanner.nextDouble();
	    scanner.nextLine(); // Consume newline
	    System.out.print("Validity period (YYYY-MM-DD): ");
	    String newValidityPeriod = scanner.nextLine();

	    // Validate inputs before creating the new discount
	    if (isValidDiscountPercentage(newDiscountPercentage) && isValidDate(newValidityPeriod)) {
	        Discount newDiscount = new Discount(newDiscountPercentage, newDiscountId, newValidityPeriod, newDiscountCode);

	        discounts.remove(oldDiscount); // Remove old discount
	        discounts.add(newDiscount);    // Add new discount

	        writeDiscountsToFile(filename, discounts); // Write updated discounts to file

	        System.out.println("Discount successfully edited.");
	    } else {
	        System.out.println("Invalid input for discount percentage or validity period.");
	    }
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
  
 
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
   
   
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////   
   
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  
  
/////////////////////////////////////////////////////////////////////////////////////	    


///////////////////////////////////////////////////////////////////////////////////////
    public static void searchEventsByCustomer(String customerId) {
    	String filename = "requst.txt"; 

    	try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    	    String line;
    	    while ((line = reader.readLine()) != null) {
    	      
    	        String[] fields = line.split(",\\s*"); 
    	        
    	 
    	        if (fields.length >= 9 && fields[5].trim().equals(customerId.trim())) {
    	         
    	            System.out.println(line);
    	        }
    	    }
    	} catch (IOException e) {
    	    System.err.println("An error occurred while reading the file: " + e.getMessage());
    	}
    	}

  	public static void searchEvent(String customerId, String searchTerm, int searchFieldIndex) {
    	    String filename = "event.txt";

    	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    	        String line;
    	        boolean eventFound = false;
    	        while ((line = reader.readLine()) != null) {
    	            String[] fields = line.split(",\\s*");

    	            if (fields.length >= 9 && fields[5].trim().equals(customerId.trim()) && fields[searchFieldIndex].trim().equals(searchTerm)) {
    	                System.out.println(line);
    	                eventFound = true;
    	            }
    	        }
    	        if (!eventFound) {
    	            if (searchFieldIndex == 0) {
    	                System.out.println("There is no event with this Name");
    	            } else if (searchFieldIndex == 8) {
    	                System.out.println("There is no event with this Venue");
    	            }
    	        }
    	    } catch (IOException e) {
    	        System.err.println("An error occurred while reading the file: " + e.getMessage());
    	    }
    	}
  	

  



///////////////////////////////////////////////////////////////////////////////////////	    






   

   
   

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
    public static double applyDiscount(double price, String code) {
	    try (BufferedReader br = new BufferedReader(new FileReader("discounts.txt"))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length >= 4) { // Ensure parts array has enough elements
	                String code_ = parts[1];
	                if (code_.equals(code)) {
	                    double percentage = Double.parseDouble(parts[2]);
	                    LocalDate validityDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	                    if (validityDate.isAfter(LocalDate.now())) {
	                        double discountedPrice = price * (1 - percentage / 100);
	                        return discountedPrice;
	                    } else {
	                    	System.out.println("\nThe code is expired");
	                    	return price ; // Example: Return -1 for expired discount
	                    }
	                }
	            } else {
	                System.out.println("Invalid format in discounts file: " + line);
	            }
	        }
	        return price; 
	    } catch (IOException e) {
	        e.printStackTrace();
	        return price;
	    }
	}

    public boolean checkAvailability(String venueName, String date) throws IOException {
        // Look up venue ID by name
        String venueId = getVenueIdByName(venueName);
        
        // If venue name doesn't exist or there's an error getting venue ID, return false
        if (venueId == null) {
            System.out.println("Venue with name " + venueName + " not found.");
            return false;
        }
        return checkAvailabilityById(venueId, date);
    }
    private boolean checkAvailabilityById(String venueId, String date) throws IOException {
        File venueBookFile = new File("venuebook.txt");
        Scanner scanner = new Scanner(venueBookFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            // Check if the parts array has at least 4 elements
            if (parts.length >= 4) {
                String venueIdFromFile = parts[0];
                String dateFromFile = parts[2];
                String reserved = parts[3];

                if (venueIdFromFile.equals(venueId) && dateFromFile.equals(date) && reserved.equalsIgnoreCase("Reserved")) {
                    System.out.println("The venue is reserved on " + date + ". Please choose another venue.");
                    scanner.close();
                    return false;
                }
            }
        }

        scanner.close();
        return true; // Venue available
    }

    public static double getPriceByVenue(String venueName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("venue.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6 && data[1].trim().equals(venueName)) {
                    return Double.parseDouble(data[5]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + e.getMessage());
        }
        return 0; // Venue name not found or error occurred
    }
    private String getVenueIdByName(String venueName) throws IOException {
        File venueFile = new File("venue.txt");
        Scanner scanner = new Scanner(venueFile);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String venueId = parts[0];
            String name = parts[1];
            
            if (name.equalsIgnoreCase(venueName)) {
                scanner.close();
                return venueId;
            }
        }
        
        scanner.close();
        return null; // Venue not found
    }
    public int getVenueCapacity(String venueName) throws IOException {
        File venueFile = new File("venue.txt");
        Scanner scanner = new Scanner(venueFile);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String name = parts[1];
            if (name.equalsIgnoreCase(venueName)) {
                scanner.close();
                return Integer.parseInt(parts[4]); // Assuming capacity is at index 4
            }
        }
        
        scanner.close();
        return -1; // Venue not found
    }
////////////////////////////////////////////////////////////////////////////         
public void selectpackagee() throws Exception {
    Scanner scanner = new Scanner(System.in);

    try {
        System.out.print("Enter the ID for the package you want: ");
        int packageId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if the package ID exists
        if (!Paackage.isPackageIdExists("package.txt", packageId)) {
            System.out.println("Package ID doesn't exist.");
            return;
        }

        addevent("requst.txt");

        System.out.println("Request successfully saved.");
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
    }
}
public static boolean isPackageIdExists(String filename, int id) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && Integer.parseInt(parts[0]) == id) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}
public static boolean isValidDiscountPercentage(double discountPercentage) {
    return discountPercentage >= 0 && discountPercentage <= 100;
}
public static boolean isVenueIdExists(String filename, String venueId) {
      try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String line;
          while ((line = reader.readLine()) != null) {
              if (line.startsWith(venueId + ",")) {
                  return true; // Venue ID exists in the file
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      return false; // Venue ID does not exist in the file
  }

  





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void addVenueToFile(String filename, String venueDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(venueDetails);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static void addDiscountToFile(String filename, String discountDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(discountDetails);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static void addPackageToFile(String filename, String packageDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(packageDetails);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static void savePackagesToFile(String filename, List<Paackage> packages) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Paackage p : packages) {
            writer.write(p.toFileString());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void writeDiscountsToFile(String filename, List<Discount> discounts) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Discount discount : discounts) {
            writer.write(discount.toString());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static List<Venue> readVenuesFromFile(String filename) {
    List<Venue> venues = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 6) {
                System.out.println("Invalid format in venue file: " + line);
                continue; // Skip this line and proceed to the next one
            }
            String venueId = parts[0];
            String venueName = parts[1];
            String venueAddress = parts[2];
            String imagePath=parts[3];
            int venueCapacity = Integer.parseInt(parts[4]);
            double venuePrice = Double.parseDouble(parts[5]);
            Venue venue = new Venue(venueId, venueName, venueAddress ,venueCapacity, venuePrice,imagePath);
            venues.add(venue);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return venues;
}
public static List<Paackage> readPackagesFromFile(String filename) {
    List<Paackage> packages = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String title = parts[1];
            String description = parts[2];
            double price = Double.parseDouble(parts[3]);
            String validityDate = parts[4];
            Paackage p = new Paackage();
            p.setTitle(title); // Set the title
            p.setDescription(description);
            p.setId(id);
            p.setPrice(price);
            p.setValidityPeriod(validityDate);
            packages.add(p);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return packages;
}
public static List<Discount> readDiscountsFromFile(String filename) {
	    List<Discount> discounts = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length >= 4) {
	                int discountId = Integer.parseInt(parts[0].trim());
	                String discountCode = parts[1].trim();
	                double discountPercentage = Double.parseDouble(parts[2].trim());
	                String validityPeriod = parts[3].trim();

	                Discount discount = new Discount(discountPercentage, discountId, validityPeriod, discountCode);
	                discounts.add(discount);
	            } else {
	                System.out.println("Invalid format in discounts file: " + line);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return discounts;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void deleteDiscountFromFile(String filename, List<Discount> updatedDiscounts) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Discount discount : updatedDiscounts) {
            writer.write(discount.toString());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
private static void deleteVenueFromFile(String filename, List<Venue> venues) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Venue venue : venues) {
            writer.write(venue.toFileString());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public static void removeDiscount(Scanner scanner, String filename) {
    System.out.println("Removing a discount...");
    System.out.print("Enter the ID of the discount to remove: ");
    int discountIdToRemove = scanner.nextInt();
    scanner.nextLine(); 

    List<Discount> discounts = readDiscountsFromFile(filename);
    boolean found = false;


    List<Discount> updatedDiscounts = new ArrayList<>();

    // Iterate over the discounts to find and remove the specified discount
    for (Discount discount : discounts) {
        if (discount.getDiscountId() == discountIdToRemove) {
            found = true;
            System.out.println("Discount found:");
            System.out.println(discount);
        } else {
            // Add discounts other than the one to be removed to the updated list
            updatedDiscounts.add(discount);
        }
    }

    // If the discount to remove was found, update the file with the updated list of discounts
    if (found) {
    	deleteDiscountFromFile(filename, updatedDiscounts);
        System.out.println("Discount successfully removed.");
    } else {
        System.out.println("Discount not found.");
    }
}

public static void deletePackageById(Scanner scanner, String filename) {

    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        System.out.println("No packages found.");
        return;
    }

    System.out.println("All Packages:");
    for (Paackage p : packages) {
        System.out.println("Package ID: " + p.getId() + " Name: " + p.getTitle());
    }

    while (true) {
        System.out.print("Enter the ID of the package to remove: ");
        int packageIdToRemove = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;

        for (Paackage p : packages) {
            if (p.getId() == packageIdToRemove) {
                found = true;
                packages.remove(p);
                System.out.println("Package with ID " + packageIdToRemove + " successfully removed.");
                break;
            }
        }

        if (found) {
            break; // Exit the loop if the package is found and removed
        } else {
            System.out.println("Package with ID " + packageIdToRemove + " not found.");
            System.out.println("Please insert a new ID.");
        }
    }

    savePackagesToFile(filename, packages);
}

public static void deleteVenueById(Scanner scanner, String filename) {
	
	
    System.out.println("Removing a venue...");
    List<Venue> venues = readVenuesFromFile(filename);

    if (venues.isEmpty()) {
        System.out.println("No venues found.");
    } else {
        System.out.println("All Venues:");
        for (Venue venue : venues) {
            System.out.print("Venue ID: " + venue.getId());
            System.out.print("  Name: " + venue.getName());
            System.out.print("  Address: " + venue.getAddress());
            System.out.print("  Image: " + venue.getImage());
            System.out.print("  Capacity: " + venue.getCapacity());
            System.out.println("  Price: " + venue.getPrice());
          
            System.out.println();
        }
    }
    System.out.print("Enter the ID of the venue to remove: ");
    String venueIdToRemove = scanner.nextLine();

    //List<Venue> venues = readVenuesFromFile(filename);
    boolean found = false;

    List<Venue> updatedVenues = new ArrayList<>();

    // Iterate over the venues to find and remove the specified venue
    for (Venue venue : venues) {
        if (venue.getId().equals(venueIdToRemove)) {
            found = true;
            System.out.println("Venue with ID " + venueIdToRemove + " successfully removed.");
        } else {
            // Add venues other than the one to be removed to the updated list
            updatedVenues.add(venue);
        }
    }

    // If the venue to remove was found, update the file with the updated list of venues
    if (found) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < updatedVenues.size(); i++) {
                Venue venue = updatedVenues.get(i);
                writer.write(venue.toFileString());
                if (i != updatedVenues.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Venue with ID " + venueIdToRemove + " not found.");
    }
}

public void deleteVenueBooking(String eventId, String filename) {
    List<String> lines = new ArrayList<>();
    
    // Read the contents of the file and exclude the line with the specified event ID
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] eventData = line.split(","); // Assuming each line is comma-separated
            if (eventData.length >= 5 && eventData[4].trim().equals(eventId)) {
                // Skip the line with the specified event ID
                continue;
            }
            lines.add(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    // Rewrite the file with updated contents (excluding the line with the specified event ID)
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
                                                     //SINGUP + UPDATE +DELETE ON profiles&FILES//
void customerSignUp() throws Exception {
    customer_obj = new Customer();
    printing.printSomething("Enter your Id: ");
    id = scanner.next();
    found = searchIdU(id);
    if (found) {
        printing.printSomething("This account is already existed, Please Sign in.");
        signInFunction();
    } else {
        customer_obj.setId(id);
        printing.printSomething("Enter your Name: ");
        customer_obj.setName(scanner.next());
        printing.printSomething("Enter your Phone: ");
        customer_obj.setPhone(scanner.next());
        printing.printSomething("Enter your Address: ");
        customer_obj.setAddress(scanner.next());
        printing.printSomething("Enter your Email: ");
        customer_obj.setEmail(scanner.next());
        printing.printSomething("\nThank you! Your information have been recorded"+"\nEnter a password: ");
        customer_obj.setPassword(scanner.next());
        printing.printSomething("\nRegistration done successfully\n");
        customers.add(customer_obj);
        customer_obj .addCustomerToFile(customer_obj);
    }
}
public void updateCustomerProfile(int n) throws IOException {
    String tmp1;
    for (Customer customer1 : customers) {
        if (customer1.getId().equals(id)) {
            switch (n){
                case 1:
                    printing.printSomething(ENTER_NAME);
                    tmp1 = scanner.next();
                    updateFile("customer.txt", customer1.getUsername(), tmp1);
                    customer1.setName(tmp1);
                    break;
                case 2:
                    printing.printSomething("Enter New Phone: ");
                    tmp1 = scanner.next();
                    updateFile("customer.txt", customer1.getphone(), tmp1);
                    customer1.setPhone(tmp1);
                    break;
                case 3:
                    printing.printSomething("Enter New Address: ");
                    tmp1= scanner.next();
                    updateFile("customer.txt", customer1.getaddress(), tmp1);
                    customer1.setAddress(tmp1);
                    break;
                case 4:
                    printing.printSomething("Enter New Email: ");
                    tmp1 = scanner.next();
                    updateFile("customer.txt", customer1.getEmail(), tmp1);
                    customer1.setEmail(tmp1);
                    break;
                default:    printing.printSomething(INVALID_CHOICE);
            }
        }
    }
}
public void deleteCustomerProfile(String val) throws IOException {
    if (val.equalsIgnoreCase("yes")) {
        File inputFile = new File(CUSTOMER_FILE_NAME);
        File tempFile = new File("temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String lineToRemove = id;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
               if (currentLine.contains(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        if (!inputFile.delete()) {
            printing.printSomething("Could not delete the original file.");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            printing.printSomething("Could not rename the temporary file.");
        }
        printing.printSomething("\nAccount Successfully Deleted\n\n");
    }
}
public boolean deleteCustomer(String id) {
    String trimmedId = id.trim();
    for (int i = 0; i < customers.size(); i++) {
        String customerId = customers.get(i).getId().trim(); 
        if (customerId.equals(trimmedId)) {
            customers.remove(i);
            try {
                deleteLineByValue(CUSTOMER_FILE_NAME, id); 
            } catch (IOException e) {
                printing.printSomething("An error occurred while deleting the customer from the file: " + e.getMessage());
            }
            return true;
        }
    }
    return false; 
}

//////////////////////////////////////
public  void providerSignUp() throws Exception {
      provider_obj = new Provider();
      printing.printSomething("Enter your Id: ");
      id = scanner.next();
      found = searchIdP(id);
      if (found) {
      printing.printSomething("This account is already existed, Please Sign in.");
      signInFunction();
      } else {
          provider_obj.setId(id);
          printing.printSomething("Enter your Name: ");
          provider_obj.setName(scanner.next());
          printing.printSomething("Enter your Phone: ");
          provider_obj.setPhone(scanner.next());
          printing.printSomething("Enter your Address: ");
          provider_obj.setAddress(scanner.next());
          printing.printSomething("Enter your Email: ");
          provider_obj.setEmail(scanner.next());
          printing.printSomething("\nThank you! Your information have been recorded"+"\nEnter a password: ");
          provider_obj.setPassword(scanner.next());
          printing.printSomething("\nRegistration done successfully\n");
          providers.add(provider_obj);
          Provider. addProviderToFile(provider_obj);
        }
}
public void updateProviderProfile(int n) throws IOException {
    String tmp1;
    for (Provider provider1 : providers) {
        if (provider1.getId().equals(id)) {
            switch (n){
                case 1:
                    printing.printSomething(ENTER_NAME);
                    tmp1 = scanner.next();
                    updateFile("provider.txt", provider1.getUsername(), tmp1);
                    provider1.setName(tmp1);
                    break;
                case 2:
                    printing.printSomething("Enter New Phone: ");
                    tmp1 = scanner.next();
                    updateFile("provider.txt", provider1.getphone(), tmp1);
                    provider1.setPhone(tmp1);
                    break;
                case 3:
                    printing.printSomething("Enter New Address: ");
                    tmp1= scanner.next();
                    updateFile("provider.txt", provider1.getaddress(), tmp1);
                    provider1.setAddress(tmp1);
                    break;
                case 4:
                    printing.printSomething("Enter New Email: ");
                    tmp1 = scanner.next();
                    updateFile("provider.txt", provider1.getEmail(), tmp1);
                    provider1.setEmail(tmp1);
                    break;
                default:    printing.printSomething(INVALID_CHOICE);
            }
        }
    }

	
}
/////////////////////////////////////
public void updateCustomerFile() {
    try {
        FileWriter customersFile = new FileWriter(CUSTOMER_FILE_NAME);
        for (Customer customer : customers) {
            customersFile.write(customer.getId() + " , "
                    + customer.getUsername() + " , "
                    + customer.getphone() + " , "
                    + customer.getaddress() + " , "
                    + customer.getEmail() + " , "
                    + customer.getPassword() + " \n ");
              
        }
        customersFile.close();
    } catch (IOException e) { printing.printSomething("An error occurred while updating the customer file: " + e.getMessage());}}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
                                                           //SEARCH IDS//
boolean searchIdU(String id) {
    boolean f = false;
    try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains(id)) {f = true;}
        }
    } catch (IOException e) { printing.printSomething("Error: " + e.getMessage());}
    return f;
}

boolean searchIdP(String id) {
    boolean f = false;
    try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains(id)) { f = true;}
        }
    } catch (IOException e) { printing.printSomething("Error: " + e.getMessage());}
    return f;
}

boolean searchIdE(String id3, String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] items = line.split(" , ");
            if (items.length >= 10) {
                String event_id =items[9].trim();
                if (event_id .equals(id3)) {
                    return true; 
               }
            }
        }
    } catch (IOException e) {
        printing.printSomething("Error: " + e.getMessage());
    } catch (NumberFormatException e) {
        printing.printSomething("Invalid input: " + e.getMessage());
    }
    return false; // Return false if the ID is not found in any line
}

boolean searchIdS(String serviceID, String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] items = line.split(" , ");
            if (items.length >= 7) { 
                String service_id = items[0].trim();
                if (service_id.equals(serviceID)) {
                    return true; 
                }
            }
        }
    } catch (IOException e) {
        printing.printSomething("Error: " + e.getMessage());
    } catch (NumberFormatException e) {
        printing.printSomething("Invalid input: " + e.getMessage());
    }
    return false; 
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
                                                   //UPDATE ON LISTS//
public void updateEventList( String filename) {
      String line;
      events.clear();
      FileReader eventFileReader;
      try {
         eventFileReader = new FileReader(filename);
          BufferedReader lineReader = new BufferedReader(eventFileReader);
          while ((line = lineReader.readLine()) != null) {
              if (line.isEmpty()) continue;
             events.add(Event.getEventFromLine(line));
          }
          lineReader.close();
         eventFileReader.close(); }
         catch (IOException e){ printing.printSomething("An error occurred: " + e.getMessage()); }}
//////////////////////////////////////
public void updateCustomersList() {
    String line;
    customers.clear();
    FileReader customersFileReader;
    try {
        customersFileReader = new FileReader(CUSTOMER_FILE_NAME);
        BufferedReader lineReader = new BufferedReader(customersFileReader);
        while ((line = lineReader.readLine()) != null) {
            //if (line.isEmpty()) continue;
            customers.add(Customer.getCustomerFromLine(line));
        }
        lineReader.close();
        customersFileReader.close();}
        catch (IOException e) { printing.printSomething("An error occurred: " + e.getMessage());}}

//////////////////////////////////////
public void updateeventandcustomer(String filename) throws Exception {
    updateEventList(filename);
    updateCustomersList();

    String E,C = null;
    for (Event event : events) {
        E = event.getUID();
        for (Customer customer : customers) {
            C = customer.getId();
            if (E != null && C != null && E.equals(C)) {
    			customer. Cevents.add (event); 
            }
        }
    }
}
//////////////////////////////////////
public void updateProvidersList() {
    providers.clear(); 
    try (BufferedReader br = new BufferedReader(new FileReader("provider.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            Provider provider = Provider.getProviderFromLine(line); 
            providers.add(provider);
        }}
      catch (IOException e) {printing.printSomething("Error reading provider data: " + e.getMessage()); }}
//////////////////////////////////////
public void updateServiceList() {
    try (BufferedReader br = new BufferedReader(new FileReader("service.txt"))) {
        String line;
        serviceDetails.clear();
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            ServiceDetails service = ServiceDetails.getServiceFromLine(line);
            serviceDetails.add(service);
            }}
             catch (IOException e) { printing.printSomething("An error occurred: " + e.getMessage());}}
/////////////////////////////////////
public void updateProviderAndServiceList() throws FileNotFoundException, IOException {
             updateProvidersList();
			 updateServiceList();
			String P=null,S=null;
			for (Provider  Prov :  providers)  
			{ P=Prov.getId();
	      			for (ServiceDetails Serv : serviceDetails)
	      			{S=Serv.getProviderID();
	      				if (P.equals(S)) 
	      				{Prov.serviceDetailsList.add(Serv); break;}}}}
	      				

//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//==========================================================================================================================================
                                                     //PAGE LISTS//
public void signInPageList(){
    printing.printSomething("\n---------- Sign in Page ----------"+"\n|                                |"+
            "\n|        1. Administrator        |"+"\n|        2. Customer             |"+
            "\n|        3. Provider             |"+"\n|                                |"+
            "\n----------------------------------\n" );
}

public void adminList() {      
    printing.printSomething("\n--------- Welcome to Admin Page --------\n"+SPACE+
            "\n|   1. Customer Management             |"+"\n|   2. Discount Management             |"+
            "\n|   3. Event Management                |"+"\n|   4. Venue Management                |"+
            "\n|   5. Provider Management             |"+"\n|   6. Package Management              |"+
            "\n|   7. View Report                     |"+"\n|   8. Notify Customer By Email        |"+ 
            "\n|   9. Log Out                                                                      |"+
            "\n \n"
    );}
public void customerPageList(){
    printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile          |"+
            "\n|        2. Make An Event              |"+"\n|        3. Update Event               |"+
            "\n|        4. Cancel Event               |"+"\n|        5. Search                     |"+
            "\n|        6. Delete My Profile          |"+"\n|        7. Show Admin MSG             |"+
            "\n|        8. Packages                   |"+"\n|        9. view all my events         |"+
            "\n|        10.view my requst             |"+"\n|       11. Calendar and Scheduling    |"+"\n|        12. Log out                   |\n"+SPACE+"\n"+LINE+"\n"
            +ENTER_CHOICE );}

    public void providerPageList() {
    printing.printSomething("\n-------- Welcome to Providers Page --------\n" + SPACE +
            "\n|        1. Update My Profile           |" + "\n|        2. Add Service                 |" +
            "\n|        3. Update Service              |" + "\n|        4. Delete Service              |" +
            "\n|        5. View Bookings               |" + "\n|        6. View Payments               |" +
            "\n|        7. Log Out                    |\n" +
            SPACE + "\n----------------------------------------\n" + ENTER_CHOICE);
}
    public void EventManagementAdminPageList() {
    printing.printSomething(
        "\n\033[1;33m"+
        "---- Welcome to EventManagement Page ----\n" +
        "\033[1;33m"+
        "|   1. IN requst                        |\n" +
        "|   2. All events                       |\n" +
        "|   3. ADD EVENT                        |\n" +
        "|   4. DELETE                           |\n" +
        "|   5. EDIT                             |\n" +
        "|   6. Log Out                          |\n" +
        "\033[1;36m" + "\n"  + "\n\033[0m"
    );
}

public void UserManagementAdminPageList() {
    printing.printSomething(
        "\n\033[1;33m" +
        "---- Welcome to User Management Page ----\n" +
        "\033[1;33m" +
        "|   1. View All                         |\n" +
        "|   2. Delete                           |\n" +
        "|   3. Log Out                          |\n" +
        "\033[1;36m" + "\n" + "\n\033[0m"
    );
}
public void UserSearchPageList() {
 printing.printSomething(
     "\n\033[1;33m" +
     "---- Welcome to User Search Page ----\n" +
     "\033[1;33m" +
     "|   1. Search by Event Name          |\n" +
     "|   2. Search by Venue Name          |\n" +
     "|   3. Log out                       |\n"+
     "\033[1;36m" + "\n" + "\n\033[0m"
 );
}

public void VenueManagementadminList() {
    printing.printSomething(
        "\n\033[1;33m" +
        "---- Welcome to Venue Management Page ----\n" +
        "\033[1;33m" +
        "|   1. VIEW ALL                         |\n" +
        "|   2. ADD                              |\n" +
        "|   3. DELETE                           |\n" +
        "|   4. EDIT                             |\n" +
        "|   5.                                  |\n" +
        "|   6.                                  |\n" +
        "|   7. Log Out                          |\n" +
        "\033[1;36m" +"\n" + "\n\033[0m"
    );}

public void ProviderManagementAdminPageList() {
 printing.printSomething(
    "\n\033[1;33m" +
    "---- Welcome to Provider Management Page ----\n" +
    "\033[1;33m" +
    "|   1. VIEW ALL                         |\n" +
    "|   2. DELETE Provider                            |\n" +
    "|   3.  Log Out                         |\n" +

    "\033[1;36m" +"\n" + "\n\033[0m"
);}

public void PackageManagementadminList() {
  printing.printSomething(
      "\n\033[1;33m" +
      "---- Welcome to Package Management Page ----\n" +
      "\033[1;33m" +
      "|   1. VIEW ALL                         |\n" +
      "|   2. ADD                              |\n" +
      "|   3. DELETE                           |\n" +
      "|   4. EDIT                             |\n" +
      "|   5. Log Out                          |\n" +
      "\033[1;36m" +"\n" + "\n\033[0m"
  );}

public void DiscountManagementadminList() {
  printing.printSomething(
      "\n\033[1;33m" +
      "---- Welcome to Discount Management Page ----\n" +
      "\033[1;33m" +
      "|   1. VIEW ALL                         |\n" +
      "|   2. ADD                              |\n" +
      "|   3. DELETE                           |\n" +
      "|   4. EDIT                             |\n" +
      "|   5. Log Out                          |\n" +
      "\033[1;36m" +"\n" + "\n\033[0m"
  );}
//==========================================================================================================================================
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////








/////////////////////////////////////////////////    haneen  new code    /////////////////////////////////////




////////////////////////////////////////////////////////////////////////////////////////////////////////*****************************	
public List<Event> makeListofEvent(String Cid) throws Exception {
updateeventandcustomer("event.txt");

List<Event> customerEvents = new ArrayList<>();

for (Customer customer : customers) {
if (customer.getId().equals(Cid)) {
customerEvents = customer.getEvents();
}
}

//Check if events are found for the customer
if (customerEvents.isEmpty()) {
return null;
} else {
return customerEvents;
}
}
////////////////////////////////////////////////////////////////////////////////////////////

//method to load events for a specific customer in calendar
public Calendar loadEventsForCustomerInCalendar(String customerId) {
List<Event> customerEvents;
try {
customerEvents = makeListofEvent(customerId);
//printing.printSomething("here");

if (customerEvents != null && !customerEvents.isEmpty()) {
Calendar calendar = new Calendar();

for (Event event : customerEvents) {
calendar.addEvent(event);
}return calendar ;
} else {
printing.printSomething( "No events found for customer with ID: " + customerId);

}
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
} return null ;

}




public void displayAllCustomerEvents(Calendar calendar) {
// Keep track of displayed months
Set<String> displayedMonths = new HashSet<>();

// Collect all unique year-month combinations from events and sort them chronologically
List<String> eventYearMonths = new ArrayList<>();
for (Event event : calendar.getEvents()) {
LocalDate eventDate = event.getDateAsLocalDate();
String yearMonthKey = eventDate.getYear() + "-" + eventDate.getMonthValue();
if (!eventYearMonths.contains(yearMonthKey)) {
eventYearMonths.add(yearMonthKey);
}
}
Collections.sort(eventYearMonths);

// Iterate over all unique year-month combinations
for (String yearMonthKey : eventYearMonths) {
String[] parts = yearMonthKey.split("-");
int year = Integer.parseInt(parts[0]);
int month = Integer.parseInt(parts[1]);

// Set the current year and month to the calendar
calendar.setYear(year);
calendar.setMonth(month);

// Print the events for the current year and month
displayCalendarEvents(calendar);
printing.printInColor("\n\n+************************************************************************************************************************************************************************+\n", Printing.ANSI_BLACK);

// Add the displayed month to the set
displayedMonths.add(yearMonthKey);
}
}
// 

public void displayCalendarEvents(Calendar calendar) {
DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

// Print year and month with appropriate color
YearMonth yearMonth = YearMonth.of(calendar.getYear(), calendar.getMonth());
if (yearMonth.isBefore(YearMonth.now())) {
printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_GRAY);
} else if (yearMonth.equals(YearMonth.now())) {
printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_ORANGE);
} else {
printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_BLUE);
}

// Print the header for days of the week
printing.printInColor("\n+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n", Printing.ANSI_LIME);
printing.printInColor("|          Mon          |          Tue          |          Wed          |          Thu          |          Fri          |          Sat          |          Sun          |\n", Printing.ANSI_GREEN);

// Get the first day of the month
LocalDate firstDayOfMonth = LocalDate.of(calendar.getYear(), calendar.getMonth(), 1);

int frow = 0;
boolean inFirstRow = false;
// Set the current date to the first day of the month
LocalDate currentDate = firstDayOfMonth;

int currentDayOfMonth = 1;
// Print the days of the month
while (currentDayOfMonth <= yearMonth.lengthOfMonth()) {//currentDate.getMonthValue() == calendar.getMonth()
printing.printInColor("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n", Printing.ANSI_LIME);

StringBuilder dayRow = new StringBuilder("|");
inFirstRow = false;
// Print the current week
for (int i = 0; i < 7; i++) {
currentDayOfMonth++;
if (currentDate.format(dayFormatter).compareTo("01") == 0 &&currentDate.getMonthValue()==calendar.getMonth()) {
inFirstRow = true;
// Print the calendar grid
for (int k = 0; k < firstDayOfMonth.getDayOfWeek().getValue() - 1; k++) {
dayRow.append("                       |");
frow = k + 2;
}
}

if (inFirstRow && i > 7 - frow) {
break;
}
String dayString = currentDate.format(dayFormatter);
if (currentDate.equals(LocalDate.now())) {
dayString = "[" + dayString + "]";
dayRow.append(String.format("********* %s ********|", dayString));
} else if(currentDate.getMonthValue()==calendar.getMonth()) {
dayRow.append(String.format("          %s           ", dayString));dayRow.append("|");
} else{
dayRow.append(String.format("                       ", dayString));dayRow.append("|");
}   

currentDate = currentDate.plusDays(1);

}

printing.printInColor(dayRow.toString(), Printing.ANSI_GREEN);
printing.printInColor("\n", Printing.ANSI_GREEN);
printing.printInColor("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n", Printing.ANSI_LIME);

// Print events for each day
printEventsForWeek(calendar, currentDate.minusDays(7), currentDate.minusDays(1), dayFormatter);
}

// Print the footer for the days of the week
printing.printInColor("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n", Printing.ANSI_LIME);
}



private void printEventsForWeek(Calendar calendar, LocalDate startDay, LocalDate endDay, DateTimeFormatter dayFormatter) {

for (int i = 0; i < 7; i++) {
for (LocalDate currentDate = startDay; currentDate.isBefore(endDay.plusDays(1)); currentDate = currentDate.plusDays(1)) {
final LocalDate currentDate2 = currentDate;  // Declare currentDate2 as final


StringBuilder eventRow = new StringBuilder("|");

Event eventForDay = calendar.getEvents().stream()
.filter(event -> event.getDateAsLocalDate().isEqual(currentDate2))
.findFirst()
.orElse(null);

// Display the event with appropriate color
if (eventForDay != null && currentDate.getMonthValue()==calendar.getMonth()) {
String eventColor = "";
YearMonth eventYearMonth = YearMonth.of(eventForDay.getDateAsLocalDate().getYear(), eventForDay.getDateAsLocalDate().getMonth());
if (eventYearMonth.isBefore(YearMonth.now())) {
eventColor = Printing.ANSI_GRAY;
} else if (eventYearMonth.equals(YearMonth.now())) {
eventColor = Printing.ANSI_ORANGE;
}else  eventColor = Printing.ANSI_BLUE;


eventRow.append(eventColor).append(String.format("[%-6s] %-14s", eventForDay.getEID(), eventForDay.getName()));
calendar.deleteEvent(eventForDay);

} else {
eventRow.append("                       ");
}

printing.printInColor(eventRow.toString(), Printing.ANSI_LIME);

}
printing.printInColor("|\n", Printing.ANSI_LIME);

}
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////*****************************	  







//Initialize a set to keep track of event IDs for which notifications have been sent
private Set<String> notifiedEvents = new HashSet<>();

//Initialize a scheduled executor service
private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

public void startApproachingUpcomingEvents() {
// Schedule the approachUpcomingEvents method to run every hour
executor.scheduleAtFixedRate(this::approachUpcomingEvents, 0, 1, TimeUnit.MINUTES);
}

public void stopApproachingUpcomingEvents() {
// Shutdown the executor service when you want to stop checking for upcoming events
executor.shutdown();
}

public void approachUpcomingEvents() {
LocalDateTime now = LocalDateTime.now();
updateEventList("event.txt");

List<Event> upcomingEvents = events.stream()
.filter(event -> {
LocalDate eventDate = LocalDate.parse((CharSequence) event.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
return eventDate.isEqual(now.toLocalDate());
})
.collect(Collectors.toList());

for (Event event : upcomingEvents) {
LocalTime eventTime = LocalTime.parse(event.getTime().trim(), DateTimeFormatter.ofPattern("h:mm a"));
LocalTime currentTime = LocalTime.now();
long timeDifferenceMinutes = currentTime.until(eventTime, java.time.temporal.ChronoUnit.MINUTES);

//  System.out.println("test: "+notifiedEvents.contains(event.getEID())+timeDifferenceMinutes );
if (!notifiedEvents.contains(event.getEID()) && timeDifferenceMinutes == 59) {
String customerId = event.getUID();
String recipientDetails = getEmailAndNameFromCustomerFile(customerId);
String[] details = recipientDetails.split("-");
String recipientName = details[1];		            
String messageContent = generateMessageContent(customerId,recipientName, event.getTime(), 60-timeDifferenceMinutes);
String subject = "Notification for Upcoming Event: " + event.getName();

sendNotificationsToParticipants(details[0], subject, messageContent);
notifiedEvents.add(event.getEID());
}
}
}


private String getEmailAndNameFromCustomerFile(String customerId) {
// Update the customers list
updateCustomersList();



// Search for the customer with the specified ID
for (Customer customer1 : customers) {
if (customer1.getId().equals(customerId)) { 	            

return customer1.getEmail()+"-"+customer1.getUsername();
}
}

// If customer not found, return null or throw an exception
return null;
}

private String generateMessageContent(String customerId, String customerName, String eventTime, long hoursDifference) {
// Implement this method to generate a professional message confirming the event start time for the customer with the specified ID
return "Dear " + customerName + ",\n\n"
+ "We are pleased to confirm your registration for the upcoming event.\n\n"
+ "Event Details:\n"
+ "Event Time: " + eventTime + "\n"
+ "Event Start Time: " + hoursDifference + " hours from now\n\n"
+ "Thank you for your participation. We look forward to seeing you at the event.\n\n"
+ "Best regards,\n"
+ "The Event Management Team";
}



private void sendNotificationsToParticipants(String recipientEmail, String subject, String messageContent) {
try {

String senderEmail = "royasmine05@gmail.com";
String password = "igun bclo kbti fzno";

Properties properties = new Properties();
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");

Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
return new javax.mail.PasswordAuthentication(senderEmail, password);
}
});

MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress(senderEmail));
message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
message.setSubject(subject);
message.setText(messageContent);

Transport.send(message);

//    System.out.println("Email sent successfully to " + recipientEmail);
} catch (MessagingException mex) {
mex.printStackTrace();
}
}

















}








	