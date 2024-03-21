package eventSteps;

import org.example.Discount;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Discountcustomertest {
	Discount d= new Discount ();
	boolean expired;
double oldprice ;
double newprice;
double price;
boolean valid ;

@Given("the discount code {string} is valid")
public void theDiscountCodeIsValid(String string) {
	expired=false;
	valid = true;
   d.setdiscountcode(string);
}
@When("the customer applies the discount code {string} to a price of ${int}")
public void theCustomerAppliesTheDiscountCodeToAPriceOf$(String string, Integer int1) {
newprice = int1 *oldprice;

}
@Then("the discounted price should be ${int}")
public void theDiscountedPriceShouldBe$(Integer int1) {

price = newprice;
}



@Given("the discount code {string} is expired")
public void theDiscountCodeIsExpired(String string) {
	expired=true;
	
}
@When("the customer applies the expired discount code {string} to a price of ${int}")
public void theCustomerAppliesTheExpiredDiscountCodeToAPriceOf$(String string, Integer int1) {
newprice=oldprice;

}
@Then("the discounted price should remain ${int}")
public void theDiscountedPriceShouldRemain$(Integer int1) {
  price=oldprice;
}

@Given("the discount code {string} is not valid")
public void theDiscountCodeIsNotValid(String string) {
   valid = false;
}
@When("the customer applies the invalid discount code {string} to a price of ${int}")
public void theCustomerAppliesTheInvalidDiscountCodeToAPriceOf$(String string, Integer int1) {
 price = oldprice;
}
}