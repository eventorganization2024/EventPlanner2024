package eventTests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.example.Functions;
import org.example.Provider;
import org.example.Service;
import org.junit.Test;



public class ProviderTest {
	Functions f =new Functions();
	 Provider p = new Provider("testId", "testPassword", "testName", "1234567890", "test@example.com","testAddress");
    String address="testAddress";
    boolean found;
	


	   @Test
	    public void testAddProviderToFile() throws IOException {
		   if (Functions.searchIdP(p.getId())) found=true;else found=false;                            
		    if (found)
		    {	
		    	  Provider.deleteProviderFromFileAndArrayList("provider.txt", p.getId());
		          Provider.addProviderToFile(p); 
		          f.viewproviderservice("testId");
		    } 
		    
	    }

	    @Test
	    public void testDeleteProviderFromFileAndArrayList() throws IOException {
	    Provider provider = new Provider("testId2", "testPassword2", "testName2", "123456789", "test2@example.com","Test2Address");
		provider.deleteProviderFromFileAndArrayList("provider.txt", provider.getId());

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
	    
	    
	    

}
