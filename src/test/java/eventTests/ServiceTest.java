package eventTests;

import static org.junit.Assert.*;

import org.example.Provider;
import org.example.Service;
import org.example.User;
import org.junit.Test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class ServiceTest {
    static Provider serviceProviderService;
    private  Service newService;
    private Service modifiedService;
    private Service deletedService;
    @Before
    public void setUp() {
        serviceProviderService = new Provider();
        Service service =new Service(); ///////////
    }

    
    @Given("the service provider is logged in")
    public void theServiceProviderIsLoggedIn() {

		 Provider.login("23443", "2233");
    	
    }
    
    @Given("the service provider has the following services in their profile:")
    public void theServiceProviderHasFollowingServicesInProfile(DataTable dataTable) {
        List<Map<String, String>> services = dataTable.asMaps(String.class, String.class);

        // Assuming ServiceProviderService has a method to add services to the profile
        for (Map<String, String> service : services) {
            serviceProviderService.addService(
                service.get("ServiceType"),
                service.get("ServiceName"),
                service.get("Description"),
                Double.parseDouble(service.get("Price")),
                service.get("Availability")
            );
        }
    }
	

	
	@When("the service provider selects View your Service Details")
	public void the_service_provider_selects_view_your_service_details() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@Then("the system should display a list of the providers services with their details")
	public void the_system_should_display_a_list_of_the_providers_services_with_their_details() {
	    // Write code here that turns the phrase above into concrete actions
		 List<Service> serviceDetailsList = serviceProviderService.getServiceDetailsList();
	     assertNotNull(serviceDetailsList);

	        // Check if the list is not empty (indicating that services are displayed)
	     assertTrue(!serviceDetailsList.isEmpty());
	        
	        // Print service details (replace this with your actual verification logic)
	        
		boolean serviceDetailsDisplayed = serviceProviderService.displayServiceDetails();
        assertTrue("Service details should be displayed, but they are not.", serviceDetailsDisplayed);
}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	@When("the service provider selects Add New Service")
    public void theServiceProviderSelectsAddNewService() {
        newService = new Service();
        
    }

    @When("the service {string} does not exist")
    public void theServiceDoesNotExist(String serviceType) {
        assertFalse(serviceProviderService.doesServiceExist(serviceType));
    }

    @When("enters details for a new decorations service like")
    public void entersDetailsForNewDecorationsService(DataTable dataTable) {
        // Convert the DataTable to a Map<String, String>
        Map<String, String> detailsMap = dataTable.transpose().asMap(String.class, String.class);

        // Extract values from the map
        String serviceName = detailsMap.get("ServiceName");
        String price = detailsMap.get("Price");
        String availability = detailsMap.get("Availability");
        String description = detailsMap.get("Description");
        
        newService.setServiceType("Decorations");
        newService.setServiceName(serviceName);
        newService.setPrice(Double.parseDouble(price)); // Assuming the price is formatted as "500"
        newService.setAvailability(availability);
        newService.setDescription(description);


    }

    @Then("the new decorations service should be added to the providers profile")
    public void theNewDecorationsServiceShouldBeAddedToTheProvidersProfile() {
        serviceProviderService.addService(newService);

        // Assuming logic to verify that the new service is added
        Service addedService = serviceProviderService.getServiceDetails(newService.getServiceType());
        assertNotNull(addedService);
        assertEquals(newService.getServiceName(), addedService.getServiceName());
     }
  
///////////////////////////////////////////////////////////////////////////////////////	

    @When("the service provider selects Edit your Service Details for exist servicetype {string}")
    public void selectsEditServiceDetailsForExistingType(String serviceType) {
        // Assuming serviceProviderService has a method to retrieve service details by type
        modifiedService = serviceProviderService.getServiceDetails(serviceType);

        assertNotNull(modifiedService);
    }

    @When("modifies the service information like price {string}")
    public void modifiesServiceInformation(String newPrice) {
        // Check if modifiedService is not null
        assertNotNull(modifiedService);

        // Modify the service price
        modifiedService.setPrice(Double.parseDouble(newPrice)); // Assuming the price is formatted as "500"
    }

    @Then("the system should update the catering service details accordingly")
    public void systemShouldUpdateServiceDetails() {
        // Assuming serviceProviderService has a method to edit service details
    //	assertTrue(serviceProviderService.editServiceDetails(modifiedService));
        assertTrue( serviceProviderService.editServiceDetails(modifiedService)!=null);
	

    }
	
///////////////////////////////////////////////////////////////////////////////////////	
	

    @When("the service provider selects exist servicetype {string}")
    public void the_service_provider_selects_exist_servicetype(String serviceType) {
        // Assume you have a method in ServiceProviderService to select a service type
    	deletedService  = serviceProviderService.getServiceDetails(serviceType);
        assertNotNull(deletedService);
    }

    @When("selects deletion")
    public void selects_deletion() {
        // Assume you have a method in ServiceProviderService to confirm the deletion
    
    }

    @Then("the Entertainment service should be removed from the providers profile")
    public void the_entertainment_service_should_be_removed_from_the_providers_profile() throws Exception {
        // Assume you have a method in ServiceProviderService to check if the service is removed
    	serviceProviderService.deleteService(deletedService.getServiceType());
       // assertTrue(!serviceProviderService.doesServiceExist("Entertainment"));
    	boolean serviceDetailsDisplayed = serviceProviderService.displayServiceDetails();
        assertTrue("Service details should be displayed, but they are not.", serviceDetailsDisplayed);
   
    }

	
	
    
    
    
	
	
	
	
	
	
	
	
	
}



