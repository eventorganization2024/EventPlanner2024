package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class Functions {
	  static Printing printing = new Printing();
	    Scanner scanner = new Scanner(System.in);
	    Customer customer_obj;
	    Provider provider_obj;
	    Event event_obj;
	    int choice;
	    int choice2;
	    boolean found;
	    Admin admin = new Admin();
	   private final ArrayList<Customer> customers = new ArrayList<>();
	   private final ArrayList<Provider> providers = new ArrayList<>();
	   private final ArrayList<Event> events = new ArrayList<>();
 
	    
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

	    
	    private String id;
	    private String password;
	    
///////////////////////////////////////////////////////////////////////////////////////	    
	    void addevent() throws IOException, ParseException {
	              event_obj = new Event();
	            
	               event_obj.setID(this.id);
	               printing.printSomething("Enter event name:");
	               event_obj.setName(scanner.next());
	               
	              	              
	               printing.printSomething("Enter event date (yyyy-MM-dd):");
	               String dateInput = scanner.next();
	               event_obj.setDate(java.sql.Date.valueOf(dateInput));
	               
	               printing.printSomething("Enter event time:");
	               event_obj.setTime(scanner.next());
	               printing.printSomething("Enter event description:");
	               event_obj.setDescription(scanner.next());
	               printing.printSomething("Enter event attendee count:");
	               event_obj.setAttendeeCount(Integer.parseInt(scanner.next()));
	               
	               
	               printing.printSomething("\n done successfully\n");
	               events.add(event_obj);
	              event_obj.addEventToFile(event_obj);
	           }
	       
///////////////////////////////////////////////////////////////////////////////////////	    
	    boolean searchIdU(String id) {
	        boolean f = false;
	        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE_NAME))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                if (line.contains(id)) {
	                    f = true;
	                }
	            }
	        } catch (IOException e) {
	            printing.printSomething("Error: " + e.getMessage());
	        }
	        return f;
	    }

	    boolean searchIdP(String id) {
	        boolean f = false;
	        try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE_NAME))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                if (line.contains(id)) {
	                    f = true;
	                }
	            }
	        } catch (IOException e) {
	            printing.printSomething("Error: " + e.getMessage());
	        }
	        return f;
	    }

/////////////////////////////////////////////////////////////////////////////////////	    
	   void inputs(){
	        printing.printSomething("Enter Id: ");
	        id = scanner.next();
	        printing.printSomething(ENTER_PASSWORD);
	        password = scanner.next();
	    }
	    int x =1;
	    void adminPage() throws IOException, Exception
	    { 
	    	
	        while (x > 0)
	        {
	            adminList();
	            printing.printSomething(ENTER_CHOICE);
	            int c = scanner.nextInt();
	              if (c == 1) 
	               {EventManagementAdminPageList();break;}
	              else if (c == 2) {
	            	UserManagementAdminPageList();break;
	            } else if (c == 3) {
	            	VenueManagementadminList();break; 
	            } else if (c == 4) {
	              
	            } else if (c == 5) {
	              
	            } else if (c == 6) {
	              
	            } else if (c == 7) {
	                signInFunction();
	            } else printing.printSomething(INVALID_CHOICE);
	        }
	    }
	    
///////////////////////////////////////////////////////////////////////////////////////	    
	    
	    
	    
	    
	    
	    
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
	               // updateCustomersList();
	                for(Customer customer1: customers){
	                    if (id.equals(customer1.getId()) && password.equals(customer1.getPassword())) {
	                        found1= true;
	                        break;
	                    }
	                }
	                if(found1){
	                    while (x>0) {
	                        customerPageList();
	                        c = scanner.nextInt();
	                      //  customerOptions(c);
	                    }
	                }
	                else{
	                    printing.printSomething("\nThis account is not exist, Please Sign up.\n");
	                   // customerSignUp();
	                }
	                break;   
	                
	                
	                
	             case 3:
	                for(Provider providerr: providers){
	                    if(providerr.getId().equals(id)){
	                        if(providerr.getPassword().equals(password)) {
	                            while (x > 0) {
	                                ProviderPageList();
	                                c = scanner.nextInt();
	                              //  providerOptions(c);
	                            }
	                        }else{
	                            printing.printSomething("\nSigning in failed, Please check your entered password\n");
	                            printing.printSomething(ENTER_PASSWORD);
	                            password = scanner.next();
	                        }
	                    }else {
	                        printing.printSomething("\nThis account is not exist, Please check the inputs.\n");
	                    }
	                }
	                break;          
	            default:    printing.printSomething("\n"+INVALID_CHOICE);

	        }
	    }

///////////////////////////////////////////////////////////////////////////////////////
       void addCustomerToFile(Customer customer) {
           try {
               FileWriter customersFile = new FileWriter(CUSTOMER_FILE_NAME, true);
               customersFile.append(customer.getId()).append(" , ")
                       .append(customer.getUsername()).append(" , ")
                       .append(customer.getphone()).append(" , ")
                       .append(customer.getaddress()).append(" , ")
                       .append(customer.getEmail()).append(" , ")
                       .append(customer.getPassword()).append(" , ")
                       .append("\n");

               customersFile.close();
           } catch (IOException e) {
               printing.printSomething("An error occurred: " + e.getMessage());
           }
       }
       
///////////////////////////////////////////////////////////////////////////////////////	    
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
              addCustomerToFile(customer_obj);
           }
       }
 //////////////////////////////////////////////////////////////////////////////////
       
       void addProviderToFile(Provider provider) {
           try {
               FileWriter ProvidersFile = new FileWriter(PROVIDER_FILE_NAME, true);
               ProvidersFile.append(provider.getId()).append(" , ")
                       .append(provider.getUsername()).append(" , ")
                       .append(provider.getphone()).append(" , ")
                       .append(provider.getaddress()).append(" , ")
                       .append(provider.getEmail()).append(" , ")
                       .append(provider.getPassword()).append("\n");
                       

               ProvidersFile.close();
           } catch (IOException e) {
               printing.printSomething("An error occurred: " + e.getMessage());
           }
       }
       
///////////////////////////////////////////////////////////////////////////////////////	    
       void providerSignUp() throws Exception {
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
addProviderToFile(provider_obj);
}
}
/////////////////////////////////////////////////////////////////////////////

	  public void signInPageList(){
	        printing.printSomething("\n---------- Sign in Page ----------"+"\n|                                |"+
	                "\n|        1. Administrator        |"+"\n|        2. Customer             |"+
	                "\n|        3. Provider             |"+"\n|                                |"+
	                "\n----------------------------------\n" );
	    }
	    
	  public void adminList(){
	        printing.printSomething("\n--------- Welcome to Admin Page --------\n"+SPACE+
	                "\n|   1. Event Management                 |"+"\n|   2. User Management                  |"+
	                "\n|   3. Venue Management                 |"+"\n|   4. Provider Management              |"+
	                "\n|   5.                                  |"+"\n|   6.                                  |"+
	                "\n|   7. Log Out                          |\n"+SPACE+"\n"+LINE+"\n");}
	    
	  public void customerPageList(){
	        printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile          |"+
	                    "\n|        2. Make An Event                |"+"\n|        3. Update  Event                |"+
	                    "\n|        4. Cancel  Event                |"+ "\n|       5.                              |"+
	                    "\n|        6.                              |"+"\n|        7. Log Out                      |\n"+SPACE+"\n"+LINE+"\n"
	                    +ENTER_CHOICE );
	        }
	        
      public void ProviderPageList(){
	         printing.printSomething("\n-------- Welcome to Providers Page --------\n"+SPACE+
	                    "\n|        1.                            |"+"\n|        2.                            |"+
	                    "\n|        3.                            |"+"\n|        4. Log Out                    |\n"+
	                    SPACE+ "\n----------------------------------------\n"+ENTER_CHOICE);
	        }
      
     public void EventManagementAdminPageList(){
	         printing.printSomething(
	             "\n--------- Welcome to EventManagement  Page --------\n"+SPACE+
		                "\n|   1. View All                         |"+"\n|   2. ADD EVENT                    |"+
		                "\n|   3. DELETE                           |"+"\n|   4. EDIT                         |"+
		                "\n|   5.                                  |"+"\n|   6.                              |"+
		                "\n|   7. Log Out                          |\n"+SPACE+"\n"+LINE+"\n");
	        }
     public void UserManagementAdminPageList(){
         printing.printSomething(
        		 "\n--------- Welcome to  User Management  Page --------\n"+SPACE+
	                "\n|   1. View All                         |"+"\n|   2. ADD                              |"+
	                "\n|   3. DELETE                           |"+"\n|   4. EDIT                             |"+
	                "\n|   5.                                  |"+"\n|   6.                                  |"+
	                "\n|   7. Log Out                          |\n"+SPACE+"\n"+LINE+"\n");
        }
     public void VenueManagementadminList(){
	        printing.printSomething(
	        	 "\n--------- Welcome to Venue Management  Page --------\n"+SPACE+
	                "\n|   1. VIEW ALL                         |"+"\n|   2. ADD                              |"+
	                "\n|   3. DELETE                           |"+"\n|   4. EDIT                             |"+
	                "\n|   5.                                  |"+"\n|   6.                                  |"+
	                "\n|   7. Log Out                          |\n"+SPACE+"\n"+LINE+"\n");}
	    
      
///////////////////////////////////////////////////////////////////////////////////////	    
	        
}
