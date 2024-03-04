package org.example;


import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.example.*;

public class Provider extends User  {
   public List<ServiceDetails> serviceDetailsList;
    private String email;
    static Printing printing = new Printing();
    private static List<Provider> providers = new ArrayList<>(); 
    Functions f=new Functions();
   
///////////////////////////////////////////////////////////////////////
    public Provider() {this.serviceDetailsList = new ArrayList<>();}
    
    
     public Provider(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    static {
        // Load provider data from the file when the class is initialized
        loadProviderDataFromFile("Provider.txt");
    }

///////////////////////////////////////////////////////////////////////////// 
    public static void login(String username, String password) {
        Provider authenticatedProvider = authenticateProvider(username, password);

        if (authenticatedProvider != null) {
        	 printing.printSomething("Service provider logged in: " + username);
        } else {
        	 printing.printSomething("Login failed. Invalid username or password.");
        }
    }
//////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////////////
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
    
    
    
	public void addService( String serviceType, String serviceName,String description, double price, String availability) {
	ServiceDetails newService = new ServiceDetails();
	newService.setServiceType(serviceType);
	newService.setServiceName(serviceName);
	newService.setDescription(description);
	newService.setPrice(price);
	newService.setAvailability(availability);
     serviceDetailsList.add(newService);}

//////////////////////////////////////////////////////////////////////////////////
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
       Provider provider =new  Provider();
    	String[] items = line.split(" , ");
       if (items.length >= 6) {
          String id = items[0];
        String name = items[1];
        String phone = items[2];
        String address = items[3];
        String email = items[4];
        String password=items[5];
        
        provider.setId(id);
        provider.setEmail(email);
        provider.setAddress(address);
        provider.setPassword(password);
        provider.setName(name);
        provider.setPhone(phone);
        
        
        
       }else {
    	      
           System.err.println("Invalid line format: " + line);

       }
        return  provider;
        
    }

    public boolean displayServiceDetails() {
        printing.printSomething("\n    ====================================================================================================================\n");
        printing.printSomething("      |Service ID   | ServiceType  | ServiceName            | Description                         | Price    | Availability             |\n");
        printing.printSomething("      |-------------|-------------|------------------------|-------------------------------------|----------|--------------------------|\n");

        for (ServiceDetails service : serviceDetailsList) {
            printing.printSomething(String.format("      |%-13s| %-12s| %-23s| %-36s| %-6.2f $ | %-23s  |\n",
                    service.getServiceID(), service.getServiceType(), service.getServiceName(),
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
	public void deleteService(String serviceID) throws FileNotFoundException, IOException {
		f.updateServiceList();
		f.updateProviderAndServiceList();
		f.updateProviderAndServiceList();
       if (serviceDetailsList != null) {
           ServiceDetails serviceToDelete = null;
            for (ServiceDetails service : f.serviceDetails) {
                if (service.getServiceID() .equals(serviceID)) {
                    serviceToDelete = service;
                    break;
                    
                    
                }
            }

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
