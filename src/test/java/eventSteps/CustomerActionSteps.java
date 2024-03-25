package eventSteps;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import eventSteps.*;

public class CustomerActionSteps {
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
	    public void i_update_my_profile_information_depends_on_and(Integer i, String val) {
        if( i == 1){
	            customer.setName(val);
	            updated = true;
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



	 @When("I choose to delete my profile")
	    public void i_choose_to_delete_my_profile() {
	        deleteProfile = true;
	    }
	 @When("I confirm the deletion")
	 public void iConfirmTheDeletion() {
	    
	 }

	  
	    @Then("my profile should be deleted successfully")
	    public void my_profile_should_be_deleted_successfully() {
	        printing.printSomething("\nAccount Successfully Deleted\n\n");
	    }


	

}
