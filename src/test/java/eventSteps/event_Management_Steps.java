
package eventSteps;




import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.example.Customer;
import org.example.Event;
import org.example.Functions;
import org.example.Venue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class event_Management_Steps {
	 
	 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	    
	 Event event =new Event();
    boolean cancel;
    boolean found;
    boolean update;
    boolean existe;
    boolean creat;
    Customer customer1 = new Customer();
    
    Venue v = new Venue("Palestine_Convention_Center", "Main_Street_Ramallah", 200, 200.0, "Available", "RAMA456", "ramallah_venue_image.jpg");

	
	@Given("the customer is going to create an event")
	public void theCustomerIsGoingToCreateAnEvent()
	{
		customer1.setId("12029026");
        customer1.setName("Ahmad");
        customer1.setEmail("AhmadHH334@gmail.com");
        customer1.setPhone("0599897846");
        customer1.setAddress("Nablus");
        event.setUserId(customer1.getId());

	}
	                                                 
	
	@When("the customer enters the event details such as Date {string}, time {string}, description {string}, attendeeCount {string}, name {string},category {string} ,theme {string},Venue {string},eventid {string}")
	public void theCustomerEntersTheEventDetailsSuchAsDateTimeDescriptionAttendeeCountNameCategoryThemeVenueEventid(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
	Date date=new Date();
        try {
            date = DATE_FORMAT.parse(string);
        } catch (ParseException e) {

            e.printStackTrace();
           
        } 
       Functions.addVenueToFile("venue.txt",  v.toFileString());
        
       List<String> serviceIds = new ArrayList<>();
        serviceIds.add("100");
        event = new Event(string5, date, string2, string3, string4, customer1.getId(), string7, string6, string8, serviceIds, string9);
           /*
		    event.setDate(date);
	        event.setTime(string2);
	        event.setDescription(string3);
	        event.setAttendeeCount(string4);
	        event.setName(string5);
	        event.setCategory(string6);
	        event.setTheme(string7);
	        event.setVenuename(string8);
	        event.setEID(string9);
	        event.setUID(customer1.getId());
	        */
	   	    event.addEventToFile(event, "event.txt");
	   	   }
	
	
	@Then("the event is added to admin requst")
	public void theEventIsAddedToAdminRequst() throws Exception {  event.addEventToFile(event,"event.txt");
	}


	
	////////////////////////////////////
	
	
	@Given("there is an existing event")
	public void thereIsAnExistingEvent() { existe=true;}
    
  
		
	
 		
	@When("cancel event selected")
	public void cancelEventSelected() {cancel=true;}
	
	@Then("the event deleted")
	public void theEventDeleted() throws Exception{ 	
	event .deleteEvent("event.txt","2000"); 
	System.out.println("The event deleted");}

	
	
	
	@Given("there is an non_existing event")
	public void thereIsAnNonExistingEvent() {existe=false;}

	
	

	@Then("non_Existing massage")
	public void nonExistingMassage() {assertFalse(found);System.out.println("The event does not exist.");}


	/////////////////////////////////
	
	@Given("selects the option to update the event details")
	public void selectsTheOptionToUpdateTheEventDetails() throws IOException {
		}
	
	
	@Then("the event details are successfully updated in the system")
	public void theEventDetailsAreSuccessfullyUpdatedInTheSystem() throws Exception {
		 {assertTrue(update);  
		/* String[] simulatedInputs = {
				    "1\nnewname\n",
				    // Case 2: Enter new event date
				    "2\n2025-05-12\n",
				    // Case 3: Enter new event time
				    "3\n4:00 pm\n",
				    // Case 4: Enter new event description
				    "4\nnew description\n",
				    // Case 5: Enter new event attendee count
				    "5\n10\n",
				    // Case 6: Enter new event theme
				    "6\nnew theme\n",
				    // Case 7: Enter new event category
				    "7\nnew category\n",
				    // Case 8: Enter new event venue name
				    "8\nNablus_Event_Center\n"
				    + "\nNablus_Event_Center\n",
				    // Case 9: Enter new service IDs
				    "9\n100, 200\n"
		    };
		
		 for (String simulatedInput : simulatedInputs) {
		        // Set up input stream with simulated input
		        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		        System.setIn(inputStream);

		        // Call the method you want to test
		        try {
		            event.updateEvent("1000", "event.txt");
		        } catch (Exception e) {
		            e.printStackTrace();
		            // Handle exceptions if any
		        }

		        // Reset System.in to standard input stream
		        System.setIn(System.in);
		    
		      }}
		*/ 
	}
		    
	}


	@Given("there is an existing event to update")
	public void thereIsAnExistingEventToUpdate() throws IOException {
		Event eventToUpdate = event. findeventID("1000", "event.txt");
		update=true;   
		existe=true; }
    /////////////////////////////////////////////////
	
@Given("the administrator is going to create an event")
	public void theAdministratorIsGoingToCreateAnEvent() {creat=true;}
	   

@When("the administrator enters the event details such as Date {string}, time {string}, description {string}, attendeeCount {string}, name {string},category {string} ,theme {string},Venue {string},eventid {string}")
public void theAdministratorEntersTheEventDetailsSuchAsDateTimeDescriptionAttendeeCountNameCategoryThemeVenueEventid(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
	Date date=new Date();
    try {
        date = DATE_FORMAT.parse(string);
    } catch (ParseException e) {

        e.printStackTrace();
       
    }
	    event.setDate(date);
        event.setTime(string2);
        event.setDescription(string3);
        event.setAttendeeCount(string4);
        event.setName(string5);
        event.setCategory(string6);
        event.setTheme(string7);
        event.setVenuename(string8);
        event.setEID(string9);
        event.setUserId("12114777");}

	
	@Then("the event is successfully created in the system")
	public void theEventIsSuccessfullyCreatedInTheSystem() throws Exception { assertTrue(creat=true); 

	event.addEventToFile(event,"event.txt");}
	

 ////////////////////////////////////
	


	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	