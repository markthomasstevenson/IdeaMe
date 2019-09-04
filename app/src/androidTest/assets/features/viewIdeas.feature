Feature: View Ideas Screen

  Scenario: View empty list
    Given I am on the "idea list" screen
    And I have 0 ideas
    Then I should see "Add an idea!"
    And The Fab should be visible

  Scenario: View non-empty list
    Given I am on the "idea list" screen
    And I have 1 ideas
    Then I should see 1 items in the list
    And I should not see "Add and idea!"
    And The Fab should be visible
