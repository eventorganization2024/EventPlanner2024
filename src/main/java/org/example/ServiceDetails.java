package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDetails {
    private String serviceID;
    private String providerID;
    private String serviceType;
    private String serviceName;
    private String description;
    private double price;
    private String availability;
    static Printing printing = new Printing();
    Functions f =new Functions();
     
    
    public ServiceDetails() {}

    public ServiceDetails( String serviceType, String serviceName, String description, String availability,String serviceID, double price,String providerid) {
        this.serviceID = serviceID;
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this .providerID=providerid;
    }

    public String getServiceID() {
        return serviceID;}
 

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

   public String getProviderID() {
        return providerID;
    }
   
   
   public static ServiceDetails getServiceFromLine(String line) {
	    ServiceDetails service = new ServiceDetails();
	    String[] items = line.split(" , ");
	    if (items.length >= 7) {
	        service.setServiceID(items[0]);
	        service.setProviderID(items[1]);
	        service.setServiceType(items[2]);
	        service.setServiceName(items[3]);
	        service.setDescription(items[4]);
	        service.setPrice(Double.parseDouble(items[5]));
	        service.setAvailability(items[6]);
	    } else {
	        System.err.println("Invalid line format: " + line);
	    }
	    return service;
	}

 public static double calculateTotalPrice(List<String> serviceIds) {
       // Map to store service IDs and their corresponding prices
       Map<String, Double> servicePrices = getServicePricesFromFile("service.txt");
       double totalPrice = 0.0;

       // Iterate through the chosen service IDs and sum up their prices
       for (String serviceId : serviceIds) {
           if (servicePrices.containsKey(serviceId)) {
               totalPrice += servicePrices.get(serviceId);
           } else {
               System.out.println("Price for service ID " + serviceId + " not found.");
           }
       }

       return totalPrice;
   }

   // Helper method to read service prices from file and store them in a map
   private static Map<String, Double> getServicePricesFromFile(String filename) {
       Map<String, Double> servicePrices = new HashMap<>();

       try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
           String line;
           while ((line = reader.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length >= 2) {
                   String serviceId = parts[0].trim();
                   double price = Double.parseDouble(parts[5].trim()); // Assuming price is at index 5
                   servicePrices.put(serviceId, price);
               }
           }
       } catch (IOException | NumberFormatException e) {
           e.printStackTrace(); // Handle or log the exception as needed
       }

       return servicePrices;
   }

   

   public String toString() {
       return 
    		   
    		   serviceID + ", " +
               serviceType + ", " +
               serviceName + ", " +
               description + ", " +
               price + ", " +
               availability+"\n";
   }

	public static void addServiceToFile(ServiceDetails service) {
  	    try {
  	        FileWriter serviceFile = new FileWriter("service.txt", true);
  	      serviceFile.append("\033[0;33m");

  	        serviceFile.append(service.getServiceID()).append(" , ")
  	                   .append(service.getProviderID()).append(" , ")
  	                   .append(service.getServiceType()).append(" , ")
  	                   .append(service.getServiceName()).append(" , ")
  	                   .append(service.getDescription()).append(" , ")
  	                   .append(String.valueOf(service.getPrice())).append(" , ")
  	                   .append(service.getAvailability())
  	                   .append("\n");
  	      serviceFile.append("\033[0m");

  	        serviceFile.close();
  	    } catch (IOException e) {
  	        printing.printSomething("An error occurred: " + e.getMessage());
  	    }
  	}
   
}