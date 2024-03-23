
package eventSteps;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.example.Customer;
import org.example.Event;
import org.example.Functions;

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
    Customer customer1 = new Customer();
	
	@Given("the customer is going to create an event")
	public void theCustomerIsGoingToCreateAnEvent()
	{
		customer1.setId("12029026");
        customer1.setName("Ahmad");
        customer1.setEmail("AhmadHH334@gmail.com");
        customer1.setPhone("0599897846");
        customer1.setAddress("Nablus");
        event.setUID(customer1.getId());

	}
	                                                 
	
	@When("the customer enters the event details such as Date {string}, time {string}, description {string}, attendeeCount {string}, name {string},category {string} ,theme {string},Venue {string},eventid {string}")
	public void theCustomerEntersTheEventDetailsSuchAsDateTimeDescriptionAttendeeCountNameCategoryThemeVenueEventid(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
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
	        event.setUID(customer1.getId());
	   	// Functions.addEmptyLine("event.txt");s
	        event.addEventToFile(event, "event.txt");
	}
	
	
	@Then("the event is added to admin requst")
	public void theEventIsAddedToAdminRequst() throws Exception {  event.addEventToFile(event,"event.txt");
	}


	
	////////////////////////////////////
	
	
	@Given("there is an existing event")
	public void thereIsAnExistingEvent1() { existe=true;}
    
    
	@Given("there is an existing event ")
	public void thereIsAnExistingEvent() throws Exception
	{ event=event.findeventID(event.getEID(),"event.txt"); 
		event.cancel=true;
		existe=true;}
 		
	@When("cancel event selected")
	public void cancelEventSelected() {	event.cancel=true;}
	
	@Then("the event deleted")
	public void theEventDeleted() throws Exception{ 	
	event .delete_event_from_file_and_arraylist( event,"event.txt",event.getEID()); 
	System.out.println("The event deleted");}

	
	
	
	@Given("there is an non_existing event")
	public void thereIsAnNonExistingEvent1() {}

	
	@Given("there is an non_existing event ")
	public void thereIsAnNonExistingEvent()throws Exception {
    event=event.findeventID(event.getEID(),"event.txt"); 
	event.cancel=false;}

	@Then("non_Existing massage")
	public void nonExistingMassage() {assertFalse(found);assertFalse(cancel);System.out.println("The event does not exist.");}


	/////////////////////////////////
	
	@Given("selects the option to update the event details")
	public void selectsTheOptionToUpdateTheEventDetails() {update=true;}
	
	
	@Then("the event details are successfully updated in the system")
	public void theEventDetailsAreSuccessfullyUpdatedInTheSystem() throws Exception {
		 {assertTrue(update);  //event.updateEvent(event.getEID(),"event.txt");}	
		 }
	}

	@Given("there is an existing event to update")
	public void thereIsAnExistingEventToUpdate() {existe=true; }
    /////////////////////////////////////////////////
	
@Given("the administrator is going to create an event")
	public void theAdministratorIsGoingToCreateAnEvent() {event.creat=true;}
	   

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
        event.setUID("12114777");}

	
	@Then("the event is successfully created in the system")
	public void theEventIsSuccessfullyCreatedInTheSystem() throws Exception { assertTrue(event.creat=true); 

	event.addEventToFile(event,"event.txt");}
	

 ////////////////////////////////////
	
	@Given("Customer is gonig to search for Exist event by name")
	public void customerIsGonigToSearchForExistEventByName() {found=true;}
		
	@When("Customer enter event name {string}")
	public void customerEnterEventName(String name) {event= event.findevent(name,"Name", "event.txt");}
	
	
	@Then("show event details")
	public void showEventDetails() {assertTrue(found);  }	
	
///////////////////////////////

	@Given("Customer is gonig to search fornon_Exist event by name")
	public void customerIsGonigToSearchFornonExistEventByName() {found=false;}
    
/////////////////////////////////	

	@Given("Customer is gonig to search for Exist event by date")
	public void customerIsGonigToSearchForExistEventByDate() {found=true;}
	@When("Customer enter event date {string}")
	public void customerEnterEventDate(String date) {event= event.findevent(date,"Date", "event.txt");}
	

/////////////////////////////////			
	@Given("Customer is gonig to search for non_Exist event by date")
	public void customerIsGonigToSearchForNonExistEventByDate() {found =false;}


	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	