package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Iterator;

public class Calendar {
	 static Printing printing = new Printing();
		
    private List<Event> events;
    private int year;
    private int month;

    public Calendar() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public Event getEventById(String eventId) {
        return events.stream()
                .filter(event -> event.getEID().equals(eventId))
                .findFirst()
                .orElse(null);
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    
    public void deleteEvent(Event event) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event currentEvent = iterator.next();
            if (currentEvent.equals(event)) {
                iterator.remove();
                break; // Assuming each event has a unique identifier
            }
        }
}
    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static  List<Event> makeListofEvent(String cId)  {
    	Functions.updateeventandcustomer(Functions. customerId,Functions.EVENT_FILE_NAME);

    	List<Event> customerEvents = new ArrayList<>();

    	for (Customer customer : Functions.customers) {
    	if (customer.getId().equals(cId)) {
    	for (Event E : Customer.getCevents()) {
    	if (E.getUsrTd().equals(cId)) {
    	customerEvents.add(E);
    	}
    	}
    	}
    	}


    	if (customerEvents.isEmpty()) {

    	return Collections.emptyList(); 
    	} else {
    	return customerEvents;
    	}
    	}
    	////////////////////////////////////////////////////////////////////////////////////////////

    	//method to load events for a specific customer in calendar
    	public static  Calendar loadEventsForCustomerInCalendar(String customerId) {
    	List<Event> customerEvents;
    	try {
    	customerEvents = makeListofEvent(customerId);

    	if (customerEvents != null && !customerEvents.isEmpty()) {
    	Calendar calendar = new Calendar();

    	for (Event event : customerEvents) {
    	calendar.addEvent(event);
    	}return calendar ;
    	} else {
    	printing.printSomething( "No events found for customer with ID: " + customerId);

    	}
    	} catch (Exception e) {

    	printing.printSomething( Functions.ERROR_PREFIX + e.getMessage());
    	} return null ;

    	}




    	public static void displayAllCustomerEvents(Calendar calendar) {
    	//Keep track of displayed months
    	Set<String> displayedMonths = new HashSet<>();

    	//Collect all unique year-month combinations from events and sort them chronologically
    	List<String> eventYearMonths = new ArrayList<>();
    	for (Event event : calendar.getEvents()) {
    	LocalDate eventDate = event.getDateAsLocalDate();
    	String yearMonthKey = eventDate.getYear() + "-" + eventDate.getMonthValue();
    	if (!eventYearMonths.contains(yearMonthKey)) {
    	eventYearMonths.add(yearMonthKey);
    	}
    	}
    	Collections.sort(eventYearMonths);

    	//Iterate over all unique year-month combinations
    	for (String yearMonthKey : eventYearMonths) {
    	String[] parts = yearMonthKey.split("-");
    	int year = Integer.parseInt(parts[0]);
    	int month = Integer.parseInt(parts[1]);

    	//Set the current year and month to the calendar
    	calendar.setYear(year);
    	calendar.setMonth(month);

    	//Print the events for the current year and month
    	displayCalendarEvents(calendar); 
    	printing.printInColor(Functions.LINE_STARS, Printing.ANSI_BLACK);

    	//Add the displayed month to the set
    	displayedMonths.add(yearMonthKey);
    	}
    	}


    	public static void displayCalendarEvents(Calendar calendar) {
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    	DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

    	//Print year and month with appropriate color
    	YearMonth yearMonth = YearMonth.of(calendar.getYear(), calendar.getMonth());
    	if (yearMonth.isBefore(YearMonth.now())) {
    	printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_GRAY);
    	} else if (yearMonth.equals(YearMonth.now())) {
    	printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_ORANGE);
    	} else {
    	printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_BLUE);
    	}

    	//Print the header for days of the week
    	printing.printInColor("\n"+Functions.LINE2, Printing.ANSI_LIME);
    	printing.printInColor("|          Mon          |          Tue          |          Wed          |          Thu          |          Fri          |          Sat          |          Sun          |\n", Printing.ANSI_GREEN);

    	//Get the first day of the month
    	LocalDate firstDayOfMonth = LocalDate.of(calendar.getYear(), calendar.getMonth(), 1);

    	int frow = 0;
    	boolean inFirstRow = false;
    	//Set the current date to the first day of the month
    	LocalDate currentDate = firstDayOfMonth;

    	int currentDayOfMonth = 1;
    	//Print the days of the month
    	while (currentDayOfMonth <= yearMonth.lengthOfMonth()) {
    	printing.printInColor(Functions.LINE2, Printing.ANSI_LIME);

    	StringBuilder dayRow = new StringBuilder("|");
    	inFirstRow = false;
    	//Print the current week
    	for (int i = 0; i < 7; i++) {
    	currentDayOfMonth++;
    	if (currentDate.format(dayFormatter).compareTo("01") == 0 &&currentDate.getMonthValue()==calendar.getMonth()) {
    	inFirstRow = true;
    	//Print the calendar grid
    	for (int k = 0; k < firstDayOfMonth.getDayOfWeek().getValue() - 1; k++) {
    	dayRow.append("                       |");
    	frow = k + 2;
    	}
    	}

    	if (inFirstRow && i > 7 - frow) {
    	break;
    	}
    	String dayString = currentDate.format(dayFormatter);
    	if (currentDate.equals(LocalDate.now())) {
    	dayString = "[" + dayString + "]";
    	dayRow.append(String.format("*** %s ****|", dayString));
    	} else if(currentDate.getMonthValue()==calendar.getMonth()) {
    	dayRow.append(String.format("          %s           ", dayString));dayRow.append("|");
    	} else{
    	dayRow.append(String.format("                       |"));}   

    	currentDate = currentDate.plusDays(1);

    	}

    	printing.printInColor(dayRow.toString(), Printing.ANSI_GREEN);
    	printing.printInColor("\n", Printing.ANSI_GREEN);
    	printing.printInColor(Functions.LINE2, Printing.ANSI_LIME);

    	//Print events for each day
    	printEventsForWeek(calendar, currentDate.minusDays(7), currentDate.minusDays(1));
    	}

    	//Print the footer for the days of the week
    	printing.printInColor(Functions.LINE2, Printing.ANSI_LIME);
    	}



    	private static void printEventsForWeek(Calendar calendar, LocalDate startDay, LocalDate endDay) {

    	for (int i = 0; i < 7; i++) {
    	for (LocalDate currentDate = startDay; currentDate.isBefore(endDay.plusDays(1)); currentDate = currentDate.plusDays(1)) {
    	final LocalDate currentDate2 = currentDate;  // Declare currentDate2 as final


    	StringBuilder eventRow = new StringBuilder("|");

    	Event eventForDay = calendar.getEvents().stream()
    	.filter(event -> event.getDateAsLocalDate().isEqual(currentDate2))
    	.findFirst()
    	.orElse(null);

    	//Display the event with appropriate color
    	if (eventForDay != null && currentDate.getMonthValue()==calendar.getMonth()) {
    	String eventColor = "";
    	YearMonth eventYearMonth = YearMonth.of(eventForDay.getDateAsLocalDate().getYear(), eventForDay.getDateAsLocalDate().getMonth());
    	if (eventYearMonth.isBefore(YearMonth.now())) {
    	eventColor = Printing.ANSI_GRAY;
    	} else if (eventYearMonth.equals(YearMonth.now())) {
    	eventColor = Printing.ANSI_ORANGE;
    	}else  eventColor = Printing.ANSI_BLUE;


    	eventRow.append(eventColor).append(String.format("[%-6s] %-14s", eventForDay.getEID(), eventForDay.getName()));
    	calendar.deleteEvent(eventForDay);

    	} else {
    	eventRow.append("                       ");
    	}

    	printing.printInColor(eventRow.toString(), Printing.ANSI_LIME);

    	}
    	printing.printInColor("|\n", Printing.ANSI_LIME);

    	}
    	}




    	//////////////////////////////////////////////////////////////////////////////////////////////////////////***********	  








    	//Initialize a set to keep track of event IDs for which notifications have been sent
    	private Set<String> notifiedEvents = new HashSet<>();

    	//Initialize a scheduled executor service
    	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    	public void startApproachingUpcomingEvents() {
    	// Schedule the approachUpcomingEvents method to run every hour
    	executor.scheduleAtFixedRate(this::approachUpcomingEvents, 0, 1, TimeUnit.MINUTES);
    	}

    	public void stopApproachingUpcomingEvents() {
    	// Shutdown the executor service when you want to stop checking for upcoming events
    	executor.shutdown();
    	}

    	public void approachUpcomingEvents()throws NullPointerException {
    	LocalDateTime now = LocalDateTime.now();
    	Functions.updateEventList(Functions.EVENT_FILE_NAME);

    	List<Event> upcomingEvents = events.stream()
    	.filter(event -> {
    	// Convert Date to String
    	String dateString = new SimpleDateFormat(Functions.DATE_FORMAT_PATTERN).format(event.getDate());
    	// Parse String as LocalDate
    	LocalDate eventDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(Functions.DATE_FORMAT_PATTERN));
    	return eventDate.isEqual(now.toLocalDate());
    	})
    	.toList();



    	for (Event event : upcomingEvents) {

    	LocalTime currentTime = LocalTime.now();
    	LocalTime eventTime = LocalTime.parse(event.getTime().trim(), DateTimeFormatter.ofPattern("h:mm a"));
    	long timeDifferenceMinutes = currentTime.until(eventTime, java.time.temporal.ChronoUnit.MINUTES);
    	if (!notifiedEvents.contains(event.getEID()) && timeDifferenceMinutes == 59) {
    	String customerId = event.getUsrTd();
    	String recipientDetails = getEmailAndNameFromCustomerFile(customerId);
    	String[] details = null;

    	if (recipientDetails != null) {
    	details = recipientDetails.split("-");
    	} else {
    	details = new String[]{"royasmine05@gmail.com", "CLIENT"};
    	}
    	String recipientName = details[1];
    	String messageContent = generateMessageContent(recipientName, event.getTime(), 60 - timeDifferenceMinutes);
    	String subject = "Notification for Upcoming Event: " + event.getName();
    	sendNotificationsToParticipants(details[0], subject, messageContent);
    	notifiedEvents.add(event.getEID());
    	}
    	}

    	}


    	public String getEmailAndNameFromCustomerFile(String customerId) {

    		Functions.updateCustomersList();



    	for (Customer cust : Functions.customers) {
    	if (cust.getId().equals(customerId)) { 	            
    	return cust.getEmail()+"-"+cust.getUsername();
    	}
    	}

    	return null;
    	}

    	public String generateMessageContent( String customerName, String eventTime, long hoursDifference) {
    	//Implement this method to generate a professional message confirming the event start time for the customer with the specified ID
    	return "Dear " + customerName + ",\n\n"
    	+ "We are pleased to confirm your registration for the upcoming event.\n\n"
    	+ "Event Details:\n"
    	+ "Event Time: " + eventTime + "\n"
    	+ "Event Start Time: " + hoursDifference + " hours from now\n\n"
    	+ "Thank you for your participation. We look forward to seeing you at the event.\n\n"
    	+ "Best regards,\n"
    	+ "The Event Management Team";
    	}



    	public void sendNotificationsToParticipants(String recipientEmail, String subject, String messageContent) {
    	try {
    	String senderEmail = "royasmine05@gmail.com";
    	String password = getEncryptedPassword();

    	Properties properties = new Properties();
    	properties.put("mail.smtp.auth", "true");
    	properties.put("mail.smtp.starttls.enable", "true");
    	properties.put("mail.smtp.host", "smtp.gmail.com");
    	properties.put("mail.smtp.port", "587");

    	Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
    	@Override
    	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
    	return new javax.mail.PasswordAuthentication(senderEmail, password);
    	}
    	});

    	MimeMessage message = new MimeMessage(session);
    	message.setFrom(new InternetAddress(senderEmail));
    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
    	message.setSubject(subject);
    	message.setText(messageContent);

    	Transport.send(message);

    	} catch (MessagingException mex) {
    	printing.printSomething( Functions.ERROR_PREFIX + mex.getMessage());
    	}
    	}


    	////////////////////////////////////new/////////////////

    	public static String getEncryptedPassword() {
    	String password = null;
    	try (BufferedReader reader = new BufferedReader(new FileReader("config.properties"))) {
    	String line;
    	while ((line = reader.readLine()) != null) {
    	if (line.startsWith("password=")) {
    	password = line.substring("password=".length());
    	break;
    	}
    	}
    	} catch (IOException e) {
    	printing.printSomething( Functions.ERROR_PREFIX + e.getMessage());
    	}
    	return password;
    	}


    
    
    
    
    
    
    
}