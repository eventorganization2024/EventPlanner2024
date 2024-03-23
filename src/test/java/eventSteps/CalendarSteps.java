package eventSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.example.User;
import org.example.Calendar;
import org.example.Customer;
import org.example.Event;
import org.example.Functions;

public class CalendarSteps {

    private Calendar calendar;
    private User currentUser;
    Functions functions = new Functions();
    
    
    @Given("there are upcoming events and important dates in the calendar")
    public void thereAreUpcomingEventsAndImportantDatesInTheCalendar() {
        
		// Load events from the event.txt file
        calendar =functions.loadEventsForCustomerInCalendar("11");

        // Assuming the user is a regular customer by default
        currentUser = new Customer();
    }

    @When("the user navigates to the calendar view")
    public void theUserNavigatesToTheCalendarView() {
        // Logic to navigate to the calendar view
    }

    @Then("the system should display a list of upcoming events and important dates")
    public void theSystemShouldDisplayAListOfUpcomingEventsAndImportantDates() {
        // Assuming logic to display events in the calendar view
       // assertNotNull(calendar);
       // assertTrue(calendar.getEvents().size() > 0);
        functions.displayAllCustomerEvents(calendar);
    }
    
///////////////////////////////////////////////////////////////////////////////
    

 
 
}
    
  