Feature: Testing  getBooksSoldDay method

  Scenario: Retrieve today's sold books information when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getBooksSoldDay method is called with the path
    Then today's sold books information should be returned

  Scenario: Retrieve today's books sold information when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getBooksSoldDay method is called with the path
    Then today's no sold books information should be returned
