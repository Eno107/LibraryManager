Feature: Testing getIntBooksSoldMonth method

  Scenario: Retrieve the number of this month's sold books when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getIntBooksSoldMonth method is called with the path
    Then this month's number of sold books should be returned, which is 13

  Scenario: Retrieve the number of this month's sold books when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getIntBooksSoldMonth method is called with the path
    Then the getIntBooksSoldMonth method should return 0
