Feature: Testing getIntBooksSoldDay method

  Scenario: Retrieve the number of today's sold books when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getIntBooksSoldDay method is called with the path
    Then today's number of sold books should be returned, which is 5

  Scenario: Retrieve the number of today's sold books when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getIntBooksSoldDay method is called with the path
    Then the getIntBooksSoldDay method should return 0
