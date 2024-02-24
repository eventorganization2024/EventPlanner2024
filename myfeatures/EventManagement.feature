Feature: Event Management

Scenario: Creates an Event
Given the user or administratoris going to make event 
When  the user or administrator  enters the event details such as date, time, location, description, etc. 
Then the event is successfully created in the system


Scenario: Cancels an exist Event
Given   there is an existing event
When the user or administrator selects the option to cancel the event
Then the event is marked as canceled in the system


Scenario: Cancels an non exist Event
Given the user or administrator selects the option to cancel the event  
When there is an non existing event
Then the message that the customer is not exist will be print



Scenario: Updates Event Details
Given the user or administrator selects the option to update the event details   
And there is an existing event 
Then the event details are successfully updated in the system