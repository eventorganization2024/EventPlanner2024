package event;

public class Admin {
	 private boolean isLoggedIn;
	 public String getEmail() {
	        return "yamamahashayer@gmail.com";
	    }
	    public String getAdminId() {
	        return "111";
	    }
	    public String getAdminPassword() {
	        return "1234";
	    }

	    public String getEmailPassword() {
	        return "YNYN";
	    }
	    
	    public void login() {
	        // Perform authentication logic here
	        // For demonstration purposes, we'll simply set isLoggedIn to true
	      isLoggedIn = true;  
	        System.out.println("Administrator logged in successfully.");
	    }
}
