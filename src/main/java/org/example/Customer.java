
package org.example;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;





public class Customer extends User {
    private String email;
    private static final Logger logger = Logger.getLogger(Customer.class.getName());
    static Scanner scanner = new Scanner(System.in);
   public static final List<Event> Cevents = new ArrayList<>();
   static boolean found;
   
    static Printing printing = new Printing();
	Functions f =new Functions();
	 
	
   public static List<Event> getCevents() {
        return Cevents;
    }
    
public static List<Event> getEvents() {
    return new ArrayList<>(Cevents);
}

    public static void addEvent(Event event) { Cevents.add(event); }
	
            
   
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
   
    
   
    
   
   public static void addCustomerToFile(Customer customer) {
    try (FileWriter customersFile = new FileWriter("customer.txt", true)) {
        customersFile.append(customer.getId()).append(" , ")
                .append(customer.getUsername()).append(" , ")
                .append(customer.getphone()).append(" , ")
                .append(customer.getaddress()).append(" , ")
                .append(customer.getEmail()).append(" , ")
                .append(customer.getPassword())
                .append("\n");
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
      
logger.warning(() -> "Invalid line format: " + line);


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
  
  ////////////////////////////////////////////////////////////////////////////
  public static boolean searchIdU(String id) {

		 try (BufferedReader br = new BufferedReader(new FileReader(Functions.CUSTOMER_FILE_NAME))) {

		        String line;
		        while ((line = br.readLine()) != null) {
		            String[] items = line.split(" , ");
		            if (items.length >= 6) {
		                String cId =items[0].trim();
		                if (cId .equals(id)) {
		                    return true; 
		               }
		            }
		        }
		    } catch (IOException e) {
		        printing.printSomething(Functions.ERROR + e.getMessage());
		    } catch (NumberFormatException e) {

		        printing.printSomething(Functions.INVALID_INPUT + e.getMessage());

		    }
		    return false; // Return false if the ID is not found in any line
	}

  ///////////////////////////////////////////////////////////////////////////
  static void customerSignUp() throws Exception {
	    Customer customerObj = new Customer();
	    printing.printSomething("Enter your Id: ");
	    String IDIn;
	    IDIn= scanner.next();
	    found = searchIdU(IDIn);
	    if (found) {
	        printing.printSomething(Functions. ACCOUNT_ALREADY_EXIST_MESSAGE);
	      Main.signInFunction();
	    } else {
	    	customerObj.setId(IDIn);
	        printing.printSomething("Enter your Name: ");
	        customerObj.setName(scanner.next());
	        printing.printSomething("Enter your Phone: ");
	        customerObj.setPhone(scanner.next());
	        printing.printSomething("Enter your Address: ");
	        customerObj.setAddress(scanner.next());
	        printing.printSomething("Enter your Email: ");
	        customerObj.setEmail(scanner.next());
	        printing.printSomething(Functions.THANK_MESSAGE);
	        customerObj.setPassword(scanner.next());
	        printing.printSomething("\nRegistration done successfully\n");
	        Functions. customers.add(customerObj);
	        Customer .addCustomerToFile(customerObj);
	    }
	}
 ///////////////////////////////////////////////////////////////////////////
  
  /*public static void deleteCustomerProfile(String val) throws IOException {
	    if (val.equalsIgnoreCase("yes")) {
	        File inputFile = new File(Functions.CUSTOMER_FILE_NAME);
	        File tempFile = new File("temp.txt");
	        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
	            String lineToRemove = id;
	            String currentLine;
	            while ((currentLine = reader.readLine()) != null) {
	               if (currentLine.contains(lineToRemove)) {
	                    continue;
	                }
	                writer.write(currentLine + System.getProperty("line.separator"));
	            }
	        }
	        if (!inputFile.delete()) {
	            printing.printSomething("Could not delete the original file.");
	            return;
	        }
	        if (!tempFile.renameTo(inputFile)) {
	            printing.printSomething("Could not rename the temporary file.");
	        }
	        printing.printSomething("\nAccount Successfully Deleted\n\n");
	    }
	}
	*/
	public static boolean deleteCustomer(String id) {
	    if (id == null) {
	        return false; // Return false if id is null
	    }
	    String trimmedId = id.trim();
	    for (int i = 0; i < Functions.customers.size(); i++) {
	        Customer customer = Functions.customers.get(i);
	        if (customer != null) {
	            String customerId = customer.getId();
	            if (customerId != null && customerId.equals(trimmedId)) {
	            	Functions.customers.remove(i);
	                try {
	                	Functions.deleteLineByValue(Functions.CUSTOMER_FILE_NAME, id); 
	                } catch (IOException e) {
	                    printing.printSomething("An error occurred while deleting the customer from the file: " + e.getMessage());
	                }
	                return true;
	            }
	        }
	    }
	    return false; 
	}

////////////////////////////////////////////
	 public static void viewCustomersanddelete() {
   	  Scanner scannerD = new Scanner(System.in); 
         
         
   	Functions.updateCustomersList();
         printing.printSomething("List of Customers: \n");
         for (Customer customer1 : Functions.customers) {            
        	 Functions.tmp = customer1.getId() + "\t  "+customer1.getUsername() + "  "+customer1.getaddress() + "  "+customer1.getphone() + "  "+customer1.getEmail()  + "  \n";
             printing.printSomething(Functions.tmp);
         }
         printing.printSomething("Please enter the ID of the customer you want to delete:");
         String customerIdToDelete = scannerD.next();

         if (Customer. deleteCustomer(customerIdToDelete)) {
             printing.printSomething("Customer deleted successfully.");
         
             Functions. updateCustomerFile();
         } else {
             printing.printSomething("Customer not found.");
         }
     }
 
///////////////////////////////////////////////
	 
     public static void viewCustomer() {
    	 Functions. updateCustomersList();
         printing.printSomething("List of Customers: \n");
         for (Customer customer1 : Functions.customers) {            
        	 Functions.tmp = customer1.getId() + "\t  "+customer1.getUsername() + "  "+customer1.getaddress() + "  "+customer1.getphone() + "  "+customer1.getEmail()  + "  \n";
             printing.printSomething(Functions.tmp);
         }}
     
//////////////////////////////////////////////////////////////////////////////////////////

public static  void updateCustomerProfile(int n) throws IOException {
     Scanner sc =new Scanner(System.in);
	String tmp1;
    for (Customer customer1 :  Functions.customers) {
        if (customer1.getId().equals( Functions.customerId)) {
           
            switch (n) {
                case 1:
                    printing.printSomething("Enter New Name: ");
                    tmp1 = sc.nextLine(); 
                    customer1.setName(tmp1);
                    Functions. updateCustomerFile();
                   break;
                case 2:
                    printing.printSomething("Enter New Phone: ");
                    tmp1 = sc.nextLine();
                  
                    customer1.setPhone(tmp1);
                    Functions. updateCustomerFile();
                    break;
                case 3:
                    printing.printSomething("Enter New Address: ");
                    tmp1 = sc.nextLine(); 
                    customer1.setAddress(tmp1);
                    Functions.updateCustomerFile();
                    break;
               
              default:
                    printing.printSomething( Functions.INVALID_CHOICE);
            }
        }  
    }
}
}
