Feature: Testing getCostDay method

  Scenario: Retrieve today's income when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getCostDay method is called with the path
    Then today's cost should be returned, which is 80.0

  Scenario: Retrieve today's income when there are no bought books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getCostDay method is called with the path
    Then the getCostDay method should return 0.0
