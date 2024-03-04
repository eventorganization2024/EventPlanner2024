package eventSteps;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.example.Event;
import org.example.Functions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewSteps {

boolean show_Adminlist ,
show_ListOfEventsforAdmin,
show_allcustomers,
show_VenueforAdmin,
show_allProviders,
show_customerlist,
show_eventsofCustomer,
show_Venueforcustomer,
show_providerlist;



Event e  = new Event("Conference", "2024-03-15", "9:00 AM", "Tech conference on AI and Machine Learning", "200", "admin123", "Tech", "Conference", "111");

Functions F=new Functions();

	@Given("the Administrator has selected show Adminlist")
	public void theAdministratorHasSelectedShowAdminlist() {
     show_Adminlist =true;
	
	}
	@Then("the list Adminlist  displayed")
	public void theListAdminlistDisplayed() {
    assertTrue(show_Adminlist); F.adminList();
	}
//////////////////////////////////////////////////////////////////


	@Given("the Administrator has selected show customar")
	public void theAdministratorHasSelectedShowCustomar() {
	 show_allcustomers =true;
	}
	@Then("the list of users  displayed")
	public void theListOfUsersDisplayed() {
		 assertTrue(show_allcustomers); //F.viewCustomers();               ////////////////////////////////////////////////////////////////////
	}


/////////////////////////////////////////////////////////////////////////////////
	
	@Given("the Administrator has selected show events")
	public void theAdministratorHasSelectedShowEvents() {
		show_ListOfEventsforAdmin =true;
	}
	@Then("the list of events  displayed")
	public void theListOfEventsDisplayed() {
		 
		assertTrue(show_ListOfEventsforAdmin); F.viewalleventsforAdmin("event.txt");              
	}


/////////////////////////////////////////////////////////////////////////////////


	@Given("the Administrator has selected show venues")
	public void theAdministratorHasSelectedShowVenues() {
		show_VenueforAdmin =true;
   }
	@Then("the list of venues  displayed")
	public void theListOfVenuesDisplayed() {
		 assertTrue(show_VenueforAdmin); F.viewallVenuesforAdmin();    /////////////////////////////////////////////////////          
	}

/////////////////////////////////////////////////////////////////////////////////

	@Given("the Administrator has selected show providers")
	public void theAdministratorHasSelectedShowProviders() {
	
	show_allProviders=true;
	}
	@Then("the list of  providers  displayed")
	public void theListOfProvidersDisplayed() {
		 assertTrue(show_allProviders); F.viewallProviders();    /////////////////////////////////////////////////////          
			
	}

/////////////////////////////////////////////////////////////////////////////////


	@Given("the User has selected show Userlist")
	public void theUserHasSelectedShowUserlist() {

	show_customerlist=true;
	}
	@Then("the list Userlist  displayed")
	public void theListUserlistDisplayed() {
		 assertTrue(show_customerlist); F.customerPageList();
	}

/////////////////////////////////////////////////////////////////////////////////

	@Given("the User has selected show Myevents")
	public void theUserHasSelectedShowMyevents() {
	show_eventsofCustomer=true;
	}
	@Then("the list of Myevents  displayed")
	public void theListOfMyeventsDisplayed() {
		 assertTrue(show_eventsofCustomer);//.viewCostomerevents();         //////////////////////////////
			
	}


//////////////////////////////////////////////////////////////////////////////////


	@Given("the USer has selected show available venues")
	public void theUSerHasSelectedShowAvailableVenues() {
		show_Venueforcustomer=true;
		}
	@Then("the list of available venues  displayed")
	public void theListOfAvailableVenuesDisplayed() {
		 assertTrue(show_Venueforcustomer); F.viewallVenuesforCoustmer();         //////////////////////////////
			}

	
	
	
//////////////////////////////////////////////////////////////////////////////////

	

/////////////////////////////////////////////////////////////////////////////////


	@Given("the provider has selected show  providerlist")
	public void theProviderHasSelectedShowProviderlist() {
	show_providerlist=true;
	
	}
	@Then("the list  providerlist  displayed")
	public void theListProviderlistDisplayed() {
		 assertTrue(show_providerlist); F.providerPageList();              

	}




	
	

}
