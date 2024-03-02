
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
	
   static Printing printing = new Printing();
   Functions f =new Functions();
    
    public  Event() {}
    
    public Event(String name, String date, String time,  String description, String attendeeCount, String UserID, String theme ,String category,String Eid) {
        this .UserID=UserID; 
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.attendeeCount = attendeeCount;
        this.theme= theme;
        this.category=category;
        this .EVENTID=Eid;
    }

   
    
    public static Event getEventFromLine(String line) {
        Event event = new Event();
       
        
        String[] items = line.split(",");
        if (items.length >= 9) {
        	 String name = items[0];
             String date = items[1];
             String time = items[2];
             String description = items[3];
             String attendeeCount=items[4];
             String UID = items[5];
             String theme = items[6];
             String category = items[7];
             String EID = items[8];
  
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
        try {
            FileWriter  eventFile = new FileWriter(filename, true);
            eventFile.append(event.getName()).append(" , ")
                    .append(event.getDate()).append(" , ")
                    .append(event.getTime()).append(" , ")
                    .append(event.getDescription()).append(" , ")
                    .append(event.getAttendeeCount()).append(" , ")
                    .append(event.getUID()).append(" , ")
                    .append(event.getCategory()).append(" , ")
                    .append(event.getTheme()).append(" , ")
                    .append(event.getEID()).append("\n");


          eventFile.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
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
            if (items.length >= 9) 
            {
                String name = items[0];
                String  date= items[1];
                String time = items[2];
                String description = items[3];
                String attendeeCount = items[4];
                String UID = items[5];
                String theme=items[6];
                String cate=items[7];
                String EID=items[8];
               
            if (EID.equals(Eid)) 
            { return new Event(name, date, time, description, attendeeCount, UID,theme,cate,Eid);
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
                if (items.length >= 9) {
                	String name = items[0];
                    String  date= items[1];
                    String time = items[2];
                    String description = items[3];
                    String attendeeCount = items[4];
                    String UID = items[5];
                    String theme=items[6];
                    String cate=items[7];
                    String EID=items[8];
                    
                    if ("Name".equalsIgnoreCase(searchType) && searchCriteria.equals(name)) {
                        return new Event(name, date, time, description, attendeeCount, UID, theme, cate, EID);
                    } else if ("Date".equalsIgnoreCase(searchType) && searchCriteria.equals(items[1])) {
                        return new Event(name, date, time, description, attendeeCount, UID, theme, cate, EID);
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

   
    
   
	 	
    public void delete_event_from_file_and_arraylist(Event e,String filename,String EID)throws Exception 
    { 
    	//f. delete_Event_from_arraylist(filename, EID);
    	
    	
        String line;
    	 StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
    	{   
            while ((line = reader.readLine()) != null)
            {   String[] items = line.split(" , ");
            if (items.length >= 9) 
            {
            	  String EVENTID=items[8];
            	  if (!EVENTID.equals(EID)) {
                      sb.append(line).append("\n");
                  } 
            } 
            
          //   else   sb.append(line).append("\n");
            }
            
           
         }
             try (FileOutputStream fos = new FileOutputStream(filename, false)) {
		            fos.write(sb.toString().getBytes());
		        }
			
             
                  
}
       
 
   
	     
    	
	public Event updateEvent(String id, String filename)throws Exception {
		
		
		Event toupdatedEvent = findeventID(id, filename);
	   delete_event_from_file_and_arraylist(toupdatedEvent, filename, id);
		
		if (toupdatedEvent != null) 
		{
           Scanner scanner = new Scanner(System.in);
           
            
           toupdatedEvent.printing.printSomething('\n'+
                    "UserID :" + toupdatedEvent.UserID + '\n' +
                    "event id :" + toupdatedEvent.EVENTID+'\n' +
                    "1. name :" + toupdatedEvent.name + '\n' +
                    "2. date :" + toupdatedEvent.date +'\n' +
                    "3. time :" + toupdatedEvent.time + '\n' +
                    "4. description :" + toupdatedEvent.description + '\n' +
                    "5. attendeeCount :" + toupdatedEvent.attendeeCount +'\n' +
                    "6. theme :" + toupdatedEvent.theme + '\n' +
                    "7. category :" + toupdatedEvent.category + '\n'
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
  	               String dateInput = scanner.next();
  	               toupdatedEvent.setDate(dateInput);
                     
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
                	 printing.printSomething("Enter  new event attendee count:");
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
                default:
                    System.out.println("Invalid choice.");
            }
		
		
		
           addEventToFile(toupdatedEvent, filename);
           //f.updateEventListFromFile( filename);
            
           
        } 
          
            
            
            
            
            
            
  return toupdatedEvent ;          
		
 }
		
		

    
    
    
    
    
    
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

	
    public String toString() {
        return "Event{" +
                "UserID='" + UserID + '\'' +
                ", event id=" + EVENTID+'\'' +
                ",1. name='" + name + '\'' +
                ",2. date=" + date +'\'' +
                ",3. time='" + time + '\'' +
                ",4. description='" + description + '\'' +
                ",5. attendeeCount=" + attendeeCount +'\'' +
                ",6. theme='" + theme + '\'' +
                ",7. category='" + category + '\''  +
                '}';
    }

    public String toString1() 
    {
    	 return	   name +
                 ", " + date +
                 "," + time + 
                 ", " + description +
                 ", " + attendeeCount +
                  ", "+UserID+
                 ", "+ theme  +
                 ", " + category  +
                 ", " + EVENTID ;
 	
    }
    
    
    

}
