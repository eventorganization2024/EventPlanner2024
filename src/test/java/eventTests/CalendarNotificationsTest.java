package eventSteps;

import org.example.Calendar;
import org.example.Functions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CalendarNotificationsTest {
	
    Functions functions = new Functions();

    
    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
    }

    @When("the user opens the calendar")
    public void theUserOpensTheCalendar() {
    }

    @Then("the user should see a list of upcoming events")
    public void theUserShouldSeeAListOfUpcomingEvents() {
    	
    	 Calendar calendar = functions.loadEventsForCustomerInCalendar("11");
     	if (calendar != null) {
     	 Functions.displayAllCustomerEvents(calendar);
     	 } 
    }
    

    @Given("there is an upcoming event")
    public void thereIsAnUpcomingEvent() {
    	//assume there is upcoming event
    }
    @When("the notification system checks for upcoming events")
    public void theNotificationSystemChecksForUpcomingEvents() {
    	
    }
    @Then("the user should receive a notification for the event")
    public void theUserShouldReceiveANotificationForTheEvent() {
        functions.startApproachingUpcomingEvents();

    }



}
