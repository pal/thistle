Feature: Check Hello World works
  As a web surfer
  I want to be able to browse the site
  so that I can find the information I need

  Scenario: Check start-page
    Given I visit http://example-app.appspot.com/
    Then title should be Example app
    
  Scenario: Check sub-page
    Given I visit http://example-app.appspot.com/
    When I click Products
    Then title should be Products
  
  Scenario: Verify we can use forms with simple values
    Given I visit http://example-app.appspot.com/
    Then I should not see the text "You searched for:"
    When I enter ABC123 into s field
    And I click Search
    Then I should see the text "You searched for: ABC123"
  