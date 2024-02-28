
package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.example.*;

public class Customer extends User {
    private String email;
    public  int numofevents;
    private List<Event> events = new ArrayList<>();
    static Printing printing = new Printing();
	  
   
    public Customer() {}
    public Customer(String id, String username, String phone, String address,String pass, String email,int numofE) {
        super(username, pass, "Customer");
        this.address = address;
        this.phone=phone;
        this.id=id;
        this.email=email;
        this.numofevents=numofE;
        
       
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;} 
    public void setNumOfEvents(int numofevents) {this.numofevents = numofevents;}
    public int getNumOfEvents() { return numofevents;}
 
    
    public List<Event> getEvents() { return this.events; }
    
    
    
    
    
    
  public  void addCustomerToFile(Customer customer,String filename) throws Exception{
	  try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
	        long fileLength = file.length();
	        file.seek(fileLength); // Position the file pointer at the end of the file

	        file.writeBytes(
	                customer.getId() + " , " +
	                customer.getUsername() + " , " +
	                customer.getphone() + " , " +
	                customer.getaddress() + " , " +
	                customer.getEmail() + " , " +
	                customer.getPassword() + "\n"
	        );

	        printing.printSomething("added");
	    } catch (IOException e) {
	        printing.printSomething("An error occurred: " + e.getMessage());
	    }}
    
    
    
    
    
    
    
    
    
    
    
    
    public static Customer getCustomerFromLine(String line){
        Customer customer1 = new Customer();
        String[] items = line.split(" , ");
        if (items.length >= 6) {
            customer1.setId(items[0]);
            customer1.setName(items[1]);
            customer1.setPhone(items[2]);
            customer1.setAddress(items[3]);
            customer1.setEmail(items[4]);
            customer1.setPassword(items[5]);
        } else {
        
            System.err.println("Invalid line format: " + line);
 
        }
        return customer1;
    }



}
