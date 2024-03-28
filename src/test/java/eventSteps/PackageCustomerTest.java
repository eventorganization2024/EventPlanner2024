package eventSteps;

import static org.junit.Assert.assertTrue;

import org.example.Admin;
import org.example.Paackage;
import org.example.Printing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PackageCustomerTest {
    private Admin admin;
    private Paackage selectedPackage;
    private boolean packageAddedSuccessfully;
    private boolean selected ;
    Printing printing = new Printing();
    		
@Given("the customer is logged in")
public void theCustomerIsLoggedIn() {
	 admin = new Admin();
    admin.setState(true);
    assertTrue( admin.getstate()); // Assuming login() method authenticates the administrator
    System.out.println("Administrator is logged into the system.");
}
@When("the customer selects a package with ID {string}")
public void theCustomerSelectsAPackageWithID(String string) {
    System.out.println("Customer selected package with ID: " + string);
    selected = true ;
}
@Then("the package should be added succesfully")
public void thePackageShouldBeAddedSuccesfully() {
	 if (selected) {
         printing.printSomething("Deleted successfully");
     }
	 else {
  
	 }
}


	 

}