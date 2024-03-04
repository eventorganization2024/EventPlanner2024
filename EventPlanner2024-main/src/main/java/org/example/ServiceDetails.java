package org.example;

public class ServiceDetails {
 //   private int serviceID;
    private String serviceType;
    private String serviceName;
    private String description;
    private double price;
    private String availability;

    // Constructors
    public ServiceDetails() {
        // Default constructor
    }

    public ServiceDetails( String serviceType, String serviceName, String description, double price, String availability) {//int serviceID,
      //  this.serviceID = serviceID;
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.availability = availability;
    }

    // Getters and Setters
  /*  public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
*/
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
}

/*package org.example;

import java.io.FileWriter;
import java.io.IOException;
import org.example.*;
public class Provider extends User {
  
 
    private String email;
  
    public Provider(String id, String password, String name, String phone,String email) 
    {
        super( name, password,  "Provider");    
        this.phone=phone;
        this.id=id;
        this .email=email;      
    }
    
    public Provider() {}
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
 
    
}*/