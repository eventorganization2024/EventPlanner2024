package event;
import java.util.ArrayList;
import java.util.List;

import event.Customer;
//import event.Event;
import event.User;
public class Customer extends User {
    private String email;
    private List<Event> events = new ArrayList<>();
   
   
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
    
 
    
    public List<Event> getEvents() { return this.events; }
    
    
    
    public static Customer getCustomerFromLine(String line){
        Customer customer1 = new Customer();
        String[] items = line.split(" , ");
        customer1.setId(items[0]);
        customer1.setName(items[1]);
        customer1.setPhone(items[2]);
        customer1.setAddress(items[3]);
        customer1.setEmail(items[4]);
        customer1.setPassword(items[5]);
        return  customer1;
    }


}

