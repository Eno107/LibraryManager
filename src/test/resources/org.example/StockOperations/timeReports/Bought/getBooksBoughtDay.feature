Feature: Testing  getBooksBoughtDay method

  Scenario: Retrieve today's sold books information when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getBooksBoughtDay method is called with the path
    Then today's bought books information should be returned

  Scenario: Retrieve total books sold information when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getBooksBoughtDay method is called with the path
    Then today's no bought books information should be returned
