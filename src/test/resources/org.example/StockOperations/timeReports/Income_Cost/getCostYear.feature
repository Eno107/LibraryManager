Feature: Testing getCostYear method

  Scenario: Retrieve this year's cost when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getCostYear method is called with the path
    Then this year's cost should be returned, which is 184.0

  Scenario: Retrieve this year's cost when there are no bought books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getCostYear method is called with the path
    Then the getCostYear method should return 0.0
