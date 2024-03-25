package org.example;

public class Venue {
	private String venueId;
	 private String name;
	    private String address;
	    private int capacity;
	    private double price;
	    private String Availability;
	    private String imagepath;
	    private String date;

	    // Constructor
	    public Venue(String name, String address, int capacity, double price,String Availability,String Id,String Image) {
	        this.name = name;
	        this.address = address;
	        this.capacity = capacity;
	        this.price = price;
	        this.Availability=Availability;
	        this.venueId=Id;
	        this.imagepath=Image;
	    }

	    public Venue() {
			// TODO Auto-generated constructor stub
		}

		public Venue(String venueId2, String name2, String address2, int capacity2, double price2,String Image) {
			// TODO Auto-generated constructor stub
			this.address=address2;
			this.capacity=capacity2;
			this.name=name2;
			this.price=price2;
			this.venueId=venueId2;
			this.imagepath=Image;
		}
		
		
		public Venue(String name , String address , int capacity , 
				String imagepath, double price , String availability,String date ) 
		{
			this.name= name ;
			this.address=address;
			this.capacity=capacity;
			this.imagepath=imagepath;
			this.price=price;
			this.date=date;
			this.Availability=availability;
		}

		public void setdate(String d) {
			this.date=d;
		}
		
		public String getdate() {
			return date;
		}

		// Getters and Setters
	    public String getId() {return venueId;}
	    public void setId(String id) {this.venueId=id;}
	    
	    public String getAvailavility() { return Availability;}
	    public void setAvailability(String A) {this.Availability=A;}

	    public String getName() {
	        return name;
	    }
	    public String getImage() {
	    	return imagepath;
	    
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    public void setImage(String i)
	    {this.imagepath=i;}

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public int getCapacity() {
	        return capacity;
	    }

	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }
	    
	    public String toFileString() {
	        return venueId + "," + name + "," + address + "," +imagepath+"," + capacity + "," + price;
	    }
}

