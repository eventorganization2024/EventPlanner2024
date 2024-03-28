package eventSteps;
 import eventSteps.*;
 import java.io.ByteArrayInputStream;
 import java.io.InputStream;
import org.example.Paackage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 public class PaackageTest
 {
	 ////
	 Paackage p=new Paackage(); 
	 
	 boolean added=false;
	 boolean updated=false;
	 boolean removed=false;
	 Printing printing =new Printing();
	 private List<Paackage> packages;
	 
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
	 }}
	 
	 
	 
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
	 }}
	 
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
	 
	 public void setUp() {
	        p = new Paackage();
	        packages = new ArrayList<>();
	        packages.add(new Paackage(1, "Package 1", 100.0));
	        packages.add(new Paackage(2, "Package 2", 200.0));
	    }

	    @Test
	    public void testPackageConstructor() {
	        Paackage package1 = new Paackage(1, "Description", 100.0);
	        assertEquals(1, package1.getId());
	        assertEquals("Description", package1.getDescription());
	        assertEquals(100.0, package1.getPrice(), 0.001);
	        assertNull(package1.getTitle());
	        assertNull(package1.getValidityPeriod());
	    }

	    @Test
	    public void testSetAndGetId() {
	        p.setId(1);
	        assertEquals(1, p.getId());
	    }

	    @Test
	    public void testSetAndGetDescription() {
	        p.setDescription("Description");
	        assertEquals("Description", p.getDescription());
	    }

	    @Test
	    public void testSetAndGetPrice() {
	        p.setPrice(100.0);
	        assertEquals(100.0, p.getPrice(), 0.001);
	    }

	    @Test
	    public void testSetAndGetValidityPeriod() {
	        p.setValidityPeriod("2024-03-28");
	        assertEquals("2024-03-28", p.getValidityPeriod());
	    }

	    @Test
	    public void testSetAndGetTitle() {
	        p.setTitle("Title");
	        assertEquals("Title", p.getTitle());
	    }
	   
	    @Test
	    public void testUpdatePackageDetails() {
	        Paackage packageToUpdate = packages.get(0); // Choose the package to update
	        String newTitle = "New Title";

	        // Prepare input for the scanner
	        String input = "2\n" + newTitle + "\n";

	        // Create a mock Scanner object using ByteArrayInputStream
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        Scanner scanner = new Scanner(in);

	        // Invoke the method to be tested
	        Paackage.updatePackageDetails(scanner, packageToUpdate, "package.txt", packages);

	        // Verify that the package title is updated
	        assertEquals(newTitle, packageToUpdate.getTitle());
	    }
 }