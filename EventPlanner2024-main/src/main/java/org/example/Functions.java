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
 String d;
	    
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
	               event_obj.setDate(dateInput);	               
	               printing.printSomething("Enter event time:");
	               event_obj.setTime(scanner.next());
	               printing.printSomething("Enter event description:");
	               event_obj.setDescription(scanner.next());
	               printing.printSomething("Enter event attendee count:");
	               event_obj.setAttendeeCount(scanner.next());
	               printing.printSomething("Enter event theme :");
		           event_obj.setTheme(scanner.next());   
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
		                       printing.printSomething("The attendee count exceeds the capacity of the venue. Please choose another venue.");
		                   }
		               } else {
		                   printing.printSomething("The venue is not available. Please choose another venue.");
		               }
		           } while (!venueAvailable);
		           
		           
		           printing.printSomething("Enter event category:");
		           event_obj.setCategory(scanner.next());
	               printing.printSomething("\n done successfully\n");	               
	               events.add(event_obj);	               	               
	               event_obj.addEventToFile(event_obj,"event.txt");
	               addBookingVenue(getVenueIdByName(event_obj.getVenuename()),d,dateInput,"Reserved",id1);
	               return event_obj;}}
	              
	 public void addBookingVenue(String venid, String custid, String date, String status, String eventid) {
	        try (FileWriter writer = new FileWriter("venuebook.txt", true)) {
	            writer.write(venid + "," + custid + "," + date + "," + status +","+eventid+ "\n");
	            System.out.println("Booking added successfully.");
	        } catch (IOException e) {
	            System.out.println("An error occurred while adding the booking: " + e.getMessage());
	        }
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
    
    // Function to get venue ID by its name from the venue file
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
    
    
    public static void viewAllVenuesCustomer(String filename) {
        List<Venue> venues = readVenuesFromFile(filename);

        if (venues.isEmpty()) {
            System.out.println("No venues found.");
        } else {
            System.out.println("All Venues:");
            for (Venue venue : venues) {
               
                System.out.print("Name: " + venue.getName());
                System.out.print("  Address: " + venue.getAddress());
                System.out.print("  Capacity: " + venue.getCapacity());
                System.out.println("  Price: " + venue.getPrice());
              
                System.out.println();
            }
        }}
    
    private boolean checkAvailabilityById(String venueId, String date) throws IOException {
        File venueBookFile = new File("venuebook.txt");
        Scanner scanner = new Scanner(venueBookFile);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String venueIdFromFile = parts[0];
            String dateFromFile = parts[2];
            String reserved = parts[3];
            
            
            if (venueIdFromFile.equals(venueId) && dateFromFile.equals(date) && reserved.equalsIgnoreCase("Reserved")) {
                System.out.println("The venue is reserved on " + date + ". Please choose another venue.");
                scanner.close();
                return false;
            }
        }
        
        scanner.close();
        return true; // Venue available
    }
    
    ///////////////////////////////////////////////////
    
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
////////////////////////////////////////////////////////////////////////////////////////////////////////	    

	
	  public static void addVenueToFile(String filename, String venueDetails) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
	            writer.write(venueDetails);
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method to interactively add venue details
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

	    // Method to interactively add venue details
	  public static void addVenue(Scanner scanner, String filename) {
	        System.out.println("Adding a new venue...");
	        
	        // Validate venue ID
	        String venueId;
	        do {
	            System.out.print("Enter venue ID: ");
	            venueId = scanner.nextLine();
	            if (!venueId.matches("\\d+")) {
	                System.out.println("Invalid input. Please enter a valid venue ID (numeric value): ");
	            }
	        } while (!venueId.matches("\\d+"));

	        // Validate venue name
	        System.out.print("Enter venue name: ");
	        String name = scanner.nextLine();

	        // Validate venue address
	        System.out.print("Enter venue address: ");
	        String address = scanner.nextLine();

	        // Validate Image
	        System.out.print("Enter Image : ");
	        String Image = scanner.nextLine();

	        // Validate venue capacity
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

	        // Validate venue price
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

	        // Create a new Venue object and add it to the file
	        Venue newVenue = new Venue(venueId, name, address, capacity, price, Image);
	        String venueDetails = newVenue.toFileString();
	        addVenueToFile(filename, venueDetails);
	        System.out.println("Venue successfully added.");
	    }
	    
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
	   
	
////////////////////////////////////////////////////////////////////////////////////////////////////////


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
	        d=id;
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
	                "\n|   7. Calendar and Scheduling         |"+"\n|   8. View Statistics                 |"+
	                "\n|   9. View Report                     |"+"\n|  10. Log Out                         |\n"+"\n"
	        );}
	  public void customerPageList(){
	        printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile          |"+
	                "\n|        2. Make An Event              |"+"\n|        3. Update Event               |"+
	                "\n|        4. Cancel Event               |"+"\n|        5. Invoices                   |"+
	                "\n|        6. Delete My Profile          |"+"\n|        7. Log Out                    |\n"+SPACE+"\n"+LINE+"\n"
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
    	        "|   5. Log Out                          |\n" +
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
	        	int C=scanner.nextInt();
	        	VenueManagementOptions(C);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	

