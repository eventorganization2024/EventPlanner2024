package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Venue {
	private String venueId;
	 private String name;
	    private String address;
	    private int capacity;
	    private double price;
	    private String availability;
	    private String imagepath;
	    private String date;

	   private static final String ERROR_PREFIX = "An error occurred: ";
	    static final String VENUE_FILE_NAME = "venue.txt";
	    static final String VENUEBOOK_FILE_NAME = "venuebook.txt";   
	    static Printing printing = new Printing();
	    private static final String FORMAT = "yyyy-MM-dd";

	
	    public Venue(String name, String address, int capacity, double price,String availability,String id,String image) {
	        this.name = name;
	        this.address = address;
	        this.capacity = capacity;
	        this.price = price;
	        this.availability=availability;
	        this.venueId=id;
	        this.imagepath=image;
	    }

	    public Venue() {
			
		}

		public Venue(String venueId2, String name2, String address2, int capacity2, double price2,String image) {
			this.address=address2;
			this.capacity=capacity2;
			this.name=name2;
			this.price=price2;
			this.venueId=venueId2;
			this.imagepath=image;
		}
		
		
		public Venue(String name , String address , int capacity , 
				String imagepath, double price , String availability,String date ) 
		{
			this.name= name ;
			this.address=address;
			this.capacity=capacity;
			this.imagepath=imagepath;
			this.price=price;
			this.date=date;
			this.availability=availability;
		}


		
		

public static void updateVenueInVenueBook(String eventId, Date newDate, String venueBookFileName) {
	 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
       
	try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
           StringBuilder sb = new StringBuilder();
           String line;
           while ((line = reader.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length >= 5 && parts[4].equals(eventId)) {
               	 Date date;
                    try {
                        date = dateFormat.parse(parts[2]);
                    } catch (ParseException e) {

                    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
                       
                    }
                    date=newDate;
                   line = String.join(",", parts);
               }
               sb.append(line).append("\n");
           }
           // Write the updated content back to the file
           try (FileWriter writer = new FileWriter(venueBookFileName)) {
               writer.write(sb.toString());
           }
       } catch (IOException e) {
    	   printing.printSomething( ERROR_PREFIX + e.getMessage());
       }
   }
   
   
   
public static void updateVenueInVenueBook(String eventId, String newV, String venueBookFileName) {
	 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
	    try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[4].equals(eventId)) {
                // Find the venue ID based on the new venue name
                String venueId = findVenueIdByName(newV, VENUE_FILE_NAME );
                if (venueId != null) {
                    Date date = new Date();
                    try {
                        date = dateFormat.parse(parts[2]);
                        parts[0] = venueId; // Update the venue ID
                        parts[2] = dateFormat.format(date); // Update the date part
                    } catch (ParseException e) {
                    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
                        
                    }
                } else {
                    // Handle case where venue ID is not found for the new venue name
                    // You can log an error message or handle it according to your requirements
                }
            }
            line = String.join(",", parts); // Construct the updated line
            sb.append(line).append("\n");
        }
        
        // Write the updated content back to the file
        try (FileWriter writer = new FileWriter(venueBookFileName)) {
            writer.write(sb.toString());
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}



   
   
public static String findVenueIdByName(String venueName, String filename) {
    String id = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[1].trim().equals(venueName.trim())) {
                id = parts[0].trim(); // Found the venue name, return its ID
                break; // No need to continue searching
            }
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    return id;
}

//////////////////////////////////////////////////////////////////////////////////////////////
public static void addBookingVenue(String venid, String custid, String date, String status, String eventid) {
    try (FileWriter writer = new FileWriter("venuebook.txt", true)) {
        writer.write(venid + "," + custid + "," + date + "," + status +","+eventid+ "\n");
        printing.printSomething("Booking added successfully.");
    } catch (IOException e) {
        printing.printSomething("\nAn error occurred while adding the booking: " + e.getMessage());
    }
}


public static void editVenuefrom(Scanner scanner, String filename) {
    List<Venue> venues = readVenuesFromFile(filename);
   

    if (venues.isEmpty()) {
        printing.printSomething("\nNo venues found.");
    } else {
        printing.printSomething("\nAll Venues:");
        for (Venue venue : venues) {
            printing.printSomething("\n"+Functions.VENUE_ID_LABEL + venue.getId());
            printing.printSomething("\n"+Functions.NAME_LABEL + venue.getName());
            printing.printSomething("\n"+Functions.ADDRESS_LABEL + venue.getAddress());
            printing.printSomething("\n"+Functions.IMAGE_LABEL + venue.getImage());
            printing.printSomething("\n"+Functions.CAPACITY_LABEL  + venue.getCapacity());
            printing.printSomething("\n"+Functions.PRICE_LABEL + venue.getPrice());
          
            printing.printSomething("\n");
        }
    }
    printing.printSomething("Enter the ID of the venue to edit: ");
    String venueId = scanner.nextLine();

    Venue oldVenue = null;
    for (Venue venue : venues) {
        if (venue.getId().equals(venueId)) {
            oldVenue = venue;
            break;
        }
    }

    if (oldVenue == null) {
        printing.printSomething("\nVenue not found.");
        return;
    }

    printing.printSomething("\nEnter new venue details:");
    printing.printSomething(Functions. VENUE_ID_LABEL);
    String newVenueId = scanner.nextLine();
    printing.printSomething("Venue name: ");
    String newVenueName = scanner.nextLine();
    printing.printSomething("Venue address: ");
    String newVenueAddress = scanner.nextLine();
    printing.printSomething("Image : ");
    String newImage = scanner.nextLine();
    printing.printSomething("Venue capacity: ");
    int newVenueCapacity = scanner.nextInt();
    printing.printSomething("Venue price: ");
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
        printing.printSomething("\nVenue successfully edited.");
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}


public static boolean checkAvailability(String venueName, String date) throws IOException {
	    String venueId = getVenueIdByName(venueName);
	    
	    if (venueId == null || venueId.isEmpty()) { // Check if venueId is null or empty
	        printing.printSomething("\nVenue with name " + venueName +Functions. NOT_FOUND_MESSAGE);
	        return false;
	    }
	    return checkAvailabilityById(venueId, date);
	}

private static boolean checkAvailabilityById(String venueId, String date) throws IOException {
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
                printing.printSomething("\nThe venue is reserved on " + date + ". Please choose another venue.");
                scanner.close();
                return false;
            }
        }
    }

    scanner.close();
    return true; 
}

public static double getPriceByVenue(String venueName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(VENUE_FILE_NAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 6 && data[1].trim().equals(venueName)) {
                return Double.parseDouble(data[5]);
            }
        }
    } catch (IOException e) {
        printing.printSomething("Error reading file: " + e.getMessage());
    } catch (NumberFormatException e) {
    	 printing.printSomething("Error parsing price: " + e.getMessage());
    }
    return 0; 
}
private static String getVenueIdByName(String venueName) throws IOException {
    File venueFile = new File(VENUE_FILE_NAME);
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
    return null; 
}
public static int getVenueCapacity(String venueName) throws IOException {
    File venueFile = new File(VENUE_FILE_NAME);
    Scanner scanner = new Scanner(venueFile);
    
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        String name = parts[1];
        if (name.equalsIgnoreCase(venueName)) {
            scanner.close();
            return Integer.parseInt(parts[4]); 
        }
    }
    
    scanner.close();
    return -1;
}
////////////////////////////////////////////////////////////////////////////     





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void addVenueToFile(String filename, String venueDetails) {
try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
    writer.write(venueDetails);
    writer.newLine();
 } catch (IOException e) {
	 printing.printSomething( ERROR_PREFIX + e.getMessage());
}}



public static List<Venue> readVenuesFromFile(String filename) {
    List<Venue> venues = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 6) {
                printing.printSomething("\nInvalid format in venue file: " + line);
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
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    return venues;
}
/////////////////////////////////////////////////////////////////////////
public static void deleteVenueById(Scanner scanner, String filename) {
	 printing.printSomething("\nRemoving a venue...");
	    List<Venue> venues = readVenuesFromFile(filename);

	    if (venues.isEmpty()) {
	        printing.printSomething("\nNo venues found.");
	        return;
	    }

	    printAllVenues(venues);

	    String venueIdToRemove = getVenueIdToRemove(scanner);

	    boolean found = removeVenue0(venues, venueIdToRemove);

	    if (found) {
	        updateVenueFile(filename, venues);
	        printing.printSomething("\nVenue with ID " + venueIdToRemove + "Deleted");

	    } else {
	        printing.printSomething("\nVenue with ID " + venueIdToRemove +Functions. NOT_FOUND_MESSAGE);
	    }
}
	    public static boolean removeVenue0(List<Venue> venues, String venueIdToRemove) {
	        for (int i = 0; i < venues.size(); i++) {
	            if (venues.get(i).getId().equals(venueIdToRemove)) {
	                venues.remove(i);
	                return true;
	            }
	        }
	        return false; // Venue with specified ID not found
	    }
	    

public static void printAllVenues(List<Venue> venues) {
    printing.printSomething("\nAll Venues:");
    for (Venue venue : venues) {
        printing.printSomething(Functions.VENUE_ID_LABEL + venue.getId());
        printing.printSomething(Functions.NAME_LABEL + venue.getName());
        printing.printSomething(Functions.ADDRESS_LABEL + venue.getAddress());
        printing.printSomething(Functions.IMAGE_LABEL + venue.getImage());
        printing.printSomething(Functions. CAPACITY_LABEL + venue.getCapacity());
        printing.printSomething("Price: " + venue.getPrice());
        printing.printSomething("\n");
    }
}

public static void updateVenueFile(String filename, List<Venue> venues) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        for (int i = 0; i < venues.size(); i++) {
            Venue venue = venues.get(i);
            writer.write(venue.toFileString());
            writer.newLine(); 
        }
    } catch (IOException e) {
        printing.printSomething(ERROR_PREFIX + e.getMessage());
    }
}

public  static void deleteVenueBooking(String eventId, String filename) {
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
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    
    // Rewrite the file with updated contents (excluding the line with the specified event ID)
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}


public static String getVenueIdToRemove(Scanner scanner) {
    printing.printSomething("Enter the ID of the venue to remove: ");
    return scanner.nextLine();
}

public static void viewAllVenuesCustomer(String filename) {
    List<Venue> venues =Venue. readVenuesFromFile(filename);
    if (venues.isEmpty()) {
        printing.printSomething("No venues found.");
    } else {
    	printing.printSomething("\033[0;35mAll Venues:\033[0m");
        for (Venue venue : venues) {  	           
        	  printing.printSomething("\nName: " + venue.getName() + ", ");
              printing.printSomething("Address: " +venue.getAddress() + ", ");
              printing.printSomething(Functions. IMAGE_LABEL + venue.getImage());
              printing.printSomething(Functions. CAPACITY_LABEL + venue.getCapacity() + ", ");
               printing.printSomething(Functions. PRICE_LABEL + venue.getPrice()+"\n");
           
        }
    }}
          
public static void viewAllVenues(String filename) {
    List<Venue> venues = Venue.readVenuesFromFile(filename);

    if (venues.isEmpty()) {
        printing.printSomething("No venues found.");
    } else {
        printing.printSomething("All Venues:\n");
        for (Venue venue : venues) {
      	 
            printing.printSomething(Functions.NAME_LABEL + venue.getName());
           
            printing.printSomething(printing.ANSI_GREEN+Functions.VENUE_ID_LABEL + venue.getId());
            printing.printSomething(printing.ANSI_GREEN+Functions. ADDRESS_LABEL  + venue.getAddress());
            printing.printSomething(printing.ANSI_GREEN+Functions.IMAGE_LABEL + venue.getImage());
            printing.printSomething(printing.ANSI_GREEN+Functions.CAPACITY_LABEL + venue.getCapacity());
            printing.printSomething(printing.ANSI_GREEN+Functions.PRICE_LABEL + venue.getPrice()+"\n");
          
          //  printing.printSomething("\n\n");
        }}
    }
 public static boolean isVenueIdExists(String filename, String venueId) {
try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    String line;
    while ((line = reader.readLine()) != null) {
        if (line.startsWith(venueId + ",")) {
            return true; 
        }
    }
} catch (IOException e) {
	  printing.printSomething( ERROR_PREFIX + e.getMessage());
}
return false; 
}
////////////////////////////////////////////////////////////////////////////////////////////////	
		public void setdate(String d) {
			this.date=d;
		}
		
		public String getdate() {
			return date;
		}

	    public String getId() {return venueId;}
	    public void setId(String id) {this.venueId=id;}
	    
	    public String getAvailavility() { return availability;}
	    public void setAvailability(String availability) {this.availability=availability;}

	    public String getName() {
	        return name;
	    }
	    public String getImage() {
	    	return imagepath;
	    
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    public void setImage(String i)
	    {this.imagepath=i;}

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public int getCapacity() {
	        return capacity;
	    }

	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }
	    
	    public String toFileString() {
	        return venueId + "," + name + "," + address + "," +imagepath+"," + capacity + "," + price;
	    }
}
