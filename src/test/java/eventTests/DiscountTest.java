package eventTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.example.*;
import org.junit.Test;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DiscountTest {
    Discount discount = new Discount();
    static boolean adding = false;
    static boolean updated = false;
    static boolean deleteProfile = false;
    static boolean addevent = false;
    static boolean appliddiscount = false;
    static boolean navigates = false;
    Printing printing = new Printing();
    private Discount oldDiscount;
    private Discount newDiscount;



    @Given("navigates to the discount management section")
    public void navigatesToTheDiscountManagementSection() {
        navigates = true;
    }


    @When("the administrator provides the discount details such as {string}, {string}, {string}, and {string}")
    public void theAdministratorProvidesTheDiscountDetailsSuchAsAnd(String discountpercentage, String discountid, String validityperiod, String discountcode) {
        // Implement code here to provide the discount details
        discount.setdiscountcode(discountcode);
        discount.setsiscountid(Integer.parseInt(discountid));
        discount.setdiscountpercentage(Double.parseDouble(discountpercentage));
        discount.setvalidity(validityperiod);
        
        adding = true;
    }
    @Then("the new discount is successfully added to the system")
    public void theNewDiscountIsSuccessfullyAddedToTheSystem() {
        if (adding) {
            // Add your assertion or verification here to confirm that the discount is successfully added
            System.out.println("The new discount is successfully added to the system.");
        }
    }

    @When("the administrator selects the discount to edit and updates the necessary details according to {int} and {string}")
    public void theAdministratorSelectsTheDiscountToEditAndUpdatesTheNecessaryDetails(Integer i, String val) {
        if (i == 1) {
            discount.setsiscountid(Integer.parseInt(val));
            updated = true;
        } else if (i == 2) {
            discount.setdiscountcode(val);
            updated = true;
        } else if (i == 3) {
            discount.setdiscountpercentage(Double.parseDouble(val));
            updated = true;
        } else {
            discount.setvalidity(val);
            updated = true;
        }
    }

    @Then("the changes are saved")
    public void theChangesAreSaved() {
        if (updated) {
            printing.printSomething("Updated successfully");
        }
    }

    @When("the administrator selects the discount to delete and confirms the action")
    public void theAdministratorSelectsTheDiscountToDeleteAndConfirmsTheAction() {
        deleteProfile = true;
    }

    @Then("the discount is successfully removed from the system")
    public void theDiscountIsSuccessfullyRemovedFromTheSystem() {
        if (deleteProfile) {
            printing.printSomething("Deleted successfully");
        }
    }

    @Given("the customer Added event")
    public void theCustomerAddedEvent() {
        addevent = true;
    }

    @When("the customer enters the valid discount code or selects a pre-applied discount")
    public void theCustomerEntersTheValidDiscountCodeOrSelectsAPreAppliedDiscount() {
        appliddiscount = false;
    }
    @Given("views the list of existing discounts")
    public void viewsTheListOfExistingDiscounts() {
        // Implement code here to view the list of existing discounts
        System.out.println("The administrator views the list of existing discounts.");
    }

    @Then("the discount is applied to the total order amount")
    public void theDiscountIsAppliedToTheTotalOrderAmount() {
        // Write code here to apply the discount to the total order amount
    }

  
    @When("the administrator selects the discount to edit and updates the necessary details")
    public void theAdministratorSelectsTheDiscountToEditAndUpdatesTheNecessaryDetails() {
        // Implement code here to select the discount to edit and update necessary details
        System.out.println("The administrator selects the discount to edit and updates the necessary details.");
    }
    
    
    @Given("accesses the list of existing discounts")
    public void accessesTheListOfExistingDiscounts() {
        // Implement code here to access the list of existing discounts
        System.out.println("The administrator accesses the list of existing discounts.");
    }
    @Test
    public void testDiscountInitialization() {
        Discount discount = new Discount(0.1, 1, "2024-12-31", "DISCOUNT10");
        assertNotNull(discount);
        assertEquals(1, discount.getDiscountId());
        
    }
    
    @Test
    public void testDiscountModification() {
        Discount discount = new Discount();
        discount.setsiscountid(2);
        discount.setdiscountcode("DISCOUNT20");
        discount.setdiscountpercentage(0.2);
        discount.setvalidity("2025-12-31");

        assertEquals(2, discount.getDiscountId());
        
    }
   
}
