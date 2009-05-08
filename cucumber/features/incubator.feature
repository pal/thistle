Feature: Check Hello World works Incubator
  As a web surfer
  I want to be able to browse the site
  so that I can find the information I need
  
  Scenario: Verify we can use forms with simple values
    Given I visit http://example-app.appspot.com/
    Then I should not see the text "You searched for:"
# Would like to match heading, like this:
#    When I enter Test, test into search field
    When I enter ABC123 into s field
    And I click Search
    Then I should see the text "You searched for: ABC123"
  
#  Scenario: Verify we can use forms without decoding
#    Given I visit http://example-app.appspot.com/
#    Then I should not see the text "You searched for:"
# Would like to match heading, like this:
#    When I enter Test, test into search field
#    When I enter ABC 123 into s field
#    And I click Search
#    Then I should see the text "You searched for: ABC 123"

  Scenario: Log in existing user
    Given I visit http://example-app.appspot.com/
    And I click "Log in"
    And I enter the following:
      | Username | testuser@mydomain.com |
      | Password  | It's secret dude! |
    When I click the "Log in" button
    Then I should see the text "Welcome testuser@mydomain.com!"

  Scenario: Sign up new user
    Given I visit http://example-app.appspot.com/
    And I login as "testuser@mydomain.com" / "It's secret dude!"
    And I click "My Page"
    And I click "Edit my profile"
    And I enter the following:
      | First name | Augustus |
    When I click "Save"
    Then "First name" should equal "Augustus"