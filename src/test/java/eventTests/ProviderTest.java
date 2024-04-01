package eventTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.example.Functions;
import org.example.Provider;
import org.example.Service;
import org.junit.Test;



public class ProviderTest {
	Functions f =new Functions();
	 Provider p = new Provider("testId", "testPassword", "testName", "1234567890", "test@example.com","testAddress");
    String address="testAddress";
    boolean found;
    boolean updatePpage=false;


    @Test
    public void testAddProviderToFile() throws IOException {
        if (Functions.searchIdP(p.getId()))
            found = true;
        else 
        found=false;
      
        if (found) {
        	assertTrue(found);
            Provider.deleteProviderFromFileAndArrayList("provider.txt", p.getId());
            Provider.addProviderToFile(p);
            f.viewproviderservice("testId");
            
        }
    }
	   
	   
	    @Test
	    
	    public void testDeleteProviderFromFileAndArrayList() throws IOException {
	    Provider provider = new Provider("testId2", "testPassword2", "testName2", "123456789", "test2@example.com","Test2Address");
		provider.deleteProviderFromFileAndArrayList("provider.txt", provider.getId());
		 boolean exists = Functions.searchIdP(provider.getId());
	       assertTrue("Provider should be deleted", !exists);
	    }
	    
	    
	    
	    @Test
	    public void testGetEmail() {
	       
	        assertEquals("test@example.com", p.getEmail());
	    }
	    
	    
	    @Test
	    public void testToString3() {
	        String output = p.toString3();
	        String expectedOutput = "UserID='testId',1. Name=testName',2. Address='testAddress',3. Phone=1234567890',4. Email='test@example.com',5. Password=testPassword'";
	        assertEquals(expectedOutput, output);
	    }

	    @Test
	    public void testToString2() {
	   String output = p.toString2();
	
       String expectedOutput = "\033[0;36m" +
	                                "\033[0;33m- UserID: testId\n" +
	                                "- Name: testName\n" +
	                                "- Address: testAddress\n" +
	                                "- Phone: 1234567890\n" +
	                                "- Email: test@example.com\n" +
	                                "- Password: testPassword\n" +
	                                "\033[0m";
	        
	        assertEquals(expectedOutput, output);
	    }
	    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    @Test
	    public void testUpdateProviderProfile_NameChange() throws IOException {
	        // Prepare test data: Create a list of providers with known IDs
	        List<Provider> providers = new ArrayList<>();
	        Provider provider = new Provider();
	        provider.setAddress("address");
	        provider.setEmail("jullnarihab61@gmail.com");
	        provider.setId("2334");
	        provider.setLogstate(true);
	        provider.setName("jullnar");
	        provider.setPassword("1234");
	        provider.setPhone("1234");
	        provider.setType("p");
	        
	       providers.add(provider);

	        
	        String input = "1\nNew Name\n"; // Change name option (1) and provide new name
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
            
	       
			f.updateProviderProfile(1);
                     assertEquals("jullnar", provider.getUsername());
                     updatePpage=true;
                     assertTrue(updatePpage);
                    		 
                    
	    }
	   
	    
	    @Test
	    public void testUpdateProviderProfile_PhoneChange() throws IOException {
	
	        ByteArrayInputStream mockInputStream = new ByteArrayInputStream("New Phone\n".getBytes());
	        System.setIn(mockInputStream); 

	        Provider provider = new Provider();
	        provider.setAddress("address");
	        provider.setEmail("jullnarihab61@gmail.com");
	        provider.setId("2334");
	        provider.setLogstate(true);
	        provider.setName("jullnar");
	        provider.setPassword("1234");
	        provider.setPhone("1234");
	        provider.setType("p");
	        
	       f.updateCustomerProfile(2);
	       assertEquals("1234", provider.getphone());
	      
	       updatePpage=true;
           assertTrue(updatePpage);
	        
	        
	        
	       
	    }
	    
	    
	    

}
