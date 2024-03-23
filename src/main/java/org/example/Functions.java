package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

////////////////////////////////////////////
import org.example.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
//////////////////////////////////////////
import java.util.Properties;
import org.example.Calendar;


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
	    Admin admin = new Admin();
	    static Customer customer1 = new Customer();
	    static Event event1=new Event();
	   public final ArrayList<Customer> customers = new ArrayList<>();
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
////////////////////////////////////////////////////////////////////////////////////////	    
	    
	    
	    
	    public void printCustomers() {
	        System.out.println("List of Customers:");
	        for (Customer customer : customers) {
	            System.out.println(customer);
	        }
	    }
	    public void printProviders() {
	        System.out.println("List of Providers:");
	        for (Provider provider : providers) {
	            System.out.println(provider);
	        }
	    }

	    public void printEvents() {
	        System.out.println("List of Events:");
	        for (Event event : events) {
	            System.out.println(event);
	        }}
	    
	    
//////////////////////////////////////////////////////////////////////////////////////////	    
	public Event addevent (String filename) throws Exception {
		          updateEventList("requst.txt");
		          updateEventList("event.txt");
	               event_obj = new Event();	            
	              
	               printing.printSomething("\n"+"Enter event Id:");
		           id1 = scanner.next();	               
	              
	               if (searchIdE(id, "requst.txt")|| searchIdE(id, "event.txt")) { found =true ;}else found=false;	              
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
	               event_obj.setDate(dateInput);	               
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
	               printing.printSomething("\n done successfully\n");	               
	               events.add(event_obj);	               	               
	               event_obj.addEventToFile(event_obj,filename);
	               return event_obj;}}
	              
	
	             
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
              
    public void  delete_Event_from_arraylist(String filename,String eventid){
              for (Event e : events) 
	           if (e.getEID()== eventid) {events.remove(e);} }
////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		  	           eventFileReader.close();
		  	        } catch (IOException e){
		  	            printing.printSomething("An error occurred: " + e.getMessage());
		  	        }
      
		  	    }
/////////////////////////////		  	     
	public void updateeventandcustomer() throws Exception{
		
		//updateEventList("event.txt");
		updateCustomersList();
		String E=null,C=null;
		 String line;
		 
    	 StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader("event.txt"))) 
    	{   
            while ((line = reader.readLine()) != null)
            {   String[] items = line.split(" , ");
            if (items.length >= 9) 
            {
            	
            	String name = items[0];
                String  date= items[1];
                String time = items[2];
                String description = items[3];
                String attendeeCount = items[4];
                String UID = items[5];
                String theme=items[6];
                String cate=items[7];
                String EID=items[8];
            	
            	
            	
            	   
            	   for (Customer customer : customers)   {
      				 C=customer.getId();
      				 
            	   if (UID.equals(C)) {
            		   
            		   Event Event111=new Event(name, date, time, description, attendeeCount, UID, theme, cate, EID);
            		   customer.Cevents.add(Event111);
            		   break;
            	   }
            	  
            	   
            	   }
            	   
            	   
            	   
            	  
            	  
            	  
            	  
            	  
            	  
            } 
            
           }
            
           
    	}
		
		
		
		
		
		
		
		
		
		
		
	/*	
		
		 for (Event event :allEvents) {
	          E=event.getUID();
	         for (Customer customer : customers)   {
				 C=customer.getId();
				 	 
		        if (C.compareTo(E) == 0) 
		           customer.Cevents.add(event);  /// must be equals /////
			      
		    }
		}

		
	*/	
	}

	  public boolean viewCostomerevents( String Cid) throws Exception {
		  boolean found = false; // Initialize found flag

		    updateeventandcustomer(); // Assuming this method updates the customers list

		    for (Customer customer : customers) {
		        if (customer.getId().equals(Cid)) {
		            List<Event> customerEvents = customer.getEvents();

		            if (!customerEvents.isEmpty()) {
		                System.out.println("\nHere are all your events:");
		                for (Event event : customerEvents) {
		                	   System.out.println(event.toString());
		                }
		            } else {
		                System.out.println("Customer found, but has no events.");
		            }

		            found = true; // Set found flag to true if customer is found
		            break; // Exit the loop once the customer is found
		        }
		    }

		    // If the loop finishes without finding the customer, print appropriate message
		    if (!found) {
		        System.out.println("Customer not found or has no events.");
		    }

		    return found;}
	       
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
	    
	    
	    boolean searchIdE(String id2, String filename) {
	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] items = line.split(" , ");
	                if (items.length >= 9) {
	                    String event_id =items[8].trim();
	                    if (event_id .equals(id2)) {
	                        return true; // Return true if the ID is found
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
		            updateCustomersListFromViewFile(); // Update method to read from file
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
		            } catch (IOException e) {
		                printing.printSomething("Error reading customer data: " + e.getMessage());
		            }

		            if (found1) {
		                // Successful customer login
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
	            	 // updateProviderListFromViewFile(); // Update method to read from file
			           
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
			            } catch (IOException e) {
			                printing.printSomething("Error reading Povider data: " + e.getMessage());
			            }
	            	 
	            	 
	            	 if (foundp) {
			                // Successful customer login
			                while (x > 0) {
			                    ProviderPageList();
			                    c = scanner.nextInt();
			                   //ProviderOptions(c);
			                }
			            } else {
			                printing.printSomething("\nThis account does not exist or the password is incorrect. Please check your inputs.\n");
			                signInFunction(); // Allow the user to retry login
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
                       .append(customer.getPassword())
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
	    
	  public void adminList() {      
	        printing.printSomething("\n--------- Welcome to Admin Page --------\n"+SPACE+
	                "\n|   1. Customer Management             |"+"\n|   2. Discount Management             |"+
	                "\n|   3. Event Management                |"+"\n|   4. Venue Management                |"+
	                "\n|   5. Provider Management             |"+"\n|   6. Notify Customer By Email        |"+
	                "\n|   7. View Statistics                 |"+"\n|   8. View Report                     |"+
	                "\n|   9. Log Out                         |\n"+"\n"
	        );}
	  public void customerPageList(){
	        printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile          |"+
	                "\n|        2. Make An Event              |"+"\n|        3. Update Event               |"+
	                "\n|        4. Cancel Event               |"+"\n|        5. Invoices                   |"+
	                "\n|        6. Delete My Profile          |"+"\n|        7. Calendar and Scheduling    |"+
	                "\n|        8. Log Out                    |\n"+SPACE+"\n"+LINE+"\n"
	                +ENTER_CHOICE );
	    }
	        
	        
     public void ProviderPageList(){
	         printing.printSomething("\n-------- Welcome to Providers Page --------\n"+SPACE+
	                    "\n|        1.Update My Profile           |"+"\n|        2.                            |"+
	                    "\n|        3.                            |"+"\n|        4. Log Out                    |\n"+
	                    SPACE+ "\n----------------------------------------\n"+ENTER_CHOICE);
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
    	        "|   2. ADD                              |\n" +
    	        "|   3. DELETE                           |\n" +
    	        "|   4. EDIT                             |\n" +
    	        "|   5.                                  |\n" +
    	        "|   6.                                  |\n" +
    	        "|   7. Log Out                          |\n" +
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
////////////////////////////////////////////////////////////////////////////////////

	public boolean viewalleventsforAdmin(String filename) {
		 List<Event> events2 = new ArrayList<>();
		//  String filename = "event.txt";
		  Event event2 = new Event();
		  
		    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
		        String line;
		       while ((line = reader.readLine()) != null) {
		           
		          event2=event2.getEventFromLine(line);
		           
		           events2.add(event2);
		        }
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
		    if (events2.isEmpty()) {
		        printing.printSomething("No events found.\n");
		        return false;
		    }
		    
		    printing.printSomething("List of Events: \n");
		    
		    for (Event event22 : events2) {
		        System.out.println(event22); 
		    }
		    
		    return true;
			
	}
	
/////////////////////////////////////////////////////////////////////////////////
   public static void addEmptyLine(String filename) {
		        try (FileWriter fileWriter = new FileWriter(filename, true)) {
		            fileWriter.write("\n");
		        } catch (IOException e) {
		            e.printStackTrace();
		            // Handle exception as needed
		        }
		    
////////////////////////////////////////////////////////////////
	
	}

	public void viewallVenuesforAdmin() {
		// TODO Auto-generated method stub
		
	}

	public void viewallProviders() {
		// TODO Auto-generated method stub
		
	}


	
	public void viewallVenuesforCoustmer() {
		// TODO Auto-generated method stub
		
	}


	    
      
///////////////////////////////////////////////////////////////////////////////////////	    
  
    public void viewCustomers() {
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

///////////////////////////////////////////////////////////////////////////////////////////////
  
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        } catch (IOException e) {
            printing.printSomething("An error occurred while updating the customer file: " + e.getMessage());
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void updateCustomersList() {
        String line;
        customers.clear();
        FileReader customersFileReader;
        try {
            customersFileReader = new FileReader(CUSTOMER_FILE_NAME);
            BufferedReader lineReader = new BufferedReader(customersFileReader);
            while ((line = lineReader.readLine()) != null) {
                if (line.isEmpty()) continue;
                customers.add(Customer.getCustomerFromLine(line)); 
                }
            lineReader.close();
            customersFileReader.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
  
    private void updateCustomersListFromViewFile() {
        customers.clear(); // Clear existing data
        try (BufferedReader br = new BufferedReader(new FileReader("customer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Customer customer = Customer.getCustomerFromLine(line); // Assuming you have a method to create Customer objects from a line
               
                customers.add(customer);
            }
        } catch (IOException e) {
            printing.printSomething("Error reading customer data: " + e.getMessage());
        }
    } 
    
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
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
                    // Check if the current line contains the ID to be deleted
                    if (currentLine.contains(lineToRemove)) {
                        // Skip writing this line to the temporary file
                        continue;
                    }
                    // Write all other lines to the temporary file
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            // Delete the original file
            if (!inputFile.delete()) {
                printing.printSomething("Could not delete the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                printing.printSomething("Could not rename the temporary file.");
            }

            printing.printSomething("\nAccount Successfully Deleted\n\n");
        }
    }

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////   
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
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
             boolean f=  viewCostomerevents(id);
             if (f)
             {   
        	    printing.printSomething("Please enter the Event ID you want to update: ");
               String eventid=scanner.next();
               event1.updateEvent(eventid, "event.txt");    /////////"event.txt"
              viewCostomerevents(id);         
               }
                
               break;
            
            
               case 4:
                printing.printSomething("\n");   
                if (viewCostomerevents(id)) {
                printing.printSomething("\n"+"Please enter the Event ID of the event you want to delete: ");
                String eventidd=scanner.next();
               event1.delete_event_from_file_and_arraylist(event1, "event.txt", eventidd);/////////"event.txt"
               printing.printSomething("\n ");
               viewCostomerevents(id); }        
                break;
                
                
                
           /*  case 5:
                updateCustomersList();
                for (Customer customer1 : customers) {
                    if (customer1.getId().equals(id)) {
                        printing.printSomething(LINE+"\n|                INVOICE               |\n"+LINE);
                        Invoice invoice = new Invoice(customer1);
                        invoice.invoiceRes(customer1);
                        printing.printSomething(LINE);
                    }
                }
                break;*/
          case 6:
                printing.printSomething("\t\t\n--- Delete profile! ---\n\nUr info will be Deleted & ur orders will be cancelled!!\nAre you sure? ");
                String str = scanner.next();
                deleteCustomerProfile(str);
                break;
            case 7:
            	
            	// Load events from the event.txt file
            	Calendar calendar =loadEventsForCustomerInCalendar(id);
                displayAllCustomerEvents(calendar);

            	
            case 8:
                signInFunction();
                break;
            default: printing.printSomething(INVALID_CHOICE);
        }
    }
    
  
  
/////////////////////////////////////////////////////////////////////////////////////	    

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
	            break;
	        case 2:
	           //discount
	            break;
	        case 3:
	        	EventManagementAdminPageList();
	        	int CE=scanner.nextInt();
	        	EventManagementOptions(CE);
	            break;
	        case 4:
	        	VenueManagementadminList();
	            break;
	        case 5:
	            //  Provider Management
	            break;
	        case 6:
	            // Handle Notify Customer By Email
	            break;
	        case 7:
	           // calendar
	            break;
	        case 8:
	            //  View Statistics
	            break;
	        case 9:
	            //  View Report
	            break;
	        case 10:
	        	signInFunction();
	            break;
	        default:
	        	printing.printSomething(INVALID_CHOICE);
	     	    
	    }
}
}
///////////////////////////////////////////////////////////////////////////////////////	    


private void EventManagementOptions(int cE) throws Exception {
    switch (cE) {
        case 1:
           if( viewalleventsforAdmin("requst.txt")) {
            printing.printSomething("\nEnter the Event ID of the event you want to accept or reject: ");
            String eventID = scanner.next();
            
            printing.printSomething("\nEnter 'Y' to accept the event or 'N' to reject: ");
            String choice = scanner.next().toUpperCase();
            if (choice.equals("Y")) {
                event1= event1.findeventID(eventID, "requst.txt");
            	event1.delete_event_from_file_and_arraylist(event1, "requst.txt", eventID);
            	event1.addEventToFile(event1, "event.txt");
               printing.printSomething("\nEvent accepted.");
                ///to send notifation
               
               
            } else if (choice.equals("N")) {
            	event1.delete_event_from_file_and_arraylist(event1, "requst.txt", eventID);
            	printing.printSomething("\nEvent rejected.");
               
            } else {
                
            	printing.printSomething(INVALID_CHOICE);
         	     }}
           
            break;        
        case 2:
        	 viewalleventsforAdmin("event.txt");
            break;
        case 3:
           addevent("event.txt");
            break;
        case 4:
        	if( viewalleventsforAdmin("event.txt")) {
                printing.printSomething("\nEnter the Event ID of the event you want to delete : ");
                String eventID = scanner.next();
                event1.delete_event_from_file_and_arraylist(event1, "event.txt", eventID);
        	  printing.printSomething("\nEvent with ID " + eventID + " successfully deleted .");}

        	break;
        case 5:
        	 if (viewalleventsforAdmin("event.txt")) {   
         	    printing.printSomething("Please enter the Event ID you want to update: ");
                String eventid=scanner.next();
                event1.updateEvent(eventid, "event.txt");    
                viewalleventsforAdmin("event.txt"); 
                printing.printSomething("\nEvent with ID " + eventid + " successfully updated.");

                }
        	
        	
            break;
        case 6:adminPage();
        default:
            printing.printSomething(INVALID_CHOICE);
     	     break;
    }
}


	
/////////////////////////////////////////////////////////////////////////////////////	    
	












/////////////////////////////////////////////////    haneen  new code    /////////////////////////////////////




////////////////////////////////////////////////////////////////////////////////////////////////////////*****************************	
public List<Event> makeListofEvent(String Cid) throws Exception {
updateeventandcustomer();

List<Event> customerEvents = new ArrayList<>();

for (Customer customer : customers) {
if (customer.getId().equals(Cid)) {
customerEvents = customer.getEvents();
}
}

// Check if events are found for the customer
if (customerEvents.isEmpty()) {
return null;
} else {
return customerEvents;
}
}
////////////////////////////////////////////////////////////////////////////////////////////

// method to load events for a specific customer in calendar
public Calendar loadEventsForCustomerInCalendar(String customerId) {
    List<Event> customerEvents;
	try {
		customerEvents = makeListofEvent(customerId);
	
    
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
		                LocalDate eventDate = LocalDate.parse(event.getDate().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
	
	
	

