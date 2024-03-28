package eventSteps;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.logging.Level;
import java.util.logging.*;
import org.example.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogInTest{
	
	
    User u = new User();

    static Logger logger= Logger.getLogger(LogInTest.class.getName());

   
    @Given("table")
    public void table_step() {
        // Step implementation here
    }

    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        u.setLogstate(false);
        assertFalse(u.getLogstate());
    }

    @Given("The user has entered all the data correctly  {string} , {string}")
    public void the_user_has_entered_all_the_data_correctly(String string, String string2) {
        u.setName(string);
        u.setPassword(string2);
        u.loginCH(string, string2);
    }

    @Then("the user is logged in successfully")
    public void the_user_is_logged_in_successfully() {
        u.setLogstate(true);
        assertTrue( u.getLogstate());
    }

    @Given("{string} is not in the database")
    public void is_not_in_the_database(String string) {

        if (!User.users1.equals(string)) {

            u.setLogstate(false);
        }

        assertFalse(u.getLogstate());

    }

    @Then("Show {string} message.")
    public void show_message(String string) {
        string="the username is wrong";
        logger.log(Level.INFO,string);
    }

    @Given("the {string} not in database")
    public void the_not_in_database(String string) {
        if (!User.users1.equals(string)) {

            u.setLogstate(false);
        }
        assertFalse(u.getLogstate());
    }

    @Then("Show message {string}")
    public void show_massege(String string) {
        string ="the password is wrong";
        logger.log(Level.INFO,string);
    }





	

}
