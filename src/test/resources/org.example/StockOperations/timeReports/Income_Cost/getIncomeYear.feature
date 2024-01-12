Feature: Testing getIncomeYear method

  Scenario: Retrieve this year's income when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getIncomeYear method is called with the path
    Then this year's income should be returned, which is 295.69

  Scenario: Retrieve this year's income when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getIncomeYear method is called with the path
    Then the getIncomeYear method should return 0.0
