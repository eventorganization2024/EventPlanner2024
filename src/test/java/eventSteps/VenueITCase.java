package eventSteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.example.*;
import org.junit.Test;

//import event.Venue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VenueITCase {
	
    static boolean adding = false;
    static boolean updated = false;
    static boolean delete = false;
    Venue v = new Venue();
    Printing printing = new Printing();
	
    @Given("that the admin is logged into the system")
    public void thatTheAdminIsLoggedIntoTheSystem() {
        Admin admin = new Admin();
        admin.setState(true);
        assertTrue( admin.getstate()); // Assuming login() method authenticates the administrator
        System.out.println("Administrator is logged into the system.");
    }

    @Test
    public void testVenueCreation() {
        Venue venue = new Venue("Venue Name", "Venue Address", 100, 150.0, "Available", "VenueID123", "image.jpg");
        
        assertNotNull(venue);
        assertEquals("Venue Name", venue.getName());
        assertEquals("Venue Address", venue.getAddress());
        assertEquals(100, venue.getCapacity());
        assertEquals(150.0, venue.getPrice(), 0.01);
        assertEquals("Available", venue.getAvailavility());
        assertEquals("VenueID123", venue.getId());
        assertEquals("image.jpg", venue.getImage());
    }
    
    @When("select to add a new venue such as details {string} , {double} , {string} ,{string} ,{string} and {int}")
    public void selectToAddANewVenueSuchAsDetailsAnd(String string, Double double1, String string2, String string3, String string4, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    	 v.setId(string);
         v.setPrice(double1);
         v.setAddress(string2);
         v.setName(string4);
         v.setCapacity(int1);
         v.setImage(string3);
        
    }

    @Then("the admin can successfully add the venue to the system.")
    public void theAdminCanSuccessfullyAddTheVenueToTheSystem() {
        // Assuming some logic to add the venue to the system
        adding = true;
        printing.printSomething("Venue added successfully");
    }

    @When("select to edit venue details by its {int} and {string}")
    public void selectToEditVenueDetailsByIts(int field, String value) {
        switch (field) {
            case 1:
                v.setAddress(value);
                break;
            case 2:
                v.setId(value);
                break;
            case 3:
                v.setName(value);
                break;
            case 4:
                v.setCapacity(Integer.parseInt(value));
                break;
            case 5:
                v.setPrice(Double.parseDouble(value));
                break;
        
        case 6 :
        	v.setImage(value);
        	}
        updated = true;
    }

    @Then("the admin can successfully modify venue information.")
    public void theAdminCanSuccessfullyModifyVenueInformation() {
        if (updated) {
            printing.printSomething("Venue information updated successfully");
        }
    }

    @When("select to delete a venue by its {string}")
    public void selectToDeleteAVenueByIts(String venueId) {
        // Assuming some logic to delete the venue from the system
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
        // Assuming some logic to retrieve all venues from the system
    }

    @Then("the admin can see a comprehensive list of all venues.")
    public void theAdminCanSeeAComprehensiveListOfAllVenues() {
        // Assuming some logic to display the list of venues
    }

//    @When("select to check the availability of venues")
//    public void selectToCheckTheAvailabilityOfVenues() {
//        v.setAvailability("available");
//    }
//
//    @Then("the admin can see the availability status of all venues within the system.")
//    public void theAdminCanSeeTheAvailabilityStatusOfAllVenuesWithinTheSystem() {
//        v.getAvailavility();
//    }
}
