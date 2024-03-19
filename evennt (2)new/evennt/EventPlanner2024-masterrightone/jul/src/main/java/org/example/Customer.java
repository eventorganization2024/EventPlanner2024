
package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.example.*;



public class Customer extends User {
    private String email;
    
   public List<Event> Cevents = new ArrayList<>();
   
   
    static Printing printing = new Printing();
	Functions f =new Functions();
	 
	
  
    
    public List<Event> getEvents() { return this.Cevents; }
    public void addEvent(Event event) { this.Cevents.add(event); }
    
    
	
    
        
   
    public Customer() {}
    public Customer(String id, String username, String phone, String address,String pass, String email) {
        super(username, pass, "Customer");
        this.address = address;
        this.phone=phone;
        this.id=id;
        this.email=email;
        
       
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;} 
   
    
   
    
   
   public void addCustomerToFile(Customer customer) {
        try {
            FileWriter customersFile = new FileWriter("customer.txt", true);
            customersFile.append(customer.getId()).append(" , ")
                    .append(customer.getUsername()).append(" , ")
                    .append(customer.getphone()).append(" , ")
                    .append(customer.getaddress()).append(" , ")
                    .append(customer.getEmail()).append(" , ")
                    .append(customer.getPassword())
                    .append("\n");

            customersFile.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    
    
 
    
    
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
    
    
  
  
  
  public String toString() {
	    return "Customer{" +
	            "id='" + getId() + '\'' +
	            ", username='" + getUsername() + '\'' +
	            ", phone='" + getphone() + '\'' +
	            ", address='" + getaddress() + '\'' +
	            ", email='" + getEmail() + '\'' +
	            ", password='" + getPassword() + '\'' +
	           '}';
	}
  
  
  
  
  
  /*  
    public static Customer getCustomerFromLine(String line){
        Customer customer1 = new Customer();
        String inputString1;
        int num=0;
        String[] items = line.split(",");
        if (items.length >= 7) {
            customer1.setId(items[0]);
            customer1.setName(items[1]);
            customer1.setPhone(items[2]);
            customer1.setAddress(items[3]);
            customer1.setEmail(items[4]);
            customer1.setPassword(items[5]);
                                  
            try {
           	 
                inputString1 = items[6];
                num = Integer.parseInt(inputString1);
                customer1.setNumOfEvents(num);
                   
            } catch (NumberFormatException EE) {
                System.err.println("Invalid input: " + EE.getMessage());
            }
      
            
        } else {
        
            System.err.println("Invalid line format: " + line);
 
        }
        return customer1;
    }

*/

}
