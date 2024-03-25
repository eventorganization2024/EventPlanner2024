
package org.example;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class Event {
    private String userId;
    private String name;
    private Date date;
    private String time;
    private String description;
    private String attendeeCount;
    private String theme;
    private String category;
    private String eventId;
    private String venueName;
    private List<String> serviceIds = new ArrayList<>();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String ERROR_PREFIX = "An error occurred: ";


	static Event eventToUpdated ;
    static Printing printing = new Printing();
    static Functions func =new Functions();
   
   
   
    public  Event() {}
    
    public Event(String eventName, Date eventDate, String eventTime, String eventDescription, String eventAttendeeCount, String userId, String eventTheme, String eventCategory, String venueName, String eventId) {
        this.userId = userId; 
        this.name = eventName;
        this.date = eventDate;
        this.time = eventTime;
        this.description = eventDescription;
        this.attendeeCount = eventAttendeeCount;
        this.category = eventCategory;
        this.theme = eventTheme;
        this.eventId = eventId;
        this.venueName = venueName;
    }

    public Event(String eventName, Date eventDate, String eventTime, String eventDescription, String eventAttendeeCount, String userId, String eventTheme, String eventCategory, String venueName, List<String> serviceIds, String eventId) {
        this(eventName, eventDate, eventTime, eventDescription, eventAttendeeCount, userId, eventTheme, eventCategory, venueName, eventId);
        this.serviceIds.addAll(serviceIds);
    }

    
   
    

   
   public List<String> getServiceIds() {
       return serviceIds;
   }

   public void setServiceIds(List<String> serviceIds) {
       this.serviceIds = serviceIds;
   }
   
 /////////////////////////////////////////////////////////////////////////////////   
   public static Event getEventFromLine(String line) {
	    Event event = new Event();
	    String[] items = line.split(" , ");
	    if (items.length >= 11) {
	        String name = items[0];
	        Date date;
	        try {
	            date = DATE_FORMAT.parse(items[1]);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return null;
	        }
	        String time = items[2];
	        String description = items[3];
	        String attendeeCount = items[4];
	        String userId = items[5];
	        String category = items[6];
	        String theme = items[7];
	        String venueName = items[8];
	        String eventId = items[9];
	        String serviceIdsString = items[10];
	        // Remove leading and trailing brackets if present
	        serviceIdsString = serviceIdsString.replaceAll("^\\[|\\]$", "");
	        String[] serviceIdsArray = serviceIdsString.split("\\s*,\\s*");
	        event.setName(name);
	        event.setDate(date);
	        event.setTime(time);
	        event.setDescription(description);
	        event.setAttendeeCount(attendeeCount);
	        event.setUserId(userId);
	        event.setCategory(category);
	        event.setTheme(theme);
	        event.setVenuename(venueName);
	        event.setEID(eventId);
	        for (String serviceId : serviceIdsArray) {
	            event.addServiceId(serviceId);
	        }
	    }
	    return event;
	}

   public void addServiceId(String serviceId) {
       this.serviceIds.add(serviceId);
   }

   
    
    
   public void addEventToFile(Event event, String filename) {
	    try (FileWriter eventFile = new FileWriter(filename, true)) { // Open the file in append mode
	        eventFile.write(event.getName() + " , " +
	                        DATE_FORMAT.format(event.getDate()) + " , " +
	                        event.getTime() + " , " +
	                        event.getDescription() + " , " +
	                        event.getAttendeeCount() + " , " +
	                        event.getUsrTd() + " , " +
	                        event.getCategory() + " , " +
	                        event.getTheme() + " , " +
	                        event.getVenuename() + " , " +
	                        event.getEID() + " , ");

	        List<String> otherServiceIds = event.getServiceIds();
	        StringBuilder formattedServiceIds = new StringBuilder("[");
	        for (int i = 0; i <  otherServiceIds.size(); i++) {
	            formattedServiceIds.append( otherServiceIds.get(i));
	            if (i <  otherServiceIds.size() - 1) {
	                formattedServiceIds.append(", ");
	            }
	        }
	        formattedServiceIds.append("]\n");
	        eventFile.write(formattedServiceIds.toString());
	    } catch (IOException e) {
	        printing.printSomething( ERROR_PREFIX + e.getMessage());
	    }
	}



    public static Event findeventID(String eventId2, String filename) throws IOException {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
           
            while ((line = reader.readLine()) != null) {
                Event e = getEventFromLine(line);
                if (e != null) {
                    String eventid = e.getEID();
                    if (eventid != null && eventid.equals(eventId2)) {
                        return e;
                    }
                }
                
               
            }
        }
		return null;

       
    }

    

    
    public static Event findevent(String searchCriteria, String searchType, String filename) {
        String line;
     
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
           
            while ((line = reader.readLine()) != null) {
               {
                    Event e = getEventFromLine(line);
                    
                    if (e != null) { // Check if "e" is not null
                        if ("Name".equalsIgnoreCase(searchType) && searchCriteria.equals(e.getName())) {
                            return e;
                        } else if ("Date".equalsIgnoreCase(searchType) && searchCriteria.equals(e.getDate())) {
                            return e;
                        }
                    }
                }
            }
        } catch (IOException e) {
            printing.printSomething( ERROR_PREFIX  + e.getMessage());
        }
        return null;
    }


   
    
   
	 	
     public void delete_event_from_file_and_arraylist(Event e, String eventFileName, String eventId2) throws IOException {
        // Delete event from the event file
       
    	 String venueBookFileName = "venuebook.txt";
    	StringBuilder eventFileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(eventFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(" , ");
                if (items.length >= 11) {
                    String eventid = items[9];
                    if (!eventid.equals(eventId2)) {
                        eventFileContent.append(line).append("\n");
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(eventFileName, false)) {
            fos.write(eventFileContent.toString().getBytes());
        }

        StringBuilder venueBookFileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",");
                if (items.length >= 5) {
                    String eventID = items[4].trim(); // Trim to remove any leading/trailing spaces
                    if (!eventID.equals(eventId2)) {
                        venueBookFileContent.append(line).append("\n");
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(venueBookFileName, false)) {
            fos.write(venueBookFileContent.toString().getBytes());
        }
    }
       
 
   
	     
    	
     public Event updateEvent(String id, String filename) throws IOException  {
    	     eventToUpdated = findeventID(id, filename);

    	    if (eventToUpdated != null && eventToUpdated.getEID() != null) {
    	        Scanner scanner = new Scanner(System.in);

    	        eventToUpdated.printing.printSomething("\033[0;35m" + // Set text color to magenta
    	        	    '\n' +
    	        	    "UserID: " + eventToUpdated.userId + '\n' +
    	        	    "Event ID: " + eventToUpdated.eventId + '\n' +
    	        	    "1. Name: " + eventToUpdated.name + '\n' +
    	        	    "2. Date: " + DATE_FORMAT.format(eventToUpdated.date) + '\n' +
    	        	    "3. Time: " + eventToUpdated.time + '\n' +
    	        	    "4. Description: " + eventToUpdated.description + '\n' +
    	        	    "5. Attendee Count: " + eventToUpdated.attendeeCount + '\n' +
    	        	    "6. Theme: " + eventToUpdated.theme + '\n' +
    	        	    "7. Category: " + eventToUpdated.category + '\n' +
    	        	    "8. Venue Name: " + eventToUpdated.venueName + '\n' +
    	        	    "9. Service IDs: " + eventToUpdated.getServiceIds() + '\n'
    	        	    + "\033[0m" // Reset text color to default
    	        	    );

    	        printing.printSomething ("\nEnter the number of the field you want to update: ");
    	        String choice = scanner.next();

    	        switch (choice) {
    	            case "1":
    	                printing.printSomething("Enter new event name:");
    	                eventToUpdated.setName(scanner.next());
    	                break;
    	            case "2":
    	                printing.printSomething("Enter new event date (yyyy-MM-dd):");
    	                String dateInput = scanner.next();
    	                Date date;
    	                try {
    	                    date = DATE_FORMAT.parse(dateInput);
    	                } catch (ParseException e) {
    	                    e.printStackTrace();
    	                    return null;
    	                }
    	                eventToUpdated.setDate(date);
    	                eventToUpdated.updateVenueInVenueBook(id, date, "venuebook.txt");
    	                break;
    	            case "3":
    	                printing.printSomething("Enter new event time:");
    	                eventToUpdated.setTime(scanner.next());
    	                break;
    	            case "4":
    	                printing.printSomething("Enter new event description:");
    	                eventToUpdated.setDescription(scanner.next());
    	                break;
    	            case "5":
    	                printing.printSomething("Enter new event attendee count:");
    	                eventToUpdated.setAttendeeCount(scanner.next());
    	                break;
    	            case "6":
    	                printing.printSomething("Enter new event theme :");
    	                eventToUpdated.setTheme(scanner.next());
    	                break;
    	            case "7":
    	                printing.printSomething("Enter new event category:");
    	                eventToUpdated.setCategory(scanner.next());
    	                break;
    	            case "8":
    	            	func.viewAllVenuesCustomer("venue.txt");
    	                printing.printSomething("\nEnter new event venue name:");
    	             //   String newVenueName = scanner.next();
    	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	                String eventDateString = dateFormat.format(eventToUpdated.getDate());
    	               boolean venueAvailable=false;
    	                
    	                do {
    	                    // Check venue availability
    	                    printing.printSomething("Enter Venue name:");
    	                    venueName = scanner.next();
    	                    if (func.checkAvailability(venueName,eventDateString )) {
    	                        if (Integer.parseInt(eventToUpdated.getAttendeeCount()) <= func.getVenueCapacity(venueName)) {
    	                            venueAvailable = true;
    	                            eventToUpdated.setVenuename(venueName);
    	                        } else {
    	                            printing.printSomething("The attendee count exceeds the capacity of the venue. Please choose another venue.\n");
    	                        }
    	                    } else {
    	                 	  // printing.printSomething("\n choose another venue \n");
    	                      }
    	                } while (!venueAvailable );

    	                
    	                eventToUpdated.setVenuename(venueName);
    	                eventToUpdated.updateVenueInVenueBook(id, venueName, "venuebook.txt");
    	                break;
    	            case "9":
    	            	func.updateServiceList();
    	            	func.viewallservice("service.txt");
    	                printing.printSomething("\nEnter new service IDs (comma-separated):\n");
    	                String serviceIdsInput = scanner.next();
    	                List<String> serviceIds = Arrays.asList(serviceIdsInput.split("\\s*,\\s*"));
    	                eventToUpdated.setServiceIds(serviceIds);
    	                break;
    	            default:
    	            	printing.printSomething("Invalid choice.");
    	        }

    	        // Read all events from the file
    	        List<Event> events = new ArrayList<>();
    	        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    	            String line;
    	            while ((line = reader.readLine()) != null) {
    	                Event event = Event.getEventFromLine(line);
    	                if (event != null) {
    	                	if (event.getEID() != null && event.getEID().equals(eventToUpdated.getEID())) {
    	                		  event.setDate(eventToUpdated.getDate());
    	                        // Replace the old event with the updated one
    	                        events.add(eventToUpdated);
    	                    } else {
    	                        events.add(event);
    	                    }
    	                }
    	            }
    	        }

    	        // Write all events back to the file
    	        try (FileWriter writer = new FileWriter(filename, false)) {
    	            for (Event event : events) {
    	            	addEventToFile(event,"event.txt");
    	            }
    	        } catch (IOException e) {
    	            printing.printSomething( ERROR_PREFIX  + e.getMessage());
    	        }
    	    }
    	    else {
    	    	printing.printSomething("Event not found or event ID is null.");
    	    }

    	    return eventToUpdated;
    	}

		
	 public void updateVenueInVenueBook(String eventId, Date newDate, String venueBookFileName) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length >= 5 && parts[4].equals(eventId)) {
	                	 Date date;
	                     try {
	                         date = DATE_FORMAT.parse(parts[2]);
	                     } catch (ParseException e) {

	                         e.printStackTrace();
	                        
	                     }
	                     date=newDate;
	                    line = String.join(",", parts);
	                }
	                sb.append(line).append("\n");
	            }
	            // Write the updated content back to the file
	            try (FileWriter writer = new FileWriter(venueBookFileName)) {
	                writer.write(sb.toString());
	            }
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle or log the exception as needed
	        }
	    }
	    
	    
	 public void updateVenueInVenueBook(String eventId, String newV, String venueBookFileName) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length >= 5 && parts[4].equals(eventId)) {
	                	 Date date=new Date();
	                     try {
	                         date = DATE_FORMAT.parse(parts[2]);
	                     } catch (ParseException e) {

	                         e.printStackTrace();
	                        
	                     }
	                     newV= DATE_FORMAT.format(date) ;
	                    line = String.join(",", parts);
	                }
	                sb.append(line).append("\n");
	            }
	            // Write the updated content back to the file
	            try (FileWriter writer = new FileWriter(venueBookFileName)) {
	                writer.write(sb.toString());
	            }
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle or log the exception as needed
	        }
	    }
	    
	    
	 
	 
	 
	 
	 
	 
	 
	    public static String findVenueIdByName(String venueName, String filename) {
	        String id = null;
	        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length >= 5 && parts[1].trim().equals(venueName.trim())) {
	                    id = parts[0].trim(); // Found the venue name, return its ID
	                    break; // No need to continue searching
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return id;
	    }
	    
	 
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String  getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount(String attendeeCount) {
        this.attendeeCount = attendeeCount;
    }
    
    public String getUsrTd() {
        return userId;
    }

    public void setUserId(String UserId) {
        this.userId = UserId;
    }
           
    public String getEID() {
        return eventId;
    }

    public void setEID(String ID) {
        this.eventId = ID;
    }
    
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVenuename(String n) {this.venueName=n; }
    public String getVenuename(){return venueName;}

    

    public String toString2() {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[0;36m"); // Set text color to cyan
        sb.append("Event Details:\n");
        sb.append("\033[0;33m"); // Set text color to yellow for attribute names
        sb.append("- UserID: ").append(userId).append("\n");
        sb.append("- Name: ").append(name).append("\n");
        sb.append("- Date: ").append(DATE_FORMAT.format(date)).append("\n");
        sb.append("- Time: ").append(time).append("\n");
        sb.append("- Description: ").append(description).append("\n");
        sb.append("- Attendee Count: ").append(attendeeCount).append("\n");
        sb.append("- Theme: ").append(theme).append("\n");
        sb.append("- Venue name: ").append(venueName).append("\n");
        sb.append("- Category: ").append(category).append("\n");
        sb.append("- Event ID: ").append(eventId).append("\n");
       sb.append("- Service IDs: ").append(String.join(", ", serviceIds)).append("\n"); // Moved service IDs to the end

        
       func. updateServiceList();
       for (String serviceId : serviceIds) {
	        serviceId = serviceId.replaceAll("\\[|\\]", "");
	       
	        for (ServiceDetails service : func.serviceDetails) {
	        	if (serviceId.equalsIgnoreCase("[No service]")) {
	        		sb.append("No service");
	        	    break;
	        	}
	        	
	            if (service.getServiceID().equals(serviceId)) {
	            	sb.append("- Service Names: ").append(service.getServiceName()).append(".");
	                break;
	            }
	        }
	    }
        sb.append("\033[0m"); // Reset text color
        return sb.toString();
    }

   

    public String toString() {
    	StringBuilder sb = new StringBuilder();
        sb.append("\033[0;35m"); // Set text color to magenta
        sb.append("Name: ").append(name).append(", ")
          .append("Date: ").append(DATE_FORMAT.format(date)).append(", ")
          .append("User ID: ").append(userId).append(", ")
          .append("Event ID: ").append(eventId).append("."); // Moved event ID to the end
        sb.append("\033[0m"); // Reset text color

        return sb.toString();
    }

    public LocalDate getDateAsLocalDate() {
        // Assuming 'date' is your java.util.Date object
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault(); // Or specify the desired time zone
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }
    
    
    public String getDateTime() {
        return date + " " + time;
    }
    
   
}