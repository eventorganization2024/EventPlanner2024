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
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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
	public void updateeventandcustomer() {
		
		updateEventList("requst.txt");
		updateCustomersList();
		
		 for (Customer customer : customers) {
		   for (Event event : events) {
		        if (!event.getUID().equals(customer.getId())) {/// must be equals /////
		            customer.Cevents.add(event);
		        }
		    }
		}

		
		
	}
	         	    
////////////////////////////////////////////////////////////////////////////////////////////////////////	    
	  

	  public void viewCostomerevents( String Cid) {
          updateeventandcustomer();   
          List<Event> allCustomerevents= null;;
           System.out.println(" Customer Events:");
		  for (Customer customer : customers) {
		      if (customer.getId().equals(Cid))
		      {
		          allCustomerevents = customer.getEvents();       
		          for (Event event : allCustomerevents)  {System.out.println(event);}
		          
		      } 
		            
		      else { System.out.println("Customer found, but has no events."); }
		          return;
		          
		      }
		    // If the loop finishes without finding the customer, print appropriate message
		    System.out.println("Customer not found or has no events.");

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
	               {}  //  addevent("requst.txt");}
	            	  //EventManagementAdminPageList();break;}
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
	                "\n|   1. Customer Management   |"+"\n|   2. Discount Management                 |"+
	                "\n|   3. Event Management                |"+"\n|   4. Venue Management                |"+
	                "\n|   5. Provider Management             |"+"\n|   6. Notify Customer By Email        |"+
	                "\n|   7. Calendar and Scheduling         |"+"\n|   8. View Statistics                 |"+
	                "\n|   9. View Report                     |"+ "\n|  10. Log Out                         |\n"
	        );}
	  public void customerPageList(){
	        printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile          |"+
	                "\n|        2. Make An Event              |"+"\n|        3. Update Event                |"+
	                "\n|        4. Cancel Event               |"+ "\n|        5. Invoices                   |"+
	                "\n|        6. Delete My Profile          |"+"\n|        7. Log Out                    |\n"+SPACE+"\n"+LINE+"\n"
	                +ENTER_CHOICE );
	    }
	        
	        
     public void ProviderPageList(){
	         printing.printSomething("\n-------- Welcome to Providers Page --------\n"+SPACE+
	                    "\n|        1.Update My Profile           |"+"\n|        2.                            |"+
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

////////////////////////////////////////////////////////////////////////////////////

	public void viewalleventsforAdmin(String filename) {
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
		    printing.printSomething("List of Events: \n");
		       
		    for (Event event22 : events2) {
		    	   tmp = " Event ID :"+event22.getEID() + "\t  " + "Event Name :"+event22.getName() + "\n";
		           printing.printSomething(tmp); 
		    }
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
////////////////////
   /* public boolean deleteEvent(String eventId) {
        String trimmedId = eventId.trim();
        for (int i = 0; i < events.size(); i++) {
            String eventEID = events.get(i).getEID().trim(); // Assuming getEID() returns the event ID
            if (eventEID.equals(trimmedId)) {
                events.remove(i);
                try {
                    deleteLineByValue("requst.txt", eventId);
                } catch (IOException e) {
                    // Handle IOException
                    printing.printSomething("An error occurred while deleting the event from the file: " + e.getMessage());
                }
                return true;
            }
        }
        return false;
    }
 
 */
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
              printing.printSomething("\n "+" all events for you: "+"\n");
               viewCostomerevents(id);
            
                 printing.printSomething("\n"+"Enter Event ID you wont to update : ");
                 String eventid=scanner.next();
                event1.updateEvent(eventid, "requst.txt");    /////////"event.txt"
                viewCostomerevents(id);         
            break;
            
            
               case 4:
                printing.printSomething("\n");
                viewCostomerevents(id);
                printing.printSomething("\n"+"Enter Event ID you wont to delete : ");
                String eventidd=scanner.next();
               event1.delete_event_from_file_and_arraylist(event1, "requst.txt", eventidd);
               viewCostomerevents(id);         
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
                signInFunction();
                break;
            default: printing.printSomething(INVALID_CHOICE);
        }
    }
    
  
  
     
    	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
