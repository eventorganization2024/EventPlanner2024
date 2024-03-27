package org.example;

public class Venue {
	private String venueId;
	 private String name;
	    private String address;
	    private int capacity;
	    private double price;
	    private String availability;
	    private String imagepath;
	    private String date;

	   
	    public Venue(String name, String address, int capacity, double price,String availability,String id,String Image) {
	        this.name = name;
	        this.address = address;
	        this.capacity = capacity;
	        this.price = price;
	        this.availability=availability;
	        this.venueId=id;
	        this.imagepath=Image;
	    }

	    public Venue() {
			
		}

		public Venue(String venueId2, String name2, String address2, int capacity2, double price2,String image) {
			this.address=address2;
			this.capacity=capacity2;
			this.name=name2;
			this.price=price2;
			this.venueId=venueId2;
			this.imagepath=image;
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
			this.availability=availability;
		}

		public void setdate(String d) {
			this.date=d;
		}
		
		public String getdate() {
			return date;
		}

	    public String getId() {return venueId;}
	    public void setId(String id) {this.venueId=id;}
	    
	    public String getAvailavility() { return availability;}
	    public void setAvailability(String availability) {this.availability=availability;}

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
