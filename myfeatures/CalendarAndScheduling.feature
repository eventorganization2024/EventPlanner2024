Feature: Calendar and Scheduling

  Scenario: User views upcoming events and important dates in the calendar
    Given there are upcoming events and important dates in the calendar
    When the user navigates to the calendar view
    Then the system should display a list of upcoming events and important dates

    