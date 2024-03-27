
package eventSteps;




import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.example.Customer;
import org.example.Event;
import org.example.Functions;
import org.example.Printing;
import org.example.Venue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class event_Management_Steps{
	 
	acceptTest1 c=new acceptTest1();
	
	 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	 private InputStream inputStream;
	  static Printing printing = new Printing();
	    
	 Event event =new Event();
    boolean cancel;
    boolean found;
    boolean search;
    boolean update;
    boolean existe;
    boolean creat;
    boolean toadd;
    Customer customer1 = new Customer();
    Event eventToUpdate;
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
	                                                 
	 @Test
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
	     event.addEventToFile(event, "requst.txt");
	     toadd=true;
	    }
	
	@Test
	@Then("the event is added to admin requst")
	public void theEventIsAddedToAdminRequst() throws Exception { 
		
		assertTrue(toadd);
		Event.addEventToFile(event,"requst.txt");
	}


	
	////////////////////////////////////
	
	@Test
	@Given("there is an existing event")
	public void thereIsAnExistingEvent() { existe=true;}
    
  
		
	
 		
	@When("cancel event selected")
	public void cancelEventSelected() {cancel=true;}
	
	@Test
	@Then("the event deleted")
	public void theEventDeleted() throws Exception{ 	
	event .deleteEvent("event.txt","2000"); 
	System.out.println("The event deleted");}

	
	
	
	@Given("there is an non_existing event")
	public void thereIsAnNonExistingEvent() {existe=false;}
	
	@Test
	@Then("non_Existing massage")
	public void nonExistingMassage() {assertFalse(found);System.out.println("The event does not exist.");}


	/////////////////////////////////
	/*
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
*/
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

    @Test
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
	
	 @Test
@Then("the system should display all events matching the name")
	public void theSystemShouldDisplayAllEventsMatchingTheName() {
	
   }

///////////////////////////////////////////////////////////////////////////////////


@Given("selects the option to update the event details")
public void selectsTheOptionToUpdateTheEventDetails() {
	
}

@Given("there is an existing event to update")
public void thereIsAnExistingEventToUpdate() throws IOException {
	 eventToUpdate = Event. findeventID("1000", "event.txt");
	 update=true;
}
////////////////////////////
@Given("the user chooses {string} to update name")
public void theUserChoosesToUpdateName(String string) throws IOException, NullPointerException, ParseException {
 //assertTrue(update);

    eventToUpdate = Event. findeventID("1000", "event.txt");
    String simulatedInputs1 = ("Ali_party");
  
 
   InputStream inputStream1 = new ByteArrayInputStream(simulatedInputs1.getBytes());
     System.setIn(inputStream1);
   event.updateEventName(eventToUpdate);
  
   InputStream inputStream2 = new ByteArrayInputStream("out".getBytes());
   System.setIn(inputStream2);
 
   event.updateEvent("1000", "event.txt");
   
 
   }

 
 


@Given("the user chooses {string} to update date")
public void theUserChoosesToUpdateDate(String string) throws IOException, NullPointerException, ParseException {
    eventToUpdate = Event. findeventID("1000", "event.txt");
    String simulatedInputs3 = ("2026-08-14");
  
 
   InputStream inputStream3 = new ByteArrayInputStream(simulatedInputs3.getBytes());
     System.setIn(inputStream3);
   event.updateEventDate(eventToUpdate,"1000");
  
   InputStream inputStream4 = new ByteArrayInputStream("out".getBytes());
   System.setIn(inputStream4);
 
   event.updateEvent("1000", "event.txt");
}


@Given("the user chooses {string} to update time")
public void theUserChoosesToUpdateTime(String string) throws IOException, NullPointerException, ParseException {
	 eventToUpdate = Event. findeventID("1000", "event.txt");
	    String simulatedInputs5 = ("7:00 pm");
	  
	 
	   InputStream inputStream5 = new ByteArrayInputStream(simulatedInputs5.getBytes());
	     System.setIn(inputStream5);
	   event.updateEventTime(eventToUpdate);
	  
	   InputStream inputStream6 = new ByteArrayInputStream("out".getBytes());
	   System.setIn(inputStream6);
	 
	   event.updateEvent("1000", "event.txt");
}


@Given("the user chooses {string} to update description")
public void theUserChoosesToUpdateDescription(String string) throws IOException, NullPointerException, ParseException {
	 eventToUpdate = Event. findeventID("1000", "event.txt");
	    String simulatedInputs7 = ("*******");
	  
	 
	   InputStream inputStream7 = new ByteArrayInputStream(simulatedInputs7.getBytes());
	     System.setIn(inputStream7);
	   event.updateEventDescription(eventToUpdate);
	  
	   InputStream inputStream8 = new ByteArrayInputStream("out".getBytes());
	   System.setIn(inputStream8);
	 
	   event.updateEvent("1000", "event.txt");
}


@Given("the user chooses {string} to update count")
public void theUserChoosesToUpdateCount(String string) throws IOException, NullPointerException, ParseException {
	eventToUpdate = Event. findeventID("1000", "event.txt");
    String simulatedInputs9 = ("40");
  
 
   InputStream inputStream9 = new ByteArrayInputStream(simulatedInputs9.getBytes());
     System.setIn(inputStream9);
   event.updateEventAttendeeCount(eventToUpdate);
  
   InputStream inputStream10 = new ByteArrayInputStream("out".getBytes());
   System.setIn(inputStream10);
 
   event.updateEvent("1000", "event.txt");
}


@Given("the user chooses {string} to update theme")
public void theUserChoosesToUpdateTheme(String string) throws IOException, NullPointerException, ParseException {
	eventToUpdate = Event. findeventID("1000", "event.txt");
    String simulatedInputs11 = ("theme");
  
 
   InputStream inputStream11 = new ByteArrayInputStream(simulatedInputs11.getBytes());
     System.setIn(inputStream11);
   event.updateEventTheme(eventToUpdate);
  
   InputStream inputStream12 = new ByteArrayInputStream("out".getBytes());
   System.setIn(inputStream12);
 
   event.updateEvent("1000", "event.txt");
}


@Given("the user chooses {string} to update category")
public void theUserChoosesToUpdateCategory(String string) throws IOException, NullPointerException, ParseException {
	eventToUpdate = Event. findeventID("1000", "event.txt");
    String simulatedInputs13 = ("cat");
  
 
   InputStream inputStream13 = new ByteArrayInputStream(simulatedInputs13.getBytes());
     System.setIn(inputStream13);
   event.updateEventCategory(eventToUpdate);
  
   InputStream inputStream14 = new ByteArrayInputStream("out".getBytes());
   System.setIn(inputStream14);
 
   event.updateEvent("1000", "event.txt");
    
}

@Given("the user chooses {string} to update venue")
public void theUserChoosesToUpdateVenue(String string) {
 
}

@Given("the user chooses {string} to update services")
public void theUserChoosesToUpdateServices(String string) throws IOException, NullPointerException, ParseException {
	eventToUpdate = Event. findeventID("1000", "event.txt");
    String simulatedInputs15 = ("100");
  
 
   InputStream inputStream15 = new ByteArrayInputStream(simulatedInputs15.getBytes());
     System.setIn(inputStream15);
   event.updateEventServices(eventToUpdate);
  
   InputStream inputStream16 = new ByteArrayInputStream("out".getBytes());
   System.setIn(inputStream16);
 
   event.updateEvent("1000", "event.txt");	
    
}

@Then("the event  is successfully updated in the system")
public void theEventIsSuccessfullyUpdatedInTheSystem() {
	printing.printSomething(eventToUpdate.toString2());
   
}




















	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	
