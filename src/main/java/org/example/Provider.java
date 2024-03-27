package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Provider extends User  {
    private static final List<ServiceDetails> serviceDetailsList = new ArrayList<>();
    private String email;
    static Printing printing = new Printing();
    static List<Provider> providers = new ArrayList<>(); 
    Functions f=new Functions();
   
///////////////////////////////////////////////////////////////////////
    public Provider() {  }
    
    
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
            	String[] providerData = line.split(",");                    
            	String username = providerData[0].trim();
                String password = providerData[5].trim(); // Assuming password is at index 5
                      providers.add(new Provider(username, password));
                
            }
        } catch (IOException e) {
        	 printing.printSomething("error at loadProviderDataFromFile :"+e); // Handle or log the exception as needed
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////
    private static Provider authenticateProvider(String username, String password) {
    	providers.clear(); // Clear the list before reloading data    
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
        for (ServiceDetails service : serviceDetailsList) {
            if (service.getServiceType() .equals (editedService.getServiceType())) {
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
    	      
    	   printing.printSomething("Invalid line format: " + line);

       }
        return  provider;
        
    }

    public static boolean displayServiceDetails() {
        printing.printSomething("\n    =====================================================================================================================\n");
        printing.printSomething("   |Service ID   | ServiceType | ServiceName            | Description                         | Price    | Availability   |\n");
        printing.printSomething("   |-------------|-------------|------------------------|-------------------------------------|----------|----------------|\n");

        for (ServiceDetails service : serviceDetailsList) {
            printing.printSomething(String.format("   |%-13s| %-12s| %-23s| %-36s| %-6.2f $ | %-23s|%n",
                    service.getServiceID(), service.getServiceType(), service.getServiceName(),
                    service.getDescription(), service.getPrice(), service.getAvailability()));
        }

        printing.printSomething("    ====================================================================================================================\n");
        return true;
    }

	


	public boolean doesServiceExist(String serviceType) {
		  if (serviceDetailsList != null) {
	            for (ServiceDetails serviceDetails : serviceDetailsList) {
	                if (serviceDetails != null && serviceDetails.getServiceID() != null
	                        && serviceDetails.getServiceType().equalsIgnoreCase(serviceType)) {
	                    return true; // Service of specified type already exists
	                }
	            }
	        }
	        return false; // Service of specified type does not exist or serviceDetailsList is null
	    }
//////////////////////////////	
	public void deleteService(String serviceID) throws Exception {
	    Functions.updateServiceList();
	    Functions.updateProviderAndServiceList();
	    Functions.updateProviderAndServiceList();

	    if (serviceDetailsList != null && !serviceDetailsList.isEmpty()) {
	        ServiceDetails serviceToDelete = null;
	        for (ServiceDetails service : Functions.serviceDetails) {
	            if (service.getServiceID().equals(serviceID)) {
	                serviceToDelete = service;
	                break;
	            }
	        }

          if (serviceToDelete != null) {
                serviceDetailsList.remove(serviceToDelete);
                deleteProviderFromFileAndArrayList("service.txt", serviceID);
                  
            } else {
            	printing.printSomething("Service not found.\n");
            }
        } else {
        	printing.printSomething("Service details list is null.\n");
        }}	
	
/////////////////////

	public void delete_provider_from_file_and_arraylist( String fileName, String pid) throws IOException {
	    String line;
	    StringBuilder sb = new StringBuilder();
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	        while ((line = reader.readLine()) != null) {
	            String[] items = line.split(" , ");
	            if (items.length >= 6) {
	            	String providerId = items[0];
	            	if (!providerId.equals(pid)) {
	                    sb.append(line).append("\n");
	                }
	            }
	        }
	    }

	    try (FileOutputStream fos = new FileOutputStream(fileName, false)) {
	        fos.write(sb.toString().getBytes());
	    }
	}

///////////////////////////////
	public void deleteProviderFromFileAndArrayList(String filename,String SID)throws Exception 
	{ 
	
		
	    String line;
		 StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
		{   
	        while ((line = reader.readLine()) != null)
	        {   String[] items = line.split(" , ");
	        if (items.length >= 7) 
	        {
	        	  String SERVICEID=items[0];
	        	  if (!SERVICEID.equals(SID)) {
	                  sb.append(line).append("\n");
	              } 
	        } 
	   
	        }
	        
	       
	     }
	         try (FileOutputStream fos = new FileOutputStream(filename, false)) {
		            fos.write(sb.toString().getBytes());
		        }
			
	         
	              
	}
	
/////////////////

    public String toString3() {
        return  
                "UserID='" + id + '\'' +
                ",1. Name=" + username+'\'' +
                ",2. Address='" + address + '\'' +
                ",3. Phone=" + phone +'\'' +
                ",4. Email='" + email + '\'' +
                ",5. Password=" + password +'\'' 
                
                ;
    }


    public String toString2() {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[0;36m"); // Set text color to cyan
      
        sb.append("\033[0;33m"); // Set text color to yellow for attribute names
        sb.append("- UserID: ").append(id).append("\n");
        sb.append("- Name: ").append(username).append("\n");
        sb.append("- Address: ").append(address).append("\n");
        sb.append("- Phone: ").append(phone).append("\n");
        sb.append("- Email: ").append(email).append("\n");
        sb.append("- Password: ").append(password).append("\n");

        sb.append("\033[0m"); // Reset text color
        return sb.toString();
    }


    public String toString() 
    {
    	 return	   username +
                 ", " + address +
                 "," + phone + 
               
                 ", " + password +
                  ", "+id+
          
                 ", " + email ;
 	
    }

//////////////////
    public static ServiceDetails findServiceByID(String serviceId, String filename) {
	    String line;
	    ServiceDetails service =new ServiceDetails();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        while ((line = reader.readLine()) != null) {
	            String[] items = line.split(" , ");
	            if (items.length >= 7) {
	    	        
	            	String sid = items[0];
	            	if (sid.equals(serviceId)) {
	            	
					service.setServiceID(items[0]);
	    	        service.setProviderID(items[1]);
	    	        service.setServiceType(items[2]);
	    	        service.setServiceName(items[3]);
	    	        service.setDescription(items[4]);
	    	        service.setPrice(Double.parseDouble(items[5]));
	    	        service.setAvailability(items[6]);

	                    return service;}
	               
	            } else {
	            	printing.printSomething("Invalid line format for service ID " + serviceId + ": " + line);
	            }
	        }
	    } catch (IOException e) {
	    	printing.printSomething("Error reading file: " + e.getMessage());
	    }

	    return null; // Service not found or error occurred
	}   
    
////////    
    
    public void updateServiceDetails(String sid) throws Exception {
		
 	   ServiceDetails toupdatedService = findServiceByID(sid,"service.txt");
 		deleteService(sid);
 	   
 	   
 		if ( toupdatedService != null)
 		{
 			Scanner scanner = new Scanner(System.in);
 			printing.printSomething("""
 				    Choose which field to update:
 				    1. Service Type
 				    2. Service Name
 				    3. Description
 				    4. Price
 				    5. Availability
 				    """);
 			
 			
 			printing.printSomething("Enter the number of the field you want to update: ");
             String x = scanner.next();
             
             switch (x) {
             case "1":
                 printing.printSomething("Enter new service type:");
                 toupdatedService.setServiceType(scanner.next());
                 break;
             case "2":
                 printing.printSomething("Enter new service name:");
                 toupdatedService.setServiceName(scanner.next());
                 break;
             case "3":
                 printing.printSomething("Enter new service description:");
                 toupdatedService.setDescription(scanner.next());
                 break;
             case "4":
                 printing.printSomething("Enter new service price:");
                 toupdatedService.setPrice(scanner.nextDouble());
                 break;
             case "5":
                 printing.printSomething("Enter new service availability:");
                 toupdatedService.setAvailability(scanner.next());
                 break;
             default:
            	 printing.printSomething("Invalid choice.");
         }
 	   
 	   
 	   
 		}
 		ServiceDetails.addServiceToFile(toupdatedService);
 			
 			
 		
 		
 		
 		
 	}

////    
	
    public static void addProviderToFile(Provider provider) {
        try (FileWriter providersFile = new FileWriter("provider.txt", true)) {
            providersFile.append(provider.getId()).append(" , ")
                        .append(provider.getUsername()).append(" , ")
                        .append(provider.getphone()).append(" , ")
                        .append(provider.getaddress()).append(" , ")
                        .append(provider.getEmail()).append(" , ")
                        .append(provider.getPassword())
                        .append("\n");
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
	
	
	
}
