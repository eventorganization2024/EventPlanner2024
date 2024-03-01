
package eventSteps;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.example.Customer;
import org.example.Event;
import org.example.Functions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class event_Management_Steps {
	 
	
	Event event =new Event();
    boolean cancel;
    boolean found;
    boolean update;
    boolean existe;
    Customer customer = new Customer();
	
	@Given("the customer is going to create an event")
	public void theCustomerIsGoingToCreateAnEvent()
	{
		customer.setId("12029026");
        customer.setName("Ahmad");
        customer.setEmail("AhmadHH334@gmail.com");
        customer.setPhone("0599897846");
        customer.setAddress("Nablus");
        event.setUID(customer.getId());

	}
	                                                 
	
	@When("the customer enters the event details such as Date {string}, time {string}, description {string}, attendeeCount {string}, name {string},category {string} ,theme {string},eventid {string}")
	public void theCustomerEntersTheEventDetailsSuchAsDateTimeDescriptionAttendeeCountNameCategoryThemeEventid(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
       event.setDate(string);
	        event.setTime(string2);
	        event.setDescription(string3);
	        event.setAttendeeCount(string4);
	        event.setName(string5);
	        event.setCategory(string6);
	        event.setTheme(string7);
	        event.setEID(string8);
	        event.setUID(customer.getId());
	   	 Functions.addEmptyLine("event.txt");}
	
	
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
		 {assertTrue(update);  event.updateEvent(event.getEID(),"event.txt");}	
	
	}

	@Given("there is an existing event to update")
	public void thereIsAnExistingEventToUpdate() {existe=true; }
    /////////////////////////////////////////////////
	
@Given("the administrator is going to create an event")
	public void theAdministratorIsGoingToCreateAnEvent() {event.creat=true;}
	   

@When("the administrator enters the event details such as Date {string}, time {string}, description {string}, attendeeCount {string}, name {string},category {string} ,theme {string},eventid {string}")
public void theAdministratorEntersTheEventDetailsSuchAsDateTimeDescriptionAttendeeCountNameCategoryThemeEventid(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
 	event.setDate(string);
        event.setTime(string2);
        event.setDescription(string3);
        event.setAttendeeCount(string4);
        event.setName(string5);
        event.setCategory(string6);
        event.setTheme(string7);
        event.setEID(string8);
        event.setUID(customer.getId());}

	
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
	
	
	