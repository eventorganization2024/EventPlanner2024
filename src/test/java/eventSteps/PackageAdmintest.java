package eventSteps;
 import eventSteps.*;
import org.example.Paackage;

import static org.junit.Assert.assertTrue;

import org.example.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 public class PackageAdmintest
 {
	 Paackage p=new Paackage(); 
	 
	 boolean added=false;
	 boolean updated=false;
	 boolean removed=false;
	 Printing printing =new Printing();
	 
	 @Given("the administrator is logged into the system")
	 public void theAdministratorIsLoggedIntoTheSystem() {
		 Admin admin = new Admin();
	        admin.setState(true);
	        assertTrue( admin.getstate()); // Assuming login() method authenticates the administrator
	        System.out.println("Administrator is logged into the system.");
	 }
	 @When("the administrator specifies the package details such as {string}, {string}, {int}, {double} , {string}")
	 public void theAdministratorSpecifiesThePackageDetailsSuchAs(String string, String string2, Integer int1, Double double1, String string3) {
	     // Write code here that turns the phrase above into concrete actions
		 p.setDescription(string2);
		 p.setId(int1);
		 p.setPrice(double1);
		 p.setTitle(string);
		 p.setValidityPeriod(string3);
		  added=true;
	 }

	 @Then("the new Package is successfully added to the system")
	 public void theNewPackageIsSuccessfullyAddedToTheSystem() {
	     if (added) {
	         System.out.println("The new package is successfully added to the system.");
	     } else {
	         throw new io.cucumber.java.PendingException();
	     }
	 }


	 
	 
	 @When("the administrator deletes the package")
	 public void theAdministratorDeletesThePackage() {
	     // Write code here that turns the phrase above into concrete actions
		 removed=true;
	 }
	 @Then("the package with id {int} should be successfully deleted")
	 public void thePackageWithIdShouldBeSuccessfullyDeleted(Integer int1) {
		 
		 
		 if (removed) {
	            printing.printSomething("Deleted successfully");
	        }
		 else {
	     // Write code here that turns the phrase above into concrete actions
	     throw new io.cucumber.java.PendingException();}
	 }
	 
	 
	 
	 @When("the administrator updates the package acording to {int} and {string}")
	 public void theAdministratorUpdatesThePackageAcordingToAnd(Integer string, String string2) {
		  if (string == 1) {
	            p.setDescription(string2);
	            updated = true;
	        } else if (string == 2) {
	           p.setId(Integer.parseInt(string2));
	            updated = true;
	        } else if (string == 3) {
	            p.setPrice((Double.parseDouble(string2)));
	            updated = true;
	        } else if (string ==4)
	        	{
	        		p.setTitle(string2);
	        	}
	        	else 
	        	{ p.setValidityPeriod(string2);
	            updated = true;
	        }
		 
		 
		 // Write code here that turns the phrase above into concrete actions
	 }
	 @Then("the package  should be successfully updated")
	 public void thePackageShouldBeSuccessfullyUpdated() {
		 
		 
		 if (updated) {
	            printing.printSomething("Updated successfully");
	        }
		 else {
	     // Write code here that turns the phrase above into concrete actions
	     throw new io.cucumber.java.PendingException();}
	 }
	 
	 @When("the administrator selects to view all packages")
	 public void theAdministratorSelectsToViewAllPackages() {
	     // Write code here that turns the phrase above into concrete actions
	        System.out.println("The administrator selects to view list of existing discounts.");
	 }
	 @Then("the system should display a list of all available packages")
	 public void theSystemShouldDisplayAListOfAllAvailablePackages() {
	     // Write code here that turns the phrase above into concrete actions
	        System.out.println("The administrator views the list of existing discounts.");
	 }
	 
 }