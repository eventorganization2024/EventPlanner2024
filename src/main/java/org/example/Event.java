package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	private String UserID;
    private String name;
    private Date date;
    private String time;
    private String description;
    private int attendeeCount;
    
 
    
    
    private static final String EVENT_FILE_NAME = "event.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static Printing printing = new Printing();
    
    public  Event() {}
    
    public Event(String name, Date date, String time,  String description, int attendeeCount, String UserID) {
        this .UserID=UserID; 
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.attendeeCount = attendeeCount;
       
    }

   
    
    void addEventToFile(Event event)
    {
        try {
            FileWriter eventsFile = new FileWriter(EVENT_FILE_NAME, true);
            eventsFile.append(event.getName()).append(" , ")
                    .append(DATE_FORMAT.format(event.getDate())).append(" , ")
                    .append(event.getTime()).append(" , ")
                    .append(event.getDescription()).append(" , ")
                    .append(String.valueOf(event.getAttendeeCount())).append(" , ")
                    .append(event.getID()).append(" , ")
                    .append("\n");

            eventsFile.close();
        } catch (IOException e)
        {
            
			printing.printSomething("An error occurred: " + e.getMessage());
       }
    }
    
    
    
    public static Event getEventFromLine(String line) {
        String[] items = line.split(" , ");
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
        int attendeeCount = Integer.parseInt(items[4]);
        String ID = items[5];

        return new Event(name, date, time, description, attendeeCount, ID);
    } 
    
    
    
    
    
    
    
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
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

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }
    
    public String getID() {
        return UserID;
    }

    public void setID(String ID) {
        this.UserID = ID;
    }

}
