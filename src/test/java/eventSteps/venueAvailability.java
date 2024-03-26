package eventSteps;
import org.example.*;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class venueAvailability
{
	 private Venue v= new Venue();
	
	Printing printing=new Printing();
	    private Venue venue;
	    private boolean isVenueAvailable;
	    private boolean bookingSuccess;

	    @Given("there is a venue available for booking")
	    public void thereIsAVenueAvailableForBooking() {
	        v.setAddress("Address1");
	        v.setAvailability("Available");
	        v.setCapacity(900);
	       v.setdate("2024-3-4");
	       v.setName("venue1");
	       v.setPrice(444.1);
	       v.setImage("path");
	        isVenueAvailable = true;
	    }

	    @When("a user books the venue")
	    public void aUserBooksTheVenue() {
	    	isVenueAvailable=false;
	    }

	    @Then("the status of the venue should be updated to reserved")
	    public void theStatusOfTheVenueShouldBeUpdatedToReserved() {
	    	bookingSuccess = true;
	    }

	    @Given("there is a venue that is already reserved")
	    public void thereIsAVenueThatIsAlreadyReserved() {
	    	 v.setAddress("Address1");
		        v.setAvailability("Reserved");
		        v.setCapacity(900);
		       v.setdate("2024-3-4");
		       v.setName("venue1");
		       v.setPrice(444.1);
		       v.setImage("path");
		       isVenueAvailable = false;
	    }

	   

	
	    @When("a user tries to book the venue")
	    public void aUserTriesToBookTheVenue() {
	        // Check if the venue is already reserved
	        if (v.getAvailavility().equals("Reserved")) {
	            bookingSuccess = false;
	        }
	    }

	@Then("a message should appear informing the user that the venue is unavailable")
	public void aMessageShouldAppearInformingTheUserThatTheVenueIsUnavailable() {
		
		 printing.printSomething("Venue is unavailable");
	}


}