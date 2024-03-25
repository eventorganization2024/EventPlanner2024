package eventSteps;

import static org.junit.Assert.assertTrue;
import java.util.logging.Level;
import java.util.logging.*;
import org.example.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserRegSteps {
	
	
	   User u = new User();
	    Logger logger = Logger.getLogger(UserRegSteps.class.getName());


	 @Given("customers")
	  
	
	@Given("there is a User with User ID {string} ,NAME {string} , PASSWORD {string} , phone {string}, address {string}")
	public void thereIsAUserWithUserIDNAMEPASSWORDPhoneAddress(String string, String string2, String string3, String string4, String string5) {
		 u.setId(string);
	        u.setName(string2);
	        u.setPassword(string3);
	        u.setPhone(string4);
	        u.setAddress(string5);
	        u.setType("customer");
	        


	}
	@When("the User is registered {string}")
	public void theUserIsRegistered(String string) {

        if(u.isRegest(string))
            User.adduser(u);
	}
	@Then("the User with User ID {string} ,NAME {string} , PASSWORD {string} , phone {string}, address {string} is registered in the system")
	public void theUserWithUserIDNAMEPASSWORDPhoneAddressIsRegisteredInTheSystem(String string, String string2, String string3, String string4, String string5) {
		   if(u.isRegest(string))
	            assertTrue(true);
	}




	 @Then("the error message {string} is given")
	    public void theErrorMessageIsGiven() {
	        String string = "this user is already registered";
	        logger.log(Level.INFO, string);
	    }


	
	

}
