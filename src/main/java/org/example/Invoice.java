package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
	static Printing printing = new Printing();
	
/////////////////////////////////////////////////////////////////////////////////////////////////////		 
		
	 public static void deleteInvoice(String eventId) {
	        List<String> invoices = readInvoicesFromFile();

	        // Filter out entries with the specified event ID
	        List<String> updatedInvoices = new ArrayList<>();
	        for (String invoice : invoices) {
	            String[] parts = invoice.split(",");
	            if (!parts[1].equals(eventId)) { // Check if the event ID matches
	                updatedInvoices.add(invoice);
	            }
	        }

	        // Rewrite the filtered entries back to the file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Functions.INVOICE_FILE_NAME))) {
	            for (String updatedInvoice : updatedInvoices) {
	                writer.write(updatedInvoice);
	                writer.newLine();
	            }
	            printing.printSomething("");
	        } catch (IOException e) {
	            printing.printSomething(Functions.ERROR_PREFIX + e.getMessage());
	        }
	    }
/////////////////////////////////////////////////////////////////////////////////////////////////////		 
		
	    public static List<String> readInvoicesFromFile() {
	        List<String> invoices = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(Functions.INVOICE_FILE_NAME))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                invoices.add(line);
	            }
	        } catch (IOException e) {
	            printing.printSomething(Functions.ERROR_PREFIX + e.getMessage());
	        }
	        return invoices;
	    }
//////////////////////////////////////////////////////////////////////////////////////////////////////		 
	
public static void readInvoiceFile(String fileName, String customerId) {
    double totalPrice = 0.0; // Variable to store the total price
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String invoiceCustomerId = parts[0].trim();
                if (invoiceCustomerId.equals(customerId)) {
                    String eventName = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    printing.printSomething("\nEvent Name: " + eventName + "  , Price: " + price);
                    totalPrice += price; // Accumulate the price
                }
            } else {
                printing.printSomething("\nInvalid format in invoice file: " + line);
            }
        }
        printing.printSomething("\nTotal Price: " + totalPrice); // Print the total price
    } catch (IOException e) {
    	 printing.printSomething(Functions. ERROR_PREFIX + e.getMessage());
    }
}
////////////////////////////////////////////////////////////////////////////
public static void addToInvoice(String customerId, String eventId, String eventName, double price) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(Functions.INVOICE_FILE_NAME, true))) {
        writer.write(customerId + "," + eventId + "," + eventName + "," + price);
        writer.newLine();           
    } catch (IOException e) { printing.printSomething( Functions.ERROR_PREFIX + e.getMessage());}
} 
///////////////////////////////////////////////////////////////////////////////////////

}
