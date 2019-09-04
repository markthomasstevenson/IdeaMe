Feature: Create Idea

  Scenario: Go to Create Idea
    Given I am on the "idea list" screen
    When I tap the Fab button
    Then I am on the "create idea" screen

  Scenario: Back out of Create Idea and Cancel
    Given I am on the "create idea" screen
    When I navigate up
    And I am shown a dialog
    And I tap the negative option
    Then I am on the "create idea" screen

  Scenario: Submit idea without Title or Elevator Pitch
    Given I am on the "create idea" screen
    And The "title" field is ""
    And The "elevator" field is ""
    When I tap the Fab button
    Then I should see "[TBC]"
    And I should see "[TBC]"

  Scenario: Submit idea without Title
    Given I am on the "create idea" screen
    And The "title" field is ""
    And The "elevator" field is "not empty"
    When I tap the Fab button
    Then I should see "[TBC]"

  Scenario: Submit idea without Elevator Pitch
    Given I am on the "create idea" screen
    And The "title" field is "not empty"
    And The "elevator" field is ""
    When I tap the Fab button
    Then I should see "[TBC]"

  Scenario: Submit idea
    Given I am on the "create idea" screen
    And The "title" field is "not empty"
    And The "elevator" field is "not empty"
    When I tap the Fab button
    Then I am on the "view ideas" screen
    And I should see "not empty"
