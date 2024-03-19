package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Venue {
	private String VenueId;
	 private String name;
	    private String address;
	    private int capacity;
	    private double price;
	    private String Availability;
	    private String imagepath;
	    private String date;

	    // Constructor
	    public Venue(String name, String address, int capacity, double price,String Availability,String Id,String Image) {
	        this.name = name;
	        this.address = address;
	        this.capacity = capacity;
	        this.price = price;
	        this.Availability=Availability;
	        this.VenueId=Id;
	        this.imagepath=Image;
	    }
	    
	    
	    
	    public static int getVenueCapacity(String venueName) throws IOException {
	        // Create a File object for the venue file
	        File venueFile = new File("venue.txt");

	        // Create a Scanner object to read from the venue file
	        Scanner scanner = new Scanner(venueFile);

	        // Initialize a variable to store the capacity
	        int capacity = -1; // Default value if venue is not found

	        // Iterate through each line in the venue file
	        while (scanner.hasNextLine()) {
	            // Read the next line
	            String line = scanner.nextLine();

	            // Split the line into parts using comma as the delimiter
	            String[] parts = line.split(",");

	            // Check if the name matches the provided venueName
	            if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(venueName.trim())) {
	                // If a match is found, extract the capacity (assuming it's at index 4)
	                if (parts.length >= 5) {
	                    capacity = Integer.parseInt(parts[4].trim());
	                }
	                // Break out of the loop since the venue is found
	                break;
	            }
	        }

	        // Close the scanner to release resources
	        scanner.close();

	        // Return the capacity of the venue
	        return capacity;
	    }

	    public Venue() {
			// TODO Auto-generated constructor stub
		}

		public Venue(String venueId2, String name2, String address2, int capacity2, double price2,String Image) {
			// TODO Auto-generated constructor stub
			this.address=address2;
			this.capacity=capacity2;
			this.name=name2;
			this.price=price2;
			this.VenueId=venueId2;
			this.imagepath=Image;
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
			this.Availability=availability;
		}

		public void setdate(String d) {
			this.date=d;
		}
		
		public String getdate() {
			return date;
		}

		// Getters and Setters
	    public String getId() {return VenueId;}
	    public void setId(String id) {this.VenueId=id;}
	    
	    public String getAvailavility() { return Availability;}
	    public void setAvailability(String A) {this.Availability=A;}

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
	        return VenueId + "," + name + "," + address + "," +imagepath+"," + capacity + "," + price;
	    }
}

