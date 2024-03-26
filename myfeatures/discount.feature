Feature: Discount Management

Scenario: when an administrator wants to create a new discount offer
Given the administrator is logged into the system
And navigates to the discount management section
When the administrator provides the discount details such as "10.0", "123", "2023-11-30", and "CODE123"
Then the new discount is successfully added to the system



Scenario: when an administrator needs to modify an existing discount according to 1 and "value"
Given the administrator is logged into the system
And accesses the list of existing discounts
When the administrator selects the discount to edit and updates the necessary details
Then the changes are saved





Scenario: when an administrator decides to remove a discount from the system
Given the administrator is logged into the system
And views the list of existing discounts
When the administrator selects the discount to delete and confirms the action
Then the discount is successfully removed from the system




Scenario: when a customer proceeds to checkout and wishes to apply a discount
Given the customer Added event
When the customer enters the valid discount code or selects a pre-applied discount
Then the discount is applied to the total order amount
#And the updated total is displayed






