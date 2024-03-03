package eventSteps;

import static org.junit.Assert.*;

import org.example.Provider;
import org.example.ServiceDetails;
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

public class ServiceStepDefinitions {
    static Provider serviceProviderService;
    private ServiceDetails newService;
    private ServiceDetails modifiedService;
    private ServiceDetails deletedService;
    @Before
    public void setUp() {
        serviceProviderService = new Provider();
    }

    
    @Given("the service provider is logged in")
    public void theServiceProviderIsLoggedIn() {

		// Assuming you have a method in your application to log in a service provider
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
		 List<ServiceDetails> serviceDetailsList = serviceProviderService.getServiceDetailsList();
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
        newService = new ServiceDetails();
        
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
        ServiceDetails addedService = serviceProviderService.getServiceDetails(newService.getServiceType());
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
    public void the_entertainment_service_should_be_removed_from_the_providers_profile() {
        // Assume you have a method in ServiceProviderService to check if the service is removed
    	serviceProviderService.deleteService(deletedService.getServiceType());
        assertTrue(!serviceProviderService.doesServiceExist("Entertainment"));
    	boolean serviceDetailsDisplayed = serviceProviderService.displayServiceDetails();
        assertTrue("Service details should be displayed, but they are not.", serviceDetailsDisplayed);
   
    }

	
	
    
    
    
	
	
	
	
	
	
	
	
	
}





/* private Provider serviceProviderService;
    private ServiceDetails editedService;
    private int deletedServiceID;

    @Given("the service provider is logged in")
    public void theServiceProviderIsLoggedIn() {
        serviceProviderService = new Provider();
    }

    @Given("the service provider has the following services in their profile:")
    public void theServiceProviderHasTheFollowingServices(java.util.List<org.example.ServiceDetails>serviceList) {//List<ServiceDetails> serviceList
        serviceList.forEach(serviceProviderService::addService);
    }
    
    @When("the service provider selects View your Service Details")
    public void the_service_provider_selects_view_your_service_details() {
        // For the purpose of this example, we are not performing any specific action here
    }

    @Then("the system should display a list of the providers services with their details")
    public void the_system_should_display_a_list_of_the_providers_services_with_their_details() {
        // Implement the assertion based on your testing framework
        List<ServiceDetails> serviceDetailsList = serviceProviderService.getServiceDetailsList();
        assertNotNull(serviceDetailsList);

        // Check if the list is not empty (indicating that services are displayed)
        assertTrue(!serviceDetailsList.isEmpty());
        
        // Print service details (replace this with your actual verification logic)
        for (ServiceDetails service : serviceDetailsList) {
            System.out.println("ServiceID: " + service.getServiceID());
            System.out.println("ServiceType: " + service.getServiceType());
            System.out.println("ServiceName: " + service.getServiceName());
            System.out.println("Description: " + service.getDescription());
            System.out.println("Price: " + service.getPrice());
            System.out.println("Availability: " + service.getAvailability());
            System.out.println("====================");
        }
    }  
  //////////////

    @When("the service provider selects {string} for ServiceID {int} \\({string})")
    public void theServiceProviderSelectsOperationForServiceID(String operation, int serviceID, String serviceType) {
        if (operation.equals("Edit your Service Details")) {
            editedService = new ServiceDetails();
            editedService.setServiceID(serviceID);
            editedService.setServiceType(serviceType);
        } else if (operation.equals("Delete Service")) {
            deletedServiceID = serviceID;
        }
    }

    @When("modifies the service information")
    public void modifiesTheServiceInformation() {
    	// Modify service details in the editedService object
        // (Simulating user modifying service details)
        editedService.setServiceName("Modified Catering Service");
        editedService.setDescription("Modified description for catering service");
        editedService.setPrice(600.0); // Set a numerical value
        editedService.setAvailability("Modified availability for booking");

        // Implement the assertion based on your testing framework
        ServiceDetails existingService = serviceProviderService
                .getServiceDetailsList()
                .stream()
                .filter(service -> service.getServiceID() == editedService.getServiceID())
                .findFirst()
                .orElse(null);

        // Assert that the modified details match the expected values
        assertNotNull(existingService);
        assertEquals("Modified Catering Service", existingService.getServiceName());
        assertEquals("Modified description for catering service", existingService.getDescription());
        assertEquals(600.0, existingService.getPrice(), 0.01); // Adjust delta based on your precision requirements
        assertEquals("Modified availability for booking", existingService.getAvailability());
   
    }

    @When("enters details for a new {string} service")
    public void entersDetailsForANewService(String serviceType) {
        ServiceDetails newService = new ServiceDetails();
        newService.setServiceType(serviceType);
        newService.setServiceName("New " + serviceType + " Service");
        newService.setDescription("New description for " + serviceType + " service");
        newService.setPrice(400.0); // Set a numerical value
        newService.setAvailability("Available for booking");
        serviceProviderService.addService(newService);
    }

    @When("confirms the deletion")
    public void confirmsTheDeletion() {
        serviceProviderService.deleteService(deletedServiceID);
    }

    @When("clicks the save button")
    public void clicksTheSaveButton() {
        serviceProviderService.editService(editedService);
    }

    @Then("the system should display a list of the provider's services with their details")
    public void theSystemShouldDisplayListOfServiceDetails() {
        // Implement the assertion based on your testing framework
        assertNotNull(serviceProviderService.getServiceDetailsList());
    }*/
