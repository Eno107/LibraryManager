Feature: Testing getCostMonth method

  Scenario: Retrieve this month's cost when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getCostMonth method is called with the path
    Then this month's cost should be returned, which is 184.0

  Scenario: Retrieve this month's cost when there are no bought books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getCostMonth method is called with the path
    Then the getCostMonth method should return 0.0
