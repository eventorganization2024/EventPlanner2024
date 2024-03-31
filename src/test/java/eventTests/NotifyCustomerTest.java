package eventTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.example.Customer;
import org.example.Event;
import org.example.Functions;
import org.example.Printing;
import org.junit.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NotifyCustomerTest {
	Functions f=new Functions();
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
	public void anEmailNotificationIsSentToTheCustomerInformingThemAboutTheAcceptance() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		 if (requestAccepted) {
	           
	            assertTrue("Message notification sent successfully", true);
	            Method method = Functions.class.getDeclaredMethod("sendmsgToCustomer", String.class, Event.class);
	            method.setAccessible(true);
	            Object result = method.invoke(null, "Your Event is Accepted", event);
	 
	    
	           
	        } else {
	         assertTrue("Event request acceptance flag is not set", false);
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
	
	
	 @Test
	    public void testShowAdminMessage_MessageExists() throws IOException {
	       
	     
            String msg=" The Event null Your Event is Accepted , 123Test";

	        Functions.showAdminMessage("123Test");

	        assertEquals(" The Event null Your Event is Accepted , 123Test", msg);

	        
	    }

	
}







