package org.example;

public class Venue {
    private String name;
    private String address;
    private int capacity;
    private double price;

    // Constructor
    public Venue(String name, String address, int capacity, double price) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.price = price;
    }

    // Getters and Setters

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
}

