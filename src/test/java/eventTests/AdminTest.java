package eventTests;

import org.example.Admin;
import org.example.Functions;
import org.example.Printing;
import org.junit.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AdminTest {
	Functions functions;
    Printing printing;
    Admin admin = new Admin();
  
    @Before
    public void setUp() {

        functions = new Functions();
     
    }
    @Given("admin log in with true {string} and {string}")
    public void adminLogInWithTrueAnd(String a0, String a1) throws Exception {

        if(a0.equals(admin.getAdminId()) && a1.equalsIgnoreCase(admin.getAdminPassword())){
            admin.setState(true);
            Functions.signInAdmin(a0);
           
        }
    }
    @Test
    public void testGetEmail() {
       
        assertEquals("royasmine05@gmail.com", admin.getEmail());
    }

    @Test
    public void testGetAdminId() {
        Admin admin = new Admin();
        assertEquals("111", admin.getAdminId());
    }

    @Test
    public void testGetAdminPassword() {
        Admin admin = new Admin();
        assertEquals("1234", admin.getAdminPassword());
    }

    @Test
    public void testGetEmailPassword() {
        Admin admin = new Admin();
        assertEquals("igun bclo kbti fzno", admin.getEmailPassword());
    }
    
    @Test
    public void testGetStateDefault() {
        Admin admin = new Admin();
        assertFalse(admin.getstate());
    }
    
    @Test
    public void testSetState() {
        Admin admin = new Admin();
        admin.setState(true);
        assertTrue(admin.getstate());
    }
    @Test
    public void testViewBusinessReports() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {

   
        Functions.updateCustomersList();
        Functions.updateEventList("event.txt");
        Method method = Functions.class.getDeclaredMethod("viewBusinessReports");
        method.setAccessible(true);
        method.invoke(functions);
      
        
        
    }
}
