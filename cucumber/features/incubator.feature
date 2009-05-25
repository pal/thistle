Feature: Up and coming features
  As a test writer
  I want to be able to express my intent clearly
  so that my tests have great value for me
  
  Scenario: Verify we can use forms with simple values (issue 1)
    Given I visit http://example-app.appspot.com/
    Then I should not see the text "You searched for:"
    When I enter ABC 123 into s field
    And I click Search
    Then I should see the text "You searched for: ABC 123"

  Scenario: Use of tables (issue 2)
    Given I visit http://example-app.appspot.com/
    And I click "Log in"
    And I enter the following:
      | Email | testuser@mydomain.com |
      | Password  | It's secret dude! |
    When I click the "Log in" button
    Then I should see the text "Welcome testuser@mydomain.com!"

  Scenario: Short-hand for login (issue 3)
    Given I visit http://example-app.appspot.com/
    And I login as "testuser@mydomain.com" / "It's secret dude!"
    And I click "My Page"
    And I click "Edit my profile"
    And I enter the following:
      | First name | Augustus |
    When I click "Save"
    Then "First name" should equal "Augustus"