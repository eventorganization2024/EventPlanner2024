package eventSteps;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.example.*;

import io.cucumber.datatable.DataTable;
//import event.Venue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class venueTest {
	
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
         Functions.addVenueToFile("venue.txt",venueDetails );
        
    }

    @Then("the admin can successfully add the venue to the system.")
    public void theAdminCanSuccessfullyAddTheVenueToTheSystem() {
        // Assuming some logic to add the venue to the system
       
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


}
