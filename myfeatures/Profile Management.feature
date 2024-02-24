Feature: Profile Management


Scenario: User Updates Their Profile
Given the user is logged in
When the user selects the option to update their profile
And makes the necessary changes to their profile information
And submits the form to save the changes
Then the user's profile is successfully updated in the system




Scenario: User Deletes Their Profile
Given the user is logged in
When the user  selects the option to delete their profile
And confirms the deletion
Then the user's profile is permanently deleted from the system
And any associated data or bookings related to the user are also remove accordingly



Scenario: Provider Updates Their Profile
Given the user is logged in as a provider
When the provider selects the option to update their profile
And makes the necessary changes to their profile information
And submits the form to save the changes
Then the provider's profile is successfully updated in the system



Scenario: Provider Deletes Their Profile
Given the user is logged in as a provider
When the provider selects the option to delete their profile
And confirms the deletion
Then the provider's profile is permanently deleted from the system
And any associated data or bookings related to the provider are also removed accordingly
