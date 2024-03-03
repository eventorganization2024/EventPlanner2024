package org.example;


import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.example.*;

public class Provider extends User  {
    private List<ServiceDetails> serviceDetailsList;
    private String email;
    static Printing printing = new Printing();
    private static List<Provider> providers = new ArrayList<>(); 
    public Provider() {
        this.serviceDetailsList = new ArrayList<>();
    }
    
///////////////////////////////////////////////////////////////////////
    public Provider(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    static {
        // Load provider data from the file when the class is initialized
        loadProviderDataFromFile("Provider.txt");
    }

    public static void login(String username, String password) {
        Provider authenticatedProvider = authenticateProvider(username, password);

        if (authenticatedProvider != null) {
        	 printing.printSomething("Service provider logged in: " + username);
        } else {
        	 printing.printSomething("Login failed. Invalid username or password.");
        }
    }

    private static void loadProviderDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                    String[] ProviderData = line.split(",");
                    String username = ProviderData[0].trim();
                    String password = ProviderData[5].trim(); // Assuming password is at index 5
                      providers.add(new Provider(username, password));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Provider authenticateProvider(String username, String password) {
    	providers.removeAll(providers);
    	loadProviderDataFromFile("provider.txt");
    	for (Provider provider : providers) {
            if (provider.getUsername().equals(username) && provider.getPassword().equals(password)) {
                return provider;
            }
        }
        return null; // Provider not found or invalid credentials
    }   
//////////////////////////////////////////////////////////////////////    
    public ServiceDetails getServiceDetails(String servicetype) {
        for (ServiceDetails service : serviceDetailsList) {
            if (service.getServiceType().equals(servicetype) ) {
                return service;
            }
        }
        return null; // Return null if the service with the specified ID is not found
    }
    public List<ServiceDetails> getServiceDetailsList() {
        return serviceDetailsList;
    }

    public void addService(ServiceDetails service) {
        serviceDetailsList.add(service);
        printing.printSomething("the Service added successfully.\n" );
    }
	public void addService( String serviceType, String serviceName,
	String description, double price, String availability) {
	// Create a new service
	ServiceDetails newService = new ServiceDetails();
	newService.setServiceType(serviceType);
	newService.setServiceName(serviceName);
	newService.setDescription(description);
	newService.setPrice(price);
	newService.setAvailability(availability);
	
	// Add the service to the list
	serviceDetailsList.add(newService);
	//printing.printSomething("the Service added successfully.\n " );
	
	// printing.printSomething("Service added: " + newService.toString());
	}


    public Provider editServiceDetails(ServiceDetails editedService) {
    	boolean f=false;
        for (ServiceDetails service : serviceDetailsList) {
            if (service.getServiceType() .equals (editedService.getServiceType())) {
            	f=true;
                service.setServiceType(editedService.getServiceType());
                service.setServiceName(editedService.getServiceName());
                service.setDescription(editedService.getDescription());
                service.setPrice(editedService.getPrice());
                service.setAvailability(editedService.getAvailability());
                break;
            }
        }
        return this;
    }

  /*  public void deleteService(String servicetype) {
        serviceDetailsList.removeIf(service -> service.getServiceType() == servicetype);
    }

 */ 
    public Provider(String id, String password, String name, String phone,String email) 
    {
        super( name, password,  "Provider");    
        this.phone=phone;
        this.id=id;
        this .email=email;      
    }
    
    public String getEmail() { return email;  }
    public void setEmail(String email) { this.email = email;}
    
    
    
    public static Provider getProviderFromLine(String line) {
        String[] items = line.split(" , ");
        String id = items[0];
        String name = items[1];
        String phone = items[2];
        String address = items[3];
        String email = items[4];
        String password=items[5];

        return new Provider(id, password, name, phone, email);
    }

    public boolean displayServiceDetails() {
        printing.printSomething("\n    ====================================================================================================================\n");
        printing.printSomething("      |ServiceType  | ServiceName            | Description                         | Price    | Availability             |\n");
        printing.printSomething("      |-------------|------------------------|-------------------------------------|----------|--------------------------|\n");  

        for (ServiceDetails service : serviceDetailsList) {
            printing.printSomething(String.format("      |%-13s| %-23s| %-36s| %-6.2f $ | %-23s  |\n",
                    service.getServiceType(), service.getServiceName(),
                    service.getDescription(), service.getPrice(), service.getAvailability()));
        }

        printing.printSomething("      ===================================================================================================================\n");
        return true;
    }
	


	public boolean doesServiceExist(String serviceType) {
		  if (serviceDetailsList != null) {
	            for (ServiceDetails serviceDetails : serviceDetailsList) {
	                if (serviceDetails != null && serviceDetails.getServiceType() != null
	                        && serviceDetails.getServiceType().equalsIgnoreCase(serviceType)) {
	                    return true; // Service of specified type already exists
	                }
	            }
	        }
	        return false; // Service of specified type does not exist or serviceDetailsList is null
	    }
//////////////////////////////	
	public void deleteService(String serviceType) {
        // Check if the serviceDetailsList is not null
        if (serviceDetailsList != null) {
            // Find the service to delete based on serviceType
            ServiceDetails serviceToDelete = null;
            for (ServiceDetails service : serviceDetailsList) {
                if (service.getServiceType().equalsIgnoreCase(serviceType)) {
                    serviceToDelete = service;
                    break;
                }
            }

            // Delete the service if found
            if (serviceToDelete != null) {
                serviceDetailsList.remove(serviceToDelete);
                printing.printSomething("Service deleted successfully.\n");
            } else {
            	printing.printSomething("Service not found.\n");
            }
        } else {
        	printing.printSomething("Service details list is null.\n");
        }}	
	
	
	
	
}
