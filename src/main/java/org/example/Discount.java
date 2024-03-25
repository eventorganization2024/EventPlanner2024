package org.example;

public class Venue {
    private String venueId;
    private String name;
    private String address;
    private int capacity;
    private double price;
    private String availability;
    private String imagepath;
    private String date;

   
    public Venue(String name, String address, int capacity, double price, String availability, String id, String image) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.price = price;
        this.availability = availability;
        this.venueId = id;
        this.imagepath = image;
    }

    public Venue() {
      
    }

    public Venue(String venueId, String name, String address, int capacity, double price, String image) {
        this.address = address;
        this.capacity = capacity;
        this.name = name;
        this.price = price;
        this.venueId = venueId;
        this.imagepath = image;
    }

    public Venue(String name, String address, int capacity, String imagepath, double price, String availability, String date) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.imagepath = imagepath;
        this.price = price;
        this.date = date;
        this.availability = availability;
    }

   
    public String getId() {
        return venueId;
    }

    public void setId(String id) {
        this.venueId = id;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return imagepath;
    }

    public void setImage(String image) {
        this.imagepath = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toFileString() {
        return venueId + "," + name + "," + address + "," + imagepath + "," + capacity + "," + price;
    }
}
