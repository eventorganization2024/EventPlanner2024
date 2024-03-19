Feature: Venue Management for Admin


Scenario: Add Venue
Given that the admin is logged into the system
When  select to add a new venue such as details "VenueId" , 100.22 , "address" ,"imagepath" ,"name" and 100
Then the admin can successfully add the venue to the system.

Scenario: Edit Venue
Given that the admin is logged into the system
When select to edit venue details by its 1 and "VenueId"
Then the admin can successfully modify venue information.

Scenario: Delete Venue
Given that the admin is logged into the system
When select to delete a venue by its "VenueId"
Then the admin can successfully remove the venue from the system.

Scenario: View All Venues
Given that the admin is logged into the system
When select to view all venues registered in the system
Then the admin can see a comprehensive list of all venues.

#Scenario: View Venue Availability
#Given that the admin is logged into the system
#When select to check the availability of venues
#Then the admin can see the availability status of all venues within the system.
#

