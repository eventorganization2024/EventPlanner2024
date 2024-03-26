
package eventSteps;




import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Scanner;
=======
import org.junit.Test;


import org.example.Customer;
import org.example.Event;
import org.example.Functions;
import org.example.Venue;
import org.junit.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class event_Management_Steps {
	 
	 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	    
	 Event event =new Event();
    boolean cancel;
    boolean found;
    boolean search;
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

        
	   	    event.addEventToFile(event, "requst.txt");

		  customer1.addEvent(event);
         assertEquals(1, customer1.getEvents().size());
        assertNotNull(customer1.getCevents());
        assertTrue(customer1.getEvents().contains(event));
	   	    event.addEventToFile(event, "event.txt");
	   	

	   	   }


	 @Test
	    public void testGetEvents() {
	      
	       
	        customer1.addEvent(event);
	        customer1.addEvent(event);
	        assertEquals(2, customer1.getEvents().size());
	    }

	    @Test
	    public void testGetEventsEmpty() {
	       
	        assertTrue(customer1.getEvents().isEmpty());
	    }

	
	@Then("the event is added to admin requst")
	public void theEventIsAddedToAdminRequst() throws Exception {  event.addEventToFile(event,"requst.txt");
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
		update=true;
	
		}
	
	
	 

	@Given("there is an existing event to update")
	public void thereIsAnExistingEventToUpdate() throws IOException {
		assertTrue(update);
		Event eventToUpdate = Event. findeventID("1000", "event.txt");
		Event.updateEventInFile(eventToUpdate,"event.txt");		  
		 }
	
	@Then("the event details are successfully updated in the system")
	public void theEventDetailsAreSuccessfullyUpdatedInTheSystem() throws Exception {
		
	}
	
	@Given("there is an nonexisting event to update")
	public void thereIsAnNonexistingEventToUpdate() throws NullPointerException, IOException {
		assertTrue(update);
		Event.updateEventInFile(null,"event.txt");
		 
	}

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
	
	@Given("the customer is going to search about event")
	public void theCustomerIsGoingToSearchAboutEvent() {
		search=true;
	
	}
	@When("the customer enters the event details such as event name {string}")
	public void theCustomerEntersTheEventDetailsSuchAsEventName(String string) {
	   assertTrue(search);
	   Event.searchEvent("12114777", string, 0);
	   
	}
	
	@When("the customer enters the event details such as venue name {string}")
	public void theCustomerEntersTheEventDetailsSuchAsVenueName(String string) {
		assertTrue(search);
		Event.searchEvent("12114777", string, 8);
		
	}
	

@Then("the system should display all events matching the name")
	public void theSystemShouldDisplayAllEventsMatchingTheName() {
	
   }

///////////////////////////////////////////////////////////////////////////////////



	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	
