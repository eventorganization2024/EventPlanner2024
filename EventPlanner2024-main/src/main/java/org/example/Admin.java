package org.example;

public class Admin {
	
	boolean state;
	public void setState(boolean s) {
		this.state =s;
	}
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
    
    public boolean getstate() 
    {
    	return state;
    }
    
    
   // Functions f =new Functions();
	 
	/* 
    public void Admainaddevent( String filename) throws Exception {
       Event e= f.addevent(getAdminId(), filename);    	
    	e.addEventToFile(e, filename );
    	 }
    
    */
}
