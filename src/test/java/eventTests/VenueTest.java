package eventTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.example.*;
import org.junit.Test;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VenueTest {
	
    static boolean adding = false;
    static boolean updated = false;
    static boolean delete = false;
    Venue v = new Venue();
	 private InputStream inputStream;
private Scanner scanner;
    Printing printing = new Printing();
    Functions func=new Functions();
    List<Venue> venues = new ArrayList<>();
    
    Venue venue1 = new Venue("VenueName", "VenueAddress", 100, 150.0, "Available", "VenueID123", "image.jpg");
   // Venue venue2 = new Venue("VenueName", "VenueAddress", 100, "image.jpg", 150.0, "Available", "2026-01-01");
   
   
    /*@Test 
    public void addVenueTo() {
 	   boolean found;
 	   Venue v1 = new Venue("Palestine_Convention_Center", "Main_Street_Ramallah", 200, 200.0, "Available", "R456", "ramallah_venue_image.jpg");
 	   String venueDetails = v.toFileString(); 
 	   if (func. isVenueIdExists("venue.txt",v1.getId()))found=true ;else found =false;  
 	   if( found) 
 	   {
 		   printing.printSomething("venue found");
 		   
 	   }
 	   else   Functions.addVenueToFile("venue.txt",venueDetails );
 	   
 	   
 	   
    }
	*/
    @Given("that the admin is logged into the system")
    public void thatTheAdminIsLoggedIntoTheSystem() {
        Admin admin = new Admin();
        admin.setState(true);
        assertTrue( admin.getstate()); // Assuming login() method authenticates the administrator
        System.out.println("Administrator is logged into the system.");
    }


    
    @When("select to add a new venue such as details {string}, {double}, {string}, {string}, {string}, {int}")
    public void selectToAddANewVenueSuchAsDetails(String string, Double double1, String string2, String string3, String string4, Integer int1) {
      
    	v.setId(string);
         v.setPrice(double1);
         v.setAddress(string2);
         v.setName(string4);
         v.setCapacity(int1);
         v.setImage(string3);
         String venueDetails = v.toFileString();
         venues.add(v);
    
        boolean found;
		if ( Venue. isVenueIdExists("venue.txt",string))found=true ;else found =false;  
  	   if( found) 
  	   {
  		   printing.printSomething("venue"+string+" found");
	   
  	   }
  	   else   {     
  		  // Functions.addVenue(scanner, "venue.txt");
  		   Venue.addVenueToFile("venue.txt",venueDetails );
       }
  	   
  	  //////////////////////////////////////// 
    }
    @Then("the admin can successfully add the venue to the system.")
    public void theAdminCanSuccessfullyAddTheVenueToTheSystem() {
        // Assuming some logic to add the venue to the system
    	
    	    adding = true;
    	 //   Functions.addVenue(scanner, "venue.txt");
    	    venue1.toFileString();
    	    printing.printSomething("Venue added successfully");
    }
    @When("select to edit venue details with the following changes:")
    public void selectToEditVenueDetails(DataTable changesTable) {
        Map<String, String> changesMap = changesTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : changesMap.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
           	Venue.findVenueIdByName("Palestine_Convention_Center","venue.txt");
            switch (field) {
                case "Address":   
                v.setAddress(value);
                 inputStream = new ByteArrayInputStream(value.getBytes());
                System.setIn(inputStream);
                
                 scanner = new Scanner(System.in);
                Venue. editVenuefrom(scanner,"venue.txt");
//                Functions.updateVenueFile("venue.txt",venues);
               
                    break;
                case "ID":
                	
                    v.setId(value);
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                     Venue.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Name":
                	
                    v.setName(value);
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                     Venue.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Capacity":
                    v.setCapacity(Integer.parseInt(value));
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                     Venue.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Price":
                    v.setPrice(Double.parseDouble(value));
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                     Venue.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Image":
                    v.setImage(value);
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                     Venue.editVenuefrom(scanner,"venue.txt");
                    break;
            }
        }
        updated = true;
    	
//    	
//    	 
    }

  
    String venueIdToRemove;
    @When("select to delete a venue by its {string}")
    public void selectToDeleteAVenueByIts(String venueId) {
      
        
    	 venueIdToRemove = "N123"; // Venue ID to remove
         inputStream = new ByteArrayInputStream(venueIdToRemove.getBytes());
         scanner = new Scanner(inputStream); // Provide venue ID as input

          
         Venue.isVenueIdExists("venue.txt","R456");
         assertTrue(  Venue.isVenueIdExists("venue.txt","N123"));
         assertFalse (  Venue.isVenueIdExists("venue.txt","nmnmnmn"));
        assertFalse( Venue.removeVenue0(venues,"9"));
        //////////////////////////////////////////////////////////////////
        
       
       
    	delete = true;
}


    @Then("the admin can successfully remove the venue from the system.")
    public void theAdminCanSuccessfullyRemoveTheVenueFromTheSystem() {
        if (delete) {
            printing.printSomething("Venue deleted successfully");
        }
    }

    @When("select to view all venues registered in the system")
    public void selectToViewAllVenuesRegisteredInTheSystem() {
        Venue.viewAllVenues("venue.txt");
    	
        Venue.printAllVenues(venues);

    }

    @Then("the admin can see a comprehensive list of all venues.")
    public void theAdminCanSeeAComprehensiveListOfAllVenues() {
        Venue.viewAllVenues("venue.txt");
        Venue.updateVenueFile("venue.txt", venues);
    	
    	
    	//venues.add(venue1);
  //	Functions.updateVenueFile("venue.txt", venues);

    }
    @Test
    public void testVenueConstructorWithAllParameters() {
        Venue venue = new Venue("Venue Name", "Venue Address", 100, 150.0, "Available", "VenueID123", "image.jpg");

        assertEquals("VenueID123", venue.getId());
        assertEquals("Venue Name", venue.getName());
        assertEquals("Venue Address", venue.getAddress());
        assertEquals(100, venue.getCapacity());
        assertEquals(150.0, venue.getPrice(), 0.001);
        assertEquals("Available", venue.getAvailavility());
        assertEquals("image.jpg", venue.getImage());
    }

    @Test
    public void testVenueConstructorWithLimitedParameters() {
        Venue venue = new Venue("VenueID123", "Venue Name", "Venue Address", 100, 150.0, "image.jpg");

        assertEquals("VenueID123", venue.getId());
        assertEquals("Venue Name", venue.getName());
        assertEquals("Venue Address", venue.getAddress());
        assertEquals(100, venue.getCapacity());
        assertEquals(150.0, venue.getPrice(), 0.001);
        assertEquals("image.jpg", venue.getImage());
    }

    @Test
    public void testSetAndGetDate() {
        Venue venue = new Venue();
        venue.setdate("2024-03-27");
        assertEquals("2024-03-27", venue.getdate());
    }

    @Test
    public void testToFileString() {
        Venue venue = new Venue("VenueID123", "VenueName", "VenueAddress", 100, 150.0, "image.jpg");
        String expectedString = "VenueID123,VenueName,VenueAddress,image.jpg,100,150.0";
        assertEquals(expectedString, venue.toFileString());
        
   
    }
    
  


    
}
