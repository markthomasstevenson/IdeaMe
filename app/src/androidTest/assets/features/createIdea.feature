Feature: Viewing no ideas

  Scenario: View empty list
    Given I am on the "idea list" screen
    And I have 0 ideas
    Then I should see "Add an idea!"
