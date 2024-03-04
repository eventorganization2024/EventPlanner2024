
package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Event {
	private String UserID;
    private String name;
    private String date;
    private String time;
    private String description;
    private String attendeeCount;
    private String theme;
    private  String category;
	public boolean creat=false;
	public boolean cancel;
	public String EVENTID;
	private String Venuenamee;
	
   static Printing printing = new Printing();
   Functions f =new Functions();
    
    public  Event() {}
    
//    public Event(String name, String date, String time,  String description, String attendeeCount, String UserID, String theme ,String category,String Eid) {
//        this .UserID=UserID; 
//        this.name = name;
//        this.date = date;
//        this.time = time;
//        this.description = description;
//        this.attendeeCount = attendeeCount;
//        this.category=category;
//        this.theme= theme;
//        this .EVENTID=Eid;
//    }

    public Event(String name, String date, String time,  String description, String attendeeCount, String UserID, String theme ,String category,String Venuename,String Eid) {
        this .UserID=UserID; 
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.attendeeCount = attendeeCount;
        this.category=category;
        this.theme= theme;
        this .EVENTID=Eid;
        this.Venuenamee=Venuename;
    }
    
    public static Event getEventFromLine(String line) {
        Event event = new Event();
       
        
        String[] items = line.split(",");
        if (items.length >= 10) {
        	 String name = items[0];
             String date = items[1];
             String time = items[2];
             String description = items[3];
             String attendeeCount=items[4];
             String UID = items[5];
             String theme = items[7];
             String category = items[6];
             String EID = items[9];
             String Venuename=items[8];
  
             event.setName(name);
             event.setDate(date);
             event.setTime(time);
             event.setDescription(description);
             event.setUID(UID);
             event.setTheme(theme);
             event.setCategory(category);
             event.setEID(EID);
             event.setAttendeeCount(attendeeCount);   
             
             
        }
        return event;
    }
    
    
    public void addEventToFile(Event event, String filename) {
        try (FileWriter eventFile = new FileWriter(filename, true)) {
            eventFile.append(event.getName()).append(" , ")
                    .append(event.getDate()).append(" , ")
                    .append(event.getTime()).append(" , ")
                    .append(event.getDescription()).append(" , ")
                    .append(event.getAttendeeCount()).append(" , ")
                    .append(event.getUID()).append(" , ")
                    .append(event.getCategory()).append(" , ")
                    .append(event.getTheme()).append(" , ")
                    .append(event.getVenuename()).append(" , ")
                    .append(event.getEID()).append("\n");
            printing.printSomething("Event added successfully.");
        } catch (IOException e) {
            printing.printSomething("An error occurred while adding the event: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
    /*
    
    
    public  void addEventToFile(Event event,String filename) throws Exception
    {
    	
    	
    	 RandomAccessFile file = new RandomAccessFile(filename, "rw");
         long fileLength = file.length();
         
         if (fileLength == 0) {
             // If the file is empty, no need to seek, write directly
             file.writeBytes(
                     event.getName() + " , " +
                     event.getDate() + " , " +
                     event.getTime() + " , " +
                     event.getDescription() + " , " +
                     event.getAttendeeCount() + " , " +
                     event.getUID() + " , " +
                     event.getCategory() + " , " +
                     event.getTheme() + " , " +
                     event.getEID()
             );
         } else {
             // If the file is not empty, seek to the end and then write
             file.seek(fileLength-1);
             file.writeBytes(
                     event.getName() + " , " +
                     event.getDate() + " , " +
                     event.getTime() + " , " +
                     event.getDescription() + " , " +
                     event.getAttendeeCount() + " , " +
                     event.getUID() + " , " +
                     event.getCategory() + " , " +
                     event.getTheme() + " , " +
                     event.getEID()
             );
         }
         file.close();
         printing.printSomething("added");
    }
 */
    public static  Event findeventID(String Eid,String filename) {
    	String line;
    	 String inputString;
         
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
    	{   int currentLine = 1;
            while ((line = reader.readLine()) != null)
            {   String[] items = line.split(" , ");
            if (items.length >= 10) 
            {
                String name = items[0];
                String  date= items[1];
                String time = items[2];
                String description = items[3];
                String attendeeCount = items[4];
                String UID = items[5];
                String theme=items[6];
                String cate=items[7];
                String EID=items[9];
                String Venuename=items[8];
               
            if (EID.equals(Eid)) 
            { return new Event(name, date, time, description, attendeeCount, UID,theme,cate,Venuename,Eid);
            }else currentLine ++; 
        } } }
    	catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());}
		   return null; }     
    
   
    
    public static Event findevent(String searchCriteria, String searchType, String filename) {
        String line;
     
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int  currentLine=1;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(" , ");
                if (items.length >= 10) {
                	String name = items[0];
                    String  date= items[1];
                    String time = items[2];
                    String description = items[3];
                    String attendeeCount = items[4];
                    String UID = items[5];
                    String theme=items[6];
                    String cate=items[7];
                    String EID=items[9];
                    String Venuename=items[8];
                    
                    if ("Name".equalsIgnoreCase(searchType) && searchCriteria.equals(name)) {
                        return new Event(name, date, time, description, attendeeCount, UID, theme, cate,Venuename, EID);
                    } else if ("Date".equalsIgnoreCase(searchType) && searchCriteria.equals(items[1])) {
                        return new Event(name, date, time, description, attendeeCount, UID, theme, cate,Venuename, EID);
                    } else {
                        currentLine++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return null;
    }

   
    
   
	 	
//    public void delete_event_from_file_and_arraylist(Event e,String filename,String EID)throws Exception 
//    { 
//    	//f. delete_Event_from_arraylist(filename, EID);
//    	
//    	Functions f=new Functions();
//    	f.deleteVenueBooking(e.getEID(),"venuebook.txt");
//    	
//        String line;
//    	 StringBuilder sb = new StringBuilder();
//		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
//    	{   
//            while ((line = reader.readLine()) != null)
//            {   String[] items = line.split(" , ");
//            if (items.length >= 10) 
//            {
//            	  String EVENTID=items[9];
//            	  if (!EVENTID.equals(EID)) {
//                      sb.append(line).append("\n");
//                  } 
//            } 
//            
//          //   else   sb.append(line).append("\n");
//            }
//            
//           
//         }
//             try (FileOutputStream fos = new FileOutputStream(filename, false)) {
//		            fos.write(sb.toString().getBytes());
//		        }
//			
//             
//                  
//}
//       
 
   
    public void delete_event_from_file_and_arraylist(Event e, String eventFileName, String EID) throws IOException {
        // Delete event from the event file
       
    	 String venueBookFileName = "venuebook.txt";
    	StringBuilder eventFileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(eventFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(" , ");
                if (items.length >= 10) {
                    String EVENTID = items[9];
                    if (!EVENTID.equals(EID)) {
                        eventFileContent.append(line).append("\n");
                    }
                }
            }
        }

        // Write the modified content back to the event file
        try (FileOutputStream fos = new FileOutputStream(eventFileName, false)) {
            fos.write(eventFileContent.toString().getBytes());
        }

        // Delete event from the venue book file
        StringBuilder venueBookFileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",");
                if (items.length >= 5) {
                    String eventID = items[4].trim(); // Trim to remove any leading/trailing spaces
                    if (!eventID.equals(EID)) {
                        venueBookFileContent.append(line).append("\n");
                    }
                }
            }
        }

        // Write the modified content back to the venue book file
        try (FileOutputStream fos = new FileOutputStream(venueBookFileName, false)) {
            fos.write(venueBookFileContent.toString().getBytes());
        }
    }
    

//	public Event updateEvent(String id, String filename)throws Exception {
//		
//		
//		Event toupdatedEvent = findeventID(id, filename);
//	   delete_event_from_file_and_arraylist(toupdatedEvent, filename, id);
//		
//		if (toupdatedEvent != null) 
//		{
//           Scanner scanner = new Scanner(System.in);
//           
//            
//           toupdatedEvent.printing.printSomething('\n'+
//                    "UserID :" + toupdatedEvent.UserID + '\n' +
//                    "event id :" + toupdatedEvent.EVENTID+'\n' +
//                    "1. name :" + toupdatedEvent.name + '\n' +
//                    "2. date :" + toupdatedEvent.date +'\n' +
//                    "3. time :" + toupdatedEvent.time + '\n' +
//                    "4. description :" + toupdatedEvent.description + '\n' +
//                    "5. attendeeCount :" + toupdatedEvent.attendeeCount +'\n' +
//                    "6. theme :" + toupdatedEvent.theme + '\n' +
//                    "7. category :" + toupdatedEvent.category + '\n'+
//                    "8. venue name :"+toupdatedEvent.Venuenamee + '\n'
//                    );
//
//            System.out.print("Enter the number of the field you want to update: ");
//            String choice = scanner.next();
//            
//            switch (choice) {
//                case "1":
//                	printing.printSomething("Enter new event name:");                  
//                   toupdatedEvent.setName(scanner.next());
// 	               
//                    break;
//                case "2":
//                	 printing.printSomething("Enter new event date (yyyy-MM-dd):");
//  	               String dateInput = scanner.next();
//  	               toupdatedEvent.setDate(dateInput);
//                     
//                    break;
//                case "3":
//                	 printing.printSomething("Enter new event time:");
//  	                toupdatedEvent.setTime(scanner.next());
//                    break;
//                case "4":
//                	printing.printSomething("Enter new event description:");
// 	                toupdatedEvent.setDescription(scanner.next());
//                    break;
//                case "5":
//                	 printing.printSomething("Enter  new event attendee count:");
//  	                toupdatedEvent.setAttendeeCount(scanner.next());
//  	               break;
//                case "6":
//                	 printing.printSomething("Enter new event theme :");
//  		             toupdatedEvent.setTheme(scanner.next());   
//  		            break;
//                case "7":
//                	 printing.printSomething("Enter new event category:");
//		              toupdatedEvent.setCategory(scanner.next());
//	                break;
//                case "8":
//                printing.printSomething("Enter new event venue name:");
//	              toupdatedEvent.setVenuename(scanner.next());
//
//                default:
//                    System.out.println("Invalid choice.");
//            }
//		
//		
//		
//           addEventToFile(toupdatedEvent, filename);
//           //f.updateEventListFromFile( filename);
//            
//           
//        } 
//          
//            
//            
//            
//            
//            
//            
//  return toupdatedEvent ;          
//		
// }
//	
//	
    
    
    
    
//    public Event updateEvent(String id, String filename) throws Exception {
//        Event toupdatedEvent = findeventID(id, filename);
//        delete_event_from_file_and_arraylist(toupdatedEvent, filename, id);
//
//        if (toupdatedEvent != null) {
//            Scanner scanner = new Scanner(System.in);
//
//            printing.printSomething('\n' +
//                    "UserID :" + toupdatedEvent.UserID + '\n' +
//                    "event id :" + toupdatedEvent.EVENTID + '\n' +
//                    "1. name :" + toupdatedEvent.name + '\n' +
//                    "2. date :" + toupdatedEvent.date + '\n' +
//                    "3. time :" + toupdatedEvent.time + '\n' +
//                    "4. description :" + toupdatedEvent.description + '\n' +
//                    "5. attendeeCount :" + toupdatedEvent.attendeeCount + '\n' +
//                    "6. theme :" + toupdatedEvent.theme + '\n' +
//                    "7. category :" + toupdatedEvent.category + '\n' +
//                    "8. venue name :" + toupdatedEvent.Venuenamee + '\n'
//            );
//
//            System.out.print("Enter the number of the field you want to update: ");
//            String choice = scanner.next();
//
//            switch (choice) {
//                case "1":
//                    printing.printSomething("Enter new event name:");
//                    toupdatedEvent.setName(scanner.next());
//                    break;
//                case "2":
//                    printing.printSomething("Enter new event date (yyyy-MM-dd):");
//                    String dateInput = scanner.next();
//                    toupdatedEvent.setDate(dateInput);
//                    break;
//                case "3":
//                    printing.printSomething("Enter new event time:");
//                    toupdatedEvent.setTime(scanner.next());
//                    break;
//                case "4":
//                    printing.printSomething("Enter new event description:");
//                    toupdatedEvent.setDescription(scanner.next());
//                    break;
//                case "5":
//                    printing.printSomething("Enter new event attendee count:");
//                    toupdatedEvent.setAttendeeCount(scanner.next());
//                    break;
//                case "6":
//                    printing.printSomething("Enter new event theme :");
//                    toupdatedEvent.setTheme(scanner.next());
//                    break;
//                case "7":
//                    printing.printSomething("Enter new event category:");
//                    toupdatedEvent.setCategory(scanner.next());
//                    break;
//                case "8":
//                    printing.printSomething("Enter new event venue name:");
//                    toupdatedEvent.setVenuename(scanner.next());
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//            }
//
//            try {
//                addEventToFile(toupdatedEvent, filename);
//            } catch (Exception e) {
//                printing.printSomething("An error occurred while updating the event: " + e.getMessage());
//            }
//        }
//
//        return toupdatedEvent;
//    }

    public Event updateEvent(String id, String filename) throws Exception {
        Event toupdatedEvent = findeventID(id, filename);
        delete_event_from_file_and_arraylist(toupdatedEvent, filename, id);

        if (toupdatedEvent != null) {
            Scanner scanner = new Scanner(System.in);

            // Display event details
            printing.printSomething('\n' +
                    "UserID :" + toupdatedEvent.UserID + '\n' +
                    "event id :" + toupdatedEvent.EVENTID + '\n' +
                    "1. name :" + toupdatedEvent.name + '\n' +
                    "2. date :" + toupdatedEvent.date + '\n' +
                    "3. time :" + toupdatedEvent.time + '\n' +
                    "4. description :" + toupdatedEvent.description + '\n' +
                    "5. attendeeCount :" + toupdatedEvent.attendeeCount + '\n' +
                    "6. theme :" + toupdatedEvent.theme + '\n' +
                    "7. category :" + toupdatedEvent.category + '\n' +
                    "8. venue name :" + toupdatedEvent.Venuenamee + '\n'
            );

            System.out.print("Enter the number of the field you want to update: ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    printing.printSomething("Enter new event name:");
                    toupdatedEvent.setName(scanner.next());
                    break;
                case "2":
                	 printing.printSomething("Enter new event date (yyyy-MM-dd):");
                	    String newDate = scanner.next();
                	    toupdatedEvent.setDate(newDate);
                	    // Update venue date in venuebook
                	    toupdatedEvent.updateVenueInVenueBook(id, newDate, "venuebook.txt");
                	  
                    break;
                case "3":
                    printing.printSomething("Enter new event time:");
                    toupdatedEvent.setTime(scanner.next());
                    break;
                case "4":
                    printing.printSomething("Enter new event description:");
                    toupdatedEvent.setDescription(scanner.next());
                    break;
                case "5":
                    printing.printSomething("Enter new event attendee count:");
                    toupdatedEvent.setAttendeeCount(scanner.next());
                    break;
                case "6":
                    printing.printSomething("Enter new event theme :");
                    toupdatedEvent.setTheme(scanner.next());
                    break;
                case "7":
                    printing.printSomething("Enter new event category:");
                    toupdatedEvent.setCategory(scanner.next());
                    break;
                case "8":
                    printing.printSomething("Enter new event venue name:");
                    String newVenueName = scanner.next();
                    toupdatedEvent.setVenuename(newVenueName);
                    // Update venue ID in venuebook
                    toupdatedEvent.updateVenueInVenueBook(id, newVenueName, "venuebook.txt");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            try {
                addEventToFile(toupdatedEvent, filename);
            } catch (Exception e) {
                printing.printSomething("An error occurred while updating the event: " + e.getMessage());
            }
        }

        return toupdatedEvent;
    }

    public void updateVenueInVenueBook(String eventId, String newDate, String venueBookFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[4].equals(eventId)) {
                    // Found the booking for the event, update the date
                    parts[2] = newDate;
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
    
    
//    public void updateVenueInVenueBook(String eventId, String newVenueName, String venueBookFilename) {
//        try {
//        	
//            File venueBookFile = new File(venueBookFilename);
//            RandomAccessFile raf = new RandomAccessFile(venueBookFile, "rw");
//            String line;
//
//            while ((line = raf.readLine()) != null) {
//                String[] data = line.split(",");
//                if (data.length >= 5 && data[4].trim().equals(eventId)) {
//                    // Update the venue name
//                    data[0] = findVenueIdByName(newVenueName,"venue.txt");
//                    // Get the position of the current line
//                    long currentPosition = raf.getFilePointer();
//                    // Go back to the start of the current line
//                    raf.seek(currentPosition - line.length() - 2); // subtracting 2 for \r\n
//                    // Write the updated line to the file
//                    raf.writeBytes(String.join(",", data) + "\r\n");
//                }
//            }
//
//            raf.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    
    
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public void setVenuename(String n) {Venuenamee=n; }
    public String getVenuename(){return Venuenamee;}


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
    
    public String getUID() {
        return UserID;
    }

    public void setUID(String ID) {
        this.UserID = ID;
    }
    
    
    
    public String getEID() {
        return EVENTID;
    }

    public void setEID(String ID) {
        this.EVENTID = ID;
    }
    
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    // Getter and setter for category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

	
    public String toString3() {
        return  
                "UserID='" + UserID + '\'' +
                ", event id=" + EVENTID+'\'' +
                ",1. name='" + name + '\'' +
                ",2. date=" + date +'\'' +
                ",3. time='" + time + '\'' +
                ",4. description='" + description + '\'' +
                ",5. attendeeCount=" + attendeeCount +'\'' +
                ",6. theme='" + theme + '\'' +                
                ",7. category='" + category + '\'' +
                ",8. Venue ='" + Venuenamee + '\''
                ;
    }

  
    
    public String toString2() {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[0;36m"); // Set text color to cyan
        sb.append("Event Details:\n");
        sb.append("\033[0;33m"); // Set text color to yellow for attribute names
        sb.append("- UserID: ").append(UserID).append("\n");
        sb.append("- Event ID: ").append(EVENTID).append("\n");
        sb.append("- Name: ").append(name).append("\n");
        sb.append("- Date: ").append(date).append("\n");
        sb.append("- Time: ").append(time).append("\n");
        sb.append("- Description: ").append(description).append("\n");
        sb.append("- Attendee Count: ").append(attendeeCount).append("\n");
        sb.append("- Theme: ").append(theme).append("\n");
        sb.append("-Venue name").append(Venuenamee).append("\n");
        sb.append("- Category: ").append(category).append("\n");
        sb.append("\033[0m"); // Reset text color
        return sb.toString();
    }









    public String toString() 
    {
    	 return	   name +
                 ", " + date +
                 "," + time + 
                 ", " + description +
                 ", " + attendeeCount +
                  ", "+UserID+
                 ", "+ theme  +
                 ", " + category  +
                 ", " +Venuenamee+
                 ", " + EVENTID ;
 	
    }
    
    
    

}
