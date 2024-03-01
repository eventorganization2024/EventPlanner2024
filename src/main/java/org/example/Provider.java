package org.example;

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
 
    
}
