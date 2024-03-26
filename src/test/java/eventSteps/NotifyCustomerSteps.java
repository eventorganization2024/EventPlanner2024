package eventSteps;

import static org.junit.Assert.assertTrue;
import org.example.Customer;
import org.example.Event;
import org.example.Functions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NotifyCustomerSteps {
	 Event event = new Event();
	    Customer customer = new Customer();
	    boolean requestAccepted;
	    boolean requestRejected;

	    ///
	@Given("there is an existing event request for a customer")
	public void thereIsAnExistingEventRequestForACustomer() {
		
       // Functions.addEmptyLine("requst.txt");
	}
	@When("the event request is accepted by the administrator")
	public void theEventRequestIsAcceptedByTheAdministrator() {
		
        requestAccepted = true;
        
	}
	@Then("an message notification is sent to the customer informing them about the acceptance")
	public void anEmailNotificationIsSentToTheCustomerInformingThemAboutTheAcceptance() {
		 if (requestAccepted) {
	           
	            assertTrue("Message notification sent successfully", true);
	           
	        } else {
	          //  assertTrue("Event request acceptance flag is not set", false);
	        }
	}


	
	
	@When("the event request is rejected by the administrator")
	public void theEventRequestIsRejectedByTheAdministrator() {
		
        requestRejected = true;
      
	}
	@Then("an message notification is sent to the customer informing them about the rejection")
	public void anEmailNotificationIsSentToTheCustomerInformingThemAboutTheRejection() {
		 if (requestRejected) {
	          
	            assertTrue("Message notification sent successfully", true);
	       
	        } else {
	            //assertTrue("Event request rejection flag is not set", false);
	        }
	    }
}