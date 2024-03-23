Feature: Event Management

Scenario: Customer Creates an Event 
Given the customer is going to create an event 
When the customer enters the event details such as Date "2024-12-27", time "5:00pm", description "A_celebration", attendeeCount "300", name "omar_party",category "Birthday" ,theme "Decades_Theme",Venue "V1",eventid "2000"
Then the event is added to admin requst

Scenario: Cancels an Existing Event 
Given there is an existing event  
When cancel event selected 
Then the event deleted

Scenario: Cancels an non_Existing Event   
Given there is an non_existing event 
When cancel event selected 
Then  non_Existing massage


Scenario:  Update Event Details
Given selects the option to update the event details
And there is an existing event to update  
Then the event details are successfully updated in the system

Scenario: Administrator Creates an Event
Given the administrator is going to create an event
When the administrator enters the event details such as Date "2024-3-24", time "5:00 pm", description "A celebration", attendeeCount "200", name "reem party",category "Birthday" ,theme "Decades Theme",Venue "V1",eventid "1000"
Then the event is successfully created in the system

Scenario: search for Existing event
Given Customer is gonig to search for Exist event by name
When Customer enter event name " party " 
Then show event details

Scenario: search for Existing event
Given Customer is gonig to search for Exist event by date
When Customer enter event date " 2024-2-25 " 
Then show event details 


Scenario: search for non_Existing event
Given Customer is gonig to search fornon_Exist event by name
When Customer enter event name " party " 
Then  non_Existing massage

Scenario: search for non_Existing event
Given Customer is gonig to search for non_Exist event by date
When Customer enter event date " 2024-2-25 " 
Then non_Existing massage











