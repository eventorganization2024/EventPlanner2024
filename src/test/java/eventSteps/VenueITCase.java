package eventSteps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.example.Venue;
import org.junit.Test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VenueITCase {
	
    static boolean adding = false;
    static boolean updated = false;
    static boolean delete = false;
    Venue v = new Venue();
    Printing printing = new Printing();
    
    
    Venue venue1 = new Venue("Venue Name", "Venue Address", 100, 150.0, "Available", "VenueID123", "image.jpg");
    Venue venue2 = new Venue("Venue Name", "Venue Address", 100, "image.jpg", 150.0, "Available", "2026-01-01");
   
    
	
    @Given("that the admin is logged into the system")
    public void thatTheAdminIsLoggedIntoTheSystem() {
        Admin admin = new Admin();
        admin.setState(true);
        assertTrue(admin.getstate()); // Assuming login() method authenticates the administrator
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
        assertEquals("Available", venue.getAvailability()); // Corrected method name
        assertEquals("VenueID123", venue.getId());
        assertEquals("image.jpg", venue.getImage());
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
        Functions.addVenueToFile("venue.txt", venueDetails);
    }

    @Then("the admin can successfully add the venue to the system.")
    public void theAdminCanSuccessfullyAddTheVenueToTheSystem() {
        adding = true;
        venue1.toFileString();
        printing.printSomething("Venue added successfully");
    }

    @When("select to edit venue details with the following changes:")
    public void selectToEditVenueDetails(DataTable changesTable) {
        Map<String, String> changesMap = changesTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : changesMap.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            switch (field) {
                case "Address":
                    v.setAddress(value);
                    break;
                case "ID":
                    v.setId(value);
                    break;
                case "Name":
                    v.setName(value);
                    break;
                case "Capacity":
                    v.setCapacity(Integer.parseInt(value));
                    break;
                case "Price":
                    v.setPrice(Double.parseDouble(value));
                    break;
                case "Image":
                    v.setImage(value);
                    break;
            }
        }
        updated = true;
    }

    @When("select to delete a venue by its {string}")
    public void selectToDeleteAVenueByIts(String venueId) {
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
        // Logic to retrieve all venues from the system
    }

    @Then("the admin can see a comprehensive list of all venues.")
    public void theAdminCanSeeAComprehensiveListOfAllVenues() {
        // Logic to display the list of venues
    }
}
