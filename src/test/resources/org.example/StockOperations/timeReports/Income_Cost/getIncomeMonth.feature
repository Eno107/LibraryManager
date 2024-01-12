Feature: Testing getIncomeMonth method

  Scenario: Retrieve this month's income when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getIncomeMonth method is called with the path
    Then this month's income should be returned, which is 295.69

  Scenario: Retrieve this month's income when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getIncomeMonth method is called with the path
    Then the getIncomeMonth method should return 0.0
