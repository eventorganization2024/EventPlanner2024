package org.example;

public class ServiceDetails {
    private String serviceType; //Note  serviceID is the serviceType
    private String serviceName;
    private String description;
    private double price;
    private String availability;

    // Constructors
    public ServiceDetails() {
        // Default constructor
    }

    public ServiceDetails( String serviceType, String serviceName, String description, double price, String availability) {
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.availability = availability;
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
}

