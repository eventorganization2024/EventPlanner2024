package eventTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.*;
import org.junit.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import eventTests.*;


/////
public class CustomerActionTest {
	Functions f=new Functions();
	 User user = new User();
Customer customer = new Customer("12114777","Ansam","057806241","Nablus","123456","ansam@gmail.com");

        
	    Functions functions = new Functions();
	    static boolean updated = false;
	    static boolean deleteProfile = false;
	    Printing printing = new Printing();
	    Event event=new Event();
	  
	         
      
	    
	    @Given("customer log in with true {string} and {string}")
	    public void customerLogInWithTrueAnd(String arg0, String arg1) {
	        customer.setPassword("12345");
	        if(arg0.equals(customer.getId()) && arg1.equalsIgnoreCase(customer.getPassword())){
	            user.setLogstate(true);
	           
	        }
	    }
	    @Given("I am logged in")
	    public void i_am_logged_in() throws Exception {
	        user.setLogstate(true);
	       
	        functions.updateCustomerFile();
	     
	        functions.updateEventList("event.txt");
	        functions.updateeventandcustomer("event.txt");
	    }
	    @When("I navigate to My Profile")
	    public void i_navigate_to_my_profile() {
	        if(user.getLogstate()){
	            functions.customerPageList();
	        }
	    }

	    @When("I update my profile information depends on {int} and {string}")
	    public void i_update_my_profile_information_depends_on_and(Integer i, String val) throws IOException {
	
	    	   
        if( i == 1){
	            customer.setName(val);
	            updated = true;
	            Functions.updateCustomerProfile(1);
	            assertEquals(val, customer.getUsername());
	        } else if (i == 2) {
	            customer.setPhone(val);
	            updated = true;
	        } else if (i==3) {
	            customer.setAddress(val);
	            updated = true;
	        }else{
	            customer.setEmail(val);
	            updated = true;
	        }
	       
	    }
	    @Then("my profile should be updated successfully")
	    public void my_profile_should_be_updated_successfully() {
	        if(updated){
	            printing.printSomething("Updated successfully");
	            Customer.addCustomerToFile(customer);
	            
	            
	        }
	    }


		  @When("I navigate to Invoices")
		    public void i_navigate_to_invoices()  {
		        //printing.printSomething("\nNumber of orders:"+ customer.getNumOfEvents());
		       //invoice = new Invoice(customer);
		        //invoice.invoiceRes(customer);
		    }
		    @Then("I should see a my invoice")
		    public void i_should_see_a_my_invoice() {

		    }


@Test
	 @When("I choose to delete my profile")
	    public void i_choose_to_delete_my_profile() throws IOException {
	     deleteProfile = true;
	        String id=customer.getId();
	  Functions.deleteCustomer(id);
	  Functions.deleteCustomerProfile(id);
	       
	        
	       
	    }
@Test
public void testDeleteCustomer_InvalidId() {
   
    boolean deleted = Functions.deleteCustomer("12345678");
    assertFalse(deleted);
}
	 @When("I confirm the deletion")
	 public void iConfirmTheDeletion() {
	    
	 }

	  
	    @Then("my profile should be deleted successfully")
	    public void my_profile_should_be_deleted_successfully() {
	        printing.printSomething("\nAccount Successfully Deleted\n\n");
	        
	    }

	    @Test
	    public void testGetCustomerFromLine_ValidLine() {
	        String line = "12114777,Ansam,057806241,Nablus,ansam@gmail.com,12345";
	        Customer.getCustomerFromLine(line);
	        assertNotNull(customer);
	     
	       
	    }
	 
	    
	    @Test
	    public void testToString() {
	        Customer customer = new Customer("123", "John", "123456", "123 Main St", "password", "john@example.com");
	        String expectedString = "Customer{id='123', username='John', phone='123456', address='123 Main St', email='john@example.com', password='password'}";
	        assertEquals(expectedString, customer.toString());
	    }
	    @Test
	    public void testGetCevents() {
	        List<Event> events = Customer.getCevents();
	        assertEquals(1, events.size());
	    }

	    @Test
	    public void testGetEvents() {
	        List<Event> events = Customer.getEvents();
	        assertEquals(1, events.size());
	    }

	    @Test
	    public void testAddEvent() throws ParseException {
	    	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	         Date eventDate = dateFormat.parse("2024-03-15");
	         
	        Event event = new Event("Conference",eventDate, "9:00 AM", "Tech conference", "200", "admin123", "Tech", "Conference", "V1", "111");
	        Customer.addEvent(event);
	        List<Event> events = Customer.getCevents();
	        assertEquals(1, events.size());
	        assertEquals(event, events.get(0));
	    }
 

}
