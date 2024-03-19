package org.example;

import static org.example.Functions.printing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Invoice {
	
	
//	
//	 private  double totalPrice;
//	  private  String customerId;
//	    private  String invoiceId;
//	    private  String eventId;
//
//	    public Invoice(String customerId, String invoiceId, String eventId) {
//	        this.customerId = customerId;
//	        this.invoiceId = invoiceId;
//	        this.eventId = eventId;
//	        this.totalPrice = calculateTotalPrice();
//	    }
//
//	    public Invoice() {
//			// TODO Auto-generated constructor stub
//		}
//
//		private double calculateTotalPrice() {
//	        double venuePrice = getVenuePrice(eventId);
////	        double servicePrice = getServicePrice(eventId);
//	        return venuePrice ;
//	    }
//
//	    private double getVenuePrice(String eventId) {
//	        String venueName = getVenueNameFromEventFile(eventId);
//	        return getPriceFromVenueFile(venueName);
//	    }
//
////	    private double getServicePrice(String eventId) {
////	        String serviceId = getEventServiceId(eventId);
////	        return getPriceFromServiceFile(serviceId);
////	    }
//
//	    private double getPriceFromVenueFile(String venueName) {
//	        try (BufferedReader reader = new BufferedReader(new FileReader("venue.txt"))) {
//	            String line;
//	            while ((line = reader.readLine()) != null) {
//	                String[] parts = line.split(",");
//	                if (parts.length >= 6 && parts[1].trim().equalsIgnoreCase(venueName.trim())) {
//	                    return Double.parseDouble(parts[5].trim());
//	                }
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        return 0.0; // Venue price not found
//	    }
//	    
//	    private String getVenueNameFromEventFile(String eventId) {
//	        // Implement your logic to get the venue name from the event file based on the event ID
//	        // For demonstration purposes, let's assume we have a method named getVenueNameFromEventFile(eventId)
//	        return "VenueName"; // Replace with actual implementation
//	    }
//	    
//	    public String generateInvoice() {
//	        StringBuilder invoice = new StringBuilder();
//	        invoice.append("Customer ID: ").append(customerId).append("\n");
//	        invoice.append("Invoice ID: ").append(invoiceId).append("\n");
//	        invoice.append("Event ID: ").append(eventId).append("\n");
//	        invoice.append("Total Price: $").append(totalPrice).append("\n");
//	        return invoice.toString();
//	    }
//	    
//	    
//
//	    public static void main(String[] args) {
//	        Scanner scanner = new Scanner(System.in);
//
//	        // Get input from the user
//	        System.out.println("Enter customer ID:");
//	        String customerId = scanner.nextLine();
//
//	        // Create an instance of the EventInvoice class
//	       Invoice eventInvoice = new Invoice();
//
//	        // Calculate the invoice for the customer
//	        String invoice = eventInvoice.calculateTotalPrice(customerId);
//
//	        // Display the invoice details to the user
//	        if (invoice != null) {
//	            System.out.println("\nInvoice Details:");
//	            System.out.println(invoice);
//	        } else {
//	            System.out.println("No invoice found for the provided customer ID.");
//	        }
//
//	        // Close the scanner
//	        scanner.close();
//	    }
//
//
	
	
	
}



