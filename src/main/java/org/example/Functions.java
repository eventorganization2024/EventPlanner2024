package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class Functions {

	   static Printing printing = new Printing();
	    static Scanner scanner = new Scanner(System.in);
	    Customer customerObj;
	    Provider providerObj;
	    static Event eventObj;
	    static int choice;
	    int choice2;
	    static boolean found;
	    static String tmp;


	    static double price;
	    public static final String IMAGE_LABEL = " Image: ";

	    public static final String ADDRESS_LABEL = " Address: ";
	    static double pricee;
	    static Admin admin = new Admin();
	   
	    static Customer customer1 = new Customer();
	    static Event event1=new Event();
	    static Provider provider1=new Provider();
	    public static final List<Customer> customers = new ArrayList<>();
	    public static final ArrayList<Provider> providers = new ArrayList<>();
	    static final ArrayList<Event> events = new ArrayList<>();
	    public static final String VENUE_ID_LABEL = "Venue ID: ";
	    public static final String NAME_LABEL = " Name: ";
	   protected static final List<Service> serviceDetails = new ArrayList<>();
	   public static final String NOT_FOUND_MESSAGE = " not found.";
	   public static final String ERROR= "Error: ";
  
	    static final String CUSTOMER_FILE_NAME = "customer.txt";
	    static final String PROVIDER_FILE_NAME = "provider.txt";
	    static final String REQUEST_FILE_NAME = "requst.txt";
	    static final String EVENT_FILE_NAME = "event.txt";
	    static final String PACKAGE_FILE_NAME = "package.txt";
	    public static final String VENUE_FILE_NAME = "venue.txt";
	    static final String INVOICE_FILE_NAME ="invoice.txt" ;
	    static final String DISCOUNT_FILE_NAME = "discounts.txt";
	    static final String SERVICE_FILE_NAME = "service.txt";
	    private static final String YELLOW_TEXT_COLOR = "\033[1;33m";
	    static final String ENTER_NAME = "Enter New Name:\n ";
	    static final String SPACE = "|                                       |";
	    public static final String ERROR_PREFIX = "An error occurred: ";
	    public static final String CAPACITY_LABEL = " Capacity: ";
	    public static final String PRICE_LABEL = " Price: ";
	    static final String EXITING = "Exiting...";

	     static final String INVALID_INPUT = "Invalid input: ";

	    static final String ENTER_CHOICE = "Enter your choice:\n ";
	    static final String ENTER_PASSWORD= "Enter Password:";
	    static final String ACCOUNT_ALREADY_EXIST_MESSAGE = "This account is already existed, Please Sign in.\n";
	    static final String THANK_MESSAGE = "  \nThank you! Your information has been recorded.    \nEnter a password: ";
	    public static final String INVALID_CHOICE = "\nInvalid choice! Please enter a valid choice.\n";

	    static final String LINE = "----------------------------------------";
	    static final String LINE_STARS="\n\n+************************************************************************************************************************************************************************+\n";
	    static final String LINE2 = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n";
	    static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	    public static final String ADD=" ADD                              |\n";
	     public static final String VIEW_ALL=" VIEW_ALL                         |\n";
	     public static final String DELETE=" DELETE                           |\n";
	     public   static final String EDIT=" EDIT                             |\n";
	     public static final String LOG_OUT=" LOG_OUT                          |\n";
	    
	    private static final String ONE =  "|   1. ";
	    private static final String TWO =  "|   2. ";
	    private static final String THREE ="|   3. ";
	    private static final String FOUR = "|   4. ";
	    private static final String FIVE = "|   5. ";
	    private static final String SIXE = "|   6. ";
	    
	    
	    
	    
	    public static void printCustomersList() {
	        for (Customer customer : customers) {
	           printing.printSomething(""+customer);
	        }
	    }
	   
	    private static String  evenId ;
	    private static String  JId ;
		   
	    private static  String id;
	    private static  String adminId =admin.getAdminId();
	    public static String customerId;
	    public static String provideId;


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
	   public static void venueManagementOptions(int c) {
	  	  Scanner scanner = new Scanner(System.in);
	  	switch (c)
	  	{
	  	case 2:
	  		  addVenue(scanner, VENUE_FILE_NAME);
	  		
	            break;
	  	 case 4:
	           Venue. editVenuefrom(scanner, VENUE_FILE_NAME);
	           
	           break;
	       case 3:
	    	   Venue.deleteVenueById( scanner,VENUE_FILE_NAME);
	           break;
	       case 1:
	    	   Venue.viewAllVenues(VENUE_FILE_NAME);
	           break;
	       case 5:
	    	   printing.printSomething(EXITING);
	           break;
	       default:
	           printing.printSomething(INVALID_CHOICE);
	    	     break;
	  		
	  	}
	  }     
                            /////////////////////////////////////////////////////////////////////
	  
	  public static void eventManagementOptions(String  userId,int cE) throws Exception {
	       switch (cE) {
	        case 1:
	        if( viewalleventsforAdmin(REQUEST_FILE_NAME)) {
	      	
	        while (true) {     	
	        printing.printSomething("\nEnter the Event ID of the event you want to accept or reject (or enter 'done' to finish): ");
	         String eventID = scanner.next();
	         if ("done".equalsIgnoreCase(eventID)) {
	        	
	          break; // Exit the switch case
	        	}
	        		     
	          printing.printSomething("\nEnter 'Y' to accept the event or 'N' to reject: ");
	         String choice = scanner.next().toUpperCase();        
	         if (choice.equals("Y")) {         	
	             event1= Event.findeventID(eventID, REQUEST_FILE_NAME);
	         	event1.deleteEvent( REQUEST_FILE_NAME, eventID);
	         	Event.addEventToFile(event1, EVENT_FILE_NAME);
	            printing.printSomething("\nEvent accepted.");             
	           sendmsgToCustomer("has been approved",event1);  
	            
	            } else if (choice.equals("N")) {
	         	 event1= Event.findeventID(eventID, REQUEST_FILE_NAME);
	         	sendmsgToCustomer("has been Rejected",event1);
	         	event1.deleteEvent(REQUEST_FILE_NAME, eventID);
	         	printing.printSomething("\nEvent rejected.");
	         	
	        } else {
	           printing.printSomething(INVALID_CHOICE);
	      	     }
	         
	        	}
	        }
	        break;        
	     case 2:
	     	 viewalleventsforAdmin(EVENT_FILE_NAME); /// to String 
	     	 
	     	 while (true) {
	     		 
	     		 printing.printSomething("\nEnter the Event ID you want to view details for (or enter 'done' to finish): ");
	     	     String eventIDToView = scanner.next();
	     	    if ("done".equalsIgnoreCase(eventIDToView)) {
	     	       break;
	     	    } else {
	     	    	 updateEventList(EVENT_FILE_NAME);
	     			   Event e=Event.findeventID(eventIDToView,EVENT_FILE_NAME);
	     	    	
	     			  printing.printSomething(e.toString2());// to String2
	     	    	
	     	    	
	     	       }
	         	 }
	     	 
	         break;
	     case 3:
	       addevent(userId,EVENT_FILE_NAME);
	       break;
	     case 4:
	     	if( viewalleventsforAdmin(EVENT_FILE_NAME)) {
	     		boolean continueDeleting = true;
	            while (continueDeleting) {
	            	
	            	 printing.printSomething("\nEnter the Event ID of the event you want to delete (or enter 'done' to finish): ");
	                  String eventID = scanner.next();
	                  if ("done".equalsIgnoreCase(eventID)) {
	                      continueDeleting = false; // Exit the loop if the user enters 'done'
	                  } 
	                  else {
	                      event1.deleteEvent( EVENT_FILE_NAME, eventID);
	     	              printing.printSomething("\nEvent with ID " + eventID + " successfully deleted .");
	     	              continueDeleting = false;
	                  }
	     	       }
	            }
	     	break;
	     case 5:
	     	 if (viewalleventsforAdmin(EVENT_FILE_NAME)) {
	     		 boolean continueUpdating = true;
	             while (continueUpdating) {
	      	    printing.printSomething("Please enter the Event ID you want to update: ");
	             String eventid=scanner.next();
	             if ("done".equalsIgnoreCase(eventid)) {
	                 continueUpdating = false; // Exit the loop if the user enters 'done'
	             } else {
	             event1.updateEvent(eventid, EVENT_FILE_NAME); 
	             continueUpdating = false;            
	             viewalleventsforAdmin(EVENT_FILE_NAME); 
	             printing.printSomething("\nEvent with ID " + eventid + " successfully updated.");
	             }}}
	        break;
	     case 6:
	    	Main.adminPage(adminId); break;
	     default:
	         printing.printSomething(INVALID_CHOICE);
	  	     break;
	 }
	}
	                           /////////////////////////////////////////////////////////////////////// 
	      static void providerAdminManagementOptions(int p) throws Exception {
	    		switch (p) {
	    	    case 1:
	    	    	 viewallprovider(PROVIDER_FILE_NAME);
	    	    	 break;
	    	    case 2:
	    	    	  if(  viewallprovider(PROVIDER_FILE_NAME)) {
	    	              printing.printSomething("\nEnter the Provider ID  you want to delete it: ");
	    	              String providerID = scanner.next();  
	    	             provider1.deleteProviderFromFileAndArrayList( PROVIDER_FILE_NAME, providerID);
	    	      	  printing.printSomething("\nProvider with ID " + providerID + " successfully deleted .");}

	    	      	break;  
	    	    default:
	 	           printing.printSomething(INVALID_CHOICE);
	 	    	     break;
	            }}   
	      
	      ///////////////////////////////////////////////////////////////////////    
   
     
          /////////////////////////////////////////////////////////////////////////
      public static boolean viewallprovider(String filename) {
 		 List<Provider> prov = new ArrayList<>();
 	 		  Provider provider2 = new Provider();
 		   		    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
 		        String line;
 		       while ((line = reader.readLine()) != null) {
 		           
 		         provider2=Provider.getProviderFromLine(line);
 		           
 		           prov.add(provider2);
 		        }
 		    } catch (Exception e) {
 		    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
 		    }
 		    if (prov.isEmpty()) {
 		        printing.printSomething("No provider found.\n");
 		        return false;
 		    } 		    
 		    printing.printSomething("List of providers: \n");
           for (Provider providers2 : prov) {
        	   printing.printSomething(""+ providers2+"\n"); 
 		    }
 		   return true;}
                       /////////////////////////////////////////////////////////////////////////			
      public static boolean viewallservice() {
      	updateServiceList();
  		    if (serviceDetails.isEmpty()) {
  		        printing.printSomething("No Services found.\n");
  		        return false;}
    		    printing.printSomething("List of Services : \n");
    		    for (Service service : serviceDetails) {
  		    	printing.printSomething(service.toString ());
  		       }
  		     return true;}
                    /////////////////////////////////////////////////////////////////////////			
     public static boolean viewalleventsforAdmin(String filename) {
		  updateEventList(filename);
		    if (events.isEmpty()) {
		        printing.printSomething("No events found.\n");
		        return false; }
		    printing.printSomething("List of Events: \n");		    
		    for (Event event22 : events) {
		   printing.printSomething(event22.toString()+"\n");} 
           return true;}
              /////////////////////////////////////////////////////////////////////////			
              ///////////////////////////////////////////////////////////////////

    	
      public static boolean viewCostomerevents( String cIdIn,String filename)  {
 		 boolean foundd = false; 
 		    updateeventandcustomer(cIdIn,filename); 
 		    for (Customer customer : customers) {
 		        if (customer.getId().equals(cIdIn)) {
 		            List<Event> customerEvents = Customer.getEvents();
 		           
 		               if (!customerEvents.isEmpty()){
 		                printing.printSomething("\nHere are all your events:\n");
 		                for (Event event : customerEvents)
 		                {printing.printSomething(event.toString());
 		                 foundd=true;}
 		                }
 		                		                
 		                else { printing.printSomething("Customer found, but has no events.");}
 		       }
           }
 		    
 			return foundd;}

                 //////////////////////////////////////////////////////////////////////////
  	public static boolean viewproviderservice(String id2)  {
  		 boolean found = false; 
  		providers.clear();
  		  updateProviderAndServiceList(id2); 

  		    for (Provider PROV  : providers) {
  		        if ( PROV.getId().equals(id2)) {
  		            List<Service> providersservices = PROV.getServiceDetailsList();

  		            if (! providersservices .isEmpty()) {
  		               
  		            	 Provider. displayServiceDetails() ;
  		            	found=true;
  		            } else { printing.printSomething("provider found, but has Servics no .");}

  		        }
  		    }
  		    return found;}
                 //////////////////////////////////////////////////////////////////////////
  	static void viewBusinessReports() {
  	    updateCustomersList();
  	    updateEventList(EVENT_FILE_NAME);
  	    
  	    tmp = printing.ANSI_PURPLE+
  	    		"=================Reports================="+"\nThe  number of Customers " + customers.size()+
  	            "\nThe  number of Event " + events.size()+ "\nThe  number of Provider " + Provider.providers.size()+
  	            "\nThe  number of Venues " + countLines(VENUE_FILE_NAME)+
  	            "\n==========================================\n\n";
  	    printing.printSomething(tmp);
  	} 
           

              //////////////////////////////////////////////////////////////////////////////
    public static void showAdminMessage(String userId) {
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
                    printing.printSomething("Admin Message: " + message);
                }
            }
            if (!found) {
                printing.printSomething("There is no message for you.");
            }
        } catch (IOException e) {
        	 printing.printSomething( ERROR_PREFIX + e.getMessage()); }
    }
  	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void deleteLineByValue(String filePath, String value) throws IOException {
    	updateCustomerFile();
    	
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
    


    public static void updateFile(String id,String filePath, String newValue,int index) throws IOException {
    	//updateCustomerFile();
    	
    	try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
             String line;
             long lastPos = 0;
             while ((line = file.readLine()) != null) {
                 String[] items = line.split(" , "); 
                 if (items.length >= 6) {
                     String selectedId = items[0].trim();
                     if (selectedId.equals(id)) {
                         switch (index) {
                             case 1:
                                 
                                 String updatedLine1 = line.replace(items[1], newValue);
                                 file.seek(lastPos);
                                 file.writeBytes(updatedLine1);
                                 break;
                             case 2:
                                 // Replace the value at index 2 (items[2])
                                 String updatedLine2 = line.replace(items[2], newValue);
                                 file.seek(lastPos);
                                 file.writeBytes(updatedLine2);
                                 break;
                             case 3:
                                 // Replace the value at index 3 (items[3])
                                 String updatedLine3 = line.replace(items[3], newValue);
                                 file.seek(lastPos);
                                 file.writeBytes(updatedLine3);
                                 break;
                             
                             default:
                                 // Handle default case
                                 break;
                         }
                         // Update the last position pointer
                        lastPos = file.getFilePointer();
                     }
                 } 
               lastPos = file.getFilePointer();
             }
         }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
    public static int countLines(String filePath) {
    	int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            printing.printSomething(ERROR_PREFIX + e.getMessage());
        }
        return count;
    }
///////////////////////////////////////////////////////////////////////////////// ///////////////////////////////////////////////////
    public static  Event addevent (String idUser,String filename) throws Exception {
    	   SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
           
        updateEventList(REQUEST_FILE_NAME);
        updateEventList(EVENT_FILE_NAME);
         eventObj = new Event();	
        
         printing.printSomething("\n"+"Enter event Id:");
         evenId = scanner.next();	               	              
         if (Event.searchIdE(evenId, REQUEST_FILE_NAME)||Event. searchIdE(evenId, EVENT_FILE_NAME)) { found =true ;}else found=false;	              
         if (found)
         { printing.printSomething(ACCOUNT_ALREADY_EXIST_MESSAGE); 
         addevent(idUser ,filename);return null; }
    	    else  {   	            	  
         eventObj.setEID(evenId);
         eventObj.setUserId(idUser);
         printing.printSomething("Enter event name:");  
         eventObj.setName(scanner.next());
         printing.printSomething("Enter event date (yyyy-MM-dd):");
         String dateInput = scanner.next();
         Date date;
         try {
             date = dateFormat.parse(dateInput);
         } catch (ParseException e) {

        	 printing.printSomething( ERROR_PREFIX + e.getMessage());
             return null;
         }
         eventObj.setDate(date);
         //////////////////////////////////////////////////
         String eventTime, amPmChoice, formattedTime;
         while (true) {
             printing.printSomething("Enter event time (H:MM):");
             eventTime = scanner.next();

             printing.printSomething("Enter 'AM' or 'PM':");
             amPmChoice = scanner.next().toUpperCase();

             // Check if AM/PM choice is valid
             if (amPmChoice.equalsIgnoreCase("AM") || amPmChoice.equalsIgnoreCase("PM")) {
                 formattedTime = eventTime + " " + amPmChoice;
                 break; // Exit the loop if valid input is provided
             } else {
                 printing.printSomething("\nInvalid input. Please enter 'AM' or 'PM'.");
             }
         }

         // Now you have the valid formattedTime
         eventObj.setTime(formattedTime);
     /////////////////////////////////////////////////////////////////
         
         
         printing.printSomething("Enter event description:");
         eventObj.setDescription(scanner.next());
         printing.printSomething("Enter event attendee count:");
         eventObj.setAttendeeCount(scanner.next());
         printing.printSomething("Enter event theme :");
         eventObj.setTheme(scanner.next());   
         printing.printSomething("Enter event category:");
         eventObj.setCategory(scanner.next());		           		           
//////////////////////         
         Venue. viewAllVenuesCustomer(VENUE_FILE_NAME);
         boolean venueAvailable = false;
         String venueName;
         do {
             
             printing.printSomething("\nEnter Venue name:");
             venueName = scanner.next();
             if (Venue.checkAvailability(venueName, dateInput)) {
                 if (Integer.parseInt(eventObj.getAttendeeCount()) <= Venue.getVenueCapacity(venueName)) {
                     venueAvailable = true;
                     eventObj.setVenuename(venueName);
                 } else {
                     printing.printSomething("The attendee count exceeds the capacity of the venue. Please choose another venue.\n");
                 }
             } 
         } while (!venueAvailable );

         pricee=Venue.getPriceByVenue(venueName);
           
        
         

         Venue. addBookingVenue(venueName,idUser,dateInput,"Reserved",evenId);


///////////////////////	               
         List<String> serviceIdsList = new ArrayList<>();
     
         printing.printSomething("\nDo you want to add a service to your event? (yes/no)\n");
         if (scanner.next().equalsIgnoreCase("yes"))
         {
             do {
                 viewallservice();
                 printing.printSomething("\nEnter service ID you want:\n");
                 String servId = scanner.next();
                 Service s = Service.findServiceByID(servId, SERVICE_FILE_NAME);
                 if (s.getAvailability().equals("available"))
                 {
                	 serviceIdsList.add(servId);
                     eventObj.setServiceIds(serviceIdsList);
                     events.add(eventObj);
                 }
                 else 
                 {
                     printing.printSomething("The service is not available.\n");
                 }
                 printing.printSomething("\nDo you want to add another service? (yes/no)\n");
                 }
             while (scanner.next().equalsIgnoreCase("yes"));
             printing.printSomething("\nDone successfully\n");
              price=Service.calculateTotalPrice(serviceIdsList);
           
         } 
       }	     
         
         pricee +=price;
         printing.printSomething("price "+pricee);
        
         
       Event.addEventToFile(eventObj, filename);
     return eventObj;
 }            
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Service addService() throws Exception {
         Scanner s =new Scanner(System.in);
         updateServiceList();
       Service service = new Service();
     
       printing.printSomething("\n"+"Enter Service Id:");
       JId = s.next();	               
      
       if (Service. searchIdS(JId,SERVICE_FILE_NAME )) { found =true ;}else found=false;	              
       if (found)
       { printing.printSomething("This service ID is already in use. Please enter a different Service ID.");
        addService( );return null; }
      else  {   
    	  
   	   service.setServiceID(JId);
   	   service.setProviderID(provideId);
   	   
        printing.printSomething("Enter service type:");
       service.setServiceType(s.next());

       printing.printSomething("Enter service name:");
       service.setServiceName(s.next());

       printing.printSomething("Enter service description:");
       service.setDescription(s.next());

       printing.printSomething("Enter service price:");
       String  price = s.next();
       Double priceD=Double.valueOf(price);
       service.setPrice(priceD);

       while (true) {
           printing.printSomething("Enter service availability(available/not_available):");
           String availabilityInput = s.nextLine();
           if (availabilityInput.equalsIgnoreCase("available") || availabilityInput.equalsIgnoreCase("not_available")) {
               service.setAvailability(availabilityInput);
               break;
           } else {
               // Handle invalid input
               printing.printSomething("\nInvalid input. Please enter either 'available' or 'not available'.\n");
           }
       }
       serviceDetails.add(service);
     Service. addServiceToFile(service);
      printing.printSomething("\nService added successfully.");
       return service;}
       
       
   }
////////////////////////////////////////////////////////////////////////////////////////////////////////	    
  public static void addVenue(Scanner scanner, String filename) {
        Scanner scannerV = new Scanner(System.in);

        printing.printSomething("Adding a new venue...");
        String venueId;
        boolean venueIdExists=false;
        
        // Read venue ID and validate
        do {
            printing.printSomething("Enter venue ID: ");
            venueId = scannerV.nextLine();
            if (!venueId.matches("\\d+")) {
                printing.printSomething("Invalid input. Please enter a valid venue ID (numeric value): ");
                continue;
            }
            venueIdExists =Venue. isVenueIdExists(filename, venueId);
            if (venueIdExists) {
                printing.printSomething("Venue ID already exists. Please enter a unique ID: ");
            }
        } while (!venueId.matches("\\d+") || venueIdExists);

        // Read other details
        printing.printSomething("Enter venue name: ");
        String name = scannerV.nextLine();
        printing.printSomething("Enter venue address: ");
        String address = scannerV.nextLine();
        printing.printSomething("Enter Image : ");
        String image = scannerV.nextLine();

        int capacity;
        // Read and validate capacity
        do {
            printing.printSomething("Enter venue capacity: ");
            while (!scannerV.hasNextInt()) {
                printing.printSomething("Invalid input. Please enter a valid venue capacity (numeric value): ");
                scannerV.next();
            }
            capacity = scannerV.nextInt();
            scannerV.nextLine(); // Consume newline
        } while (capacity <= 0);

        double price;
        // Read and validate price
        do {
            printing.printSomething("Enter venue price: ");
            while (!scannerV.hasNextDouble()) {
                printing.printSomething("Invalid input. Please enter a valid venue price (numeric value): ");
                scannerV.next();
            }
            price = scannerV.nextDouble();
            scannerV.nextLine(); // Consume newline
        } while (price <= 0);

        // Create new venue object and add it to file
        Venue newVenue = new Venue(venueId, name, address, capacity, price, image);
        String venueDetails = newVenue.toFileString();
        Venue.addVenueToFile(filename, venueDetails);
        printing.printSomething("Venue successfully added.");
    }
   
    
    

/////////////////////////////////////////////////////////////////////////////////
   private static void sendmsgToCustomer(String msg, Event event) {
        try (FileWriter fileWriter = new FileWriter("Msg.txt", true)) {
            fileWriter.append("The Event ")
                      .append(event.getName()).append(" ")
                      .append(msg).append(" , ")
                      .append(event.getUsrTd()).append("\n");
        } catch (IOException e) {
            printing.printSomething(ERROR_PREFIX + e.getMessage());
        }
    }


/////////////////////////////////////
public static void updateCustomerFile() {
    try (FileWriter customersFile = new FileWriter(CUSTOMER_FILE_NAME)) {
        for (Customer customer : customers) {
            customersFile.write(customer.getId() + " , "
                    + customer.getUsername() + " , "
                    + customer.getphone() + " , "
                    + customer.getaddress() + " , "
                    + customer.getEmail() + " , "
                    + customer.getPassword() + "\n"); 
        }
    } catch (IOException e) {
        printing.printSomething("An error occurred while updating the customer file: " + e.getMessage());
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
                                                   //UPDATE ON LISTS//
public static void updateEventList(String filename) {
    String line;
    events.clear();
    try (FileReader eventFileReader = new FileReader(filename);
         BufferedReader lineReader = new BufferedReader(eventFileReader)) {
        while ((line = lineReader.readLine()) != null) {
            if (line.isEmpty()) continue;
            events.add(Event.getEventFromLine(line));
        }
    } catch (IOException e) {
        printing.printSomething(ERROR_PREFIX + e.getMessage());
    }
}

//////////////////////////////////////
public static void updateCustomersList() {
    String line;
    customers.clear();
    try (FileReader customersFileReader = new FileReader(CUSTOMER_FILE_NAME);
         BufferedReader lineReader = new BufferedReader(customersFileReader)) {
        while ((line = lineReader.readLine()) != null) {
            customers.add(Customer.getCustomerFromLine(line));
        }
    } catch (IOException e) {
        printing.printSomething(ERROR_PREFIX + e.getMessage());
    }
}
//////////////////////////////////////
public  static void updateeventandcustomer(String cId, String filename) {
	Customer.Cevents.clear();
	events.clear();
	updateEventList(filename);
    updateCustomersList();

    String e = null;
    String c = null;

    for (Event event : events) {
        e = event.getUsrTd();
        for (Customer customer : customers) {
            c = customer.getId();
            if (e != null && c != null && e.equals(c)&&c.equals(cId)) {
            	Customer.Cevents.add (event); 
            }
        }
    }
}
//////////////////////////////////////
public static void updateProvidersList() {
    providers.clear(); 
    try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            Provider provider = Provider.getProviderFromLine(line); 
            providers.add(provider);
        }}
      catch (IOException e) {printing.printSomething("Error reading provider data: " + e.getMessage()); }}
//////////////////////////////////////
public static void updateServiceList() {
    try (BufferedReader br = new BufferedReader(new FileReader(SERVICE_FILE_NAME))) {
        String line;
        serviceDetails.clear();
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            Service service = Service.getServiceFromLine(line);
            serviceDetails.add(service);
            }}
             catch (IOException e) { printing.printSomething(ERROR_PREFIX + e.getMessage());}}
/////////////////////////////////////
public  static void updateProviderAndServiceList(String id)  {
            Provider.serviceDetailsList.clear();
           providers.clear();
           serviceDetails.clear();
            updateProvidersList();
			updateServiceList();
			
			String p=null;
			String s=null;
			for (Provider  Prov :  providers)  
			{ p=Prov.getId();
	      			for (Service Serv : serviceDetails)
	      			{s=Serv.getProviderID();
	      				if (p.equals(s)&& p.equals(id)) 
	      				{Prov.serviceDetailsList.add(Serv); }}}}
	      				

//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//==========================================================================================================================================
                                                     //PAGE LISTS//
public static void signInPageList() {
    printing.printSomething("""
        \n---------- Sign in Page ----------
        |                                |
        |        1. Administrator        |
        |        2. Customer             |
        |        3. Provider             |
        |                                |
        ----------------------------------
        """);
}


public static void adminList() {      
    printing.printSomething("\n--------- Welcome to Admin Page --------\n"+SPACE+
            "\n|   1. Customer Management              |"+"\n|   2. Discount Management              |"+
            "\n|   3. Event Management                 |"+"\n|   4. Venue Management                 |"+
            "\n|   5. Provider Management              |"+"\n|   6. Package Management               |"+
            "\n|   7. View Report                      |"+"\n|   8. Log Out                          |"+            
            "\n"+ SPACE+ "\n-----------------------------------------\n");
    }
public  static void customerPageList(){
    printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile           |"+
            "\n|        2. Make An Event               |"+"\n|        3. Update Event                |"+
            "\n|        4. Cancel Event                |"+"\n|        5. Search                      |"+
            "\n|        6. Delete My Profile           |"+"\n|        7. Show Admin MSG              |"+
            "\n|        8. Packages                    |"+"\n|        9. view all my events          |"+
            "\n|        10.view my requst              |"+"\n|        11. Calendar and Scheduling    |"+
            "\n|        12. Invoice                    |"+
            "\n|        13. Log out                    |\n"+SPACE+"\n"+LINE+"\n"
            +ENTER_CHOICE );}

    public  static void providerPageList() {
    printing.printSomething("\n-------- Welcome to Providers Page --------\n" + SPACE +
            "\n|        1. Update My Profile           |" + "\n|        2. Add Service                 |" +
            "\n|        3. Update Service              |" + "\n|        4. Delete Service              |" +            
            "\n|        5. ALl My Services             |"+  "\n|        6.Log Out                      |" +
            SPACE + "\n----------------------------------------\n" + ENTER_CHOICE);
}
     public static void eventManagementAdminPageList() {
    	printing.printSomething(
    			YELLOW_TEXT_COLOR+
    		    " ---- Welcome to EventManagement Page ----\n"+
    		    YELLOW_TEXT_COLOR+
    		      ONE+" IN requst                        |\n"+
    		      TWO+" All events                       |\n"+
    		    THREE+" ADD EVENT                        |\n"+
    		       FOUR+DELETE+
    		       FIVE+EDIT+
    		       SIXE+LOG_OUT+
    		    " ----------------------------------------\n" + ENTER_CHOICE);
}

public static void userManagementAdminPageList() {
    printing.printSomething(
    		YELLOW_TEXT_COLOR +
        "---- Welcome to User Management Page ----\n" +
        YELLOW_TEXT_COLOR+
        ONE+VIEW_ALL+
        TWO+DELETE+
        THREE+LOG_OUT+
         " ----------------------------------------\n" + ENTER_CHOICE);
        
}
public static void userSearchPageList() {
 printing.printSomething(
		 YELLOW_TEXT_COLOR+
     "---- Welcome to User Search Page ----\n" +
     YELLOW_TEXT_COLOR +
     "|   1. Search by Event Name          |\n" +
     "|   2. Search by Venue Name          |\n" +
     "|   3. Log out                       |\n"+
     " ----------------------------------------\n" + ENTER_CHOICE
 );
}

public static void venueManagementadminList() {
    printing.printSomething(
    		YELLOW_TEXT_COLOR +
        " ---- Welcome to Venue Management Page ----\n" +
        YELLOW_TEXT_COLOR  +
        ONE+VIEW_ALL+
        TWO+ADD+
        THREE+DELETE+
        FOUR+EDIT+
        FIVE+LOG_OUT+
        " ----------------------------------------\n" + ENTER_CHOICE);
    }


public static void providerManagementAdminPageList() {
 printing.printSomething(
		 YELLOW_TEXT_COLOR +
    " -- Welcome to Provider Management Page --\n" +
    YELLOW_TEXT_COLOR +
    ONE+VIEW_ALL+
    TWO+DELETE+
    THREE+LOG_OUT+
    " ----------------------------------------\n" + ENTER_CHOICE
);}

public static void packageManagementadminList() {
  printing.printSomething(
		  YELLOW_TEXT_COLOR +
      " -- Welcome to Package Management Page --\n" +
      YELLOW_TEXT_COLOR+
        ONE+VIEW_ALL+
        TWO+ADD+
        THREE+DELETE+
        FOUR+EDIT+
        FIVE+LOG_OUT+
        " ----------------------------------------\n" + ENTER_CHOICE
  );}

public static void discountManagementadminList() {
  printing.printSomething(
		  YELLOW_TEXT_COLOR +
      " -- Welcome to Discount Management Page --\n" +
      YELLOW_TEXT_COLOR+
        ONE+VIEW_ALL+
        TWO+ADD+
        THREE+DELETE+
        FOUR+EDIT+
        FIVE+LOG_OUT+
        " ----------------------------------------\n" + ENTER_CHOICE
  );}
//==========================================================================================================================================
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}







	
