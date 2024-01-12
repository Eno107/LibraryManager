Feature: Testing getIncomeDay method

  Scenario: Retrieve today's income when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getIncomeDay method is called with the path
    Then today's income should be returned, which is 167.69

  Scenario: Retrieve today's income when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getIncomeDay method is called with the path
    Then the getIncomeDay method should return 0.0
