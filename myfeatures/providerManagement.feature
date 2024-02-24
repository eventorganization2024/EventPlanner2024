Feature: Service Management


Scenario: Administrator or Provider Adds a Service
Given  the Provider or administratoris going to add service 
When the Provider or administrator  enters the service details
Then the new service is successfully added to the system



Scenario: Administrator or Provider Deletes a Service
Given the Provider or administrator selects the option to delete the service  
Then the service is removed from the system
And any associated bookings or events related to the service are also updated accordingly



Scenario: Administrator or Provider Edits Service Details
Given the Provider or administrator selects the option to update  the service details 
And makes the necessary changes to the service information
And submits the form to save the changes
Then the service details are successfully updated in the system



